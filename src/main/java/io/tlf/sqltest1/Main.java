package io.tlf.sqltest1;

import com.impossibl.postgres.jdbc.PGDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author tlfal
 */
public class Main {

    private static PGDataSource ds = new PGDataSource();

    public static void main(String[] args) {
        //Create DB connection
        try {
            Class.forName("com.impossibl.postgres.jdbc.PGConnectionPoolDataSource");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        System.out.println("Connecting to db...");
        Connection con = null;
        try {
            ds.setServerName("localhost");
            ds.setPort(5432);
            ds.setDatabaseName("test1");
            ds.setUser("test1");
            ds.setPassword("test1");
            con = ds.getConnection();
            //Create mappings
            Map map = con.getTypeMap();
            map.put("CHARA", Chara.class);
            map.put("RANGE", Range.class);
            map.put("STAT", Stat.class);
            con.setTypeMap(map);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (con == null) {
            System.out.println("Connection null");
            return;
        }
        
        System.out.println("Reading test data");
        try {
            Chara data = null;
            PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM users LIMIT 1");
            ResultSet result = pstmt1.executeQuery();
            if (result.next()) {
                data = result.getObject("data", Chara.class);
                result.close();
            }
            pstmt1.close();
            if (data == null) {
                System.out.println("DID NOT GET DATA");
                return;
            }

            System.out.println("Writing test data");
            PreparedStatement pstmt2 = con.prepareStatement("UPDATE users SET data=?");
            data.setupArrays(con);
            pstmt2.setObject(1, data);
            pstmt2.executeUpdate();
            pstmt2.close();
            data.deallocArrays();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
