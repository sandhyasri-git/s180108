package p1;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	
	Connection con=null;
	public Connection createConnection()
	{
		try
		{
			Class.forName("org.h2.Driver");
			con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/sandhya", "sa", "");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

}
