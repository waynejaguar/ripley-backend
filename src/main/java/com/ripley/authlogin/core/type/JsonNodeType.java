package com.ripley.authlogin.core.type;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

public class JsonNodeType implements UserType {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public int[] sqlTypes() {
    return new int[]{Types.OTHER};
  }

  @Override
  public Class returnedClass() {
    return JsonNode.class;
  }

  @Override
  public boolean equals(final Object x, final Object y) throws HibernateException {
    return x.equals(y);
  }

  @Override
  public int hashCode(final Object x) throws HibernateException {
    return x.hashCode();
  }

  @Override
  public Object nullSafeGet(final ResultSet rs, final String[] names,
      final SharedSessionContractImplementor session, final Object owner)
      throws HibernateException, SQLException {
    try {
      return this.mapper.readTree(rs.getBytes(names[0]));
    } catch (final IOException e) {
      throw new HibernateException("failed to parse the json from result set", e);
    }
  }

  @Override
  public void nullSafeSet(
      final PreparedStatement statement,
      final Object value,
      final int index,
      final SharedSessionContractImplementor session) throws HibernateException, SQLException {
    try {
      final PGobject object = new PGobject();
      object.setType("json");
      object.setValue(this.mapper.writeValueAsString(value));
      statement.setObject(index, object);
    } catch (final JsonProcessingException e) {
      throw new HibernateException("failed to set the json on the prepared statement", e);
    }
  }

  @Override
  public Object deepCopy(final Object value) throws HibernateException {
    try {
      return this.mapper.readTree(value.toString());
    } catch (final JsonProcessingException e) {
      throw new HibernateException("failed to copy the value", e);
    }
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(final Object value) throws HibernateException {
    return (Serializable) value;
  }

  @Override
  public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
    return cached;
  }

  @Override
  public Object replace(final Object original, final Object target, final Object owner)
      throws HibernateException {
    return original;
  }

}
