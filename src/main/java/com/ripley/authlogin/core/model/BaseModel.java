package com.ripley.authlogin.core.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.ripley.authlogin.core.type.JsonNodeType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.domain.Persistable;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@TypeDefs({
    @TypeDef(name = "json-node", defaultForType = JsonNode.class, typeClass = JsonNodeType.class)
})
public abstract class BaseModel implements Persistable<Long>, Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "created_at", updatable = false)
  private Date createdAt;

  @Column(name = "updated_at", insertable = false)
  private Date updatedAt;

  @Column(name = "deleted_at", insertable = false)
  private Date deletedAt;

  @Override
  @JsonIgnore
  @Transient
  public boolean isNew() {
    return this.id == null || this.id == 0L;
  }

  @PrePersist
  public void prePersist() {
    this.createdAt = new Date();
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = new Date();
  }

}
