import java.io.*;
import java.net.*;
class Client{
	public static void main(String argv[]) throws Exception{
		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(
						new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", 9005);
		DataOutputStream outToServer = new DataOutputStream(
						clientSocket.getOutputStream());

		BufferedReader inFromServer = new BufferedReader(
						new InputStreamReader(
						clientSocket.getInputStream()));

		System.out.println("Enter text (END to stop)");
		while(true){
			sentence = inFromUser.readLine();
			outToServer.writeBytes(sentence + '\n');
			if(sentence.equals("END")) break;
			modifiedSentence = inFromServer.readLine();
			System.out.println(modifiedSentence);
		}
	}
}
