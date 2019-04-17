package sql.schema.types;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Range implements SQLData {
  private static final String TYPE_NAME = "public.range";

  private Long max;

  private Long min;

  private Integer[] constructors;

  private String type;

  @Override
  public String getSQLTypeName() throws SQLException {
    return TYPE_NAME;
  }

  public Long getMax() {
    return max;
  }

  public void setMax(Long max) {
    this.max = max;
  }

  public Long getMin() {
    return min;
  }

  public void setMin(Long min) {
    this.min = min;
  }

  public Integer[] getConstructors() {
    return constructors;
  }

  public void setConstructors(Integer[] constructors) {
    this.constructors = constructors;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public void readSQL(SQLInput in, String typeName) throws SQLException {
    this.max = in.readLong();
    this.min = in.readLong();
    this.constructors = in.readObject(Integer[].class);
    this.type = in.readString();
  }

  @Override
  public void writeSQL(SQLOutput out) throws SQLException {
    out.writeLong(this.max);
    out.writeLong(this.min);
    out.writeObject(this.constructors, null);
    out.writeString(this.type);
  }
}
