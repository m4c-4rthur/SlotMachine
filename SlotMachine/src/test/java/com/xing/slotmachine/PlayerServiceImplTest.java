/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xing.slotmachine;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xing.slotmachine.exception.AmountNotAjustedException;
import com.xing.slotmachine.exception.NotEnoughCreditException;
import com.xing.slotmachine.main.Main;
import com.xing.slotmachine.model.Player;
import com.xing.slotmachine.service.PlayerService;

/**
 *
 * @author hesham.ibrahim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Main.class})
public class PlayerServiceImplTest {
	
	@Autowired
	PlayerService instance;
	
	@Autowired
	Player player;
	
    public PlayerServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of updatePlayerCredit method, of class PlayerServiceImpl.
     */
    @Test
    public void testUpdatePlayerCredit() throws Exception {
        System.out.println("updatePlayerCredit");
        player.setCredit(20);
        Integer value = 5;
        Integer expResult = 25;
        Integer result = instance.updatePlayerCredit(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    /**
     * Test of UpdatePlayerCreditNegative method, of class PlayerServiceImpl.
     */
    @Test
    public void testUpdatePlayerCreditNegative() throws Exception {
        System.out.println("updatePlayerCredit");
        player.setCredit(20);
        Integer value = -5;
        Integer expResult = 15;
        Integer result = instance.updatePlayerCredit(value);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of UpdatePlayerCreditInvalidAmount method, of class PlayerServiceImpl.
     */
    @Test(expected = AmountNotAjustedException.class)
    public void testUpdatePlayerCreditInvalidAmount() throws Exception {
        System.out.println("updatePlayerCredit");
        player.setCredit(3);
        Integer value = -5;
        instance.updatePlayerCredit(value);
        
        
    }

    /**
     * Test of checkPlayerCredit method, of class PlayerServiceImpl.
     */
    @Test
    public void testCheckPlayerCredit() throws Exception {
    	player.setCredit(20);
        System.out.println("checkPlayerCredit");
        Integer deductedAmount = -3;
        instance.checkPlayerCredit(deductedAmount);
    }
    /**
     * Test of CheckPlayerCreditNotEnough method, of class PlayerServiceImpl.
     */
    @Test(expected = NotEnoughCreditException.class)
    public void testCheckPlayerCreditNotEnough() throws Exception {
    	player.setCredit(2);
        System.out.println("checkPlayerCredit");
        Integer deductedAmount = 3;
        instance.checkPlayerCredit(deductedAmount);
    }

    /**
     * Test of getPlayerCredit method, of class PlayerServiceImpl.
     */
    @Test
    public void testGetPlayerCredit() {
        System.out.println("getPlayerCredit");
        player.setCredit(20);
        Integer expResult = 20;
        Integer result = instance.getPlayerCredit();
        assertEquals(expResult, result);
        
    }
    
    
}
