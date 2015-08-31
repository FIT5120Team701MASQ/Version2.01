///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package RoutePlanner;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Collection;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author Loops
// */
//@WebServlet(name = "routeBlock", urlPatterns = {"/routeBlock"})
//public class routeBlock extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        double height = Double.parseDouble(request.getParameter("truck-height"));
//        double length = Double.parseDouble(request.getParameter("truck-length"));
//        double width = Double.parseDouble(request.getParameter("truck-width"));
// 
//        JDBC.jdbc db = new JDBC.jdbc();
//        Collection<domain.BridgeInfo> brigeInfo = db.findBlock(height, length, width);
//        //Collection<domain.BridgeInfo> brigeInfo = db.findBlock(2, 2, 2);
//
//
//    }
//    
//     // Method to handle POST method request.
//    public void doPost(HttpServletRequest request,HttpServletResponse response)
//        throws ServletException, IOException {
//    doGet(request, response);
//  }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}






/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoutePlanner;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Loops
 */
@WebServlet(name = "routeBlock", urlPatterns = {"/routeBlock"})
public class routeBlock extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double height = Double.parseDouble(request.getParameter("truck-height"));
        double length = Double.parseDouble(request.getParameter("truck-length"));
        double width = Double.parseDouble(request.getParameter("truck-width"));

        dao.jdbc db = new dao.jdbc();
        Collection<domain.BridgeInfo> brigeInfo = db.findBlock(height, length, width);


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}