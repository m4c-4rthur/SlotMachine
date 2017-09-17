/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xing.slotmachine.controller;

import com.xing.slotmachine.model.GameResult;

import com.xing.slotmachine.service.SlotGameService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hesham.ibrahim
 */
@RestController
public class GameController {

    
    @Autowired
    SlotGameService slotGameService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * Restful Controller to play Slot Machine Game 
     * @return
     */
    @RequestMapping(method=RequestMethod.GET,value="/playGame")
    public GameResult playGame() {
    	logger.info("Start Playing the Game");
        return slotGameService.playGame();
    }

}
