/**
 * 
 */
package csi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sap.aii.mapping.api.StreamTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;

import samlGabLib.SamlHeaderBuilder;
import samlGabLib.SamlHeaderBuilderException;

/**
 * @author greusr
 *
 */
public class SamlTicketFactory implements StreamTransformation{

	public String convertStreamToString(InputStream is) throws IOException {
		 /*
		 * To convert the InputStream to String we use the
		 * Reader.read(char[] buffer) method. We iterate until the
		 * Reader return -1 which means there's no more data to
		 * read. We use the StringWriter class to produce the string.
		 */
			if (is != null) 
			{
				Writer writer = new StringWriter();
		
				char[] buffer = new char[1024];
				
				/*
				try
				{
				*/
					Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
					int n;
					while ((n = reader.read(buffer)) != -1) 
					{
						writer.write(buffer, 0, n);
					}
					//reader.close();
				/*
				}
				finally
				{
					//is.close();
					
				}
				*/
				return writer.toString();
			}
			else
			{
				return "";
			}
		}
	
	private String obtenirValor(Node child)
	{
		String tag = child.getNodeName();
		String xml = child.toString();
		
		int inici = xml.indexOf("<"+tag+">") + tag.length() + 2;
		int fi = xml.indexOf("</"+tag+">");

		if (fi==-1 || fi>xml.length()) return "";
		
		return xml.substring(inici, fi);
	}
	
