package SwiftSeleniumWeb;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
//import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class WebService {
	
	public static String responseXml;
	public static String response;

	public static void callWebService() throws Exception
	{
		OutputStreamWriter requestWriter = null;
		BufferedReader responseReader = null;
		Scanner scanner = null;

		try{ 	

			URL wsdlUrl = new URL(WebHelper.wsdl_url);
			HttpURLConnection con = (HttpURLConnection)wsdlUrl.openConnection(); 

			/** Proxy settings ONLY if required **/
			System.setProperty("http.proxyHost", "192.168.100.40");
			System.setProperty("http.proxyPort", "8080");
			con.usingProxy();

			con.setRequestMethod("POST");
			con.setRequestProperty("Content-type", "application/xml");
			con.setRequestProperty("SOAPAction",WebHelper.request_url);
	/*		
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestMethod("POST");
						
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			*/
			con.setDoOutput(true);
			con.setDoInput(true);

		/** Reading data from Request XML **/
			
			String reqXml = System.getProperty("user.dir") + "\\Resources\\Input\\WebService\\" + WebHelper.request_xml + ".xml";
			System.out.println(reqXml);
	/*		String reqXml = System.getProperty("user.dir") + "\\Resources\\Input\\WebService\\" + WebHelper.request_json + ".json";
			System.out.println(reqXml);*/
			//String soapMessage = new Scanner(new File(reqXml)).useDelimiter("\\A").next();
			scanner = new Scanner(new File(reqXml));
			String soapMessage = scanner.useDelimiter("\\A").next();
			System.out.println("-------------------------READING DATA FROM FILE-----------------------------------");
			System.out.println(soapMessage);
			
			System.out.println("----------------------------------------------------------------------------------");
			/*JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			
			String name = (String) jsonObject.get("Name");
            String author = (String) jsonObject.get("Author");
			*/
				 
			
			/** Sending Request **/
			

				requestWriter = new OutputStreamWriter(con.getOutputStream());
				requestWriter.write(soapMessage);
				requestWriter.flush();
				
		///n		int Code=con.getResponseCode();
				System.out.println("Response code is here"+con.getResponseCode());
			
			
				
				
		/*		public static int getResponsecode(int R_coe) throws Exception
				{
					String tagResponseCode=null;
				
		        switch (con.getResponseCode()) {
		            case HttpURLConnection.HTTP_OK:
		               // log.fine(entries + " **OK**");
		            	//return con;
		            	tagResponseCode=con.toString();
		            	System.out.println(tagResponseCode=con.toString());
		            	
		                break; // **EXIT POINT** fine, go on
		            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
		            	tagResponseCode=con.toString();
		            	System.out.println(tagResponseCode=con.toString());
		                break;// retry
		            case HttpURLConnection.HTTP_UNAVAILABLE:
		            	tagResponseCode=con.toString();
		            	System.out.println(tagResponseCode=con.toString());
		                break;// retry, server is unstable
		            default:
		              System.out.println(wsdlUrl + " **unknown response code**.");
		                break; // abort
		        }
*/
					//}
				
				
				
				
			
			//----------------------------------------------------
			
			
			
			/** Reading data from Response XML**/
			
			
		//	System.out.println("-------------------------READING DATA FROM RESPONSE-----------------------------------");
			
			
			
		/*	responseReader = new BufferedReader(new InputStreamReader(con.getInputStream())); 
			String line; 
			StringBuffer stringBuffer = new StringBuffer(); 
			while ((line = responseReader.readLine())!= null) { 
				//if (line.startsWith("<?xml "))	//DS:This condition is used when response contains non-xml data also
				//{
					stringBuffer.append(line);
					stringBuffer.append("\n");
				//}
			}				

			*//** Printing Response **//*
			//System.out.println(stringBuffer.toString()); 

			*//** Writing Response to XML file **//*
			String date = SwiftSeleniumWeb.WebDriver.report.frmDate.replaceAll("[-/: ]","");
			String fileName = WebHelper.testcaseID.toString() + "_" + WebHelper.transactionType.toString() + "_"+date;
			responseXml = System.getProperty("user.dir") + "\\Resources\\Results\\XMLOutput\\" + fileName + ".xml";			
			
			File file = new File(responseXml);
			String content = stringBuffer.toString();
			FileOutputStream fop = new FileOutputStream(file);						
			if (!file.exists())
				file.createNewFile();
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();	*/
			
		}
		catch(Exception e)
		{
			throw new Exception("Error while triggering web service: " + e.getMessage());
		}
		finally
		{			
			/** Closing input and output stream buffers **/
		//	requestWriter.close();
		//	responseReader.close();
			scanner.close();
		}
	}
	
	public static String getXMLTagValue(String xmlTagName) throws Exception
	{
		String tagValue = null;
		File fXmlFile = new File(responseXml);
			
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			//First matching node						
			Node firstNode = doc.getElementsByTagName(xmlTagName).item(0);
			tagValue = firstNode.getTextContent().toString();			
			return tagValue;

		}
		catch(Exception e)
		{
			throw new Exception("Error while XML tag verification: " + e.getMessage());
		}	
	}
	
	/**
	 * This method captures the JSON Reponse into a txt file
	 * @param JSONURL
	 * @param responseFileName
	 * @throws Exception
	 * @throws IOException
	 */
	public static void downloadAndStoreJson(String JSONURL, String responseFileName) throws Exception, IOException {

		InputStream input = null;
		OutputStream output = null;
		// Proxy Configuration
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.100.40", 8080));
		try {
			final URL url = new URL(JSONURL);
			final URLConnection urlConnection = url.openConnection(proxy);
			input = urlConnection.getInputStream();
			output = new FileOutputStream(responseFileName);
			byte[] buffer = new byte[1024];
			for (int length = 0; (length = input.read(buffer)) > 0;) {
				output.write(buffer, 0, length);
			}
			// Here you could append further stuff to `output` if necessary.
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException logOrIgnore) {
				}
			if (input != null)
				try {
					input.close();
				} catch (IOException logOrIgnore) {
				}
		}
	}
}

		