package mvc.Controller;

import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import mvc.view.CardGameFrame;

import mvc.view.SummaryPanel;

public class CardGameController {
	// Encapsulation of JComponents
	private CardGameFrame Frame;
	private GameEngine Model;
	
	
	public CardGameController(CardGameFrame Frame, GameEngine model)
	   {
		// instantiation of Variables
	      this.Frame = Frame;
	      this.Model = model;
	   }
	
	// Creates a new ActionListener for any button in the toolbar
	public ActionListener newToolbarListener() {
		return new ToolbarListener(Frame, Model);
	}
	
	// Creates an ItemChangeListener for anything changed in the JComboBox
	
	// NOTE: I had some problems implementing the JComboBox
	// for this listener to have an action the player in the JComboBox needs to be reselected
	public ItemChangeListener newItemChangeListener() {
		return new ItemChangeListener(Frame, Model);
	}
	
	public ActionListener newMenuListener() {
		return null;
		
	}
	

	


	
	
	

}
