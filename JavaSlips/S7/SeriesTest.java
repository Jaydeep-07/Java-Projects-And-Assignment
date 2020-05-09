import series.*;
import java.io.*;

class SeriesTest{
	public static void main(String a[]) throws IOException{
		BufferedReader br =new BufferedReader(
					new InputStreamReader(System.in));

		System.out.print("Enter no:");
                int no = Integer.parseInt(br.readLine());
                
		Prime p = new Prime();
                System.out.println("Prime Series:");
		p.print_series(no);

                Square s = new Square();
                System.out.println("Square Series:");
		s.print_series(no);

	}
}
