/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.Collection;

/**
 *
 * @author Loops
 */
public interface DAO {
    Connection getConnection();
    Collection<domain.BridgeInfo> findBlock(double height, double length, double width);
    
}
