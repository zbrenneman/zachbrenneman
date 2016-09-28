/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.aop;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author apprentice
 */
public class TimerAspect {
    
    public Object timeMethod(ProceedingJoinPoint jp){
        
        Object o = null;
        
        long start = System.nanoTime();
        
        try {
            o = jp.proceed();
        } catch (Throwable ex) {
            Logger.getLogger(TimerAspect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long end = System.nanoTime();
        
        System.out.println("\n" + jp.getSignature().getName() + " took " + (end - start) + " nanoseconds");
        
        return o;
    }
    
}
