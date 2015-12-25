package SwiftSeleniumWeb;

import java.sql.*;

public class JDBCConnection {

	protected static ResultSet rs = null;
	protected static Connection c = null;
	protected static Statement st = null;


	public static ResultSet establishExcelConn(String filePath,String sheetName) throws SQLException, ClassNotFoundException
	{

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		c = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+ filePath + ";DriverID=22;READONLY=false;");
		st = c.createStatement();
		rs = st.executeQuery("Select * from ["+ sheetName +"$]");
		return rs;
	}

	public static ResultSet establishDBConn(String filePath,String Query) throws SQLException, ClassNotFoundException
	{

		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());//oracle.jdbc.driver.OracleDriver()
		//Class.forName("com.mysql.jdbc.Driver");
		//c = DriverManager.getConnection("jdbc:sqlserver://172.16.221.182:1433;DatabaseName=TimesheetBilling", "sa", "Mnettest200$");
		c = DriverManager.getConnection("jdbc:oracle:thin:@172.16.244.237:1521:stgngbid", "stg_release", "RELEASEDUJUE");

		st = c.createStatement();
		rs = st.executeQuery(Query);
		return rs;
	}

	public static int getRowCount(ResultSet rs) throws SQLException
	{
		int rowCount = 0 ;
		while(rs.next())
		{
			rowCount += 1;
		}

		return rowCount;

	}
}