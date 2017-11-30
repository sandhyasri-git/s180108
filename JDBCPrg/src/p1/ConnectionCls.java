package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionCls {

	public void createConnection() throws SQLException
	{
		Connection con=null;
		try
		{
			//Loading the driver
			Class.forName("org.h2.Driver");
			
			//2. Establishing the connection
			con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/sandhya", "sa", "");
			/*if(con!=null)
			{
				System.out.println("Connection established");
			}
			else
			{
				System.out.println("Connection failed");
			}*/
			//3. Creating and sending the request
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from student");
			
			//4. Retriving the data
			while(rs.next())
			{
				System.out.println("Student Id: "+rs.getInt(1)+"\nStudent Name: "+rs.getString("sname")+"\nMarks: "+rs.getString(4)+"\nEmail Id: "+rs.getInt(3));
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			//5.Closing the connection
			con.close();
		}
	}
	public static void main(String[] args) throws SQLException {
		
		ConnectionCls c1=new ConnectionCls();
		c1.createConnection();
	}

}




