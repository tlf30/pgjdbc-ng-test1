package sql.schema.types;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Stat implements SQLData {
  private static final String TYPE_NAME = "public.stat";

  private Boolean visible;

  private Long xp;

  private Float max;

  private Float min;

  private Float value;

  private String display;

  private String name;

  private Range[] ranges;

  @Override
  public String getSQLTypeName() throws SQLException {
    return TYPE_NAME;
  }

  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public Long getXp() {
    return xp;
  }

  public void setXp(Long xp) {
    this.xp = xp;
  }

  public Float getMax() {
    return max;
  }

  public void setMax(Float max) {
    this.max = max;
  }

  public Float getMin() {
    return min;
  }

  public void setMin(Float min) {
    this.min = min;
  }

  public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Range[] getRanges() {
    return ranges;
  }

  public void setRanges(Range[] ranges) {
    this.ranges = ranges;
  }

  @Override
  public void readSQL(SQLInput in, String typeName) throws SQLException {
    this.visible = in.readBoolean();
    this.xp = in.readLong();
    this.max = in.readFloat();
    this.min = in.readFloat();
    this.value = in.readFloat();
    this.display = in.readString();
    this.name = in.readString();
    this.ranges = in.readObject(Range[].class);
  }

  @Override
  public void writeSQL(SQLOutput out) throws SQLException {
    out.writeBoolean(this.visible);
    out.writeLong(this.xp);
    out.writeFloat(this.max);
    out.writeFloat(this.min);
    out.writeFloat(this.value);
    out.writeString(this.display);
    out.writeString(this.name);
    out.writeObject(this.ranges, null);
  }
}
