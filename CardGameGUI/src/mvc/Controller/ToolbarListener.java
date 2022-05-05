package mvc.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.view.CardGameFrame;
import mvc.view.SummaryPanel;
import mvc.view.ToolBar;

public class ToolbarListener implements ActionListener{
	// Encapsulation
	
	private GameEngine model;
	private CardGameFrame frame;
	private int PlayerBet;
	
	// Constructor
	public ToolbarListener(CardGameFrame Frame, GameEngine Model)
	   {
		  this.model = Model;
	      this.frame = Frame;
	     
	   }
	
	// ActionListener for every button in the JToolbar
	// NOTE: I tried to implement individual listeners for each button 
	// However I kept on receiving a NullPointerException which I was unable to figure out the reason for
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Checks what the action command is equal to
		String action = e.getActionCommand();
		
		if (action.equals("Add Player")) {
		
			// Generates 3 JTextFields
			JTextField AskPlayerId = new JTextField(10);
			JTextField AskPlayerName = new JTextField(10);
			JTextField AskPlayerPoints = new JTextField(10);
			
			// Array of fields 
			Object[] fields = {
				"Player ID", AskPlayerId,
				"Player Name", AskPlayerName,
				"PlayerPoints", AskPlayerPoints
			};
			
			// JOptionPane that displays the array of fields
			JOptionPane.showConfirmDialog(null, fields, "NEW PLAYER!", JOptionPane.OK_CANCEL_OPTION);
			
			
			try {
				
				// Takes input from JOptionPane
				 String PlayerId = AskPlayerId.getText();
				 String PlayerName = AskPlayerName.getText();
				 String PlayerPointsString = AskPlayerPoints.getText();
				 
				 // String to int Conversion
				 int PlayerPoints = Integer.parseInt(PlayerPointsString);
				 int PlayerBet = Integer.parseInt(PlayerPointsString);
				 
				 // adds Player to model
				 model.addPlayer(new SimplePlayer(PlayerId, PlayerName, PlayerPoints));
				
				 // update the frame with model updates
				 frame.UpdateSummaryPanel(model.getAllPlayers());
				 frame.UpdateComboBoxAdd(PlayerId);
				 frame.UpdateStatus(null);
				 frame.UpdateStatus(model.getPlayer(PlayerId).getPlayerName() + " has been added!");
				 frame.setSelectedIndex();
				 
				 
				 // If anything in the JOptionPane nothing will be recorded/updated
			}catch(NumberFormatException a){
				JOptionPane.showMessageDialog(null, "ERROR: Incorrect format or missing field!");				
			}
		
			 
			 
			 

			 
		}
		
		if (action.equals("Bet")) {
			
		
			try {
				
				String PlayerBetString = JOptionPane.showInputDialog("Enter Player Bet: ");
			
			     // Takes input of JOptionPane
			     
			 	 this.PlayerBet = Integer.parseInt(PlayerBetString);

			 
			 	 // If the bet is satisfiable ie. Bet is less then or equal to the players points
			    
				 if(model.placeBet(frame.GetPlayer(), PlayerBet)) {
					 
					 // Updates SummaryPanel
					 frame.UpdateSummaryPanel(model.getAllPlayers());
					 frame.revalidate();
					 JOptionPane.showMessageDialog(null, "Bet has been placed!");
					 frame.setSelectedIndex();

					 
				 }else {
					 
					 // If condition isn't satified
					 JOptionPane.showMessageDialog(null, "Player Needs more points");
				 }
			 
				 
			 }catch(NumberFormatException a) {
				 JOptionPane.showMessageDialog(null, "ERROR: Number needs to be entered!");
			 }
		}

			 

		
		
		if (action.equals("Remove Player")) {
			
			// Gets player id of the combobox
			String PlayerId = frame.GetPlayer().getPlayerId();
			
			// Updates the view, Status bar with new information
			frame.UpdateStatus(null);
			frame.UpdateStatus(frame.GetPlayer().getPlayerName() + " has been removed!");

			// Calls method from model to remove player
			model.removePlayer(frame.GetPlayer());
			
			
			// Updates the view, ComboBox and SummaryPanel
			frame.UpdateComboBoxRemove(PlayerId);
			frame.UpdateSummaryPanel(model.getAllPlayers());
			frame.DisableDealBet();
			
		}

			 
		
		
		if(action.equals("Deal")) {
			// New thread needed as gui will lock up with delays
			 new Thread()
			 {
			 @Override
			 public void run()
			 {
				// Adds Cardpanel to a hashmap with key being the player
				 
				frame.NewCardPanel(frame.GetPlayer());
				model.dealPlayer(frame.GetPlayer(), 100);
				
				// Disables buttons prevents user from dealing or betting twice
				frame.DisableDealBet();
				frame.revalidate();
				
				// Deals the house if all players have bet
				 if(frame.AllPlayersDealt()) {
					 frame.DisableDealBet();
					 frame.DealHouse();
					 
				 }
			 }
			 }.start();
			 
		

			 

			
		}
		
		if (action.equals("Reset Bet")) {
			// Reset bet and update the summary panel
			frame.GetPlayer().resetBet();
			frame.UpdateSummaryPanel(model.getAllPlayers());
			frame.setSelectedIndex();
			JOptionPane.showMessageDialog(null, "Bet has been reset to 0!");
				 }
		
		
	}
	
	

}
