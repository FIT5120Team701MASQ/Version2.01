/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoutePlanner;

import com.google.gson.Gson;
import domain.BridgeInfo;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;


@WebServlet(name = "DatabaseAccess", urlPatterns = {"/DatabaseAccess"})
public class DatabaseAccess extends HttpServlet{
    
      private final static String connectionString
            = "jdbc:sqlserver://id8i41z6ld.database.windows.net:1433;"
            + "database=smarttrip;"
            + "user=smartrip@id8i41z6ld.database.windows.net;"
            + "password=teamMASQ*701;"
            + "encrypt=true;"
            + "trustServerCertificate=false;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;";
      private static Connection conn = null;
      private Statement stmt = null;
      private static PreparedStatement preps = null;
      Collection<domain.BridgeInfo> bridgeInf = new ArrayList<>();
      
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      //String buttonID = request.getParameter("button-id");

      try{
         // Register JDBC driver
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

         // Open a connection
         conn = DriverManager.getConnection(connectionString);

         // Execute SQL query
         stmt = conn.createStatement();
         String sql;
         sql = "SELECT OBJECTID, COLLOQUIAL_NAME_1, COLLOQUIAL_NAME_2, COLLOQUIAL_NAME_3, "
                + "CAST(MIN_CLEARANCE AS FLOAT) AS MIN_CLEARANCE, "
                + "CAST(OVERALL_LENGTH AS FLOAT) AS OVERALL_LENGTH, "
                + "CAST(OVERALL_WIDTH AS FLOAT) AS OVERALL_WIDTH, LAT, LONGIT FROM guest.Tbl_bridge_structure_vic "
                + "WHERE CAST(MIN_CLEARANCE AS FLOAT) > 0 AND CAST(MIN_CLEARANCE AS FLOAT) < ? "
                + "AND CAST(OVERALL_LENGTH AS FLOAT) < ? AND CAST(OVERALL_WIDTH AS FLOAT) > 0 AND CAST(OVERALL_WIDTH AS FLOAT) < ?;";
         
         Float height = Float.parseFloat(request.getParameter("height"));
         Float length = Float.parseFloat(request.getParameter("length"));
         Float width = Float.parseFloat(request.getParameter("width"));
         
         preps = conn.prepareStatement(sql);
         preps.setFloat(1, height);
         preps.setFloat(2, length);
         preps.setFloat(3, width);
         
         ResultSet rset = preps.executeQuery();
         
         while (rset.next()) {
                domain.BridgeInfo bridge = new BridgeInfo();
                
                bridge.setObjectId(rset.getString(1));
                bridge.setCollName1(rset.getString(2));
                bridge.setCollName2(rset.getString(3));
                bridge.setCollName3(rset.getString(4));
                bridge.setMinClearance(rset.getDouble(5));
                bridge.setLength(rset.getDouble(6));
                bridge.setWidth(rset.getDouble(7));
                bridge.setLat(rset.getDouble(8));
                bridge.setLongit(rset.getDouble(9));
                bridgeInf.add(bridge);
            }
         Gson json =new Gson();
         json.toJson(bridgeInf);
         response.getWriter().write(new Gson().toJson(bridgeInf));
         
         // Clean-up environment
         rset.close();
         //stmt.close();
         conn.close();
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
      }finally{
         //finally block used to close resources
         try{
            if(stmt!=null)
               stmt.close();
         }catch(SQLException se2){
         }// nothing we can do
         try{
            if(conn!=null)
            conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }//end finally try
      } //end try
   }
  
    public void doPost(HttpServletRequest request,HttpServletResponse response)
                        throws IOException, ServletException {
    doGet(request, response);
  }
}
