package tools;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


public class DOMizeTemplate {
	StreamResult result = null;
	private Transformer transformer;
	private Document doc;
	public DOMizeTemplate (String templatePath,String targetPath) {
		DocumentBuilderFactory documentBuilderFactory = null;
		DocumentBuilder documentBuilder = null;
		
		TransformerFactory transformerFactory = null;
		transformerFactory = TransformerFactory.newInstance();
		result = new StreamResult(targetPath);

		try {
			String filePath = templatePath;
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			transformer = transformerFactory.newTransformer();

			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			this.doc = documentBuilder.parse(new File(filePath));
		} catch (Exception e) {
			System.out.println("Exception encountered during document build: " + e);
		}

	}
	public Document getDoc() {
		return doc;
	}
	public void writeDocument() {
		DOMSource domSource = new DOMSource(doc);

		try {
		    transformer.transform(domSource,result);
		} catch (Exception e) {
			System.out.println("Error while writing modified HTML: "+e);
		}
	}

}
