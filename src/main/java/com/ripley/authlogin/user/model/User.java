package com.ripley.authlogin.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ripley.authlogin.core.model.BaseModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class User extends BaseModel {

  @EqualsAndHashCode.Include
  @Column(name = "username")
  private String username;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column(name = "second_name")
  private String secondName;

  @Column(name = "father_last_name")
  private String fatherLastName;

  @Column(name = "mother_last_name")
  private String motherLastName;

  @Column(name = "email_user")
  private String emailUser;

  @JsonIgnore
  public String getFullName() {
    StringBuilder sb = new StringBuilder();
    appendFullNamePart(sb, this.name);
    appendFullNamePart(sb, this.secondName);
    appendFullNamePart(sb, this.fatherLastName);
    appendFullNamePart(sb, this.motherLastName);
    return sb.toString();
  }

  private void appendFullNamePart(StringBuilder sb, String namePart) {
    if(namePart == null || namePart.isBlank()) {
      return;
    }
    if (sb.length() > 0) {
      sb.append(" ");
    }
    sb.append(namePart);
  }
}