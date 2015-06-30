package redes_digitais_controle_de_notas.domain.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="tb_turma", schema="redesdigitais_controledenotas")
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_turma", nullable=false)
	private Integer idTurma;

	@Column(name="nm_turma", length=100, nullable=false)
	private String nomeTurma;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="tb_turma_tb_aluno",
		joinColumns=
			@JoinColumn(name="turma", referencedColumnName="id_turma")
		,
		inverseJoinColumns=
			@JoinColumn(name="aluno", referencedColumnName="id_aluno")
	)
	private List<Aluno> alunos;

	public Turma() {
		super();
	}

	public Turma(Integer idTurma, String nomeTurma, List<Aluno> alunos) {
		this();
		this.idTurma = idTurma;
		this.nomeTurma = nomeTurma;
		this.alunos = alunos;
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTurma == null) ? 0 : idTurma.hashCode());
		result = prime * result + ((nomeTurma == null) ? 0 : nomeTurma.hashCode());
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
		Turma other = (Turma) obj;
			if (idTurma == null) {
				if (other.idTurma != null) {
					return false;
				}
			} else if (!idTurma.equals(other.idTurma)) {
				return false;
			}
			if (nomeTurma == null) {
				if (other.nomeTurma != null) {
					return false;
				}
			} else if (!nomeTurma.equals(other.nomeTurma)) {
				return false;
			}
		return true;
	}

}
