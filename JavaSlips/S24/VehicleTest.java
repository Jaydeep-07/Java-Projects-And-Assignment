import java.io.*;
class Vehicle{          
	private String cname;
	private float price;

	public void accept() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter Company name:");
                cname= br.readLine();
		System.out.print("Enter price : ");
		price = Float.parseFloat(br.readLine());
	}

	public void display(){
		System.out.println("Company Name : "+cname+
				"\nPrice : "+price);
	}
}

class LightMotorVehicle extends Vehicle{
	private int mileage;

	public void accept() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		super.accept();

		System.out.print("Enter mileage : ");
		mileage = Integer.parseInt(br.readLine());
	}

	public void display(){
		super.display();
		System.out.println("Mileage : "+mileage);
	}
}

class HeavyMotorVehicle extends Vehicle{
	public int capacity;

	public void accept() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		super.accept();
		System.out.print("Enter capacity in tons : ");
                capacity = Integer.parseInt(br.readLine());
	}

	public void display(){
		super.display();
		System.out.println("Capacity int tons : "+capacity);
	}
}

class VehicleTest{
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

              	System.out.print("Enter no.of vehicles:");
              	int n = Integer.parseInt(br.readLine());
		
		Vehicle v[] = new Vehicle[n];

		for(int i=0;i<n;i++){
			System.out.print("Light Motor(0)/Heavy Motor(1)? ");
			int ch = Integer.parseInt(br.readLine());

			if(ch==0) v[i] = new LightMotorVehicle();
			if(ch==1) v[i] = new HeavyMotorVehicle();

			v[i].accept();
		}

		for(int i=0;i<n;i++){
			v[i].display();
		}
	}
}
