import java.sql.*;
import java.io.*;

public class StudentDB {
	private static Connection con;
	private static PreparedStatement ps;
	private static Statement s;
	private static ResultSet rs;
	
	private static BufferedReader br;
	
	public static void doConnection(){
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql:ty1","postgres","");
			br = new BufferedReader(new InputStreamReader(System.in));
		} 
		catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public static void insert(){
		try {
			ps = con.prepareStatement("insert into student values(?,?,?)");
			
			System.out.print("Enter roll no:");
			int rno = Integer.parseInt(br.readLine());
			
			System.out.print("Enter name:");
			String name = br.readLine();
			
			System.out.print("Enter percentage:");
			float per = Float.parseFloat(br.readLine());
			
			ps.setInt(1, rno);
			ps.setString(2, name);
			ps.setFloat(3, per);
			
			ps.executeUpdate();
			System.out.println("Record inserted successfully.");
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void displayAll(){
		try {
			s = con.createStatement();
			rs = s.executeQuery("select * from student");
			System.out.println("Roll No\tName\tPercentage");
			while (rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+
							rs.getString(2)+"\t"+ 
							rs.getFloat(3));
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void search(){
		try {
			System.out.print("Enter roll no to be searched:");
			int rno = Integer.parseInt(br.readLine());
			ps = con.prepareStatement("select * from student where roll_no=?");
			ps.setInt(1, rno);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Roll No:"+rs.getInt(1)+
							"\nName:"+rs.getString(2)+
							"\nPercentage:"+rs.getFloat(3));
			} 
			else {
				System.out.println("Roll No "+rno+" not found.");
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void delete(){
		try {
			System.out.print("Enter roll no to be deleted:");
			int rno = Integer.parseInt(br.readLine());
			
			ps = con.prepareStatement("delete from student where roll_no=?");
			ps.setInt(1, rno);
			
			if (ps.executeUpdate()==1) {
				System.out.println("Student deleted successfully.");
			} 
			else {
				System.out.println("Roll No "+rno+" not found");
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void update(){
		try {
			System.out.print("Enter roll no to be searched:");
			int rno = Integer.parseInt(br.readLine());
			
			ps = con.prepareStatement("select * from student where roll_no=?");
			ps.setInt(1, rno);
			rs = ps.executeQuery();
			
			if(!rs.next()){
				System.out.println("Roll No "+rno+" not found.");
				return;
			}
			
			System.out.print("Enter new name:");
			String name = br.readLine();
			System.out.print("Enter new per");
			float per = Float.parseFloat(br.readLine());
			
			ps = con.prepareStatement("update student set name=?, per=? where roll_no=?");
			ps.setString(1, name);
			ps.setFloat(2, per);
			ps.setInt(3, rno);
			ps.executeUpdate();
			
			System.out.println("Record updated successfully.");
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws Exception{
		doConnection();
		while (true) {
			System.out.println("1.Insert\n2.Modify\n3.Delete\n4.Search\n5.View All\n6.Exit");
			System.out.print("Enter your choice (1-6):");
			int ch = Integer.parseInt(br.readLine());
			
			switch (ch) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				search();
				break;
			case 5:
				displayAll();
				break;
			case 6:
				System.exit(0);
			default:
				System.out.println("Invalid choice "+ch);
			}
		}
	}
}
