/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.aop;

import com.mycompany.floormaster.dao.AuditDao;
import com.mycompany.floormaster.dto.Audit;
import com.mycompany.floormaster.dto.Order;
import java.util.Calendar;
import java.util.Date;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class AuditAspect {

    AuditDao aDao;
    

    public AuditAspect(AuditDao aDao) {
        
        this.aDao = aDao;

        
    }

    public Audit createAudit(JoinPoint jp) {
        
        Date date = Calendar.getInstance().getTime();

        Audit audit = new Audit();

        Order o = (Order) jp.getArgs()[0];
        
        String operation = jp.getSignature().getName();

        audit.setOrderId(o.getOrderNumber());

        audit.setOperation(operation);
        
        audit.setDate(date.toString());
        
        Audit addedAudit = aDao.add(audit);
        
        return addedAudit;

    }

}
