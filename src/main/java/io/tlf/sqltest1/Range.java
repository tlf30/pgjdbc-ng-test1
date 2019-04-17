
package io.tlf.sqltest1;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;


/**
 *
 * @author Trevor Flynn <trevorflynn@liquidcrystalstudios.com>
 */
public class Range implements SQLData {

    public String type;
    public long min, max;
    public Integer[] constructors;
    private String sql_type;

    @Override
    public String getSQLTypeName() {
        return sql_type;
    }

    private Array detailsArray0;

    public void setupArrays(Connection con) throws SQLException {
        if (detailsArray0 == null) {
            detailsArray0 = con.createArrayOf("int4", constructors);
        }
    }

    public void deallocArrays() throws SQLException {
        if (detailsArray0 != null) {
            detailsArray0.free();
        }
    }

    @Override
    public void readSQL(SQLInput stream, String type) throws SQLException {
        sql_type = type;

        min = stream.readLong();
        max = stream.readLong();
        this.type = stream.readString();
        detailsArray0 = stream.readArray();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeLong(min);
        stream.writeLong(max);
        stream.writeString(type);
        stream.writeArray(detailsArray0);
    }
}

