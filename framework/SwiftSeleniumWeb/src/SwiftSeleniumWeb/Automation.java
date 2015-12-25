package SwiftSeleniumWeb;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Automation {	
	public static HashMap<String, Object> configHashMap = new HashMap<String, Object>();
	public static ResultSet result = null;
	public static WebDriver driver;
	public static enum browserTypeEnum {InternetExplorer,FireFox,Chrome,Safari};
	public static String browser =null;
	public static browserTypeEnum browserType = null;
	public static WebDriverFactory webDriverObj = new WebDriverFactory();
	public static DateFormat dtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	
	public static void setUp() throws Exception
	{	
		if(driver != null)
		{
			WebHelper.wait = null;
			driver.quit();
		}

		if(CalendarSnippet.isProcessRunning("IEDriverServer.exe") )
		{
			CalendarSnippet.killProcess("IEDriverServer.exe");
		}
		if(CalendarSnippet.isProcessRunning("chromedriver.exe"))
		{
			CalendarSnippet.killProcess("chromedriver.exe");
		}		

		try
		{

			//browser	= configHashMap.get("BROWSERTYPE").toString();
			//browserType = browserTypeEnum.valueOf(browser);
		//	final Object baseURL = configHashMap.get("BASEURL");			

			switch(browserType)
			{
			case InternetExplorer:
				driver = getIEDriverInstance();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				//driver.get(baseURL.toString());				
				break;			
			case FireFox:
				driver = getFFDriverInstance();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				//driver.navigate().to(baseURL.toString());
				break;

			case Chrome:
				driver = getChromeDriverInstance();
				driver.manage().deleteAllCookies();
				//driver.get(baseURL.toString());
				break;
				
			//TM-20/01/2015-Case added for Safari	
			case Safari:
				driver = getSafariDriverInstance();
				driver.manage().window().maximize();
				//driver.get(baseURL.toString());
				break;
			}
			/**Implicit Wait**/
			//driver.manage().timeouts().implicitlyWait(Long.parseLong(Automation.configHashMap.get("TIMEOUT").toString()), TimeUnit.SECONDS);

		}
		catch(NullPointerException npe)
		{
			MainController.pauseFun("Null Values Found in Automation.SetUp Function");
		}
		catch(Exception e)
		{
			MainController.pauseFun("Error from Automation.Setup " + e.getMessage());
		}		
	}

	/**Loads the Config sheet into HashMap**/
	public static void LoadConfigData() throws IOException, SQLException, ClassNotFoundException, URISyntaxException
	{
		
		try {
			
			Date initialDate = new Date();
			String strInitialDate = dtFormat.format(initialDate);
			SwiftSeleniumWeb.WebDriver.report.setFromDate(strInitialDate);		
	
			DataFormatter format = new DataFormatter();
			String projectPath = System.getProperty("user.dir");					
			String configPath = projectPath + "\\CommonResources\\Config.xls";			
			HSSFSheet configSheet = ExcelUtility.GetSheet(configPath, "Config");			
			int rowCount = configSheet.getLastRowNum()+1;

			for(int rowIndex=1;rowIndex<rowCount;rowIndex++)
			{
				HSSFRow rowActual = configSheet.getRow(rowIndex);
				String parameterName = format.formatCellValue(rowActual.getCell(0));				
				String value = format.formatCellValue(rowActual.getCell(1));
				//Following 'if' is replacement of above, checks if parameterName and value are neither null nor Blank
				if(StringUtils.isNotBlank(parameterName) || StringUtils.isNotBlank(value)){
					configHashMap.put(parameterName,value);
				}
							
			}
		} 
		catch(NullPointerException npe)
		{
			MainController.pauseFun("Null Values Found in Config Sheet");			
		}
		catch(Exception e)
		{
			MainController.pauseFun(e.getMessage()+ " From LoadConfig Function");
		}
	}

	/**Returns an IE Driver's Instance**/
	public static WebDriver getIEDriverInstance() throws InterruptedException,Exception
	{		
		//TM:Commented the following code as driver is defined global
		return webDriverObj.createDriver("msie");
	}

	/**Returns a FireFox Driver's Instance**/
	public static WebDriver getFFDriverInstance() throws Exception
	{
		//TM: commented the following code as driver is defined global		
		FirefoxProfile profile = new FirefoxProfile();		
		profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "masteknet.com");//for https
		return new FirefoxDriver(profile);
	}

	/**Returns a Chrome Driver's Instance**/
	public static WebDriver getChromeDriverInstance() throws Exception
	{
		//TM: commented the following code as driver is defined global
		return webDriverObj.createDriver("chrome");
	}
	
	/**Returns a Safari Driver Instance**/
	public static WebDriver getSafariDriverInstance() throws IOException
	{
		//TM: commented the following code as driver is defined global
		return webDriverObj.createDriver("safari");
	}	
}
