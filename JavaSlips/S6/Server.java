import java.io.*;
import java.net.*;
class Server{
	public static void main(String argv[]) throws Exception{
		String clientSentence, capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(9005);
		
		while(true){
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(
						connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
						connectionSocket.getOutputStream());

			while(true){			
				clientSentence = inFromClient.readLine();
				if(clientSentence.equals("END")) break;
				capitalizedSentence=clientSentence.toUpperCase()+'\n';
				outToClient.writeBytes(capitalizedSentence);
			}
		}
	}
}
