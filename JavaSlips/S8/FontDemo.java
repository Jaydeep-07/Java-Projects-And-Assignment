import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FontDemo extends JFrame{
	private JLabel lblFont,lblSize,lblStyle;
	private JComboBox cmbFont,cmbSize;
	private JCheckBox cbBold,cbItalic;
	private JTextField txtMsg;

	private int valItalic = Font.PLAIN, valBold = Font.BOLD;

	public FontDemo(){
		lblFont = new JLabel("Font");
		lblSize = new JLabel("Size");
		lblStyle = new JLabel("Style");

		String fontName[] = GraphicsEnvironment.getLocalGraphicsEnvironment().
						getAvailableFontFamilyNames();

		cmbFont = new JComboBox();

		for(int i=0;i<fontName.length;i++)
			cmbFont.addItem(fontName[i]);

		cmbSize = new JComboBox(new String[]{"5","6","7","8","9","10"});

		cbBold = new JCheckBox("Bold");
		cbItalic = new JCheckBox("Italic");

		txtMsg = new JTextField(100);

		lblFont.setBounds(10,10,70,25);
		cmbFont.setBounds(10,40,170,25);
		lblSize.setBounds(10,70,70,25);
		cmbSize.setBounds(10,100,170,25);
		txtMsg.setBounds(10,150,250,30);
		lblStyle.setBounds(200,10,70,25);
		cbBold.setBounds(200,40,70,25);
		cbItalic.setBounds(200,70,70,25);

		setTitle("Font Demo");
		setLayout(null);
		setSize(450,300);
		add(lblFont);
		add(cmbFont);
		add(lblSize);
		add(cmbSize);
		add(txtMsg);
		add(lblStyle);
		add(cbBold);
		add(cbItalic);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cbBold.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				if(cbBold.isSelected())
					valBold = Font.BOLD;
				else
					valBold = Font.PLAIN;
				change();
			}
		});
		
		cbItalic.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				if(cbItalic.isSelected())
					valItalic = Font.ITALIC;
				else
					valItalic = Font.PLAIN;
				change();
			}
		});

		cmbFont.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				change();
			}
		});

		cmbSize.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				change();
			}
		});
	}

	public void change(){
		String fontName = cmbFont.getSelectedItem().toString();
		int fontSize = Integer.parseInt(cmbSize.getSelectedItem().toString());
		txtMsg.setFont(new Font(fontName, valBold+valItalic, fontSize));
	}

	public static void main(String args[]){
		new FontDemo();
	}
}

