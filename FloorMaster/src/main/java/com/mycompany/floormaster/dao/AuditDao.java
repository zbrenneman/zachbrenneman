/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormaster.dao;

import com.mycompany.floormaster.dto.Audit;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AuditDao {

    public Audit add(Audit audit);

    public void encode();

    public List<Audit> decode();

}
