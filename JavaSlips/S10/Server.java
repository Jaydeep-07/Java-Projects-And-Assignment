import java.io.*;
import java.net.*;

class Server{
	public static void main(String args[])
	throws Exception{
    		ServerSocket ss = new ServerSocket(5555);

    		while(true){
      			Socket s = ss.accept();

      			DataInputStream fromClient = new DataInputStream(
							s.getInputStream());
      			DataOutputStream toClient = new DataOutputStream(
							s.getOutputStream());
      			while(true){
				String fileName = fromClient.readLine();

				if(fileName.equals("No")) break;

				File f = new File(fileName);

        			if(f.exists())
          				toClient.writeBytes(fileName+" found.\n");
        			else
	  				toClient.writeBytes(fileName+" not found.\n");
      			}
    		}
  	}
}
