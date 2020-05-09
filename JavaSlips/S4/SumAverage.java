import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SumAverage extends JFrame{
	private JMenuBar mbMain;
	private JMenu mnuOperation,mnuCompute;
	private JMenuItem miLoad,miSave,miExit,miSum,miAvg;
	private JTextArea txtNumbers;
	private int nos[]=new int[10];

	public SumAverage(){
		mbMain = new JMenuBar();

		mnuOperation = new JMenu("Operation");
		mnuCompute = new JMenu("Compute");

		miLoad = new JMenuItem("Load");
		miSave = new JMenuItem("Save");
		miExit = new JMenuItem("Exit");
		miSum = new JMenuItem("Sum");
		miAvg = new JMenuItem("Average");

		txtNumbers = new JTextArea();

		mbMain.add(mnuOperation);
		mbMain.add(mnuCompute);

		mnuOperation.add(miLoad);
		mnuOperation.add(miSave);
		mnuOperation.addSeparator();
		mnuOperation.add(miExit);

		mnuCompute.add(miSum);
		mnuCompute.add(miAvg);

		setTitle("Sum & Average");
		setSize(400,400);
		setLocation(100,100);
		add(new JScrollPane(txtNumbers));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(mbMain);

		miLoad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int n=10;
				txtNumbers.setText("");
				for(int i=0;i<n;i++){
					int t = (int)(Math.random()*100+1);
					if(t<10) t+=10;
					nos[i] = t;
					txtNumbers.append(nos[i]+"\n");
				}
			}
		});

		miExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.exit(0);	
			}
		});

		miSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					BufferedWriter bw = new BufferedWriter(
							new FileWriter("numbers.txt"));
					for(int i=0;i<10;i++)
						bw.write(nos[i]+"\n");
					bw.close();
				}
				catch(Exception e){}	
			}
		});

		miSum.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int n=10,sum=0;
				for(int i=0;i<n;i++){
					sum+=nos[i];
				}
				txtNumbers.setText("Sum="+sum);
			}
		});				

		miAvg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int n=10;
				float sum=0;
				for(int i=0;i<n;i++){
					sum+=nos[i];
				}
				txtNumbers.setText("Avg="+(sum/10));
			}
		});				
	}

	public static void main(String args[]){
		new SumAverage();
	}
}		

