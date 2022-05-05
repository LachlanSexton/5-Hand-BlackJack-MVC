package mvc.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import model.interfaces.*;
import mvc.Controller.CardGameController;
import mvc.model.PlayerState;
import model.GameEngineImpl;
import model.interfaces.*;

public class CardGameFrame extends JFrame {
	
	// Encapsulation
	private SummaryPanel SummaryPannel;
	private ToolBar Toolbar;
	private CardGameController Controller; 
	private PlayerState State;
	private GameEngine Model; 
	private PlayerCardPanel CardPanel;

	

	// Hashmap of mulitple player panels
	HashMap<Player,PlayerCardPanel> PlayerPanel = new HashMap<Player,PlayerCardPanel>(10);
	
	// The default green panel
	private PlayerCardPanel DefaultCardPanel = new PlayerCardPanel(null);
	
	
	// Constructor
		public CardGameFrame(){
			
			// Name Displayed at the top of JFrame
		super("CardGame");
		
		// Design of JFrame itself
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Open size
		setSize(1000,800);
		
		getContentPane().setBackground(new Color(200,200,200));
		
		// Setting a minimum size <= screen size
		setMinimumSize(new Dimension(800, 500));

		
		setVisible(true);
		
	}  
		
		
	// Populate the JFrame with JComponents
	public void populate(GameEngine model) {
		
		this.Model = model;
		this.State = new PlayerState(model, this);
		this.Controller = new CardGameController(this, model);
		this.Toolbar = new ToolBar(model, this);

		// Populate JToolbar with more JComponents
		Toolbar.populate();
		
		// Adds Actionlisteners to JComponents in Toolbar
		Toolbar.AddActionListeners();
		this.SummaryPannel = new SummaryPanel();
		setLayout(new BorderLayout());
		
		// Add Jcomponent to JFrame and set position inside frame
		add(DefaultCardPanel, BorderLayout.CENTER);
		add(SummaryPannel, BorderLayout.SOUTH);
		add(Toolbar, BorderLayout.NORTH);
		revalidate();


	}
	
	
	
	public void UpdateComboBoxAdd(String PlayerId) {
		
		Toolbar.UpdateComboBoxAdd(PlayerId);
	}
	
	
	public void UpdateComboBoxRemove(String playerId) {
		
		Toolbar.UpdateComboBoxRemove(playerId);
		
	}
	
	
	
	public void UpdateSummaryPanel(Collection<Player> ActivePlayers) {
		
		SummaryPannel.UpdateSummaryPanel(ActivePlayers);
	
	}
	
	
	
	
	public void SetDealTrue(boolean bet){
	
		Toolbar.SetDealVisible(bet);
	
	}
	
	
	

	
	public void EnableButton() {
	
		if (GetPlayer() != null) {
		
			Toolbar.SetVisible();
		
		}else {
	
			Toolbar.SetFalse();
	
		}
	}
	
	
	
	// Passes method to toolbar
	public Player GetPlayer() {
	
		return Toolbar.GetPlayer();
	
	}
	
	
	// Updates view according to model
	public boolean HasBet(Player Player) {
	
		return State.HasBet(Player);
		
	}
	
	
	
	public boolean HasDealt(Player Player) {
		return State.HasDealt(Player);
	}
	
	
	
	public boolean AllPlayersDealt() {
		
		return State.PlayersHaveBet();
		 
	}
	
	
	


	
	
	// Needs to revalidate because the controller is repainting
	public void NextCard(Player Player, PlayingCard Card) {
		// Adds the card to the 
		CardPanel.AddCard(Card);
		
		CardPanel.revalidate();

	}

	
	// Adds to the existing panel
	public void NewCardPanel(Player Player) {
		
		
		// removes panel been shown BUT not from hashmap
		remove(DefaultCardPanel);
	
		
		// Sets this Cardpanel 
		this.CardPanel = new PlayerCardPanel(Player);
		add(CardPanel);
		revalidate();
		
		
		Toolbar.SetFalse();
		PlayerPanel.put(Player, CardPanel);
	}
	

	
	// The panel for that player is shown
	public PlayerCardPanel GetPlayerPanel(Player Player) {
		return PlayerPanel.get(Player);
	}
	
	public void UpdatePanel(PlayerCardPanel Panel) {
		remove(this.CardPanel);

		this.CardPanel = Panel;
		add(Panel);
	}
	
	
	// Shows the defualt green panel
	public void RemovePanel() {
		if (CardPanel != null) {
			remove(this.CardPanel);
		}
		this.CardPanel = DefaultCardPanel;
		add(CardPanel);
		repaint();
	}	
		
		
	
	
	public void DisableDealBet() {
		Toolbar.SetFalse();
	}
	
	
	// Instead of the GameEngineCallBackGUI calling the methods from the model
	// The methods are done here to reduce coupling
	public void DealHouse() {
		JOptionPane.showMessageDialog(null, "House Ready");
		
		// deals house
		Model.dealHouse(10);
		remove(CardPanel);
		
		// Presents user with options
		int PlayAgain = JOptionPane.showConfirmDialog(this,"Would you like to play again?", "Game Finished",
	               JOptionPane.YES_NO_OPTION,
	               JOptionPane.QUESTION_MESSAGE);
		
		// If Yes on the option pane is clicked
		if (PlayAgain == JOptionPane.YES_OPTION) {
			RemovePanel();
			
			
			for (Player Player: Model.getAllPlayers()) {
			
				// removes all player panels
				PlayerPanel.remove(Player);
				
				Player.resetBet(); 
				
				// resets player result for new round
				Player.setResult(0);
				
				
				// if a player bets all their points and loses the player is removed from the game
				if(Player.getPoints() == 0) {
					
					Model.removePlayer(Player);
				    UpdateComboBoxRemove(Player.getPlayerId());
					 
				}
			}
			SummaryPannel.UpdateSummaryPanel(Model.getAllPlayers());
			
			PlayerPanel.clear();
			setSelectedIndex();
			
		}
		
		// Ends Application
		if(PlayAgain == JOptionPane.NO_OPTION || PlayAgain == JOptionPane.CLOSED_OPTION) {
			System.exit(0);
		}
		
	
	}
	
	public void UpdateStatus(String message) {
		Toolbar.UpdateStatus(message);
	}
	
	public void ShowHouseScore(int Score) {
		JOptionPane.showMessageDialog(null, "The House has received the score "  + Score);
	}
	
	

	public void setSelectedIndex() {
		Toolbar.SetSelectedIndex();
	}
	
	// Decreases couping as the controller doesn't have direct contact with toolbar
	public ActionListener MakeNewToolBarListener() {
		return Controller.newToolbarListener();
		
	}
	
	public ActionListener MakeNewItemChangeListener() {
		return Controller.newItemChangeListener();
	}
	
	//public ActionListener MakeNewMenuListener() {
		
	//}

	
	

	

	

	

	


	


		
	
	
	



	


	
}
