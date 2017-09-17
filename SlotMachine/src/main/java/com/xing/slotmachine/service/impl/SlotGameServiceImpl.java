package com.xing.slotmachine.service.impl;

import java.lang.reflect.Field;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xing.slotmachine.exception.AmountNotAjustedException;
import com.xing.slotmachine.exception.NotEnoughCreditException;
import com.xing.slotmachine.main.AppInitialization;
import com.xing.slotmachine.model.GameReels;
import com.xing.slotmachine.model.GameResult;
import com.xing.slotmachine.model.Player;
import com.xing.slotmachine.service.PlayerService;
import com.xing.slotmachine.service.SlotGameService;

@Service
public class SlotGameServiceImpl implements SlotGameService {

    @Autowired
    PlayerService playerService;
    @Autowired
    Player player;
    @Autowired
    AppInitialization ai;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * Generate the 3 reels values with pseudorandom number generator from 
     * 1 to 3 inclusive (1 represent Apple, 2 Banana and 3 fruit circle)
     */
    @Override
    public GameReels pullLever() {

        Random random = new Random(System.currentTimeMillis());
        GameReels gameOutcome = new GameReels();
        gameOutcome.setReel1(random.nextInt(3 - 1 + 1) + 1);
        gameOutcome.setReel2(random.nextInt(3 - 1 + 1) + 1);
        gameOutcome.setReel3(random.nextInt(3 - 1 + 1) + 1);
        return gameOutcome;
    }
    /**
     * Check the outcome result, the method return the the success type id
     * (three reel in row) else return -1 indicating Failed
     */
    @Override
    public Integer checkResult(GameReels gameOutcome) {
        if (gameOutcome.getReel1().equals(gameOutcome.getReel2())
                && gameOutcome.getReel2().equals(gameOutcome.getReel3())) {
            return gameOutcome.getReel1();
        }
        return -1;
    }
    /**
     * Provision credit to player in case of success according to reels result 
     */
    @Override
    public void provisionCredit(Integer Type) throws AmountNotAjustedException {
        switch (Type) {
            case 1:
                playerService.updatePlayerCredit(10);
                break;
            case 2:
                playerService.updatePlayerCredit(15);
                break;
            case 3:
                playerService.updatePlayerCredit(20);
                break;

            default:
                break;
        }

    }

    @Override
    public GameResult playGame() {
        logger.info("Starting Game Handling");
        GameResult gameResult = new GameResult();
        try {
            logger.info("Check Player Credit ");
            playerService.checkPlayerCredit(3);
            logger.info("Deduct game cost from Player Credit ");
            playerService.updatePlayerCredit(-3);
            logger.info("Pulling the liver ");
            GameReels gameOutcome = pullLever();
            Integer resultType = checkResult(gameOutcome);
            if (resultType > -1) {
                logger.info("Player Has won with result " + resultType);
                provisionCredit(resultType);
                gameResult.setCredit(player.getCredit());
                gameResult.setMessage(ai.getMessage("0" + resultType));

            } else {
                logger.info("Player Didn't win");
                gameResult.setCredit(player.getCredit());
                gameResult.setMessage(getReelsValues(gameOutcome) + " " + ai.getMessage("-1"));
            }
        } catch (AmountNotAjustedException e) {
            logger.error("Credit Adjustment failed");
            gameResult.setCredit(player.getCredit());
            gameResult.setMessage(ai.getMessage("-2"));
        } catch (NotEnoughCreditException e) {
            logger.warn("Player has not enough credit");
            gameResult.setCredit(player.getCredit());
            gameResult.setMessage(ai.getMessage("-3"));
        } catch (Exception e) {
            logger.error("General Exception Occured", e);
            gameResult.setCredit(player.getCredit());
            gameResult.setMessage(ai.getMessage("-4"));
        }
        return gameResult;
    }
    /**
     * Map the generated Id from reels to fruit names for the message returned to user
     */
    @Override
    public String getReelsValues(GameReels gameReels) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Field field : gameReels.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Integer value = (Integer) field.get(gameReels);
                switch (value) {
                    case 1:
                        stringBuilder.append("Apple ");
                        break;
                    case 2:
                        stringBuilder.append("Banana ");
                        break;
                    case 3:
                        stringBuilder.append("Citrus Fruits ");
                        break;

                    default:
                        break;
                }

            }
        } catch (Exception e) {
            logger.error("Error While getting data from object");
        }
        return stringBuilder.toString();
    }

}
