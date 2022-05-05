package mvc.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import mvc.view.CardGameFrame;

import mvc.view.PlayerCardPanel;
import mvc.view.ToolBar;

public class ItemChangeListener implements ActionListener {
	// Encapsulation
	private GameEngine model;
	private CardGameFrame frame;
	
	// Constructor
	public ItemChangeListener(CardGameFrame Frame, GameEngine Model)
	   {
		  this.model = Model;
	      this.frame = Frame;
	     
	   }

	// Determines if the player in the JComboBox has already been dealt cards
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			// Checks the PlayerState and disables buttons
			if (frame.HasDealt(frame.GetPlayer())) {
				frame.DisableDealBet();
				frame.repaint();
			
			}else {
				
				try {	
				// Removes current panel but not from hashmap
					frame.RemovePanel();
				
					frame.SetDealTrue(frame.HasBet(frame.GetPlayer()));
					
					frame.EnableButton();
					
					frame.revalidate();
					
				
				}catch(NullPointerException c) {
					
			
				}
				
			}
		}catch(NullPointerException d) {
			
		}

		
		
	}

}
