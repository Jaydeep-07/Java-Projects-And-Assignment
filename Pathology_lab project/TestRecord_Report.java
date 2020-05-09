
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.sql.*;


 class TestRecord_Report extends JDialog
 {
    Object[] data = new Object[4];  // Array of Objects of "Object" Class of size 4

	DefaultTableModel dtm = new DefaultTableModel();

	JTable jt = new JTable(dtm);

	Connection con1;
	Statement st1;
	ResultSet rs1;
	
	public TestRecord_Report()
	{
		setTitle("TEST RECORD REPORT");
		Container cc= getContentPane();
		cc.setBackground(Color.white);
	    cc.setLayout(null);
		setLocation(100,20);
		setSize(620,600);
		
		Font f = new Font("Times new Roman",Font.BOLD+Font.ITALIC,30);
		JLabel lHeading= new JLabel("TEST DETAILS");
		lHeading.setFont(f);
		lHeading.setBounds(90,5,550,100);
		cc.add(lHeading);
		String Tcol[] = {"Tid","Tname","Range","Unit"};  // Array of objects of String(Object type) class
		
		for(int i = 0 ; i < 4 ; i++)			
		{
			dtm.addColumn(Tcol[i]);
		}
		try
		{
			initialize();
			while(rs1.next())
			{
				for(int j = 0; j < 4; j++)
				{
					String str = rs1.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			
	     	JScrollPane js=new JScrollPane(jt);
			js.setBounds(70,90,500,400);
			cc.add(js);
			
			setVisible(true);
            //this.add(p);  
			rs1.close();
			st1.close();
			con1.close();
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
			con1 = DriverManager.getConnection("jdbc:odbc:PathDSN","","");
			st1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1 = st1.executeQuery("Select * From Test_info");
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
		
	/*public static void main (String[] args) 
	{
		TestRecord_Report t1= new TestRecord_Report();				
	} */      
}

