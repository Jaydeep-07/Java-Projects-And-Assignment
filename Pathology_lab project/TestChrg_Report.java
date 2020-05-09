
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.sql.*;


 class TestChrg_Report extends JDialog
 {

    Object[] data = new Object[2];  // Array of Objects of "Object" Class of size 2

	DefaultTableModel dtm = new DefaultTableModel();

	JTable jt = new JTable(dtm);

	Connection con;
	Statement st;
	ResultSet rs;
	
	public TestChrg_Report()
	{
		setTitle("TEST CHARGES REPORT");
		Container cc= getContentPane();
		cc.setBackground(Color.white);
	    cc.setLayout(null);
		setLocation(100,20);
		setSize(620,600);
		
		Font f = new Font("Times new Roman",Font.BOLD+Font.ITALIC,30);
		JLabel lHeading= new JLabel("TEST CHARGES");
		lHeading.setFont(f);
		lHeading.setBounds(90,5,550,100);
		cc.add(lHeading);
		
		String Pcol[] = {"Tname","Charges"};  // Array of objects of String(Object type) class
		
		for(int i = 0 ; i < 2 ; i++)			
		{
			dtm.addColumn(Pcol[i]);
		}
		try
		{
			initialize();
			while(rs.next())
			{
				for(int j = 0 ; j < 2 ; j++)
				{
					String str = rs.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			
			JScrollPane js=new JScrollPane(jt);
			js.setBounds(70,90,500,400);
			cc.add(js);
			
			setVisible(true);
            //this.add(p);  
			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception : " + e);
		}
	}
	
	public void initialize()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:PathDSN","","");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery("Select * From Test_Charges");
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
		
	/*public static void main (String[] args) 
	{
		TestChrg_Report t1= new TestChrg_Report();
		
	}*/
    
    
}

