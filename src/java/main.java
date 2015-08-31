
import dao.jdbc;
import RoutePlanner.routeBlock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Loops
 */
public class main {

    public static void main(String[] args) {
        dao.jdbc jd = new jdbc();
        //RoutePlanner.DatabaseAccess data = new RoutePlanner.DatabaseAccess();
        RoutePlanner.routeBlock bridgeBlock = new routeBlock();
   //     String [][] test= jd.findBlock(2, 2, 2);
     //   System.out.println(test[50][50]);
        System.out.println(jd.findBlock(5, 2, 2).size());
    }
}
