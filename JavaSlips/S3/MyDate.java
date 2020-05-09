import java.io.*;

class InvalidDateException extends Exception{}

class MyDate{

	int day,month,year;

	MyDate(){
		day=month=year=0;
	}
	MyDate(int d,int m,int y){
		try{	
			if(m <= 0 || m >=13)
				throw new InvalidDateException();

			month = m;
			switch(m)
			{
				case 1: 
				case 3: 
				case 5: 
				case 7: 
				case 8: 
				case 10: 
				case 12:
					if(d <=0 || d >=32)
						throw new InvalidDateException();
				
					day = d;	
					break;
				case 4: 
				case 6: 
				case 9: 
				case 11:
					if(d<=0 || d >=31)
						throw new InvalidDateException();
					day = d;					
					break;
				case 2 : 
					if(day <=0 || day >=29)
						throw new InvalidDateException();
					day = d;
					break;
			}
			year = y;
		}
		catch(InvalidDateException e){
			System.out.println(e);		
		}
	}

	public String toString(){
		return day+"/"+month+"/"+year;
	}
}

class DateTest{
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(
					new InputStreamReader(
						System.in));			
		System.out.print("Enter date: dd mm yyyy");
		MyDate d = new MyDate(Integer.parseInt(br.readLine()),
					Integer.parseInt(br.readLine()),
					Integer.parseInt(br.readLine()));

		System.out.println(d);
	}
}
