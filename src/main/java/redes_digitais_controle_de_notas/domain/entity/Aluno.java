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
	@ManyToOne(optional=false)
	@JoinColumn(name="id_aluno", referencedColumnName="id_user")
	private User user;

	@OneToMany(mappedBy="aluno", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<Desempenho> desempenhos;

	@OneToMany(mappedBy="aluno", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<DesempenhoBimestral> desempenhoBimestrais;

	@OneToMany(mappedBy="aluno", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<TurmaAluno> turmaAlunos;

	public Aluno() {
		super();
		user = new User();
	}

	public Aluno(User user, List<Desempenho> desempenhos, List<DesempenhoBimestral> desempenhoBimestrais, List<TurmaAluno> turmaAlunos) {
		this();
		this.user = user;
		this.desempenhos = desempenhos;
		this.desempenhoBimestrais = desempenhoBimestrais;
		this.turmaAlunos = turmaAlunos;
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

	public List<Desempenho> getDesempenhos() {
		if (desempenhos == null) {
			desempenhos = new ArrayList<Desempenho>();
		}
		return desempenhos;
	}

	public void setDesempenhos(List<Desempenho> desempenhos) {
		this.desempenhos = desempenhos;
	}

	public List<DesempenhoBimestral> getDesempenhoBimestrais() {
		if (desempenhoBimestrais == null) {
			desempenhoBimestrais = new ArrayList<DesempenhoBimestral>();
		}
		return desempenhoBimestrais;
	}

	public void setDesempenhoBimestrais(List<DesempenhoBimestral> desempenhoBimestrais) {
		this.desempenhoBimestrais = desempenhoBimestrais;
	}

	public List<TurmaAluno> getTurmaAlunos() {
		if (turmaAlunos == null) {
			turmaAlunos = new ArrayList<TurmaAluno>();
		}
		return turmaAlunos;
	}

	public void setTurmaAlunos(List<TurmaAluno> turmaAlunos) {
		this.turmaAlunos = turmaAlunos;
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