	private String getTicketSAML2String(Document document) {
		/*
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		Document document = db.parse(in);*/
		
		  String usuariResponsableCodi = "";
		    String codiUsuari = "";
		    String nomUsuari = "";
		    String cognom1Usuari = "";
		    String cognom2Usuari = "";
		    String tipusDocument = "";
		    String dniUsuari = "";
		    String perfilUsuari = "";
		    String categoriaUsuari = "";
		    String organitzacio = "";
		    String unitatOrganitzativa = "";
		    String dataComunicacio = "";

		
		/*
		DOMParser p = new DOMParser();
		InputSource source = new InputSource(new StringReader(input));
		Document document = p.parse(source);
		*/
		NodeList child = document.getChildNodes();
		child = child.item(0).getChildNodes();
		
		boolean trobat = false;
		for (int i=0; i<child.getLength() && trobat == false; i++)
		{
			Node element = child.item(i);
			if (element.getLocalName()==null) continue;
			
			if (element.getLocalName().equals("DadesSAML"))
			{
				child = element.getChildNodes();
				trobat = true;
			}
		}
		
		for (int i=0; i<child.getLength(); i++)
		{
			Node element = child.item(i);
			if (element.getLocalName()==null) continue;
			if (element.getLocalName().equals("Usuari_Responsable_codi"))
			{
				usuariResponsableCodi = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("codi_usuari"))
			{
				codiUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("nom_usuari"))
			{
				nomUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("cognom1_usuari"))
			{
				cognom1Usuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("cognom2_usuari"))
			{
				cognom2Usuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("tipus_document"))
			{
				tipusDocument = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("dni_usuari"))
			{
				dniUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("perfil_usuari"))
			{
				perfilUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("categoria_usuari"))
			{
				categoriaUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("organitzacio"))
			{
				organitzacio = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("unitat_organitzativa"))
			{
				unitatOrganitzativa = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("data_comunicacio"))
			{
				dataComunicacio = this.obtenirValor(element);
			}
		}
		
		

		SamlHeaderBuilder builder = null;
		try{
			//builder = new SamlHeaderBuilder("samlGabLib/rsc/saml.properties",true);
			builder = new SamlHeaderBuilder("saml.properties",true);
		}catch(SamlHeaderBuilderException e){
			e.printStackTrace();
			return null;
		}
		String samlHeader="";
		try {
			samlHeader = builder.build2String(null);
			System.out.println(samlHeader);
		} catch (SamlHeaderBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return samlHeader;
	}
	
	private Element getTicketSAML2Element(Document document) {
		/*
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		Document document = db.parse(in);*/
		
		  String usuariResponsableCodi = "";
		    String codiUsuari = "";
		    String nomUsuari = "";
		    String cognom1Usuari = "";
		    String cognom2Usuari = "";
		    String tipusDocument = "";
		    String dniUsuari = "";
		    String perfilUsuari = "";
		    String categoriaUsuari = "";
		    String organitzacio = "";
		    String unitatOrganitzativa = "";
		    String dataComunicacio = "";

		
		/*
		DOMParser p = new DOMParser();
		InputSource source = new InputSource(new StringReader(input));
		Document document = p.parse(source);
		*/
		NodeList child = document.getChildNodes();
		child = child.item(0).getChildNodes();
		
		boolean trobat = false;
		for (int i=0; i<child.getLength() && trobat == false; i++)
		{
			Node element = child.item(i);
			if (element.getLocalName()==null) continue;
			
			if (element.getLocalName().equals("DadesSAML"))
			{
				child = element.getChildNodes();
				trobat = true;
			}
		}
		
		for (int i=0; i<child.getLength(); i++)
		{
			Node element = child.item(i);
			if (element.getLocalName()==null) continue;
			if (element.getLocalName().equals("Usuari_Responsable_codi"))
			{
				usuariResponsableCodi = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("codi_usuari"))
			{
				codiUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("nom_usuari"))
			{
				nomUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("cognom1_usuari"))
			{
				cognom1Usuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("cognom2_usuari"))
			{
				cognom2Usuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("tipus_document"))
			{
				tipusDocument = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("dni_usuari"))
			{
				dniUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("perfil_usuari"))
			{
				perfilUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("categoria_usuari"))
			{
				categoriaUsuari = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("organitzacio"))
			{
				organitzacio = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("unitat_organitzativa"))
			{
				unitatOrganitzativa = this.obtenirValor(element);
			}
			else if (element.getLocalName().equals("data_comunicacio"))
			{
				dataComunicacio = this.obtenirValor(element);
			}
		}
		
		

		SamlHeaderBuilder builder = null;
		try{
			//builder = new SamlHeaderBuilder("samlGabLib/rsc/saml.properties",true);
			builder = new SamlHeaderBuilder("saml.properties",true);
		}catch(SamlHeaderBuilderException e){
			e.printStackTrace();
			return null;
		}
		Element samlHeader= null;
		try {
			samlHeader = builder.build2Element(null);
			System.out.println(samlHeader);
		} catch (SamlHeaderBuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return samlHeader;
	}

	
	
	public void execute(InputStream in, OutputStream out) throws com.sap.aii.mapping.api.StreamTransformationException
	{
		String enc = "UTF-8";
		
	  
		
		try
		{


			//String input = "";
			//input = this.convertStreamToString(in);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			Document documentInput = db.parse(in);
			Document documentOutput = null;
			StringBuffer missatge = new StringBuffer();
			
			/*
			 *   public void fillXfaForm(Node node) {
Node data = datasetsNode.getFirstChild();
NodeList list = data.getChildNodes();
if (list.getLength() == 0) {
	data.appendChild(domDocument.importNode(node, true));
}
else {
	data.replaceChild(domDocument.importNode(node, true), data.getFirstChild());
}
      extractNodes();
setChanged(true);
  }
			 * 
			 */
			
			Node dadesSAMLNode = null;
			NodeList nodeList = documentInput.getElementsByTagName("DadesSAML");
			if (nodeList == null || nodeList.getLength() == 0) {
				//Error no encontramos el tag
				Exception e = new Exception("No s'han trobat les dades SAML");
				try{
					FileWriter fichero = new FileWriter("/tmp/Error_LES.txt");
					PrintWriter pw = new PrintWriter(fichero);
					e.printStackTrace(pw);
					fichero.close();
				} 
				catch (Exception e2) 
				{
				}
				e.printStackTrace();
				throw new com.sap.aii.mapping.api.StreamTransformationException(e.getMessage());
			}
			dadesSAMLNode = nodeList.item(0);
			
			Element samlHeader = this.getTicketSAML2Element(documentInput);
			documentInput.adoptNode(samlHeader);
			dadesSAMLNode.getParentNode().replaceChild(samlHeader, dadesSAMLNode);
			
			
			try
		    {
		       DOMSource domSource = new DOMSource(documentInput);
		       StringWriter writer = new StringWriter();
		       StreamResult result = new StreamResult(writer);
		       TransformerFactory tf = TransformerFactory.newInstance();
		       Transformer transformer = tf.newTransformer();
		       transformer.transform(domSource, result);
		       out.write((writer.toString()).getBytes(enc));
		       //return writer.toString();
		    }
		    catch(TransformerException ex)
		    {
		       ex.printStackTrace();
		       throw new com.sap.aii.mapping.api.StreamTransformationException(ex.getMessage());
		       //return null;
		    }
			
			/*
			String samlHeader = null;
			
			samlHeader = this.getTicketSAML2String(documentInput);

			
			missatge.append("<wsse:Security xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" soap:mustUnderstand=\"1\">");
			missatge.append(samlHeader);
			missatge.append("</wsse:Security>");
			*/
			//ServidorSAMLGenericRemoteInterface saml = (ServidorSAMLGenericRemoteInterface)Naming.lookup ("//"+GestorProperties.getIP()+":"+GestorProperties.getPort()+"/ServidorSAMLGeneric");
			/*String samlHeader = saml.getSAML_LES(usuariResponsableCodi, codiUsuari, nomUsuari, cognom1Usuari, cognom2Usuari,
					tipusDocument, dniUsuari, perfilUsuari, categoriaUsuari, organitzacio, unitatOrganitzativa, dataComunicacio);
*/
			
			/*int inici = input.indexOf("<demanda>");
			int fi = input.indexOf("</demanda>")+"</demanda>".length();
			input = input.substring(inici, fi);
			
			input = input.replaceAll("<demanda>", "<demanda xmlns:ns3=\"http://salut.gencat.net/les/tramesa_demanda/peticio\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
			input = input.replaceAll("</", "x/ns3:");
			input = input.replaceAll("<", "<ns3:");
			input = input.replaceAll("x/ns3:", "</ns3:");
			
			input = "<![CDATA[" + input + "]]>";
			
			//afegim la cap�alera
			StringBuffer missatge = new StringBuffer( 
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>"+
				//"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"+
				"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"+
					"<soap:Header>"+
						"<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" soap:mustUnderstand=\"1\">");
					
			//Afegim el SAML
			missatge.append(samlHeader.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length(), samlHeader.length()));
			
			//Afegim el fi de cap�alera i l'inici del cos del missatge
			missatge.append(
						"</wsse:Security>"+
					"</soap:Header>"+
					"<soap:Body>");
			
			//Afegim el cos del missatge
			missatge.append(
						"<ns1:trametreDemandaElement xmlns:ns1=\"http://salut.gencat.net/les/ws/types/\">"+
							"<ns1:strXML>"+input+"</ns1:strXML>" +
						"</ns1:trametreDemandaElement>");
					
			//Tanquem el cos del missatge i el header
			missatge.append(
					"</soap:Body>"+
				"</soap:Envelope>");
			
			
			*/
			//out.write((missatge.toString()).getBytes(enc));
		}
		catch(Exception e)
		{
			try
			{
				FileWriter fichero = new FileWriter("/tmp/Error_LES.txt");
				PrintWriter pw = new PrintWriter(fichero);
				e.printStackTrace(pw);
				fichero.close();
			} 
			catch (Exception e2) 
			{
			}
			e.printStackTrace();
			throw new com.sap.aii.mapping.api.StreamTransformationException(e.getMessage());
		}
	}


	public void setParameter(Map map)
	{} /** * method execute is called by the XI mapping program */
	
	public static void main(String[] args) {
		
		InputStream is = null;
		OutputStream os = null;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		//is =classLoader.getResourceAsStream("src/main/resources/ids.txt");
		is = classLoader.getResourceAsStream("input.xml");
		//os = classLoader.getResourceAsStream("/output.xml");
		try {
			os = new FileOutputStream("/home/triskel/Proyectos/Java/INT012_LES_SAML/xml/output.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		SamlTicketFactory samlFactory = new SamlTicketFactory();
		try {
			samlFactory.execute(is, os);
		} catch (StreamTransformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		
		
		
		
	}
}
