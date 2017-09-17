package com.xing.slotmachine.service;



import com.xing.slotmachine.exception.AmountNotAjustedException;
import com.xing.slotmachine.model.GameReels;
import com.xing.slotmachine.model.GameResult;

public interface SlotGameService {
	
	GameReels pullLever();
	
	Integer checkResult(GameReels gameOutcome);
	
	void provisionCredit(Integer Type)throws AmountNotAjustedException;
	
	GameResult playGame();
        
    String getReelsValues(GameReels gameReels);

	
}
