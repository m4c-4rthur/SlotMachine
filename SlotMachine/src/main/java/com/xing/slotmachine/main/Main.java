/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xing.slotmachine.main;



import com.xing.slotmachine.SlotMachineMarker;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author hesham.ibrahim
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {SlotMachineMarker.class})
public class Main {
    
    
    public static void main(String[] args) {
        System.setProperty("server.port","8090");
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        context.getBean(AppInitialization.class).init();
    }
    
}
