package com.xing.slotmachine.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xing.slotmachine.model.Player;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

@Component
public class AppInitialization {

    @Autowired
    Player player;
    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
    	/**
    	 * Player Default credit is configurable through Properties File
    	 */
         player.setCredit(creditDefault);
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }
    /**
     * Return appropriate message according to error code 
     * from messages.properties file
     * @param code
     * @return
     */
    public String getMessage(String code) {
        return accessor.getMessage(code);
    }

    @Value("${creditDefault}")
    private Integer creditDefault;


}
