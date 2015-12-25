package SwiftSeleniumWeb;

public class Reporter {

	public  String strIteration;
	public 	String strTestDescription;
	public  String strGroupName;
	public  String strTestcaseId;
	public  String strTrasactionType;
	public  String strStatus;
	public  String strStepName;
	public  String strMessage;
	public  String strFieldName;
	public  String strExpectedValue;
	public  String strActualValue;
	public  String toDate;
	public  String frmDate;
	public 	String strTransactioncode;
	public 	String strDirectoryPath;
	public 	String strOperationType;
	public 	String strInputPath;
	public  String strScreenshot="";
	Reporter report=null;


	public String getScreenShot()
	{
		return strScreenshot;
	}
	public void setScreenShot(String strScreenshot)
	{
		this.strScreenshot =  strScreenshot;
	}
	public String getInputPath()
	{
		return strInputPath;
	}
	public void setInputPath(String strInputPath)
	{
		this.strInputPath =  strInputPath;
	}
	public String getOperationType()
	{
		return strOperationType;
	}
	public void setOperationType(String strOperationType)
	{
		this.strOperationType =  strOperationType;
	}
	public String getDirectoryPath()
	{
		return strTransactioncode;
	}
	public void setDirectoryPath(String strDirectoryPath)
	{
		this.strDirectoryPath =  strDirectoryPath;
	}
	public String getTransactioncode()
	{
		return strTransactioncode;
	}
	public void setTransactioncode(String strTransactioncode)
	{
		this.strTransactioncode =  strTransactioncode;
	}
	public String getStrIteration() {
		return strIteration;
	}
	public void setStrIteration(String strIteration) {
		this.strIteration = strIteration;
	}
	public String getStrTestcaseId() {
		return strTestcaseId;
	}
	public void setStrTestcaseId(String strTestcaseId) {
		this.strTestcaseId = strTestcaseId;
	}
	//DS:8-7-14:Adding GroupName
	public String getStrGroupName() {
		return strTestcaseId;
	}
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}
	public String getStrTrasactionType() {
		return strTrasactionType;
	}
	public void setStrTrasactionType(String strTrasactionType) {
		this.strTrasactionType = strTrasactionType;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getStrStepName() {
		return strStepName;
	}
	public void setStrStepName(String strStepName) {
		this.strStepName = strStepName;
	}
	public String getStrMessage() {
		return strMessage;
	}
	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}
	public String getStrFieldName() {
		return strFieldName;
	}
	public void setStrFieldName(String strFieldName) {
		this.strFieldName = strFieldName;
	}
	public String getStrExpectedValue() {
		return strExpectedValue;
	}
	public void setStrExpectedValue(String strExpectedValue) {
		this.strExpectedValue = strExpectedValue;
	}
	public String getStrActualValue() {
		return strActualValue;
	}
	public void setStrActualValue(String strActualValue) {
		this.strActualValue = strActualValue;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return frmDate;
	}
	public void setFromDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public Reporter getReport() {
		return report;
	}
	public void setReport(Reporter report) {
		this.report = report;
	}
	public String getStrTestDescription() {
		return strTestDescription;
	}
	public void setStrTestDescription(String strTestDescription) {
		this.strTestDescription = strTestDescription;
	}
}
