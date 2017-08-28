// Test case editor server - helps edit test cases
// Setup all required packages
var express = require("express");
var bodyParser = require("body-parser");
var fs = require("fs");
var jsdom = require("jsdom");
var WebSocketServer = require("ws").Server;
var spawn = require("child_process").spawn;
var execSync = require("child_process").execSync;
// Read main html page - this will be parsed later
let mainPageContents = fs.readFileSync("./index.html");
// Read log entry page - this will be reissued later
let logEntryContents = fs.readFileSync("./log_entry.html");
// create an express server to make a static file server
var app = express();
// initialize global information
var childProcess = null;
var statTime = [];
var target;
var relay = [];
// start a Web Socket Server
var wss = new WebSocketServer({port: 3001});
wss.on("connection", function(connection) {
	relay.push(connection); // store for communication
	connection.send("connected");
	connection.on("message", function (message) {
		if (message === "exit") {
			relay.splice(relay.indexOf(connection), 1);
			connection.close();
		}
	});
	connection.on("close", function(message) {
		relay.splice(relay.indexOf(connection), 1);
		connection.close();
		console.log("closing a connection");
	});
});
// check for changes to the test database every second
setInterval(function(){
	let changed = false;
	for (let entry of statTime) {
		changed |= fs.statSync(entry.Path).ctime.valueOf() != entry.Time.valueOf();
		entry.Time = fs.statSync(entry.Path).ctime;
	}
	if (changed) { // if any of the files changed, rebuild the section and trace tables
		execSync('./buildHTMLSection '+ target.slice(1));
		execSync('./buildHTMLTraceTables book');
		for (let connection of relay) {
			connection.send("refresh");
			console.log("sending refresh to the client");
		}
	}
},1000);
// if the express server is contacted, look at the request and build a response or
// forward the request to the standard server behavior.
app.use(bodyParser.urlencoded({extended: true}));
app.post('/log_entry.html', function(request, response, next){
	let appendData = "ID:" + request.body.testID + "\n";
	appendData += "Test Start Time: " + request.body.testStartTime +"\n";
	appendData += "Log data:\n" + request.body.testData.replace(/[\r]+/g,"") + "\n";
	appendData += "Results: " + request.body.results + "\n";
	appendData += "Test End Time: " + request.body.testEndTime + "\n";
	appendData += "Tester: " + request.body.tester + "\n";
	fs.appendFileSync("./TEST.LOG",appendData);
	let dom = new jsdom.JSDOM(logEntryContents);
	response.send(dom.serialize());
});
app.get("/", function(request, response, next) {
    //	this is the main page so build replacement DOM
	// that has the sections available to edit
	let files = fs.readdirSync("./");
	let dom = new jsdom.JSDOM(mainPageContents);
	let document = dom.window.document;
	let insertionPoint = document.querySelector("#list");
	for (let file of files) {
		if (file.indexOf("_toc") >= 0) {
			let element = document.createElement("a");
			element.setAttribute("href",file);
			element.innerHTML = file;
			let listElement = document.createElement("li");
			listElement.appendChild(element);
			insertionPoint.appendChild(listElement);
		}
	}
	response.send(dom.serialize());
});
app.get("/*/*.html", function(request, response, next) {
	// found a terminal html file that a user wants (may want) to edit, bring up an editor
	// for the database and tailor the test case file so it can refresh
	let testCaseContents = fs.readFileSync("./"+request.url);
	let dom = new jsdom.JSDOM(testCaseContents);
	let document = dom.window.document;
	let insertionPoint = document.querySelector("body");
	let scriptElement = document.createElement("script");
	scriptElement.setAttribute("type","text/javascript");
	scriptElement.innerHTML = 
		"var hostName = location.hostname;" +
		"var ws = new WebSocket(\"ws://\" + hostName + \":3001\");" +
		"ws.onmessage = function(message) {"+
		" console.log(\"got this message:\" + message.data);" + 
		" if (message.data === \"refresh\") {" +
		"  window.location.reload();};" +
		" };";
	insertionPoint.appendChild(scriptElement);
	response.send(dom.serialize());
	if (childProcess === null) {
		let argumentList = [ '-e', 'emacs'];
		target = request.url.slice(request.url.lastIndexOf('/'),request.url.indexOf('-'));
		let additionalFiles = ['.testDbID',
			'.testDbResults',
			'.testDbProcedures',
			'.testDbPre',
			'.testDbPost',
			'.testDbObjective',
			'.testDbExpectedResults',
			'.testDbCategoryTitle',
			'.testDbCategoryDescription'];
		statTime = [];
		for (let additionalFile of additionalFiles) {
			// record the state of the current database, if it changes
			// the server will update the test case HTML and push the change
			// to the client browser that is displaying the test case
			let fullPath = '.' + target + additionalFile;
			console.log(fullPath);
			argumentList.push(fullPath);
			statTime.push({'Path': fullPath,
				'Time': fs.statSync(fullPath).ctime});
		}
		childProcess = spawn('xterm',argumentList);
		childProcess.on("close", function(code){
			console.log("spawn_edits process closed with code: " + code);
			childProcess = null;
		});
	}
	return;
});
app.get("*", function(request, response, next) {
	next();
});
app.use(express.static("./"));
app.listen(3000);

console.log("Editor server is listening on port 3000");
