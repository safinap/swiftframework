package SwiftSeleniumWeb;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class TransactionMapping {

	private static String operationType ="";

	public static Reporter TransactionInputData(HSSFCell controllerTestCaseID,HSSFCell controllertransactionType,String filePath) throws Exception
	{
		Reporter report = new Reporter();
		HashMap<String, Object> inputHashTable = new HashMap<String, Object>();
		HSSFSheet workSheet = ExcelUtility.GetSheet(Automation.configHashMap.get("TRANSACTION_INPUT_FILEPATH").toString(), "Web_Transaction_Input_Files");
		int rowCount = workSheet.getLastRowNum()+1;
		for(int rowIndex=1;rowIndex<rowCount&&!MainController.pauseExecution;rowIndex++)
		{
			String transactionCode = WebHelper.getCellData("TransactionCode", workSheet, rowIndex, inputHashTable);
			String transactionType = WebHelper.getCellData("TransactionType", workSheet, rowIndex, inputHashTable);
			String directoryPath = WebHelper.getCellData("DirPath", workSheet, rowIndex, inputHashTable).toString();//
			String inputExcel = WebHelper.getCellData("InputSheet", workSheet, rowIndex, inputHashTable).toString();
			if(transactionType.toString().equalsIgnoreCase(controllertransactionType.toString()))
			{
				if(transactionCode != null&&directoryPath == null && controllertransactionType.toString().equalsIgnoreCase(transactionType.toString()))
				{
					report.strInputPath = "";
					report.strOperationType = "";
					report.strTransactioncode = transactionCode;
					WebDriver.DataInput("", controllerTestCaseID.toString(), transactionType, transactionCode, "");
					break;
				}
				
				if(!transactionType.toString().startsWith("Verify"))
				{
					operationType = "Input";
				}

				if(transactionType.toString().startsWith("Verify") && (!directoryPath.toString().isEmpty()) && (!inputExcel.toString().isEmpty()))
				{
					operationType = "InputandVerfiy";

				}
				else if(transactionType.toString().startsWith("Verify") && (directoryPath.toString().isEmpty()) && (inputExcel.toString().isEmpty()))
				{
					operationType = "Verify";
				}
				if(controllertransactionType.toString().equalsIgnoreCase(transactionType.toString()))
				{
					if((directoryPath == null||inputExcel == null)&& operationType !="Verify")
					{
						MainController.pauseFun("Please Enter the directory or excelsheet name");
					}
					else
					{
						String inputFilePath=null;
						if(operationType != "Verify")
						{
							inputFilePath = Automation.configHashMap.get("INPUT_DATA_FILEPATH").toString() + directoryPath.toString() + "\\" + inputExcel.toString();
						}					
						System.out.println(inputFilePath);
						report.strInputPath = inputFilePath;
						report.strOperationType = operationType;
						report.strTransactioncode = transactionCode;						
						WebDriver.DataInput(inputFilePath,controllerTestCaseID.toString(),transactionType,transactionCode,operationType);
						break;
					}
				}
			}
			else if(!transactionType.toString().equalsIgnoreCase(controllertransactionType.toString())&& rowIndex == rowCount-1)
			{
				MainController.pauseFun("Transaction "+MainController.controllerTransactionType.toString()+" Not Found");
				ExcelUtility.writeReport(SwiftSeleniumWeb.WebDriver.report);
			}
		}
		return null;
	}

}
