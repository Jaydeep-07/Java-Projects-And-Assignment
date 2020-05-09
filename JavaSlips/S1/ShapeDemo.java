import java.io.*;

abstract class Shape{
	protected double s1,s2;

	public Shape(double s1, double s2){
		this.s1 = s1;
		this.s2 = s2;
	}

	public abstract double calcArea();
	public abstract double calcVolume();
}

class Sphere extends Shape{

	public Sphere(double radius){
		super(radius,0);
	}

	public double calcArea(){
		return 4*Math.PI*s1*s1;
	}

	public double calcVolume(){
		return 4*Math.PI*Math.pow(s1,3)/3;
	}
}

class Cone extends Shape{

	public Cone(double radius, double height){
		super(radius,height);
	}

	public double calcArea(){
		return Math.PI*s1*(s1+
			Math.sqrt(s1*s1+s2*s2));
	}

	public double calcVolume(){
		return Math.PI*s1*s1*s2/3;
	}
}

class Cylinder extends Shape{
	
	public Cylinder(double radius, double height){
		super(radius,height);
	}

	public double calcArea(){
		return 2*Math.PI*s1*(s1+s2);
	}

	public double calcVolume(){
		return Math.PI*s1*s1*s2;
	}
}

class ShapeDemo{
	public static void main(String args[])
	throws Exception{
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
					System.in));
		Shape s = null;

		double r=0,h=0,l=0,b=0;

		while(true){
			System.out.print("1.Sphere"+
			"\n2.Cone"+
			"\n3.Cylinder"+
			"\n4.Exit"+
			"\nEnter your choice (1-4):");

			int ch = Integer.parseInt(br.readLine());

			switch(ch){
			case 1:
				System.out.print("Enter sphere radius:");
				r=Double.parseDouble(br.readLine());
				s = new Sphere(r);
				break;
			case 2:
				System.out.print("Enter cone radius:");
				r=Double.parseDouble(br.readLine());
				System.out.print("Enter cone height:");
				h=Double.parseDouble(br.readLine());
				s = new Cone(r,h);
				break;
			case 3:
				System.out.print("Enter cylinder radius:");
				r=Double.parseDouble(br.readLine());
				System.out.print("Enter cylinder height:");
				h=Double.parseDouble(br.readLine());
				s = new Cylinder(r,h);
				break;
			case 4:
				System.exit(0);
			}
			System.out.println("Area="+s.calcArea()+
					"\nVolume="+s.calcVolume());
		}
	}
}
