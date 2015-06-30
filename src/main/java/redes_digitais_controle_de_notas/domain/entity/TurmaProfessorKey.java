package redes_digitais_controle_de_notas.domain.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import redes_digitais_controle_de_notas.domain.entity.Turma;
import redes_digitais_controle_de_notas.domain.entity.Professor;

public class TurmaProfessorKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer turma;

	private Long professor;

	public TurmaProfessorKey() {
	}

	public TurmaProfessorKey(Integer turma, Long professor) {
		this();
		this.turma = turma;
		this.professor = professor;
	}

	public Integer getTurma() {
		return turma;
	}

	public void setTurma(Integer turma) {
		this.turma = turma;
	}

	public Long getProfessor() {
		return professor;
	}

	public void setProfessor(Long professor) {
		this.professor = professor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
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
		TurmaProfessorKey other = (TurmaProfessorKey) obj;
			if (turma == null) {
				if (other.turma != null) {
					return false;
				}
			} else if (!turma.equals(other.turma)) {
				return false;
			}
			if (professor == null) {
				if (other.professor != null) {
					return false;
				}
			} else if (!professor.equals(other.professor)) {
				return false;
			}
		return true;
	}

}
