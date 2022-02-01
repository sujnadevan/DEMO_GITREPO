package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class dependCon {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn= null;
		try {
			
		
		Driver dref = new Driver();
		/*load/Register the database */
		DriverManager.registerDriver(dref);
		/*connect to db */
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		System.out.println("========Connection is done==========");
		/*create the statement*/
		Statement stat = conn.createStatement();
		String query = "select * from project";
		/*execute the query*/
		ResultSet resultset = stat.executeQuery(query);
		while(resultset.next())
		{
			System.out.println(resultset.getString(1)+"\t"+resultset.getString(2)+"\t"+resultset.getString(3)+"\t"+resultset.getString(4)+"\t"+resultset.getString(5)+"\t"+resultset.getString(6));
		
		}
		}
		catch(Exception e) {
		}
		
		finally {
			
		
		conn.close();
		System.out.println("=====Connection to db is close==========");
		}
		
		
		
	}

}
