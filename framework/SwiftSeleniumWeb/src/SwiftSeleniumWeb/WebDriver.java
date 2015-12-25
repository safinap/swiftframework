package SwiftSeleniumWeb;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WebDriver {
	
	public static Reporter report=new Reporter();
	public static JFrame frame = new JFrame("SWIFT FRAMEWORK");
	
	public static void main(String args[]) throws IOException
	{
		try
		{	
			Automation.LoadConfigData();		
		    //Automation.setUp();			
			MainController.ControllerData(Automation.configHashMap.get("CONTROLLER_FILEPATH").toString());			
		}
		catch(Exception e)
		{			
			report.strStatus = "FAIL";
			if(MainController.controllerTestCaseID != null)
				report.strTestcaseId = MainController.controllerTestCaseID.toString();
			report.strTrasactionType = MainController.controllerTransactionType.toString();		
			try {
				MainController.pauseFun("TestCase: "+MainController.controllerTestCaseID +", Tranasction: "+MainController.controllerTransactionType+", Error: "+e.getMessage());
			} catch (IOException e1) {
				MainController.pauseFun("File Not Found");
			}
		}
		finally
		{	
			//TM: 16-01-2015
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
			frame.setLocationRelativeTo(null);
			JOptionPane.showMessageDialog(frame, "Execution Completed");		
			frame.dispose();	
			//Runtime.getRuntime().exec("wscript.exe Report.vbs");//Provide the path to the Report.vbs file incase not present in the Project folders
			//Runtime.getRuntime().exec("wscript.exe DetailedReport.vbs");
			//SS:21-10-2015 Handled Null condition for non-browser based operation such as Web service.
			if(Automation.driver != null)			{
				Automation.driver.quit();
			}
			
		}
 	}


	public static void DataInput(String filePath,String testcaseID,String transactionType,String transactionCode,String operationType) throws Exception
	{
		if(transactionCode == null)
		{
			transactionCode = transactionType; 
		}
		System.out.println(transactionCode);
		
		if(operationType.equalsIgnoreCase("InputandVerfiy")&&!operationType.isEmpty())
		{
			ExcelUtility.GetDataFromValues(filePath, testcaseID.toString(), transactionType.toString());
			WebVerification.performVerification(transactionType, testcaseID);
		}
		else if(!operationType.equalsIgnoreCase("Verify")&&!operationType.isEmpty())
		{
			ExcelUtility.GetDataFromValues(filePath, testcaseID.toString(), transactionType.toString());
		}
		else if(!operationType.equalsIgnoreCase("Input")&&!operationType.isEmpty())
		{
			WebVerification.performVerification(transactionType, testcaseID);
		}
	}
}

