package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.PlayingCard;

public class StatusBar extends JPanel {
	// Encapsulation
	private JLabel update = new JLabel();
	
	// Sets options Jpanel
	public StatusBar(){
	   
	    setBorder(BorderFactory.createEtchedBorder());
	    setBackground(Color.LIGHT_GRAY);
	    // If the Jlabel consists of a string ie. for when the GUI launches
	    try {
	    	add(update);
	    }catch(NullPointerException e){
	    	
	    }

	   
	}
	
	// Updates the Jlabel's text according to string passed in
	public void Update(String message) {
		this.update.setText(message);
	}
}
