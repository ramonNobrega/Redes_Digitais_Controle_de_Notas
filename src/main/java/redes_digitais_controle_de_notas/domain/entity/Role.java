package redes_digitais_controle_de_notas.domain.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.frameworkdemoiselle.transaction.Transactional;

@Entity
@Table(name="tb_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="nm_role", nullable=false, length=30)
	private String name;
	
  @Column(name="ds_role", length=255)
  private String description;

  @Column(name="fl_active")
  private Boolean active = Boolean.TRUE;

  @ManyToMany(mappedBy="roles")
  private List<User> users;

  @Column(name = "ts_last_update", insertable = true, updatable = true)
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date lastUpdate;

  @PrePersist
  private void onInsert() {
    this.lastUpdate = new Date();
  }

  @PreUpdate
  private void onUpdate() {
    this.lastUpdate = new Date();
  }

  public Role() {
		super();
	}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Date getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((active == null) ? 0 : active.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((users == null) ? 0 : users.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Role other = (Role) obj;
    if (active == null) {
      if (other.active != null) {
        return false;
      }
    } else if (!active.equals(other.active)) {
      return false;
    }
    if (description == null) {
      if (other.description != null) {
        return false;
      }
    } else if (!description.equals(other.description)) {
      return false;
    }
    if (lastUpdate == null) {
      if (other.lastUpdate != null) {
        return false;
      }
    } else if (!lastUpdate.equals(other.lastUpdate)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }
	
	@Override
	public String toString() {
		if (name != null) {
			return name;
		} else {
			return super.toString();
		}
	}
	
}
