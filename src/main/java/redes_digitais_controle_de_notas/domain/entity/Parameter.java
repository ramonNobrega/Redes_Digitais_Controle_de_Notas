package redes_digitais_controle_de_notas.domain.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="tb_parameter")
public class Parameter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_parameter", nullable=false)
	private Long id;

	@Column(name="nm_admin_email", length=255)
	private String adminEmail;

	@Column(name="ts_last_update")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date lastUpdate;

	@PrePersist
		private void onInsert() {
		this.lastUpdate = new Date();
	}
	
	@PreUpdate
		private void onUpdate() {
		this.lastUpdate = new Date();
	}
	
	public Parameter() {
		super();
		setId(1L);
	}
	
	public Parameter(Long id, String adminEmail, Date lastUpdate) {
		this();
		this.id = id;
		this.adminEmail = adminEmail;
		this.lastUpdate = lastUpdate;
		setId(1L);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
