package redes_digitais_controle_de_notas.domain.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import redes_digitais_controle_de_notas.domain.entity.Turma;
import redes_digitais_controle_de_notas.domain.entity.Professor;

@Entity
@IdClass(TurmaProfessorKey.class)
@Table(name="tb_turma_tb_professor", schema="redesdigitais_controledenotas")
public class TurmaProfessor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="turma", referencedColumnName="id_turma")
	private Turma turma;

	@Id
	@ManyToOne
	@JoinColumn(name="professor", referencedColumnName="id_professor")
	private Professor professor;

	public TurmaProfessor() {
		super();
		turma = new Turma();
		professor = new Professor();
	}

	public TurmaProfessor(Turma turma, Professor professor) {
		this();
		this.turma = turma;
		this.professor = professor;
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

	public Professor getProfessor() {
		if (professor == null) {
			professor= new Professor();
		}
		return professor;
	}

	public void setProfessor(Professor professor) {
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
		TurmaProfessor other = (TurmaProfessor) obj;
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
