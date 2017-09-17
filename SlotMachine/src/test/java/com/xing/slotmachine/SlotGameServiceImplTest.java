/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xing.slotmachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xing.slotmachine.main.Main;
import com.xing.slotmachine.model.GameReels;
import com.xing.slotmachine.model.Player;
import com.xing.slotmachine.service.SlotGameService;

/**
 *
 * @author hesham.ibrahim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Main.class})
public class SlotGameServiceImplTest {

    @Autowired
    Player player;
    @Autowired
    SlotGameService instance;

    public SlotGameServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of pullLever method, of class SlotGameServiceImpl.
     */
    @Test
    public void testPullLever() {
        System.out.println("pullLever");
        GameReels result = instance.pullLever();
        assertTrue(1<= result.getReel1()&&result.getReel1() <=3);
        assertTrue(1<= result.getReel2()&&result.getReel2() <=3);
        assertTrue(1<= result.getReel3()&&result.getReel3() <=3);
        
    }

    /**
     * Test of checkResult method, of class SlotGameServiceImpl.
     */
    @Test
    public void testCheckResultSuccess() {
        System.out.println("checkResult");
        GameReels gameOutcome = new GameReels();
        gameOutcome.setReel1(1);
        gameOutcome.setReel2(1);
        gameOutcome.setReel3(1);
        Integer expResult = 1;
        Integer result = instance.checkResult(gameOutcome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    /**
     * Test of CheckResult method, of class SlotGameServiceImpl.
     */
    
    @Test
    public void testCheckResultFailed() {
        System.out.println("checkResult");
        GameReels gameOutcome = new GameReels();
        gameOutcome.setReel1(1);
        gameOutcome.setReel2(2);
        gameOutcome.setReel3(3);
        Integer expResult = -1;
        Integer result = instance.checkResult(gameOutcome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of provisionCredit method, of class SlotGameServiceImpl.
     */
    @Test
    public void testProvisionCredit() throws Exception {
        System.out.println("provisionCredit");
        Integer Type = 1;
        player.setCredit(15);
        instance.provisionCredit(Type);
        Integer expResult = 25;
        assertEquals(expResult, player.getCredit());
        
    }

    /**
     * Test of getReelsValues method, of class SlotGameServiceImpl.
     */
     @Test
    public void testGetReelsValues() {
        System.out.println("checkResult");
        GameReels gameOutcome = new GameReels();
        gameOutcome.setReel1(1);
        gameOutcome.setReel2(2);
        gameOutcome.setReel3(3);
        String expResult = "Apple Banana Citrus Fruits ";
        String result = instance.getReelsValues(gameOutcome);
        assertTrue(expResult.equalsIgnoreCase(result));
        // TODO review the generated test code and remove the default call to fail.
    }

}
