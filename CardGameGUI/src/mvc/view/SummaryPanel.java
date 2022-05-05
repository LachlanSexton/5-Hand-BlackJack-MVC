package mvc.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SummaryPanel extends JPanel{
	
	//Encapsulation
	private DefaultTableModel DefaultTable = new DefaultTableModel(); 
	private JTable table = new JTable(DefaultTable);
	
	// Constuctor, creates table and border with title
	public SummaryPanel() {
		
		// Border String
		String title = "Summary";
		
		// Creates border
		Border border = BorderFactory.createTitledBorder(title);
		
		
		setBorder(border);
		
		// Sets layout of the JPanel
		setLayout(new GridLayout(1,1));
		add(new JScrollPane(table));
		
		// Set Dimension so that it can be resized appropriately
		setPreferredSize(new Dimension(250, 100));
		
		// Adds new columns to the table
		DefaultTable.addColumn("Player-Name");
		DefaultTable.addColumn("Player-ID");
		DefaultTable.addColumn("Points");
		DefaultTable.addColumn("Player-Bet");
		DefaultTable.addColumn("Result");
		
		

	}
	

	// Sets the type of table
	public void SetTable(DefaultTableModel DefaultTable) {
	
		table.setModel(DefaultTable);
				
	}
	
	
	// Updates the panel, able to remove and add depending on the all of the players information
	public void UpdateSummaryPanel(Collection<Player> ActivePlayers) {
		
		DefaultTable.setRowCount(0);

		
		for (Player Players: ActivePlayers) {
			
			DefaultTable.addRow(new Object[]{Players.getPlayerName(), Players.getPlayerId(), Players.getPoints(), Players.getBet(), Players.getResult(),});
		
		}
			
			
			
	
		SetTable(DefaultTable);
		}
	

}

