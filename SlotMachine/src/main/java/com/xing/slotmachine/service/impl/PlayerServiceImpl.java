package com.xing.slotmachine.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xing.slotmachine.exception.AmountNotAjustedException;
import com.xing.slotmachine.exception.NotEnoughCreditException;
import com.xing.slotmachine.model.Player;
import com.xing.slotmachine.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
	Player player;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * Update Customer credit with(positive/negative) value and return new vlaue
	 * The method is synchronized to ensure thread safety on credit update.
	 * 
	 */
	@Override
	public synchronized Integer updatePlayerCredit(Integer value) throws AmountNotAjustedException {
		logger.info("Updating Player Credit with value " + value);
		Integer credit = player.getCredit();
		Integer newCredit = credit + value;
		if(newCredit < 0 )
		{
			throw new AmountNotAjustedException();
		}
		player.setCredit(newCredit);
		return newCredit;
	}
	/**
	 * Check if the player credit is enough for certain deduction amount
	 */
	@Override
	public void checkPlayerCredit(Integer deductedAmount) throws NotEnoughCreditException {
		logger.info("check Player credit eligbility for deducted amount " + deductedAmount);
		Integer creditAfter = player.getCredit() - deductedAmount;
		
		if (creditAfter < 0)
		{
			throw new NotEnoughCreditException();
		}
		
	}
	/**
	 * Get Current player credit
	 */
	@Override
	public Integer getPlayerCredit() {
		logger.info("getting Player credit");
		return player.getCredit();
		
	}

	

}
