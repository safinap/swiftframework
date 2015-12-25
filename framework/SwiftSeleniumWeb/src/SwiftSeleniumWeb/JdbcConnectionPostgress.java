package SwiftSeleniumWeb;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.python.modules.thread.thread;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class JdbcConnectionPostgress {
	private static final String String = null;
	protected static ResultSet rs = null;
	protected static Connection c = null;
	protected static Statement st = null;
	static StringBuffer stringBuffer = new StringBuffer();
	public static String responseXml;
	public static String response;

	public static HashMap<String,String> dataMap = new HashMap<String,String>();
	static ArrayList dataList = new ArrayList();
	static String msg, msg1,msg11;

	// Create database connection and fetch query from excel sheet
	public static void establishestablishDBConnn(String filePath, String Query)
			throws Exception {
		try {

			Class.forName("org.postgresql.Driver");
			/*
			 * try {
			 */
			c = DriverManager.getConnection(
					"jdbc:postgresql://172.16.239.68:5432/NotificationEngine","testuser", "test123");
			/*c = DriverManager.getConnection(
					"jdbc:postgresql://172.16.204.177:5432/NotificationEngine","postgres","post@123");
*/			st = c.createStatement();

			// Execute query
			rs = st.executeQuery(Query);

			
			String msg2, msg3,msg12;
			while (rs.next()) {
				msg = rs.getString("email_notification");
				msg1 = rs.getString("sms_notification");
				msg11=rs.getString("time_line_notification");
				
				
				msg2 = rs.getString("email_channel");
				msg3 = rs.getString("sms_channel");
				msg12=rs.getString("time_line_channel");
				
				
				System.out.println("Database email_notification value:-" + msg);
				System.out.println("Database sms_notification value:-" + msg1);
				System.out.println("Database timeline value:-" + msg11);
				
				
				dataMap.put("email_notification", msg);
				dataMap.put("sms_notification", msg1);
				dataMap.put("time_line_notification", msg11);
				
				dataMap.put("email_channel", msg2);
				dataMap.put("sms_channel", msg3);
				dataMap.put("time_line_channel", msg12);
				
				
				System.out.println("Database email_channel value:-" + msg2);
				System.out.println("Database sms_channel value:-" + msg3);
				System.out.println("Database time_line_channel value:-" + msg12);

				dataList.add(dataMap);
				
			}

		} catch (Exception e) {
			throw new Exception("Error while triggering web service: "
					+ e.getMessage());
		}

		// Close rs,st and connections
		finally {
			if (rs != null) {
				rs.close();
			}

			if (st != null) {
				st.close();
			}

			if (c != null) {
				c.close();
			}
		}

		
		

	//	-----------------------------
		

		 
		 //---------------------------------
	
		writeToFile(dataList);

	}

	// This code will be creating new text file based on data fetched from
	// database

	private static void writeToFile(List data2) {
		// BufferedWriter out = null;
		try {
			String date = SwiftSeleniumWeb.WebDriver.report.frmDate.replaceAll("[-/: ]", ""); 
		
			String fileName = WebHelper.testcaseID.toString() + "_"	+ WebHelper.transactionType.toString() + "_" + date;
			responseXml = System.getProperty("user.dir")+"\\Resources\\Results\\Database_Output\\" + fileName + ".txt";
		
			File file=new File(responseXml) ;
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			
			
			BufferedWriter bw = new BufferedWriter(fw);
			// System.out.println(fw);
			file.createNewFile();
				System.out.println("File Created Successfully");
			
			
		

			Iterator<HashMap> irIterator = data2.iterator();
			while (irIterator.hasNext()) 
			{
								
				HashMap msgMap = irIterator.next();
				String str = (java.lang.String) msgMap.get("email_notification");
				String str1 = (java.lang.String) msgMap.get("sms_notification");
				String str2 = (java.lang.String) msgMap.get("email_channel");
				String str3 = (java.lang.String) msgMap.get("sms_channel");
				String str4=(java.lang.String) msgMap.get("time_line_notification");
				String str5=(java.lang.String) msgMap.get("time_line_channel");

				String finalStr1 = str2+" "+str+"\n";
				String finalStr2 = str3+" "+str1+"\n";
				String finalstr3 = str5+" "+str4+"\n";
				String finalstr = finalStr1+"\r\n"+finalStr2+"\r\n"+finalstr3;
			//	String finalStr = finalStr1+"\r\n"+finalstr3;
				bw.write(finalstr);
			/*	bw.write(finalStr1);
				bw.newLine();
				bw.write(finalStr2);*/
				

				System.out.println("Done");
				
			}
			bw.close();
		


		} catch (IOException e) {

		}

	}

	// Actual & Expected verification is done with the help of below code

	public static String getStringTextValue(String StringTextValue)	throws Exception {

		System.out.println("---------------");
		System.out.println("StringTextValue:" + StringTextValue);
		System.out.println("---------------");
		String textValue = null;

		// Initializing text file
		File textFile = new File(responseXml);
		
		System.out.println("File For reading response from database:"+responseXml);

		try {

			Scanner scnr = new Scanner(textFile);

			// Reading each line of file using Scanner class
		
			while (scnr.hasNextLine()) {
				
			 scnr.nextLine();
			
			 
			 switch(StringTextValue)
			 {
			 
			 case "Email_Notification": 
				 						textValue=dataMap.get("email_notification");
				 						System.out.println("textValue value:"+textValue);
				 						//lineNumber++;
				 						break;
			 case "SMS_Notification": 
										textValue=dataMap.get("sms_notification");
										System.out.println("textValue value:"+textValue);
										//lineNumber++;
										break;
								 
			
			 case "Email_Channel": 
									 	textValue=dataMap.get("email_channel");
										System.out.println("textValue value:"+textValue);
										//lineNumber++;
										break;
			 case "SMS_Channel": 
										textValue=dataMap.get("sms_channel");
										System.out.println("textValue value:"+textValue);
										//lineNumber++;
										break;

										
			 case "TimeLine_Notification":
				 						textValue=dataMap.get("time_line_notification");
				 						System.out.println("textValue value:"+textValue);
					
				 						break;
				 						
			 case "TimeLine_Channel":
										textValue=dataMap.get("time_line_channel");
										System.out.println("textValue value:"+textValue);

										break;
				 

			default:
										System.out.println("You are in default case");
			 
			 }
			 
		
			}
			
			return textValue;

		} catch (Exception e) {
			throw new Exception("Error while text file verification: "
					+ e.getMessage());
		}
	}

}
