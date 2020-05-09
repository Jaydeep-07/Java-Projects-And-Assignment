

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class Path_menu extends JFrame implements ActionListener 
{
     JMenuBar mb;
     JMenu M_master,M_trans,M_report,M_exit,M_test,M_TestReport,M_About;
     JMenuItem P_Entry_form , Refdoc_form,Lab_Assis_form,Test_Info_form, Stock_Det, Supp_Det,Test_charge_info;
     JMenuItem Pur_Order;
     JMenuItem P_entry_rep,Test_rec_rep,Refdoc_rep,Lab_Ass_rep, Stock_rep,Supp_rep,Test_charge_rep,Order_rep,Bill_rep;
     JMenuItem T_Serology1,T_HIV,T_Blood_Count,T_Serology2,T_Heamatology,T_Biochemistry, T_Urine_Ana;
     JMenuItem exit;
     JMenuItem aboutus;
     JMenuItem Heamatology, Urineana,Biochem,Bloodcnt,HIV,Serology,Serology2;
     Container cc;
     
     public JDesktopPane jdesk = new JDesktopPane();

     
     public Path_menu() 
     {
       	 super("JADHAV PATHOLOGY LAB");
		 setLayout(null);
       	 setSize(1050,1000);
       	 show();
         this.setResizable(true);
       	 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       	 
       	 mb= new JMenuBar();
    	 
         M_master = new JMenu("FORM");
   	     M_trans = new JMenu("TRANSACTION");
   	     M_report = new JMenu("REPORT");                                       
   	     M_test= new JMenu("TEST");
   	     M_TestReport= new JMenu("TEST REPORT");
   	     M_About= new JMenu("ABOUT US");
   	     M_exit = new JMenu("EXIT"); 
        
         P_Entry_form= new JMenuItem("PATIENT ENTRY FORM");
         Refdoc_form= new JMenuItem("REFERENCE DOCTOR FORM");
         Lab_Assis_form= new JMenuItem("LAB ASSISTENT FORM");
         Test_Info_form= new JMenuItem("TEST INFORMATION FORM");
         Stock_Det= new JMenuItem("STOCK DETAILS");
         Supp_Det= new JMenuItem("SUPPLIER DETAILS");
         Test_charge_info= new JMenuItem("TEST CHARGE INFORMATION");
         
         Pur_Order= new JMenuItem("PURCHASE ORDER");
         
         
         P_entry_rep= new JMenuItem("PATIENT ENTRY REPORT");
         Test_rec_rep= new JMenuItem("TEST RECORD REPORT");
         Refdoc_rep= new JMenuItem("REFERENCE DOCTOR REPORT");
         Lab_Ass_rep= new JMenuItem("LAB ASSISTANT REPORT ");
         Stock_rep= new JMenuItem("STOCK REPORT");
         Supp_rep= new JMenuItem("SUPPLIER REPORT");
         Test_charge_rep= new JMenuItem("TEST CHARGES REPORT");
         Order_rep= new JMenuItem("ORDER REPORT");
         
         
         T_Serology1= new JMenuItem("TEST SEROLOGY");
         T_HIV= new JMenuItem("TEST HIV");
         T_Blood_Count= new JMenuItem("TEST BLOOD COUNT");
         T_Serology2= new JMenuItem("TEST SEROLOGY-RA&HBSAG");
         T_Heamatology= new JMenuItem("TEST HEAMATOLOGY");
         T_Biochemistry= new JMenuItem("TEST BIOCHEMISTRY");
         T_Urine_Ana= new JMenuItem("TEST URINE ANALYSIS");
         
         Heamatology=new JMenuItem("HEAMATOLOGY TEST");
         Urineana=new JMenuItem("URINE ANALYSIS TEST");
         Biochem=new JMenuItem("BIOCHEMISTRY TEST");
         Bloodcnt=new JMenuItem("COMPLETE BLOOD COUNT TEST");
         HIV=new JMenuItem("HIV TEST");
         Serology=new JMenuItem("SEROLOGY MONOTOX TEST");
         Serology2=new JMenuItem("SEROLOGY RA FACTOR & HBSAG TEST");	
          
         aboutus= new JMenuItem("ABOUT US");
         exit= new JMenuItem("EXIT");
         
         M_master.add(P_Entry_form); 
         M_master.add(Refdoc_form);
         M_master.add(Lab_Assis_form);
         M_master.add(Test_Info_form);
         M_master.add(Stock_Det);
         M_master.add(Supp_Det);
         M_master.add(Test_charge_info);
          
         M_trans.add(Pur_Order);
         
       
         M_report.add(P_entry_rep);
         M_report.add(Test_rec_rep);
         M_report.add(Refdoc_rep);
         M_report.add(Lab_Ass_rep);
         M_report.add(Stock_rep);
         M_report.add(Supp_rep);
         M_report.add(Test_charge_rep);
         M_report.add(Order_rep);
         
         
         M_test.add(T_Serology1);
         M_test.add(T_HIV);
         M_test.add(T_Blood_Count);
         M_test.add(T_Serology2);
         M_test.add(T_Heamatology);
         M_test.add(T_Biochemistry);
         M_test.add(T_Urine_Ana);
      
         M_TestReport.add(Heamatology);
         M_TestReport.add(Urineana);
         M_TestReport.add(Biochem);
         M_TestReport.add(Bloodcnt);
         M_TestReport.add(HIV);
         M_TestReport.add(Serology);
         M_TestReport.add(Serology2);
         
         M_About.add(aboutus);
         M_exit.add(exit);
         
    	 mb.add(M_master); 
   		 mb.add(M_trans); 
         mb.add(M_report);
         mb.add(M_test);
         mb.add(M_TestReport);
         mb.add(M_About);
         mb.add(M_exit);
         
        
		 ImageIcon ic=new ImageIcon("obj01.jpg");
    	 JLabel lc=new JLabel(ic);
    	 setLayout(null);
    	 add(lc);
    	 lc.setBounds(2,-50,1010,800);
         
        
         setJMenuBar(mb);
         
      	         
         P_Entry_form.addActionListener(this);
         Refdoc_form.addActionListener(this);
         Lab_Assis_form.addActionListener(this);
         Test_Info_form.addActionListener(this);
         Stock_Det.addActionListener(this);
         Supp_Det.addActionListener(this);
         Test_charge_info.addActionListener(this);
         
         Pur_Order.addActionListener(this);
         
         
         P_entry_rep.addActionListener(this);
         Test_rec_rep.addActionListener(this);
         Refdoc_rep.addActionListener(this);
         Lab_Ass_rep.addActionListener(this);
         Stock_rep.addActionListener(this);
         Supp_rep.addActionListener(this);
         Test_charge_rep.addActionListener(this);
         Order_rep.addActionListener(this);
         
         T_Serology1.addActionListener(this);
         T_HIV.addActionListener(this);
         T_Blood_Count.addActionListener(this);
         T_Serology2.addActionListener(this);
         T_Heamatology.addActionListener(this);
         T_Biochemistry.addActionListener(this);
         T_Urine_Ana.addActionListener(this);
         
         Heamatology.addActionListener(this);
         Urineana.addActionListener(this);
         Biochem.addActionListener(this);
         Bloodcnt.addActionListener(this);
         HIV.addActionListener(this);
         Serology.addActionListener(this);
         Serology2.addActionListener(this);
         
         aboutus.addActionListener(this);
         exit.addActionListener(this);
         
         
     }
     public void actionPerformed(ActionEvent ae)
     {
     	if(ae.getSource()==P_Entry_form )
     	{
     	  new PEntry_Frame();
     	}
     	if(ae.getSource()==Refdoc_form )
     	{
     	  new Ref_DocDetail();
     	}
     	if(ae.getSource()==Lab_Assis_form)
     	{
     	  new LabAss_DetFrame();
     	}
     	if(ae.getSource()==Test_Info_form )
     	{
     	  new Test_DetFrame();
     	}
     	if(ae.getSource()==Stock_Det)
     	{
     	  new Stock_detFrame();
     	}
     	if(ae.getSource()==Supp_Det)
     	{
     	  new Supp_DetFrame();
     	}
     	if(ae.getSource()==Test_charge_info)
     	{
     	  new Test_charge_info();
     	}
     	if(ae.getSource()==Pur_Order)
     	{
     	  new Order_DetFrame();
     	}
     	if(ae.getSource()==P_entry_rep )
     	{
     	  new PEntry_Report();
     	}
     	if(ae.getSource()== Test_rec_rep )
     	{
     	  new TestRecord_Report();
     	}
     	if(ae.getSource()==Refdoc_rep)
     	{
     	  new RefDoc_report();
     	}
     	if(ae.getSource()==Lab_Ass_rep )
     	{
     	  new Lab_Assis_Report();
     	}
     	if(ae.getSource()==Stock_rep )
     	{
     	  new Product_Report();
     	}
     	if(ae.getSource()==Supp_rep )
     	{
     	  new Supp_Report();
     	}
     	if(ae.getSource()==Test_charge_rep )
     	{
     	  new TestChrg_Report();
     	}
     	if(ae.getSource()==Order_rep )
     	{
     	  new Order_Report();
     	}
     	if(ae.getSource()==Stock_Det)
     	{
     	  new Stock_detFrame();
     	}
     	if(ae.getSource()==T_Serology1)
     	{
     	  new Serology_form();
       	}
     	if(ae.getSource()==T_HIV)
     	{ 
     	  new HIV_form();
     	}
     	if(ae.getSource()==T_Blood_Count)
     	{
          new Bloodcnt_form();
      	}
     	if(ae.getSource()==T_Serology2)
     	{
     	  new Serology2_form();
     	}
     	if(ae.getSource()==T_Heamatology)
     	{
     	  new Heamatology_form();
     	}
     	if(ae.getSource()==T_Biochemistry)
     	{
     	  new Biochem_form();
     	}
     	if(ae.getSource()==T_Urine_Ana)
     	{
     	  new Urineana_form();
      	}
        if(ae.getSource()==Heamatology)
     	{ 
     	  new TR_Heamatology();
      	}
      	if(ae.getSource()==Urineana)
     	{
     	  new TR_Urineana();
      	}
      	if(ae.getSource()==Biochem)
     	{
     	  new TR_Biochem();
      	}
      	if(ae.getSource()==Bloodcnt)
     	{
     	  new TR_Bloodcnt();
      	}
      	if(ae.getSource()==HIV)
     	{
     	  new TR_HIV();
      	}
      	if(ae.getSource()==Serology)
     	{
     	  new TR_Serology();
      	}
      	if(ae.getSource()==Serology2)
     	{
     	  new TR_Serology2();
      	}
      	if(ae.getSource()==aboutus)
      	{
      		new About_Frame();
      	}
     	if(ae.getSource()==exit )
     	{
     		System.exit(1);
     	}
     } 
        
     public static void main (String[] args) throws Exception
     {
       new Path_menu();
     }
}