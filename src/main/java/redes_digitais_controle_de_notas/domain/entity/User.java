package redes_digitais_controle_de_notas.domain.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="tb_user", uniqueConstraints=@UniqueConstraint(name="uk_user_01", columnNames={"nm_email"}))
@SequenceGenerator(name="user_sequence", sequenceName="sq_user", initialValue=1, allocationSize=1)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
  @Column (name="id_user", nullable=false)
	private Long id;
	
	@Column(name="nm_user", nullable=false, length=255)
	private String name;
	
	@Column (name="nm_password", length=32)
	private String password;
	
  @Column(name="nm_email", length=255)
  private String email;

  @Column(name="fl_active")
  private Boolean active = Boolean.TRUE;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="tb_user_role",	
	    joinColumns=
      @JoinColumn(name="id_user", referencedColumnName="id_user"),
  inverseJoinColumns=
      @JoinColumn(name="nm_role", referencedColumnName="nm_role")
  )	
	private List<Role> roles = new java.util.ArrayList<Role>();
	
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

  public User() {
		super();
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
  public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
	  if (password != null && password.length()<32) {
	    password = redes_digitais_controle_de_notas.security.SecurityHelper.cryptMD5(password);
	  }
		this.password = password;
	}

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
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
