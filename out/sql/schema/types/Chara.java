package sql.schema.types;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Chara implements SQLData {
  private static final String TYPE_NAME = "public.chara";

  private Stat[] stats;

  @Override
  public String getSQLTypeName() throws SQLException {
    return TYPE_NAME;
  }

  public Stat[] getStats() {
    return stats;
  }

  public void setStats(Stat[] stats) {
    this.stats = stats;
  }

  @Override
  public void readSQL(SQLInput in, String typeName) throws SQLException {
    this.stats = in.readObject(Stat[].class);
  }

  @Override
  public void writeSQL(SQLOutput out) throws SQLException {
    out.writeObject(this.stats, null);
  }
}
