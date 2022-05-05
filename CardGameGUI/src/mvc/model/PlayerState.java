package mvc.model;

import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.view.CardGameFrame;

public class PlayerState {
	private GameEngine Model;
	private CardGameFrame frame;
	private int CurrentPoints;
	public PlayerState(GameEngine model, CardGameFrame frame) {
		this.Model = model;
		this.frame = frame;
	}
	public boolean HasDealt(Player Player) {
		if (frame.GetPlayerPanel(Player) != null) {
			frame.UpdatePanel(frame.GetPlayerPanel(Player));
			return true;
		}else {
			return false;
		}
		
	
			
		
	}
	public boolean HasBet(Player Player) {
		if (Player.getBet() > 0) {
			return true;
	}
		else {
			return false;
		}
	}
	public String WinLoss(int PreviousPoints, int CurrentPoints) {
		if (PreviousPoints > CurrentPoints) {
			return "LOSS";
		}
		if(CurrentPoints > PreviousPoints) {
			return "WIN";
		}
		else {
			return "DRAW";
		}
		
	}
	
	
	
	public boolean PlayersHaveBet() {
		int Counter = 0;
		for (Player Player: Model.getAllPlayers()) {
			if (Player.getResult() != 0) {
				Counter++;
			}
		}
		if(Counter == Model.getAllPlayers().size() ) {
			return true;
		}else {
			return false;
		}
		
		


		
	}

}
