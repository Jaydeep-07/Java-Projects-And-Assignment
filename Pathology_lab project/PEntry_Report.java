
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.sql.*;


 class PEntry_Report extends JDialog
 {

    Object[] data = new Object[8];  // Array of Objects of "Object" Class of size 8

	DefaultTableModel dtm = new DefaultTableModel();

	JTable jt = new JTable(dtm);

	Connection con;
	Statement st;
	ResultSet rs;
	
	public PEntry_Report()
	{
		
        setTitle("PATIENT ENTRY REPORT");
        Container cc= getContentPane();
		cc.setBackground(Color.white);
	    cc.setLayout(null);
		setLocation(100,20);
		setSize(620,600);
		
		Font f = new Font("Times new Roman",Font.BOLD+Font.ITALIC,30);
		JLabel lHeading= new JLabel("PATIENT ENTRY RECORD");
		lHeading.setFont(f);
		lHeading.setBounds(60,5,550,100);
		cc.add(lHeading);
		String Pcol[] = {"Pno","Regcode","Date","Pname","RDname","Padd","Phno","Age"};  // Array of objects of String(Object type) class
		
		for(int i = 0 ; i < 8 ; i++)			
		{
			dtm.addColumn(Pcol[i]);
		}
		try
		{
			initialize();
			while(rs.next())
			{
				for(int j = 0 ; j < 8 ; j++)
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
			con = DriverManager.getConnection("jdbc:odbc:PathLab","","");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery("Select * From PatientEntry_Mast");
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
		
	/*public static void main (String[] args) 
	{
		PEntry_Report p1 = new PEntry_Report();
		
	}*/
    
    
}

