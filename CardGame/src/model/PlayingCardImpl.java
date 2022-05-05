//Author: Lachlan Jacob Sexton
//Purpose: The purpose of PlayingCardImpl is to implement the inferface of playingCard
// This class will be used to create a new playing card, which will be assigned a suit, value and will be able to be compared to other existing cards

package model;

import model.interfaces.PlayingCard;
// Implements the PlayingCard interface
public class PlayingCardImpl implements PlayingCard{
	
	Suit CardSuit; 
	Value CardValue;
	// creates new int "Score"
	int Score; 

	// Assigns the values that are passed in into this method, as the object variables
	public PlayingCardImpl(Value v, Suit s) {
		// sets suit of this card
		setSuit(s);
		// sets value of this card
		setValue(v);
		// sets score of this card
		setScore(v);
	}

	@Override
	// returns this card suit
	public Suit getSuit() {
		return CardSuit;
	}

	@Override
	// returns this cards value
	public Value getValue() {
		// TODO Auto-generated method stub
		return CardValue; 
	}

	@Override
	// Returns this cards score value
	public int getScore() {
		// TODO Auto-generated method stub
		return Score;
	}
	// Sets the card suit for the suit that is passed in
	private void setSuit(Suit suit) {
		this.CardSuit = suit;
	}
	// Sets the value of this card from the value that is passed in
	private void setValue(Value value) {
		this.CardValue = value; 
		
	
	
	}
	
	// Sets the score of the value using if statements
	private void setScore(Value value) {
		// Checks what the value is using the enums then assigns it to its correct score
		if (value == Value.EIGHT) {
			Score = 8;
			
		}else if(value == Value.NINE){
			Score = 9;
			// All uses the same value of 10
		}else if (value == Value.JACK || value == Value.QUEEN || value == Value.KING || value == Value.TEN) {
			Score = 10;
		}else if (value == Value.ACE) {
			Score = 11; 
		}
	
	}
	


	@Override
	// Checks if the card passed in is equal to "this" card
	public boolean equals(PlayingCard card) {
		if ((card.getSuit() == this.CardSuit) && (card.getValue() == this.CardValue)) {
			// if the passed in cards suit and value are equal to the suit and value of "this" card then return true
			return true;
		}
		// Otherwise return false
		return false;
	}
	
	// Presents this cards variables into a string that is used in GameEngineCallbackImpl
	public String toString() {
		return "Suit: " + CardSuit + ", Value: " + CardValue + ", Score: " + Score;
		
	}
	

}
