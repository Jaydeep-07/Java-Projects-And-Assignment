import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
 
public class BounceAlphabets{
	public static void main(String[] args){
		new BounceFrame();
	}
}

class AlphabetRunnable implements Runnable{
	private Alphabet alphabet;
	private Component component;

	public AlphabetRunnable(Alphabet aAlphabet, Component aComponent){
   		alphabet = aAlphabet;
   		component = aComponent;
	}

	public void run(){
   		while(true){
	 		alphabet.move(component);
			component.repaint();
			try{
		 		Thread.sleep(100);
	  		}catch (InterruptedException e){}
		}
	}
}

class Alphabet{
	private int x, y, dy=1;
	private String alpha;
	private Color c;

	Alphabet(){
		x = (int)(Math.random()*400);
		y = (int)(Math.random()*400);
		alpha = (char)(int)(Math.random()*26+65) + "";
		c = new Color((int)(Math.random()*256),
				(int)(Math.random()*256),
				(int)(Math.random()*256));
	}

	public int getX(){	
		return x;
	}

	public int getY(){
		return y;
	}

	public String getAlphabet(){
		return alpha;
	}

	public Color getColor(){
		return c;
	}

	public void move(Component bounds){
   		y+=dy;
   		if(y < 0){
	  		y = 0;
	  		dy = -dy;
   		}
   		if(y>=bounds.getHeight()){
	  		y = bounds.getHeight();
	  		dy = -dy;
   		}
	}
}

class AlphabetPanel extends JPanel{
	private ArrayList<Alphabet> alphabets = new ArrayList<Alphabet>();

	public void add(Alphabet b){
   		alphabets.add(b);
	}

	public void paintComponent(Graphics g){
   		super.paintComponent(g);
		for(Alphabet b : alphabets){
			g.setColor(b.getColor());
	  		g.drawString(b.getAlphabet(),b.getX(),b.getY());
   		}
	}
}


class BounceFrame extends JFrame{
	private AlphabetPanel panel;
	private JPanel buttonPanel;
	private JButton btnStart,btnClose;

	BounceFrame(){
		setSize(400,400);
   		setTitle("BounceThread");

   		panel = new AlphabetPanel();


   		buttonPanel = new JPanel();
		btnStart = new JButton("Start");
		btnClose = new JButton("Close");
		buttonPanel.add(btnStart);
		buttonPanel.add(btnClose);


		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	  	btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				addBall();
		 	}
	  	});

		btnClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
		 	}
	  	});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   	setVisible(true);
	}


	public void addBall(){
		Alphabet b = new Alphabet();
   		panel.add(b);
   		Runnable r = new AlphabetRunnable(b, panel);
   		Thread t = new Thread(r);
   		t.start();
	}
}
