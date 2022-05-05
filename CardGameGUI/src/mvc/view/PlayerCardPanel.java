package mvc.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;

import view.interfaces.GameEngineCallback;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;



public class PlayerCardPanel extends JPanel {
	// Encapsulation
	private Collection<PlayingCard> Cards = new ArrayList<PlayingCard>();
	private Player Player;
	private ImageIcon Icon;
	
	
	// Constructor
	public PlayerCardPanel(Player Player) {
		setBackground(Color.green);
	}

	
	@Override
	// Overriden paint component that draws the cards
	// NOTE: the x and y cord of the icon is not perfect when resizing however I have used trial and error to ajust accordingly
    public void paintComponent(Graphics g) {
    	int height = super.getHeight();
    	int CardWidth = (super.getWidth() / 6);
    	int xcord = 0;
        super.paintComponent(g);
        for (PlayingCard Card: Cards) {
        	Icon = new ImageIcon(GetPath(Card.getSuit()));
        	
        	Image Image = Icon.getImage();
        	String Type = CardString(Card.getValue());
        	int DrawingHeight = height - 200;
        	
        	Font font = g.getFont().deriveFont((height * CardWidth)/3000f  );        	

        		g.setColor(new Color(255,255,254));
            	g.fillRoundRect(xcord,100,CardWidth - 1,DrawingHeight,20,20);
        		g.drawImage(Image, xcord, 100, CardWidth/4, height/9, this);
        		
        		g.setColor(SetColor(Card.getSuit()));
        		g.setFont(font);
        		g.drawString(Type, CardWidth/2 + xcord - (CardWidth/20), height /2);
        		g.drawImage(Image, xcord + CardWidth -(CardWidth / 4), 100 + DrawingHeight - (height/8), CardWidth/4, height/9, this);
        		xcord = xcord + CardWidth;
  		
        	}
        

        	
        }
        
        
 // Returns path as string depending on Suit passed in
    private String GetPath(Suit Suit) {
    	
    	switch(Suit) {
    	case SPADES:
    		return "images/spades.png";
    	case DIAMONDS:
    		return "images/diamonds.png";
    	case HEARTS:
    		return "images/hearts.png";
    	case CLUBS:
    		return "images/clubs.png";
        }
    		
    			
    	return null;
    }
    
    // Changes paint component according to value of card
    private String CardString(Value Value) {
    	if(Value == Value.EIGHT) {
    		
    		return "8";
    		
    	}
    	
    	if(Value == Value.NINE) {
    	
    		return "9";
    	
    	}
    	
    	if( Value == Value.TEN) {
    		
    		return "10";
    	
    	}
    	
    	if( Value == Value.JACK) {
    		
    		return "J";
    		
    	}
    	
    	if( Value == Value.QUEEN) {
    		
    		return "Q";
    		
    	}
    	
    	if( Value == Value.KING) {
    		
    		return "K";
    		
    	}
    	
    	if(Value == Value.ACE) {
    	
    		return "A";
    	
    	}
    	
    	
		return null;
    	
    }
    
    // Sets the color of text to red or black according to suit
    private Color SetColor(Suit Suit) {
    	
    	if (Suit == Suit.SPADES || Suit == Suit.CLUBS) {
    	
    		return new Color(1,1,1);
    	}
    
    	if (Suit == Suit.DIAMONDS || Suit == Suit.HEARTS) {
    	
    		return new Color(255,0,0);
    	
    	}
		return null;
    	
    }

        
        

      
    // adds card to a collection so more then one can be displayed on the panel
    public void AddCard(PlayingCard Card) {
    	Cards.add(Card);
    }
    



	

	 



	




 

}
