
import java.rmi.*;
import java.rmi.server.*;
public class PlayerScores extends UnicastRemoteObject 
		implements PlayerScoresRemoteInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int player1Score;
	int player2Score;
	public PlayerScores(int player1Score, int player2Score) throws RemoteException{
		this.player1Score = player1Score;
		this.player2Score = player2Score;
	}
	@Override
	public int getPlayer1Score() throws RemoteException {
		return player1Score;
	}
	@Override
	public int getPlayer2Score() throws RemoteException {
		return player2Score;
	}
	@Override
	public void setPlayer1Score(int player1Score) throws RemoteException {
		this.player1Score = player1Score;
	}
	@Override
	public void setPlayer2Score(int player2Score) throws RemoteException {
		this.player2Score = player2Score;
		
	}
}
