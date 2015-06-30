package redes_digitais_controle_de_notas.domain.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import redes_digitais_controle_de_notas.domain.entity.Turma;
import redes_digitais_controle_de_notas.domain.entity.Aluno;

public class TurmaAlunoKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer turma;

	private Long aluno;

	public TurmaAlunoKey() {
	}

	public TurmaAlunoKey(Integer turma, Long aluno) {
		this();
		this.turma = turma;
		this.aluno = aluno;
	}

	public Integer getTurma() {
		return turma;
	}

	public void setTurma(Integer turma) {
		this.turma = turma;
	}

	public Long getAluno() {
		return aluno;
	}

	public void setAluno(Long aluno) {
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
		TurmaAlunoKey other = (TurmaAlunoKey) obj;
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
