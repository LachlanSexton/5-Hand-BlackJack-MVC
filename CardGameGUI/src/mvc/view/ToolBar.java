package mvc.view;
import model.interfaces.*;
import mvc.Controller.CardGameController;
import mvc.Controller.ToolbarListener;
import model.GameEngineImpl;
import model.SimplePlayer;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;




public class ToolBar extends JToolBar {
	
	// Encapsulation
	private JButton Deal = new JButton("Deal");
	private JButton Bet = new JButton("Bet");
	private JButton AddPlayer = new JButton("Add Player");
	private JButton RemovePlayer = new JButton("Remove Player");
	private JButton ResetBet = new JButton("Reset Bet");
	private JComboBox combo = new JComboBox();
	private JPanel Panel = new JPanel();
	private StatusBar Status;
	private GameEngine model;

	private CardGameFrame frame;
	
	// Constructor 
	public ToolBar(GameEngine model, CardGameFrame frame) {
		setVisible(true);
		this.model = model;
		this.frame = frame;

	
	}
	// Populates Toolbar with JComponents
	public void populate() {
		
		add(Bet);
		add(ResetBet);
		add(Deal);
		add(combo);
		add(AddPlayer);
		add(RemovePlayer);
		this.Status = new StatusBar();
		Status.Update("Add a new Player!");
		add(Status);
		repaint();

	}
	// Adds actionlisteners to all JComponents in Toolbar besides statusbar
	public void AddActionListeners() {
		AddPlayer.addActionListener(frame.MakeNewToolBarListener());
		Bet.addActionListener(frame.MakeNewToolBarListener());
		RemovePlayer.addActionListener(frame.MakeNewToolBarListener());
		combo.addActionListener(frame.MakeNewItemChangeListener());
		Deal.addActionListener(frame.MakeNewToolBarListener());
		ResetBet.addActionListener(frame.MakeNewToolBarListener());
		Deal.setEnabled(false);
		Bet.setEnabled(false);
		RemovePlayer.setEnabled(false);
		ResetBet.setEnabled(false);
	}


	 


	 // Adds the player's id to the combo box
	 public void UpdateComboBoxAdd(String PlayerId) {
		
		 combo.addItem(PlayerId);
		
	 }
	
	 // Gets the string in the JCombobox and passes to the model
	 public Player GetPlayer() {
	
		 String PlayerId = combo.getSelectedItem().toString();
		
		 return model.getPlayer(PlayerId);
		 
	 }
	 
	 // If the player has a bet greater then 0 deal will be enabled
	 public void SetDealVisible(boolean bet) {
		 if (bet) {
			 Deal.setEnabled(true);
		 }else {
			 Deal.setEnabled(false);
		 }
	 }
	 
	 // Enable the buttons
	 public void SetVisible() {
		
		 ResetBet.setEnabled(true);
		 Bet.setEnabled(true);
		 RemovePlayer.setEnabled(true);
		
	 }
	 

	 // Removes the id from the combobox but not from the game
	 public void UpdateComboBoxRemove(String playerId) {
		
		 combo.removeItem(playerId);
		
	 }


// Set all buttons to not enabled
	public void SetFalse() {
		 Bet.setEnabled(false);
		 RemovePlayer.setEnabled(false);
		 Deal.setEnabled(false);
		 ResetBet.setEnabled(false);
	}
	
	// Update the status bar with according to the string passed in
	public void UpdateStatus(String message) {
		
		Status.Update(message);
	
	}
	
	// Was having an error whenever an event took place the Combobox was deselecting the current item
	// Therefore the buttons weren't been enabled
	// To fix this after each action the current item is reselected
	public void SetSelectedIndex() {
		
		combo.setSelectedIndex(GetSelectedIndex());
		
	}
	
	// reselects item in JCombobox
	public int GetSelectedIndex() {
		
		return combo.getSelectedIndex();
		
	}
	 
		
	}
	
	




	

	
