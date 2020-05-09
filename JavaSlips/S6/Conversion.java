import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Conversion extends JFrame{
	private JLabel lblDec,lblBin,lblBinAns,lblOct,lblOctAns,lblHex,lblHexAns;
	private JTextField txtDec;
	private JButton btnCalc,btnClear;

	public Conversion(){
		lblDec = new JLabel("Decimal Number:");
		lblBin = new JLabel("Binary Number:");
		lblOct = new JLabel("Octal Number:");
		lblHex = new JLabel("Hexadecimal Number:");

		txtDec = new JTextField();
		
		lblBinAns = new JLabel();
		lblOctAns = new JLabel();
		lblHexAns = new JLabel();

		btnCalc = new JButton("Calculate");
		btnClear = new JButton("Clear");

		setTitle("Conversions");
		setSize(450,200);
		setLayout(new GridLayout(5,2));
		add(lblDec);
		add(txtDec);
		add(lblBin);
		add(lblBinAns);
		add(lblOct);
		add(lblOctAns);
		add(lblHex);
		add(lblHexAns);
		add(btnCalc);
		add(btnClear);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		btnCalc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int n = Integer.parseInt(txtDec.getText());
				lblBinAns.setText(Integer.toBinaryString(n));
				lblOctAns.setText(Integer.toOctalString(n));
				lblHexAns.setText(Integer.toHexString(n));
			}
		});

		btnClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				txtDec.setText("");
				lblBinAns.setText("");
				lblOctAns.setText("");
				lblHexAns.setText("");
				txtDec.requestFocus();
			}
		});
	}

	public static void main(String args[]){
		new Conversion();
	}
}
