package p1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatementDemo {
	
	PreparedStatement ps=null;
	ResultSet rs=null;
	int x;
	Connection c=null;
	int id,marks;
	String name,email;
	public void storeRec() throws SQLException
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Student Id");
		id=sc.nextInt();
		System.out.println("Enter Student Name");
		name=sc.next();
		System.out.println("Enter Student Marks");
		marks=sc.nextInt();
		
		System.out.println("Enter Student Mail Id");
		email=sc.next();
		
		MyConnection m1=new MyConnection();
		c=m1.createConnection();
		
		ps=c.prepareStatement("insert into student values(?,?,?,?)");
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, marks);
		ps.setString(4, email);
		x=ps.executeUpdate();
		if(x>0)
		{
			System.out.println("Row added");
		}
		else
		{
			System.out.println("Row not added");
		}
		
	}
	public void readRec() throws SQLException
	{
		MyConnection m1=new MyConnection();
		c=m1.createConnection();
		/*Statement st=c.createStatement();
		rs=st.executeQuery("select * from student");
		while(rs.next())
		{
System.out.println("Student Id: "+rs.getInt(1)+"\nStudent Name: "+rs.getString("sname")+"\nMarks: "+rs.getString(4)+"\nEmail Id: "+rs.getInt(3));
		}
		*/
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Student Id");
		id=sc.nextInt();
		ps=c.prepareStatement("select * from student where sid=?");
		ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next())
		{
System.out.println("Student Id: "+rs.getInt(1)+"\nStudent Name: "+rs.getString("sname")+"\nMarks: "+rs.getString(4)+"\nEmail Id: "+rs.getInt(3));
		}
		c.close();
	}

	public static void main(String[] args) throws SQLException {
		PreparedStatementDemo p1=new PreparedStatementDemo();
		//p1.storeRec();
		System.out.println();
		p1.readRec();
		

	}

}
