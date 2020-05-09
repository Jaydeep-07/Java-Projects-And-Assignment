import java.net.*;
import java.io.*;
import java.util.*;
public class Client{
	public static void main(String args[])
	throws Exception{
		Socket s = new Socket("localhost",4444);
		DataInputStream dis = new DataInputStream(s.getInputStream());
		System.out.println("From Server:"+dis.readLine());
	}
}

