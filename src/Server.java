import java.rmi.*;
import java.rmi.server.*;   

public class Server {
	public static void main (String[] argv) {
		   try {
			   MatchMaker matchMaker = new MatchMaker();
			   Naming.rebind("rmi://localhost/MatchMaker", matchMaker);

			   System.out.println("Server is ready.");
			   }catch (Exception e) {
				   System.out.println("Server failed: " + e);
				}
		   }
}
