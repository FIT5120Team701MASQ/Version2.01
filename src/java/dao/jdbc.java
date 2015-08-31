package dao;



import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import domain.BridgeInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Reference:https://azure.microsoft.com/en-us/documentation/articles/sql-database-develop-java-simple-windows/
 */
/**
 *
 * @author Loops
 */
public class jdbc implements DAO{

    private final String connectionString
            = "jdbc:sqlserver://id8i41z6ld.database.windows.net:1433;"
            + "database=smarttrip;"
            + "user=smartrip@id8i41z6ld.database.windows.net;"
            + "password=teamMASQ*701;"
            + "encrypt=true;"
            + "trustServerCertificate=false;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;";

    // Declare the JDBC objects.
    private Connection connection = null;
    private Statement stmt = null;
    private PreparedStatement preps = null;
    private PreparedStatement prepsUpdateAge = null;

    public jdbc() {
    }

    public Connection getConnection() {

        try {
            connection = DriverManager.getConnection(connectionString);
            System.out.println(connection);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;

    }

    public Collection<domain.BridgeInfo> findBlock(double height, double length, double width) {
        connection = getConnection();
        Collection<domain.BridgeInfo> brigeInf = new ArrayList<>();

        String query = "SELECT OBJECTID, COLLOQUIAL_NAME_1, COLLOQUIAL_NAME_2, COLLOQUIAL_NAME_3, "
                + "CAST(MIN_CLEARANCE AS FLOAT) AS MIN_CLEARANCE, "
                + "CAST(OVERALL_LENGTH AS FLOAT) AS OVERALL_LENGTH, "
                + "CAST(OVERALL_WIDTH AS FLOAT) AS OVERALL_WIDTH, LAT, LONGIT FROM guest.Tbl_bridge_structure_vic "
                + "WHERE CAST(MIN_CLEARANCE AS FLOAT) > ? "
                + "AND CAST(OVERALL_LENGTH AS FLOAT) > ? AND CAST(OVERALL_WIDTH AS FLOAT) > ?;";

        try {
            preps = connection.prepareStatement(query);

            preps.setDouble(1, height);
            preps.setDouble(2, length);
            preps.setDouble(3, width);
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
                brigeInf.add(bridge);
            }

            connection.close();
            rset.close();

        } catch (SQLException ex) {
            Logger.getLogger(jdbc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(brigeInf.size());
        return brigeInf;
    }
}
