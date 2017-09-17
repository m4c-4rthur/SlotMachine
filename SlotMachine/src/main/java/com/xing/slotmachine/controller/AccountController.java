package com.xing.slotmachine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xing.slotmachine.exception.AmountNotAjustedException;
import com.xing.slotmachine.main.AppInitialization;
import com.xing.slotmachine.model.DepositCreditRequest;
import com.xing.slotmachine.model.DepositCreditResponse;
import com.xing.slotmachine.model.GetCreditResponse;
import com.xing.slotmachine.service.PlayerService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AccountController {
	
	@Autowired
	PlayerService playerService;
	@Autowired
	AppInitialization ai;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Restful controller to get player current Credit
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getCredit")
    public GetCreditResponse getPlayerCredit() {
		logger.info("Getting Player Credit request received");
		GetCreditResponse creditResponse = new GetCreditResponse();
		creditResponse.setCredit(playerService.getPlayerCredit());
		creditResponse.setMessage("Success");
        return creditResponse;
    }
	
	
	/**
	 * Restful controller to Add deposit to Player Credit 
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/depositCredit")
    public DepositCreditResponse depositPlayerCredit(@RequestBody DepositCreditRequest request) {
		logger.info("Deposit Player Credit request received with amount " + request.getAmount());
    	Integer newCredit = 0;
    	DepositCreditResponse creditResponse = new DepositCreditResponse();
    	try {
    		
    		newCredit = playerService.updatePlayerCredit(request.getAmount());
    		creditResponse.setMessage("Success");
    		creditResponse.setNewCredit(newCredit);
		} catch (AmountNotAjustedException e) {
			creditResponse.setMessage(ai.getMessage("-2"));
		}
        return creditResponse;	
    }

}
