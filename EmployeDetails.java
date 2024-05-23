package org.j2ee.connection.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeDetails {
	
	
	private static Scanner sc = new Scanner(System.in);
	static Connection connect = null;
	
	public static void main(String[] args)
	{
		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = "root";
		
		int input;
		
		EmployeDetails ui = new EmployeDetails();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(;;)
		{
			System.out.println("--------------------------------------------");
			System.out.println("1. Insert Data");
			System.out.println("2. Update Data");
			System.out.println("3. Find Record");
			System.out.println("4. Delete Record");
			System.out.println("5. Exit");
			System.out.println("Please enter above option sr no: ");
			
			input = sc.nextInt();
			
			switch(input)
			{
			case 1: {ui.insertData();}
			break;
			case 2: {ui.updateData();}
			break;
			case 3: {ui.findRecord();}
			break;
			case 4: {ui.deleteRecord();}
			break;
			case 5: {ui.exit();}
			break;
			default: {System.out.println("Please Enter Valid Number!");}
			break;
			}
			
		}
	}
	public void insertData() 
	{
		String qry1 = "insert into j2ee.employeeinfo values(?,?,?)";
		PreparedStatement statement = null;
		
		System.out.println("Please Enter Name: ");
		String name = sc.next();
		System.out.println("Please Enter Id: ");
		int id = sc.nextInt();
		System.out.println("Please Enter Salary: ");
		double salary = sc.nextDouble();
		
		
		try {
			statement = connect.prepareStatement(qry1);
			
			statement.setString(1, name);
			statement.setInt(2, id);
			statement.setDouble(3, salary);
			
			statement.execute();
			
			System.out.println("Data Inserted!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void updateData()
	{
		PreparedStatement statement = null;
		
		String qry2 = "update j2ee.employeeinfo set name=? where id=?";
		String qry3 = "update j2ee.employeeinfo set salary=? where id=?";
		String qry4 = "update j2ee.employeeinfo set id=? where name=?";
		
		System.out.println("1. Update the name");
		System.out.println("2. Update the salary");
		System.out.println("3. Update the id");
		
		int rec = sc.nextInt();
		
		if(rec==1)
		{
			System.out.println("Please enter name: ");
			String name = sc.next();
			System.out.println("Please enter id: ");
			int id = sc.nextInt();
			
			try {
				statement = connect.prepareStatement(qry2);
				statement.setString(1, name);
				statement.setDouble(2, id);
				statement.executeUpdate();
				System.out.println("Data updated!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rec==2)
		{
			System.out.println("Please enter salary");
			double salary = sc.nextDouble();
			System.out.println("Please enter id");
			int id = sc.nextInt();
			
			try {
				statement = connect.prepareStatement(qry3);
				statement.setDouble(1, salary);
				statement.setInt(2, id);
				statement.executeUpdate();
				System.out.println("Data updated!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if(rec==3)
		{
			System.out.println("Please enter id");
			int id = sc.nextInt();
			System.out.println("please enter name");
			String name = sc.next();
			
			try {
				statement = connect.prepareStatement(qry4);
				statement.setInt(1, id);
				statement.setString(2, name);
				statement.executeUpdate();
				System.out.println("Data updated!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void findRecord()
	{
		PreparedStatement statement = null;
		
		String qry5 = "select * from j2ee.employeeinfo where id=?";
		String qry6 = "select * from j2ee.employeeinfo where name=?";
		String qry7 = "select * from j2ee.employeeinfo where salary=?";
		
		System.out.println("1. Search by id");
		System.out.println("2. Search by name");
		System.out.println("3. Search by salary");
		
		System.out.println("Choose option: ");
		int opt = sc.nextInt();
		
		if(opt==1)
		{
			System.out.println("Please enter id");
			int eid = sc.nextInt();
			
			try {
				statement = connect.prepareStatement(qry5);
				statement.setInt(1, eid);
				ResultSet resultset = statement.executeQuery();
				
				while(resultset.next())
				{
					String name = resultset.getString(1);
					int id = resultset.getInt(2);
					double salary = resultset.getDouble(3);
					
					System.out.println("Name is "+name+" id is "+id+" salary is "+salary);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(opt==2)
		{
			System.out.println("Please enter name");
			String ename = sc.next();
			
			try {
				statement = connect.prepareStatement(qry6);
				statement.setString(1, ename);
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next())
				{
					String name  = resultSet.getString(1);
					int id = resultSet.getInt(2);
					double salary = resultSet.getDouble(3);
					
					System.out.println("Name is "+name+" id is "+id+" salary is "+salary);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(opt==3)
		{
			System.out.println("Please enter salary");
			double esalary = sc.nextDouble();
			
			try {
				statement = connect.prepareStatement(qry7);
				statement.setDouble(1, esalary);
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next())
				{
					String name = resultSet.getString(1);
					int id = resultSet.getInt(2);
					double salary = resultSet.getDouble(3);
					
					System.out.println("Name is "+name+" id is "+id+" salary is "+salary);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
	}
	public void deleteRecord()
	{
		PreparedStatement statement = null;
		
		String qry8 = "delete from j2ee.employeeinfo where id=?";
		String qry9 = "delete from j2ee.employeeinfo where name=?";
		String qry10 = "delete from j2ee.employeeinfo where salary=?";
		
		System.out.println("1. Enter id to delete data");
		System.out.println("2. Enter name to delete data");
		System.out.println("3. Enter salary to delete data");
		
		int opt = sc.nextInt();
		
		if(opt==1)
		{
			System.out.println("Enter id: ");
			int id = sc.nextInt();
			
			try {
				statement = connect.prepareStatement(qry8);
				statement.setInt(1, id);
				statement.execute();
				
				System.out.println("Data deleted!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(opt==2)
		{
			System.out.println("Enter name: ");
			String name = sc.next();
			
			try {
				statement = connect.prepareStatement(qry9);
				statement.setString(1, name);
				statement.execute();
				
				System.out.println("Data deleted");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(opt==3)
		{
			System.out.println("Enter salary");
			double salary = sc.nextDouble();
			
			try {
				statement = connect.prepareStatement(qry10);
				statement.setDouble(1, salary);
				statement.execute();
				
				System.out.println("Data deleted!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void exit()
	{
		System.out.println("Exited!");
		System.exit(0);
	}

}
