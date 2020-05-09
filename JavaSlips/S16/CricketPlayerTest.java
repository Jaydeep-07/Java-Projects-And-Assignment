import java.io.*;

class CricketPlayer{
	private String name;
	private int noOfInngs,notOut,totalRuns;
	private float batAvg;

	public CricketPlayer(String n, 
		int i, int no, int r){
		name = n;
		noOfInngs = i;
		notOut = no;
		totalRuns = r;
	}

	public String toString(){
		return name+"\t"+
			noOfInngs+"\t"+
			notOut+"\t"+
			totalRuns+"\t"+batAvg;
	}

	public int getRuns(){
		return totalRuns;
	}

	public int getInngs(){
		return noOfInngs;
	}

	public float getAvg(){
		return batAvg;
	}

	public void setAvg(float b){
		batAvg = b;
	}
}

class CricketPlayerTest{
	public static void calcAvg(CricketPlayer q[]){
		int n = q.length;
		
		for(int i=0;i<n;i++){
			float t = 
				(float)q[i].getRuns()/
				q[i].getInngs();
			q[i].setAvg(t);
		}
	}

	public static void sortPlayers(
				CricketPlayer q[]){
		int n = q.length;

		for(int i=0;i<n-1;i++){
			for(int j=0;j<n-1-i;j++){
				if(q[j].getAvg()<
				q[j+1].getAvg()){
				CricketPlayer t=q[j];
				q[j] = q[j+1];
				q[j+1] = t;
				}
			}
		}
	}

	public static void main(String args[])
	throws Exception{
		BufferedReader br =
			new BufferedReader(
				new InputStreamReader(
					System.in));

		System.out.print(
			"Enter no.of players:");
		int n = Integer.parseInt(
				br.readLine());

		CricketPlayer cp[] = 
			new CricketPlayer[n];

		for(int i=0;i<n;i++){
			System.out.print(
			"Enter name:");
			String s = br.readLine();
			System.out.print(
			"Enter no.of innings:");
			int ings = Integer.parseInt(
				br.readLine());
			System.out.print(
			"Enter times not out:");
			int no = Integer.parseInt(
				br.readLine());
			System.out.print(
			"Enter total runs:");
			int r = Integer.parseInt(
				br.readLine());
			cp[i] = new CricketPlayer(
				s,ings,no,r);
		}

		calcAvg(cp);

		sortPlayers(cp);

		for(int i=0;i<n;i++)
			System.out.println(cp[i]);
	}
}
	 








 		










 














	














	


	



