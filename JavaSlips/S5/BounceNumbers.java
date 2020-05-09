import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
 
public class BounceNumbers{
	public static void main(String[] args){
		new BounceFrame();
	}
}

class NumberRunnable implements Runnable{
	private Number number;
	private Component component;

	public NumberRunnable(Number aNumber, Component aComponent){
   		number = aNumber;
   		component = aComponent;
	}

	public void run(){
   		while(true){
	 		number.move(component);
			component.repaint();
			try{
		 		Thread.sleep(100);
	  		}catch (InterruptedException e){}
		}
	}
}

class Number{
	private int x, y, dy=1;
	private String no;
	private Color c;

	Number(){
		x = (int)(Math.random()*400);
		y = (int)(Math.random()*400);
		no = Integer.toString((int)(Math.random()*10+1));
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

	public String getNumber(){
		return no;
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

class NumberPanel extends JPanel{
	private ArrayList<Number> numbers = new ArrayList<Number>();

	public void add(Number b){
   		numbers.add(b);
	}

	public void paintComponent(Graphics g){
   		super.paintComponent(g);
		for(Number b : numbers){
			g.setColor(b.getColor());
	  		g.drawString(b.getNumber(),b.getX(),b.getY());
   		}
	}
}


class BounceFrame extends JFrame{
	private NumberPanel panel;
	private JPanel buttonPanel;
	private JButton btnStart,btnClose;

	BounceFrame(){
		setSize(400,400);
   		setTitle("BounceThread");

   		panel = new NumberPanel();


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
		Number b = new Number();
   		panel.add(b);
   		Runnable r = new NumberRunnable(b, panel);
   		Thread t = new Thread(r);
   		t.start();
	}
}
