/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dao;

import com.mycompany.floormaster.dto.Audit;
import com.mycompany.floormaster.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class AuditDaoImpl implements AuditDao {
    
    private static final String TOKEN = ",";
    private static String FILENAME = "audits.txt";
    
    List<Audit> audits = null;
    private int nextId = 1;
    
    public AuditDaoImpl() {
        
        audits = decode();
        
        for (Audit a : audits) {
            
            if (a.getAuditId() >= nextId) {
                
                nextId = a.getAuditId() + 1;
            }
        }
    }
    
    public Audit add(Audit audit) {
        
        audit.setAuditId(nextId);
        
        audits.add(audit);
        
        nextId++;
        
        encode();
        
        return audit;
        
    }
    
    @Override
    public void encode() {
        
        PrintWriter out = null;
        
        try {
            out = new PrintWriter(new FileWriter(FILENAME));
            
            for (Audit a : audits) {
                
                out.print(a.getAuditId());
                out.print(TOKEN);
                
                out.print(a.getDate());
                out.print(TOKEN);
                
                out.print(a.getOperation());
                out.print(TOKEN);
                
                out.print(a.getOrderId());
                out.println("");
                
            }
            
            out.flush();
            
        } catch (IOException ex) {
            
        } finally {
            out.close();
            
        }
    }
    
    @Override
    public List<Audit> decode() {
        
        List<Audit> tempAuditList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Audit audit = new Audit();
                
                
                int auditId = Integer.parseInt(stringParts[0]);
                int orderId = Integer.parseInt(stringParts[3]);
                
                audit.setAuditId(auditId);
                audit.setDate(stringParts[1]);
                audit.setOperation(stringParts[2]);
                audit.setOrderId(orderId);
                

                

                tempAuditList.add(audit);
            }

        } catch (FileNotFoundException ex) {
        }

        return tempAuditList;
        
    }
    
}
