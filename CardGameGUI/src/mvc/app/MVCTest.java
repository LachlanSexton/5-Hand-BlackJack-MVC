package mvc.app;




import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import mvc.view.CardGameFrame;
import mvc.view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

// AWT/SWING MVCTest made by Lachlan Sexton
// Demonstrates Model, View, Controller Design for a CardGame consisting of 2 projects
// One of the projects classes acts as the model
// The view is the main JFrame CardGameFrame
// The Controller consists of a main controller that can create multiple listeners

public class MVCTest


{
   public static void main(String[] args)
   {
	   // Reference to the model
	   
	   final GameEngine model = new GameEngineImpl();  
	   
	   
	   
	   model.getShuffledHalfDeck();
	   CardGameFrame Frame = new CardGameFrame();
	   
	   
	   // Populates the frame with JComponent's
	   Frame.populate(model);
	   
	   // Adding GameEngineCallbacks to a collection
	   model.addGameEngineCallback(new GameEngineCallbackGUI(Frame));
	   model.addGameEngineCallback(new GameEngineCallbackImpl());

       
       
       
      
        

   
}
   }
