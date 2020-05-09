import java.io.*;

class Employee{
	private int id;
	private String name,dept;
	private float sal;

	public Employee(){
		id = 0;
		name = "";
		dept = "";
		sal = 0.0f;
	}

	public Employee(int i, String n, String d, float s){
		id = i;
		name = n;
		dept = d;
		sal = s;
	}

	public void accept()
	throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter id:");
		id = Integer.parseInt(br.readLine());
		System.out.print("Enter name:");
		name = br.readLine();
		System.out.print("Enter department:");
		dept = br.readLine();
		System.out.print("Enter salary:");
		sal = Float.parseFloat(	br.readLine());
	}

	public void display(){
		System.out.println(
		"ID:"+id+
		"\nName:"+name+	
		"\nDepartment:"+dept+
		"\nSalary:"+sal);
	}

	public float getSal(){
		return sal;
	}
}

class Manager extends Employee{
	private float bonus;

	public Manager(){
		super();
		bonus = 0.0f;
	}

	public Manager(int i, String n, String d, float s, float b){
		super(i,n,d,s);
		bonus = b;
	}

	public void accept()
	throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		super.accept();
		System.out.print("Enter bonus:");
		bonus = Float.parseFloat(br.readLine());
	}

	public void display(){
		super.display();
		System.out.println("Bonus:"+bonus);
	}

	public float getBonus(){
		return bonus;
	}
}

class ManagerTest{
	public static void main(String args[])
	throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter no.of managers:");

		int n = Integer.parseInt(br.readLine());

		Manager m[] = new Manager[n];

		for(int i=0;i<n;i++){
			m[i]=new Manager();
			m[i].accept();
		}

		float max=-9999;
		int max_pos=-1;

		for(int i=0;i<n;i++){
			float tot=m[i].getSal()+m[i].getBonus();
			if(tot>max){
				max=tot;
				max_pos=i;
			}
		}

		System.out.println("Manager with maximum salary:");

		m[max_pos].display();
	}
}
