package redes_digitais_controle_de_notas.domain.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import redes_digitais_controle_de_notas.domain.entity.Turma;
import redes_digitais_controle_de_notas.domain.entity.Aluno;

@Entity
@IdClass(TurmaAlunoKey.class)
@Table(name="tb_turma_tb_aluno", schema="redesdigitais_controledenotas")
public class TurmaAluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="turma", referencedColumnName="id_turma")
	private Turma turma;

	@Id
	@ManyToOne
	@JoinColumn(name="aluno", referencedColumnName="id_aluno")
	private Aluno aluno;

	public TurmaAluno() {
		super();
		turma = new Turma();
		aluno = new Aluno();
	}

	public TurmaAluno(Turma turma, Aluno aluno) {
		this();
		this.turma = turma;
		this.aluno = aluno;
	}

	public Turma getTurma() {
		if (turma == null) {
			turma= new Turma();
		}
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		if (aluno == null) {
			aluno= new Aluno();
		}
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
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
		TurmaAluno other = (TurmaAluno) obj;
			if (turma == null) {
				if (other.turma != null) {
					return false;
				}
			} else if (!turma.equals(other.turma)) {
				return false;
			}
			if (aluno == null) {
				if (other.aluno != null) {
					return false;
				}
			} else if (!aluno.equals(other.aluno)) {
				return false;
			}
		return true;
	}

}
