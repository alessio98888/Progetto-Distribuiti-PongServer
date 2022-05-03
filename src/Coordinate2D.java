import java.rmi.*;
import java.rmi.server.*;
public class Coordinate2D extends UnicastRemoteObject 
		implements Coordinate2DRemoteInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	public Coordinate2D(int x, int y) throws RemoteException{
		this.x = x;
		this.y = y;
	}

	public int getX() throws RemoteException{
		return x;
	}
	public void setX(int x) throws RemoteException{
		this.x = x;
	}
	public int getY() throws RemoteException{
		return y;
	}
	public void setY(int y) throws RemoteException{
		this.y = y;
	}
}
