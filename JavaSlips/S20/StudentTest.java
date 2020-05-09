import SY.*;
import TY.*;
import java.io.*;

class Student{
	private int rollNo;
	private String name;
	private SYMarks sy;
	private TYMarks ty;
	private String grade;

	public Student(int rno, String n, 
		int m, int e, int c, int t, int p){
		rollNo = rno;
		name = n;
		sy = new SYMarks(m,e,c);
		ty = new TYMarks(t,p);

		int tot = sy.getCompTotal()+
			ty.getTheory()+ty.getPractical();

		float per = tot/12.0f;

		if(per>=70) grade="A";
		else if(per>=60) grade="B";
		else if(per>=50) grade="C";
		else if(per>=40) grade="Pass";
		else grade="Fail";
	}

	public String toString(){
		return "Roll No:"+rollNo+
			"\nName:"+name+
			"\nSY Marks:\n"+sy+
			"\nTY Marks:\n"+ty+
			"\nGrade:"+grade;
	}
}

class StudentTest{
	public static void main(String args[])
	throws Exception{
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
					System.in));

		System.out.print("Enter no.of students:");
		int n = Integer.parseInt(br.readLine());

		Student s[] = new Student[n];

		for(int i=0;i<n;i++){
			System.out.print("Enter roll no:");
			int rno=Integer.parseInt(
					br.readLine());
			System.out.print("Enter name:");
			String name = br.readLine();
			System.out.print(
			"Enter maths total:");
			int m=Integer.parseInt(
				br.readLine());
			System.out.print(
			"Enter electronics total:");
			int e=Integer.parseInt(
				br.readLine());
			System.out.print(
			"Enter computer total:");
			int c=Integer.parseInt(
				br.readLine());
			System.out.print(
			"Enter theory total:");
			int t=Integer.parseInt(
				br.readLine());
			System.out.print(
			"Enter practical total:");
			int p=Integer.parseInt(
				br.readLine());
			s[i] = new Student(rno,name,
				m,e,c,t,p);
		}

		for(int i=0;i<n;i++)
			System.out.println(s[i]);
	}
}







		





		




















		



		








