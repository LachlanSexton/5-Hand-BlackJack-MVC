package mvc.view;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
	// Encapsulation
	private CardGameFrame Frame;
	
	//Constructor
	public GameEngineCallbackGUI(CardGameFrame Frame){
	
		this.Frame = Frame; 
	
	}

	@Override
	// Run on new thread as GUI would not show animation otherwise
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		 new Thread()
		 {
		 @Override
		 public void run()
		 {
			// Calls methods in view 
			Frame.NextCard(player, card);
			Frame.repaint();
		 }
		 }.start();

		
		
		
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {

		 new Thread()
		 {
		 @Override
		 public void run()
		 {
			 Frame.NextCard(player, card);
			 Frame.repaint();
			 
		 }
		 }.start();

			 
			 


		
		
	}

	@Override
	// Update the summary panel with the players new result
	public void result(Player player, int result, GameEngine engine) {
		
		Frame.UpdateSummaryPanel(engine.getAllPlayers());
		
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		// No code required as it has no impact on the GUI
		
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		// No code required as it has no impact on the GUI
		
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		// Calls method in view
		Frame.ShowHouseScore(result);
		
	}
}
