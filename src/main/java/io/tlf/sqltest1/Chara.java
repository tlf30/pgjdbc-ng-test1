
package io.tlf.sqltest1;
import java.sql.Array;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Connection;

/**
 *
 * @author Trevor Flynn <trevorflynn@liquidcrystalstudios.com>
 */
public class Chara implements SQLData {

    public Stat[] stats;
    private String sql_type;

    @Override
    public String getSQLTypeName() {
        return sql_type;
    }

    private Array detailsArray2;

    public void setupArrays(Connection con) throws SQLException {
        System.out.println("Setting up stat arrays");
        for (Stat s : stats) {
            s.setupArrays(con);
        }
        System.out.println("Building stats array");
        detailsArray2 = con.createArrayOf("STAT", stats);
    }

    public void deallocArrays() throws SQLException {
        detailsArray2.free();
        for (Stat s : stats) {
            s.deallocArrays();
        }
    }

    @Override
    public void readSQL(SQLInput stream, String type) throws SQLException {
        sql_type = type;
        stats = stream.readObject(Stat[].class);
        System.out.println("Read " + stats.length + " stats");
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        System.out.println("Writing chara");
        stream.writeArray(detailsArray2);
    }
}
