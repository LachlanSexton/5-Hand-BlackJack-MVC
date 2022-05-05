package view;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
// Implements the GameEnigneCallback interface
{
   public static final Logger logger = Logger.getLogger(GameEngineCallbackImpl.class.getName());
   
   // utility method to set output level of logging handlers
   public static Logger setAllHandlers(Level level, Logger logger, boolean recursive)
   {
      // end recursion?
      if (logger != null)
      {
         logger.setLevel(level.INFO);
         for (Handler handler : logger.getHandlers())
            handler.setLevel(level);
         // recursion
         setAllHandlers(level, logger.getParent(), recursive);
      }
      return logger;
   }

   public GameEngineCallbackImpl()
   {
      // NOTE can also set the console to FINE in %JRE_HOME%\lib\logging.properties
      setAllHandlers(Level.FINE, logger, true);
   }

   @Override
   // Prints the next card that is dealt to the player
   public void nextCard(Player player, PlayingCard card, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
	   // takes the player's name and calls the toString method in the PlayingCardImpl class
      logger.log(Level.INFO, "Card Dealt to " + player.getPlayerName() + " .. " + card.toString());
     
   }

   @Override
   // Prints the result of the player against the house
   public void result(Player player, int result, GameEngine engine)
   {
      // final results logged at Level.INFO
	  // Gets the players name and prints the result that is passed in as an int
      logger.log(Level.INFO, player.getPlayerName() + ", final result = " + result);
   }

@Override
// Prints the card that the player would of busted on
public void bustCard(Player player, PlayingCard card, GameEngine engine) {
	// TODO Auto-generated method stub
	// prints the players name and the PlayingCardImpl toString method of the busted card
	logger.log(Level.INFO, "Card Dealt to " + player.getPlayerName() + " .. " + card.toString() + " ... YOU BUSTED!" );
}

@Override
// Prints the card that is dealt to the house
public void nextHouseCard(PlayingCard card, GameEngine engine) {
	// TODO Auto-generated method stub
	// Calls the card toString method of the card passed in
	logger.log(Level.INFO,   " Card Dealt to House .. " + card.toString());
}

@Override
// Prints the bust card that would have been dealt to the house
public void houseBustCard(PlayingCard card, GameEngine engine) {
	// TODO Auto-generated method stub
	// Calls the card toString method of the card that would have busted
	logger.log(Level.FINE,   " Card Dealt to House .. " + card.toString() + " ... HOUSE BUSTED!" );
}

@Override
// Prints the result of the house as well as the players in ascending order
public void houseResult(int result, GameEngine engine) {
	// prints the result of the house using HouseScore as the int passed in
	logger.log(Level.INFO,  " House, final result = " + result);
	logger.log(Level.INFO,  " Final Player Results");
	// Creates a loop for all players and prints calls the toString method to print them into the logger
	
	for(Player printPlayer : engine.getAllPlayers()) {
		engine.applyWinLoss(printPlayer, result);
		logger.log(Level.INFO,  printPlayer.toString());
	}

	}



	
}

   // TODO complete the rest of this interface


