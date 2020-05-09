import java.io.*;

interface CreditCard{
	void viewCreditAmount();
	void increaseLimit() throws IOException;
	void useCard() throws IOException;
	void payCard() throws IOException;
}

class SliverCardCustomer implements CreditCard{
	private String name,card_no;
	double creditAmount,creaditLimit;
	static int cnt;

	BufferedReader br=new BufferedReader(
				new BufferedReader(
					new InputStreamReader(System.in)));

        public SliverCardCustomer() throws IOException{
		creditAmount=0;
		creaditLimit=50000;
		System.out.print("Enter the name : ");
		name=br.readLine();
                System.out.print("Enter the card number :");
                card_no=br.readLine();
	}

	public void viewCreditAmount(){
		System.out.println("Your amount is : "+creditAmount);
	}

	public void payCard()throws IOException{
		System.out.print("Enter amount to be pay");
		double pay=Double.parseDouble(br.readLine()) ;
		creditAmount=creditAmount+pay;
		System.out.println("Balance is paid");
	}

	public void useCard()throws IOException{
		System.out.print("Enter amount : ");
		double amount=Double.parseDouble(br.readLine());
		if(amount<creditAmount){
			creditAmount=creditAmount-amount;
                        viewCreditAmount();
                        System.out.println("Thanks for using the service") ;
		}
		else System.out.println("Error!");
	}

	public void increaseLimit()throws IOException{
		cnt++;
		if(cnt<=3)
		{
                	System.out.print("Enter amount limit to increase : ");
                	double amt=Double.parseDouble(br.readLine());
                        if(amt<=2500){
				creaditLimit=creaditLimit+amt;
				System.out.println("Amount limit to increse Successfully : ");
			}
			System.out.println("You can't increase creadit amount more than 2500 at a time ");
		}
		else
			System.out.println("You can't increse limit more than 3 times ");
	}
}

class GoldCardCustomer extends SliverCardCustomer{
	static int cnt;

	public GoldCardCustomer() throws IOException{}

	public void increaseLimit()throws IOException{
		cnt++;
		if(cnt<=3){
                	System.out.print("Enter amount limit to increse : ");
                	double amt=Double.parseDouble(br.readLine());
                	if(amt<=5000){
                        	creaditLimit=creaditLimit+amt;
                        	System.out.println("Amount limit to increse Successfully : ");
                       	}
                     	System.out.println("You can't increse creadit amount more than 2500 at a time ");

		}
		else
        		System.out.println("You can't increse limit more than 3 times ");
	}
}
class CardTest{
	public static void main(String args[])throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the info for silver card holder : ");
		SliverCardCustomer sc=new SliverCardCustomer();

		System.out.println("Enter the info for Gold card holder : ");
		GoldCardCustomer gc=new GoldCardCustomer();

		while(true){
			System.out.print("1.Use silver card \n"+
				"2.Pay credit for Silver card\n"+
				"3.Increse limit for silver card\n"+
				"4.Use Gold card\n"+
				"5.Pay credit for Gold card\n"+
				"6.Increase limit for Gold card\n"+
				"7.Exit\n"+
				"Enter your choice (1-7): ");
               		int ch=Integer.parseInt(br.readLine());

			switch(ch){
			case 1: 
				sc.useCard();
				break;
			case 2:
				sc.payCard();
				break;
			case 3:
				sc.increaseLimit();
				break;
			case 4:
				gc.useCard();
				break;
			case 5:
				gc.payCard();
				break;
			case 6:
				gc.increaseLimit();
				break;
			case 7:
				System.exit(0);
			}
		}
	}
}
