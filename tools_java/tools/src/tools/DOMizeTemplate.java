package tools;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * 
 * @author broihier
 * <pre>
 * Class for creating a DOM object from a template and writing it out after it is filled in
 * </pre>
 */

public class DOMizeTemplate {
	private StreamResult result = null;
	private Transformer transformer;
	private Document doc;

	/** 
	 * Constructor for creating DOMized templates
	 * 
	 * <pre>
	 * Pseudo-code:
	 * {@code
	 * use the document builder parse method to create a DOM using the information at templatePath;
	 * 
	 * }
	 * </pre>
	 * @param templatePath file path to an HTML template file
	 * @param targetPath file path for the filled out template
	 * 
	 */

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
	/**
	 * Method that returns the DOMized template object
	 * @return doc - getter
	 */
	public Document getDoc() {
		return doc;
	}
	/**
	 * Method that writes the expanded DOM to the target location
	 */
	public void writeDocument() {
		DOMSource domSource = new DOMSource(doc);

		try {
		    transformer.transform(domSource,result);
		} catch (Exception e) {
			System.out.println("Error while writing modified HTML: "+e);
		}
	}

}
