//
import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Urineana_form extends JDialog implements ActionListener
{

    
    JLabel ltname,lpname,ll1,ll2,ll3,ll4,ll5;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18; 
	
	JButton b1,b2;
	List list;
	Container cc= getContentPane();
	JLabel RN,NAME,REFBY,AGE,SEX,DATE,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15,L16,L17,L18;
	JLabel RN1,NAME1,REFBY1,AGE1,SEX1,DATE1,Ldrname;
	String regno,date,refby,age,sex;
	
    Font f1 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,20);
	Font f2 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,14);
   
	
	Connection con;
	Statement st;
    ResultSet rs;
    PreparedStatement pst;
    	
    public Urineana_form()
    {
    	setTitle("URINE ANALYSIS");
		
	    cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
		cc.setBackground(Color.white);
		
		setVisible(true);
    	
    	ltname= new JLabel("URINE ANALYSIS REPORT");  
    	ltname.setFont(f1);
    	ll1= new JLabel("TEST");
    	ll1.setFont(f1);
    	ll2= new JLabel("PHYSICAL EXAMINATION");
    	ll2.setFont(f1);
    	ll5= new JLabel("RESULT");
    	ll5.setFont(f1);
    	
    	lpname= new JLabel("PATIENT NAME:");
    	
    	l1= new JLabel("COLOR                :");    t1= new JTextField(10);   
    	l2= new JLabel("QUANTITY          :");    t2= new JTextField(10);
    	l3= new JLabel("APPEARANCE     :");    t3= new JTextField(10);
    	l4= new JLabel("DEPOSITE        :");    t4= new JTextField(10);
 		l5= new JLabel("REACTION        :");    t5= new JTextField(10);
   	    ll3= new JLabel("CHEMICAL EXAMINATION");
    	ll3.setFont(f1);
    	
   	    l6= new JLabel("PROTEINS           :");    t6= new JTextField(10);
        l7= new JLabel("SUGAR                 :");    t7= new JTextField(10);
        l8= new JLabel("ACETONE            :");    t8= new JTextField(10);
        l9= new JLabel("BILE SALTS         :");    t9= new JTextField(10);
        l10=new JLabel("BILE PIGMENTS :");    t10= new JTextField(10);   
    	l11=new JLabel("UROLILINOGEN  :");    t11= new JTextField(10);   
    	ll4= new JLabel("MICROSCOPIC EXAMINATION");
    	ll4.setFont(f1);
    	
    	l12= new JLabel("RBC                   :");   t12= new JTextField(10);   
    	l13= new JLabel("PUS(WBC)CELLS");   t13= new JTextField(10);   
    	l14= new JLabel("EPITHELIAL CELL:");   t14= new JTextField(10);   
    	l15= new JLabel("CASTS                 :");   t15= new JTextField(10);   
    	l16= new JLabel("CRYSTALS           :");   t16= new JTextField(10);   
    	l17= new JLabel("AMORPHOUS DEPO:");   t17= new JTextField(10);   
    	l18= new JLabel("BACTERIA           :");   t18= new JTextField(10);   
    	
    	l1.setFont(f2);   l2.setFont(f2);    l3.setFont(f2);
    	l4.setFont(f2);   l5.setFont(f2);    l6.setFont(f2); 
    	l7.setFont(f2);   l8.setFont(f2);    l9.setFont(f2);
    	l10.setFont(f2);  l11.setFont(f2);   l12.setFont(f2);
    	l13.setFont(f2);  l14.setFont(f2);  l15.setFont(f2); 
    	l16.setFont(f2);  l17.setFont(f2);  l18.setFont(f2);
    	
    	ltname.setBounds(150,-30,1000,100);
    	
    	ll1.setBounds(50,20,200,60);      ll5.setBounds(270,20,200,60);
    	ll2.setBounds(50,50,1000,50);     ll3.setBounds(50,180,1000,50);
    	ll4.setBounds(50,310,1000,50);
    	
    	l1.setBounds(50,100,150,30);         t1.setBounds(200,100,150,25);
      	l2.setBounds(50,130,150,30);         t2.setBounds(200,130,150,25);
    	l3.setBounds(50,160,150,30);         t3.setBounds(200,160,150,25);
    	l4.setBounds(380,100,150,30);         t4.setBounds(520,100,150,25);
 		l5.setBounds(380,130,150,30);         t5.setBounds(520,130,150,25);
    	
    	
    	l6.setBounds(50,230,150,30);         t6.setBounds(200,230,150,25);
    	l7.setBounds(50,260,150,30);         t7.setBounds(200,260,150,25);
    	l8.setBounds(50,290,150,30);         t8.setBounds(200,290,150,25);
        l9.setBounds(380,230,150,30);         t9.setBounds(520,230,150,25);
        l10.setBounds(380,260,150,30);         t10.setBounds(520,260,150,25);
      	l11.setBounds(380,290,150,30);         t11.setBounds(520,290,150,25);
      	
    
      	l12.setBounds(50,360,150,30);          t12.setBounds(200,360,150,25);
      	l13.setBounds(50,390,150,30);          t13.setBounds(200,390,150,25);
      	l14.setBounds(50,420,150,30);          t14.setBounds(200,420,150,25);
      	l15.setBounds(50,450,150,30);          t15.setBounds(200,450,150,25);
      	l16.setBounds(380,360,150,30);          t16.setBounds(520,360,150,25);
      	l17.setBounds(380,390,150,30);          t17.setBounds(520,390,150,25);
      	l18.setBounds(380,420,150,30);          t18.setBounds(520,420,150,25);
      	
    	  
    	b1= new JButton("ADD");
    	b1.setBounds(300,550,100,40);
    	b2= new JButton("EXIT");
    	b2.setBounds(450,550,100,40);
    	
    	list= new List();
    	try
        {
         	
          initialize();
          rs = st.executeQuery("Select * From PatientEntry_Mast");
         
          String Pname= new String("");
          while(rs.next())
          { 
          	Pname= rs.getString(3);
         	list.add(Pname);
          }
          
          lpname.setFont(f2);
    	  lpname.setBounds(50,465,1000,50);
    	
          list.setBounds(40,500,200,100);
          cc.add(list);
          cc.add(b1);   cc.add(b2);
          cc.add(ltname);  cc.add(lpname);
          cc.add(ll1); cc.add(ll2); 
          cc.add(ll3); cc.add(ll4);
          cc.add(ll5);
          cc.add(l1);   cc.add(t1);
          cc.add(l2);   cc.add(t2);
          cc.add(l3);   cc.add(t3);
          cc.add(l4);   cc.add(t4);
          cc.add(l5);   cc.add(t5);
          cc.add(l6);   cc.add(t6);
          cc.add(l7);   cc.add(t7);
          cc.add(l8);   cc.add(t8);
          cc.add(l9);   cc.add(t9);
          cc.add(l10);  cc.add(t10);
          cc.add(l11);  cc.add(t11);
          cc.add(l12);  cc.add(t12);
          cc.add(l13);  cc.add(t13);
          cc.add(l14);  cc.add(t14);
          cc.add(l15);  cc.add(t15);
          cc.add(l16);  cc.add(t16);
          cc.add(l17);  cc.add(t17);
          cc.add(l18);  cc.add(t18);
          
          
                            
          rs.close();
          st.close();
          con.close();
        }
        catch(Exception e)
		{
			System.out.println("Exception : " + e);
		}
		b1.addActionListener(this);
		b2.addActionListener(this);
		
         
        
    }
    public void actionPerformed(ActionEvent ae)
    {
    	if(ae.getActionCommand().equals("EXIT"))
    	{
    		dispose();
    	}
    	
     	if(ae.getActionCommand().equals("ADD"))
     	{
     		String Pname= list.getSelectedItem();
     		
     		if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals("")||t5.getText().equals("")||t6.getText().equals("")||t7.getText().equals("")||t8.getText().equals("")||t9.getText().equals("")||t10.getText().equals("")||t11.getText().equals("")||t12.getText().equals("")||t13.getText().equals("")||t14.getText().equals("")||t15.getText().equals("")||t16.getText().equals("")||t17.getText().equals("")||t18.getText().equals(""))
     		{
     		  JOptionPane.showMessageDialog((Component)null,"Please insert proper value..","Sorry",JOptionPane.ERROR_MESSAGE);	
     		}
     		
     		else
     		{
     		
     		 	try
     			{
     				initialize();
     				pst= con.prepareStatement("Insert into Test_UrinAna values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
     				pst.setString(1,Pname);
     				pst.setString(2,t1.getText());
     				pst.setString(3,t2.getText());
     				pst.setString(4,t3.getText());
     				pst.setString(5,t4.getText());
     				pst.setString(6,t5.getText());
     				pst.setString(7,t6.getText());
     				pst.setString(8,t7.getText());
     				pst.setString(9,t8.getText());
     				pst.setString(10,t9.getText());
     				pst.setString(11,t10.getText());
     				pst.setString(12,t11.getText());
     				pst.setString(13,t12.getText());
     				pst.setString(14,t13.getText());
     				pst.setString(15,t14.getText());
     				pst.setString(16,t15.getText());
     				pst.setString(17,t16.getText());
     				pst.setString(18,t17.getText());
     				pst.setString(19,t18.getText());
     			
     				pst.executeUpdate();
     				rs = st.executeQuery("Select * From PatientEntry_Mast ");
                	while(rs.next())
                	{
                    	if(rs.getString(3).equals(Pname))
                    	{
                 		     regno= rs.getString(1);
     		             	 date= rs.getString(2);
     			             refby= rs.getString(4);
     		 	             age= rs.getString(7);
     			             sex= rs.getString(8);
     		                 break;
                    	}
                    };
     			JOptionPane.showMessageDialog((Component)null,"Records inserted successfully......","Inserted.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     		  }
     		
     		catch(Exception e)
     		{
     		 JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		}
     		this.setBounds(0,0,700,1000); this.setBackground(Color.white);
     		ltname.setText("Jadhav Pathology Lab");
     		ltname.setBounds(100,-110,300,300);
     		
     	    RN= new JLabel("REG NO.:");    RN.setBounds(50,50,150,60);      RN.setFont(f2);
     	    NAME=new JLabel("DR.NAME  :");  NAME.setBounds(50,70,150,60);    NAME.setFont(f2);
     	    REFBY= new JLabel("REF BY: "); REFBY.setBounds(50,90,150,60);   REFBY.setFont(f2);
     	    AGE= new JLabel("AGE :");      AGE.setBounds(350,50,150,60);    AGE.setFont(f2);
     	    SEX= new JLabel("SEX :");      SEX.setBounds(350,70,150,60);    SEX.setFont(f2);
     	    DATE= new JLabel("DATE:");     DATE.setBounds(350,90,150,60);   DATE.setFont(f2);
     	    
     	    RN1= new JLabel(regno);        RN1.setBounds(150,50,150,60);    RN1.setFont(f2);
     	    NAME1=new JLabel("DR MANOHAR JADHAV"); NAME1.setBounds(150,70,200,60);  NAME1.setFont(f2);
     	    REFBY1= new JLabel(refby);     REFBY1.setBounds(150,90,150,60); REFBY1.setFont(f2);
     	    AGE1= new JLabel(age);         AGE1.setBounds(450,50,150,60);    AGE1.setFont(f2);
     	    SEX1= new JLabel(sex);         SEX1.setBounds(450,70,150,60);   SEX1.setFont(f2);
     	    DATE1= new JLabel(date);       DATE1.setBounds(450,90,150,60);  DATE1.setFont(f2);
     	    
     	    lpname.setText("URINE ANALYSIS");
     	    lpname.setBounds(100,100,150,100);
     	    b2.setBounds(200,600,100,40);
     	    l1.setBounds(50,200,150,30);   l2.setBounds(50,230,150,30);   l3.setBounds(50,260,150,30);  
     	    l4.setBounds(350,200,150,30);   l5.setBounds(350,230,150,30);   l6.setBounds(50,320,150,30);  
     	    l7.setBounds(50,350,150,30);   l8.setBounds(50,380,150,30);   l9.setBounds(350,320,150,30);  
     	    l10.setBounds(350,350,150,30);  l11.setBounds(350,380,150,30);  l12.setBounds(50,430,150,30);  
     	    l13.setBounds(50,460,150,30);  l14.setBounds(50,490,150,30);  l15.setBounds(50,520,150,30);  
     	    l16.setBounds(350,430,150,30);   l17.setBounds(350,460,150,30);   l18.setBounds(350,490,150,30);  
     	    
     	    
     	    L1=new JLabel(t1.getText());  L1.setBounds(220,200,150,30); L1.setFont(f2);	     
     	    L2=new JLabel(t2.getText());  L2.setBounds(220,230,150,30); L2.setFont(f2);	     
     	    L3=new JLabel(t3.getText());  L3.setBounds(220,260,150,30); L3.setFont(f2);
     	    L4=new JLabel(t4.getText());  L4.setBounds(500,200,150,30); L4.setFont(f2);	     
     	    L5=new JLabel(t5.getText());  L5.setBounds(500,230,150,30); L5.setFont(f2);	     
     	    L6=new JLabel(t6.getText());  L6.setBounds(220,320,150,30); L6.setFont(f2);	     
     	    L7=new JLabel(t7.getText());  L7.setBounds(220,350,150,30); L7.setFont(f2);	     
     	    L8=new JLabel(t8.getText());  L8.setBounds(220,380,150,30); L8.setFont(f2);	     
     	    L9=new JLabel(t9.getText());  L9.setBounds(500,320,150,30); L9.setFont(f2);	    
     	    L10=new JLabel(t10.getText()); L10.setBounds(500,350,150,30); L10.setFont(f2);	     	     
     	    L11=new JLabel(t11.getText()); L11.setBounds(500,380,150,30); L11.setFont(f2);	     	     
     	    L12=new JLabel(t12.getText()); L12.setBounds(200,430,150,30); L12.setFont(f2);	     	     
     	    L13=new JLabel(t13.getText()); L13.setBounds(220,460,150,30); L13.setFont(f2);	     	     
     	    L14=new JLabel(t14.getText()); L14.setBounds(220,490,150,30); L14.setFont(f2);	     	     
     	    L15=new JLabel(t15.getText()); L15.setBounds(220,520,150,30); L15.setFont(f2);	     	     
     	    L16=new JLabel(t16.getText()); L16.setBounds(500,430,150,30); L16.setFont(f2);	     	     
     	    L17=new JLabel(t17.getText()); L17.setBounds(500,460,150,30); L17.setFont(f2);	     	     
     	    L18=new JLabel(t18.getText()); L18.setBounds(500,490,150,30); L18.setFont(f2);	     	     
     	    
     	    ll2.setBounds(50,170,1000,50);     ll3.setBounds(50,280,1000,50);
    	    ll4.setBounds(50,400,1000,50);
     	    
     	    ll2.setFont(f2); ll3.setFont(f2); ll4.setFont(f2); 
     	    b1.setBounds(200,600,100,40);
     	    b1.setLabel("PRINT");
      	   
      	    t1.setVisible(false);    t2.setVisible(false);      t3.setVisible(false);
     	    t4.setVisible(false);	 t5.setVisible(false);		t6.setVisible(false);
     	    t7.setVisible(false);	 t8.setVisible(false);		t9.setVisible(false);
     	    t10.setVisible(false);	 t11.setVisible(false);		t12.setVisible(false);
     	    t13.setVisible(false);	 t14.setVisible(false);		t15.setVisible(false);
     	    t16.setVisible(false);	 t17.setVisible(false);		t18.setVisible(false);
     	    list.setVisible(false);  b2.setVisible(false);    
     	    	     	    
     	    ll1.setVisible(false);   ll5.setVisible(false);
     	    cc.add(RN); cc.add(RN1);
     		cc.add(NAME); cc.add(NAME1);
     		cc.add(REFBY);cc.add(REFBY1);
     		cc.add(AGE); cc.add(AGE1);
     		cc.add(SEX);cc.add(SEX1);
     		cc.add(DATE);cc.add(DATE1);
     		
     		cc.add(L1); cc.add(L2); cc.add(L3); cc.add(L4); cc.add(L5); cc.add(L6); cc.add(L7); cc.add(L8); cc.add(L9);
     		cc.add(L10); cc.add(L11); cc.add(L12); cc.add(L13); cc.add(L14); cc.add(L15); cc.add(L16); cc.add(L17); cc.add(L18);
     		
     	   }//else   	
        }
     	if(ae.getActionCommand().equals("PRINT"))
     	{
     	    PrintUtilities.printComponent(this);
  
     	 }
    }
    public void initialize()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:PathDSN","","");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
	
	 
    /*public static void main (String[] args) throws Exception
    {
    	new Urineana_form();
    }*/
      
}