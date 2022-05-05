// Author: Lachlan Jacob Sexton
// Purpose: The purpose of GameEngineImpl is to implement the Game Engine Interface and implement the methods in GameEngine
// This class will be the backbone of the assignment and provide the main functions. 

package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;
// Implements the GameEngine Interface
public class GameEngineImpl implements GameEngine {	
	final int bust = 42; 
	int deckIndex = 0; 
	int CallbacksIndex = 0;
	// Creates a new ArrayList of players, the reason for a list is that is can be sorted in an order easily
	HashMap<String,Player> activePlayers = new HashMap<String,Player>(10);
	// Creates a ArrayList of PlayingCard, the reason there is two is that a list can be shuffled.
	ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
	// ArrayList of GameEngineCallbacks, this will be used to loop through all call backs
	Collection<GameEngineCallback> Callbacks = new LinkedHashSet<GameEngineCallback>();
	// Dequeue of PlayingCards that will take all of the values of the list, after being shuffled
	Deque<PlayingCard> ShuffledDeck = new ArrayDeque<PlayingCard>();
	

	
	
	
	
	// Deals card a card to a player
	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {
		// Creates reference to GameEngineImpl 
		boolean dealing = true; 
		int score = 0;
		// Uses a try catch to catch NoSuchElementException i e. when the created deck runs out of cards it creates a new deck. 
		try {	
			// Calls dealToPlayer and passes a Player, Integer, GameEngine, boolean
			
			dealToPlayer(player, score, this, dealing, delay);
			// catches exception
			}catch(NoSuchElementException e){
				// Creates a new deck
				getShuffledHalfDeck();
				dealToPlayer(player, score, this, dealing, delay);
			}
			

	}
	@Override
	// Similar code to dealPlayer however some differences 
	public void dealHouse(int delay) throws IllegalArgumentException {
		int HouseScore = 0;
		boolean dealing = true;
		// Instead of calling dealToPlayer calls dealToHouse which passes integer, GameEnigne, Boolean
		try{
			dealToHouse(HouseScore, this, dealing, delay);
		}catch(NoSuchElementException e){
			getShuffledHalfDeck();
			dealToHouse(HouseScore, this, dealing, delay);
		}
		for (Player Player: getAllPlayers()) {
			applyWinLoss(Player, HouseScore);
		
		}
		


			
	}

	@Override
	// Checks if the house or the player wins the existing round
	public void applyWinLoss(Player player, int houseResult) {
		
		
		// if house result is less then the player's result
		if(houseResult < player.getResult()){
			player.setPoints(player.getPoints() + player.getBet());
		// if players result is less then the houses result
		}else if(player.getResult() < houseResult) {
			player.setPoints(player.getPoints() - player.getBet());
		}
		
	}

	// Adds player to the activePlayers collection
	@Override
	public void addPlayer(Player player) {
		activePlayers.put(player.getPlayerId(), player);
		
		
	}

	@Override
	// returns the player with the same id as the id passed into the method
	public Player getPlayer(String id) {
		return activePlayers.get(id);
	}

	@Override
	// removes the player passed into the method from the activePlayers collection
	public boolean removePlayer(Player player) {
		return activePlayers.remove(player.getPlayerId()) != null;
	}

	@Override
	// takes the bet that a particular player wants to places and assigns it to that player
	public boolean placeBet(Player player, int bet) {
		return player.setBet(bet);
		// creates new int and assigns to the player's points
		// if the points of the player is greater then the bet then the bet can be placed

		// returns false if the points of the player is lower then the bet wanting to be placed
		
	}
	

	@Override
	// Adds a GameEngineCallback to a collection of gameEngineCallbacks
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		Callbacks.add(gameEngineCallback);
		
	}

	@Override
	// Removes a GameEngineCallback from the Call backs collections
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return Callbacks.remove(gameEngineCallback);
		
	}

	@Override
	// returns the collection of activePlayers
	public Collection<Player> getAllPlayers() {
		return activePlayers.values();
	}

	

	@Override
	// Creates a deck of cards using the PlayingCard Interface and PlayingCardImpl Class
	public Deque<PlayingCard> getShuffledHalfDeck() {
		// loops through both sets and creates a new object for each suit and value
		for (Suit s : Suit.values()) {
			for (Value v : Value.values()) {
				// adds to the deck collection the new playing card
				deck.add(new PlayingCardImpl(v,s));
		
		
	}


		}
		// Shuffles the list, then assigns all of the objects in the list into a dequeue so it can be returned
		Collections.shuffle(deck);
		ShuffledDeck.addAll(deck);
		return ShuffledDeck;
		
}


	private void dealToPlayer(Player player, int score, GameEngine Game, boolean dealing, int delay) {
	
		while(dealing){
	
			
	
			if(score + ShuffledDeck.getFirst().getScore() < bust) {	
				score = score + ShuffledDeck.getFirst().getScore();	
				for(GameEngineCallback Callbacks : Callbacks) {

					Callbacks.nextCard(player, ShuffledDeck.getFirst() , Game);
					
					
					
				
					try {
						TimeUnit.MILLISECONDS.sleep(delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			}
		
			
		else if(score + ShuffledDeck.getFirst().getScore() > bust) {
			for(GameEngineCallback Callbacks : Callbacks) {

					Callbacks.bustCard(player, ShuffledDeck.getFirst(), Game);
					Callbacks.result(player, score, Game);
					try {
						TimeUnit.MILLISECONDS.sleep(delay);
					} catch (InterruptedException e) {
					
						
						e.printStackTrace();
					}
					
					
					dealing = false; 				
			}
		}

		
			//removes the first card in the deque once it has been drawn
			ShuffledDeck.removeFirst();
			// asigns a score for the player in this round
			player.setResult(score);
			
		}
	}
	// Very similar to dealToPlayer however the call backs are different therefore this code cannot be shortened
	private void dealToHouse(int HouseScore, GameEngine Game, boolean dealing, int delay ) {
		while(dealing){
			
			
				
			if(HouseScore + ShuffledDeck.getFirst().getScore() < bust) {
				HouseScore = HouseScore + ShuffledDeck.getFirst().getScore();
				for (GameEngineCallback Callbacks : Callbacks) {
					Callbacks.nextHouseCard(ShuffledDeck.getFirst(), Game);
					
					
					try {
						TimeUnit.MILLISECONDS.sleep(delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
			// if the score  plus the next cards score is greater then the bust score
				else if(HouseScore + ShuffledDeck.getFirst().getScore() > bust) {


					for (GameEngineCallback Callbacks : Callbacks) {
					Callbacks.houseBustCard(ShuffledDeck.getFirst(), Game);
					// calls again through all the call backs the house result method
					Callbacks.houseResult(HouseScore, Game);

					try {
						TimeUnit.MILLISECONDS.sleep(delay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
					dealing = false; 
					
				}

				
			
			}
			// removes the first element of the dequeue
			ShuffledDeck.removeFirst();

		}


	
	}
	
	
	


}

