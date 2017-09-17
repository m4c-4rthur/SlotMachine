package com.xing.slotmachine.service;

import com.xing.slotmachine.exception.AmountNotAjustedException;
import com.xing.slotmachine.exception.NotEnoughCreditException;

public interface PlayerService {
	
	Integer updatePlayerCredit(Integer value)throws AmountNotAjustedException;
	
	void checkPlayerCredit(Integer deductedAmount)throws NotEnoughCreditException;
	
	Integer getPlayerCredit();


}
