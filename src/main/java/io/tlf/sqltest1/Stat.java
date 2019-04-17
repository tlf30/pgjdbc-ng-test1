package io.tlf.sqltest1;

import com.impossibl.postgres.api.jdbc.PGType;
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
public class Stat implements SQLData {
    
    public String name;
    public String display;
    public long xp;
    public float value;
    public float min;
    public float max;
    public Range[] ranges;
    public boolean visible;
    private String sql_type;

    @Override
    public String getSQLTypeName() {
        return sql_type;
    }

    private Array detailsArray0;

    public void setupArrays(Connection con) throws SQLException {
        System.out.println("Setting up range arrays");
        for (Range r : ranges) {
            r.setupArrays(con);
        }
        System.out.println("Building ranges array");
        detailsArray0 = con.createArrayOf("RANGE", ranges);
    }

    public void deallocArrays() throws SQLException {
        detailsArray0.free();
        for (Range r : ranges) {
            r.deallocArrays();
        }
    }

    @Override
    public void readSQL(SQLInput stream, String type) throws SQLException {
        sql_type = type;
        name = stream.readString();
        display = stream.readString();
        xp = stream.readLong();
        value = stream.readFloat();
        min = stream.readFloat();
        max = stream.readFloat();
        ranges = stream.readObject(Range[].class);
        visible = stream.readBoolean();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        System.out.println("Writing stat");
        stream.writeString(name);
        stream.writeString(display);
        stream.writeLong(xp);
        stream.writeObject(value, PGType.FLOAT4);
        stream.writeObject(min, PGType.FLOAT4);
        stream.writeObject(max, PGType.FLOAT4);
        stream.writeArray(detailsArray0);
        stream.writeBoolean(visible);
    }
}
