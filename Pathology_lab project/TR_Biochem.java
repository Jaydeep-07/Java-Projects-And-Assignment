
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.sql.*;


 class TR_Biochem extends JDialog
 {
    Object[] data = new Object[13];  // Array of Objects of "Object" Class of size 5

	DefaultTableModel dtm = new DefaultTableModel();

	JTable jt = new JTable(dtm);
    Connection con1;
	Statement st1;
	ResultSet rs1;
	

	public TR_Biochem()
	{
		setTitle("BIOCHEMISTRY TEST");
		Container cc= getContentPane();
		cc.setBackground(Color.white);
	    cc.setLayout(null);
		setLocation(100,20);
		setSize(870,600);
		
		Font f = new Font("Times new Roman",Font.BOLD+Font.ITALIC,30);
		JLabel lHeading= new JLabel("BIOCHEMISTRY TEST RECORD");
		lHeading.setFont(f);
		lHeading.setBounds(70,5,550,70);
		cc.add(lHeading);
		
		String Tcol[] = {"Pname","Fast_Result","Fast_Method","Fast_Urine_Sugar","Fast_Urine_Acetone","Post_Result","Post_Method","Post_Urine_Sugar","Post_Urine_Acetone","Serum_Creatinine","Serum_Method","SGPT","SGPT_Method"};  // Array of objects of String(Object type) class
		
		for(int i = 0 ; i < 13 ; i++)			
		{
			dtm.addColumn(Tcol[i]);
		}
		try
		{
			initialize();
			while(rs1.next())
			{
				for(int j = 0; j <13; j++)
				{
					String str = rs1.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			
			JScrollPane js=new JScrollPane(jt);
			js.setBounds(20,90,825,400);
			cc.add(js);
			
			setVisible(true);
            //this.add(p);  
            rs1.close();
			st1.close();
			con1.close();	
		
		}
		catch(Exception e)
		{
		JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
		}
	}
	
	public void initialize()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con1 = DriverManager.getConnection("jdbc:odbc:PathDSN","","");
			st1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1 = st1.executeQuery("Select * From Test_Biochemistry");
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
		
	/*public static void main (String[] args) 
	{
	   new TR_Biochem();
	} */      
}

