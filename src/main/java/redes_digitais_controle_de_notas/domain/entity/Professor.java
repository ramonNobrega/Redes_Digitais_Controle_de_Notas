package redes_digitais_controle_de_notas.domain.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import redes_digitais_controle_de_notas.domain.entity.User;

@Entity
@Table(name="tb_professor", schema="redesdigitais_controledenotas")
public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="id_professor", referencedColumnName="id_user")
	private User user;

	@Column(name="disciplina", length=100, nullable=false)
	private String disciplina;

	@OneToMany(mappedBy="professor", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<TurmaProfessor> turmaProfessores;

	public Professor() {
		super();
		user = new User();
	}

	public Professor(User user, String disciplina, List<TurmaProfessor> turmaProfessores) {
		this();
		this.user = user;
		this.disciplina = disciplina;
		this.turmaProfessores = turmaProfessores;
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

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public List<TurmaProfessor> getTurmaProfessores() {
		if (turmaProfessores == null) {
			turmaProfessores = new ArrayList<TurmaProfessor>();
		}
		return turmaProfessores;
	}

	public void setTurmaProfessores(List<TurmaProfessor> turmaProfessores) {
		this.turmaProfessores = turmaProfessores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
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
		Professor other = (Professor) obj;
			if (user == null) {
				if (other.user != null) {
					return false;
				}
			} else if (!user.equals(other.user)) {
				return false;
			}
			if (disciplina == null) {
				if (other.disciplina != null) {
					return false;
				}
			} else if (!disciplina.equals(other.disciplina)) {
				return false;
			}
		return true;
	}

}
