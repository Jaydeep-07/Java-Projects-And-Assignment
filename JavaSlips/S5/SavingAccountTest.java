import java.io.*;

class InsufficientFundsException extends Exception{}

class SavingAccount{
	private int accno;
	private String name;
	private float bal;
                
	private static BufferedReader br= new BufferedReader(
						new InputStreamReader(System.in));

	SavingAccount(int a,String nm,float b){
		accno=a;
		name=nm;
		bal=b;
	}

	public void withDraw() throws Exception{
		try{
			System.out.print("Enter amount to be withdrawn:");
                       	float amt=Integer.parseInt(br.readLine());
			if(bal-amt < 500)
				throw new InsufficientFundsException();
			else{
				bal=bal-amt;
				System.out.println("Amount withdrawn successfully.");
			}
		}
		catch(InsufficientFundsException ob){
			System.out.println(ob);
		}
	}
                
	public void deposit() throws Exception{
		System.out.println("Enter amount to be deposited:");
		float amt=Integer.parseInt(br.readLine());
		bal+=amt;
		System.out.println("Amount deposited successfully.");
	}

	public void viewBalance(){
		System.out.println("Balance:"+bal);
	}
}

class SavingAccountTest{
	public static void main(String ar[]) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter Account no:");
                int ano =Integer.parseInt(br.readLine());
                System.out.print("Enter Name:");
		String name=br.readLine();
		System.out.print("Enter Balance:");
		float bal=Float.parseFloat(br.readLine());
		SavingAccount sa=new SavingAccount(ano,name,bal);

		while(true){
			System.out.println("1.Deposit\n2.Withdraw\n3.Check Balance\n4.Exit");
			System.out.print("Enter your choice (1-4):");
                                                               
			int ch=Integer.parseInt(br.readLine());

			switch(ch){
			case 1: 
				sa.deposit();
				break;
			case 2:
				sa.withDraw();
                                break;
			case 3: 
				sa.viewBalance();
				break;
			case 4: 
				System.exit(0);
			}
		}
	}
}

