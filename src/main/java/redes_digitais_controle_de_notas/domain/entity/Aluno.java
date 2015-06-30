package redes_digitais_controle_de_notas.domain.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import redes_digitais_controle_de_notas.domain.entity.User;

@Entity
@Table(name="tb_aluno", schema="redesdigitais_controledenotas")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="tb_user",
		joinColumns=
			@JoinColumn(name="id_aluno", referencedColumnName="id_user")
	)
	private User user;

	public Aluno() {
		super();
		user = new User();
	}

	public Aluno(User user) {
		this();
		this.user = user;
	}

	public User getUser() {
		if (user == null) {
			user= new User();
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Aluno other = (Aluno) obj;
			if (user == null) {
				if (other.user != null) {
					return false;
				}
			} else if (!user.equals(other.user)) {
				return false;
			}
		return true;
	}

}
