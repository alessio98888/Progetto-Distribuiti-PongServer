
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MatchMaker extends UnicastRemoteObject 
		implements MatchMakerRemoteInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, String> playersMatching;

	public MatchMaker() throws RemoteException {
		playersMatching = new LinkedHashMap<>();
	}

	@Override
	public String getGameId(String requestingPlayerId) throws RemoteException {
		// TODO Auto-generated method stub
		if(playersMatching.containsKey(requestingPlayerId)) {
			String gameId = playersMatching.get(requestingPlayerId); 
			if(gameId != null) {
				return gameId;
			}
		} else {
			if(playersMatching.isEmpty()) {
				playersMatching.put(requestingPlayerId, null);
			} else {
				Iterator<Entry<String, String>> iterator = playersMatching.entrySet().iterator();
				while (iterator.hasNext()) { // search the first player that has not yet found a match 
					Entry<String, String> entry = iterator.next();
					String entryGameId = entry.getValue();
					if(entryGameId == null) { // not already matched
						String gameId = generateGameId(requestingPlayerId, entry.getKey());
						playersMatching.put(entry.getKey(), gameId);
						return gameId;
					}
				}

				playersMatching.put(requestingPlayerId, null);
			}
		}
		
		return null;
	}

	public boolean deletePlayerId(String playerId) {
		String res = playersMatching.remove(playerId);
		if(res == null)
			return false;
		return true;
	}

	private String generateGameId(String requestingPlayer1, String requestingPlayer2) {
		String gameId = Integer.toString(requestingPlayer1.hashCode()) + 
				Integer.toString(requestingPlayer2.hashCode());
	    try {

	    	System.out.println("CREATING GAME WITH ID " + gameId);
	    	System.out.println("PLAYER1: " + requestingPlayer1);
	    	System.out.println("PLAYER2: " + requestingPlayer2);
			Coordinate2D player1 = new Coordinate2D(0,0);
			Coordinate2D player2 = new Coordinate2D(0,0);
			Coordinate2D ball = new Coordinate2D(0,0);
			PlayerScores playerScores = new PlayerScores(0,0);
			Naming.rebind("rmi://localhost/"+gameId+"/"+requestingPlayer1, player1);
			Naming.rebind("rmi://localhost/"+gameId+"/"+requestingPlayer2, player2);
			Naming.rebind("rmi://localhost/"+gameId+"/"+requestingPlayer1+"/ball", ball);
			Naming.rebind("rmi://localhost/"+gameId+"/playerScores", playerScores);
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
		return gameId;
	}

}
