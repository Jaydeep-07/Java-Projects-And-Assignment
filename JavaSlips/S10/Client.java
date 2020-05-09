import java.io.*;
import java.net.*;
class ClientList{
	public static void main(String args[])
	throws Exception{
    		Socket s = new Socket("localhost",5555);
    
    		BufferedReader br = new BufferedReader(
			    		new InputStreamReader(System.in));

		DataInputStream fromServer = new DataInputStream(s.getInputStream());

    		DataOutputStream toServer = new DataOutputStream(s.getOutputStream());

    		while(true){
      			System.out.print("Enter file name (No to stop):");
      			String fileName = br.readLine();

      			toServer.writeBytes(fileName+"\n");
     
      			if(fileName.equals("No")) break;
   
      			System.out.println(fromServer.readLine());
    		}
  	}
}
