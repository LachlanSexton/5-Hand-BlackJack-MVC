// Author: Lachlan Sexton
// Purpose: This class implements the Player interface
// This class will be used to create a player object, these objects can be placed in a collection and hold multiple variables
// It will also be able to be compared to other players and the house

package model;

import model.interfaces.Player;
// Implements the Player interface
public class SimplePlayer implements Player{
	// Creates 2 strings and 5 new ints
	private String id, playerName; 
	private int points, result;
	private int bet = 0;
	
	// Constructor method that allows for new Player objects to be created
	public SimplePlayer(String id, String playerName, int initialPoints){
		// Sets the player name
		setPlayerName(playerName); 
		// Sets the players id
		this.id = id;
		// Sets the players initialPoints
		setPoints(initialPoints);
		
	}

	@Override
	// Returns this players name
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return this.playerName;
	}



	@Override
	// Returns the points of this player
	public int getPoints() {
		// TODO Auto-generated method stub
		return this.points;
	}



	@Override
	// Returns the player id String
	public String getPlayerId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	// If the players points are greater then the bet wanting to be placed then true is returned
	public boolean setBet(int bet) { 
		if(bet >= 1 && bet <= points) {
			this.bet = bet;
			return true;
		}else {
			return false;
		}
			
		}
		
	

	@Override
	// returns the bet wanting to be placed by the player
	public int getBet() {
		// TODO Auto-generated method stub
		return bet;
	}

	@Override
	// resets the bet value of the player to 0
	public void resetBet() {
		this.bet = 0; 
		
	}

	@Override
	// returns the result of player
	// result of all the values of the cards dealt 
	public int getResult() {
		return result;
	}

	@Override
	// Sets the result of this player
	public void setResult(int result) {
		this.result = result;
		// TODO Auto-generated method stub
		
	}

	@Override
	// To see if this player is equal to the player passed as a param
	public boolean equals(Player player) {
		// if this player equals the id of the player passed
		if (player.getPlayerId() == this.id) {
			return true;
		}
		return false;
	}

	@Override
	// Sorts the collection of players in ascending order
	public int compareTo(Player player) {
		// Creates a string and sets it equal to the player id of the player that is passed in 
		String compareIdString = ((Player)player).getPlayerId();
		// Turns the String into an Int
		int compareId = Integer.parseInt(compareIdString);
		// Turns the string of this id into an int, 
		int thisId = Integer.parseInt(this.id);
		// returns in ascending order
		return thisId-compareId;
			
		
		
	}
	// Checks if the Player passed in is equal to the "this" player
	public boolean equals(Object player) {
		return false;

	}
	// returns the hash code of this player, which is equal to the integer of the player id 
	public int hashCode() {
		return Integer.parseInt(getPlayerId()); 
	}
	// Returns a string format of this player, so it can be logged in the GameEngineCallbackImpl class
	public String toString() {
		
		return "Player: " + this.id + ", name =" + this.playerName + ", bet= " + this.getBet() + ", points= " + this.getPoints() + ", RESULT .. " + this.result;
		
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		
	}

	@Override
	public void setPoints(int points) {
		this.points = points; 
		
	}
	

}
