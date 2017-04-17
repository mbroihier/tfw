package tools;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public final class Utilities {
	public static Element findElementById(Node head, String id) {
		Element element = null;
		for (Node node = head.getFirstChild(); node != null; node = node.getNextSibling()) {
			if (node.hasChildNodes()) {
				element = findElementById(node,id);
				if (element != null) return element;
			}
			if (node.hasAttributes()) { // only elements will have attributes
				NamedNodeMap attributes = node.getAttributes();
				for (int i = 0; i < attributes.getLength(); i ++ ) {
					//System.out.println("Node: "+node+ " "+id);
					//System.out.println("attributes: "+i+" "+attributes.item(i)+" "+attributes.item(i).getTextContent());
					if (attributes.item(i).getTextContent().equals(id)){
						element = (Element) node;
						//System.out.println("found id");
						break;
					}
				}
			}
			if (element != null) break;
		}
		return element;
	}
	public static void printNodes(Node node,int level) {
		level++;
		System.out.println(node);
		for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
			printNodes(child,level);
		}
		return;
	}

}
