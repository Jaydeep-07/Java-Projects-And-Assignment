import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class About_Frame  extends JDialog
{
    JLabel lPartner1,lPartner2,lGuide,lImage,lHeading,lName,lcreatedBy,lguidedBy;
    Font f3 = new Font("Bookman Old Style", Font.BOLD+Font.ITALIC,20);

    public About_Frame()
    {
       setTitle("About us");
       Container cc= getContentPane();
       this.setBounds(100,100,620,550);
       cc.setLayout(null);
       //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       setVisible(true);
       
       Font f1 = new Font("Papyrus",Font.BOLD+Font.ITALIC,20);
       lName=new JLabel("   PATHOLOGY  LAB  SYSTEM");
       lName.setBounds(100,50,430,50);
       lName.setFont(f1);
       lName.setForeground(Color.BLACK);
       lName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	   cc.add(lName);
	   
	   lguidedBy=new JLabel("Guided By");
       lguidedBy.setBounds(80,170,400,60);
       lguidedBy.setFont(f3);
       lguidedBy.setForeground(Color.BLACK);
       cc.add(lguidedBy);
       
       lGuide=new JLabel(" MR. NILESH MAHAJAN");
       lGuide.setBounds(80,220,400,70);
       lGuide.setFont(f3);
       lGuide.setForeground(Color.BLACK);
       cc.add(lGuide);
	   
	   lcreatedBy=new JLabel("Created By");
       lcreatedBy.setBounds(250,300,400,30);
       lcreatedBy.setFont(f3);
       lcreatedBy.setForeground(Color.BLACK);
       cc.add(lcreatedBy);
	   
	   lPartner1=new JLabel("MISS. LATA SWAMY");
       lPartner1.setBounds(300,350,400,30);
       lPartner1.setFont(f3);
       lPartner1.setForeground(Color.BLACK);
       cc.add(lPartner1);
	   
	   lPartner2=new JLabel("MISS. VRUSHALI GADEKAR");
       lPartner2.setBounds(300,380,370,50);
       lPartner2.setFont(f3);
       lPartner2.setForeground(Color.BLACK);
       cc.add(lPartner2);
       
        ImageIcon ic=new ImageIcon("about.jpg");
    	JLabel lc=new JLabel(ic);
    	setLayout(null);
    	add(lc);
    	lc.setBounds(7,-15,650,600);
         
	   
    }
    /*public static void main (String[] args) 
    {
    	new About_Frame(); 
    }*/
    
    
}