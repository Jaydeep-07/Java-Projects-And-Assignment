import java.net.*;
import java.io.*;
import java.util.*;

public class Server{
	public static void main(String args[])
	throws Exception{
		ServerSocket ss = new ServerSocket(4444);
		while(true)
		{
			Socket s = ss.accept();
			DateOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.println(new Date());
		}
	}
}
