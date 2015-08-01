package redes_digitais_controle_de_notas.domain.entity;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import redes_digitais_controle_de_notas.domain.entity.Aluno;

@Entity
@Table(name="tb_desempenho_bimestral", schema="redesdigitais_controledenotas")
public class DesempenhoBimestral implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bimestre", nullable=false)
	private Integer idBimestre;

	@Column(name="nu_bimestre", nullable=false)
	private Integer numBimestre;

	@Column(name="nota1", nullable=false, columnDefinition="DECIMAL()(2) default 0.0")
	private Double nota1;

	@Column(name="nota2", nullable=false, columnDefinition="DECIMAL()(2) default 0.0")
	private Double nota2;

	@Column(name="nota3", nullable=false, columnDefinition="DECIMAL()(2) default 0.0")
	private Double nota3;

	@Column(name="media_bimestre", columnDefinition="DECIMAL()(2) default 0.0")
	private Double mediaBimestre;

	@ManyToOne
	@JoinColumn(name="aluno", referencedColumnName="id_aluno")
	private Aluno aluno;

	public DesempenhoBimestral() {
		super();
	}

	public DesempenhoBimestral(Integer idBimestre, Integer numBimestre, Double nota1, Double nota2, Double nota3, Double mediaBimestre, Aluno aluno) {
		this();
		this.idBimestre = idBimestre;
		this.numBimestre = numBimestre;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
		this.mediaBimestre = mediaBimestre;
		this.aluno = aluno;
	}

	public Integer getIdBimestre() {
		return idBimestre;
	}

	public void setIdBimestre(Integer idBimestre) {
		this.idBimestre = idBimestre;
	}

	public Integer getNumBimestre() {
		return numBimestre;
	}

	public void setNumBimestre(Integer numBimestre) {
		this.numBimestre = numBimestre;
	}

	public Double getNota1() {
		return nota1;
	}

	public void setNota1(Double nota1) {
		this.nota1 = nota1;
	}

	public Double getNota2() {
		return nota2;
	}

	public void setNota2(Double nota2) {
		this.nota2 = nota2;
	}

	public Double getNota3() {
		return nota3;
	}

	public void setNota3(Double nota3) {
		this.nota3 = nota3;
	}

	public Double getMediaBimestre() {
		return mediaBimestre;
	}

	public void setMediaBimestre(Double mediaBimestre) {
		this.mediaBimestre = mediaBimestre;
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
		result = prime * result + ((idBimestre == null) ? 0 : idBimestre.hashCode());
		result = prime * result + ((numBimestre == null) ? 0 : numBimestre.hashCode());
		result = prime * result + ((nota1 == null) ? 0 : nota1.hashCode());
		result = prime * result + ((nota2 == null) ? 0 : nota2.hashCode());
		result = prime * result + ((nota3 == null) ? 0 : nota3.hashCode());
		result = prime * result + ((mediaBimestre == null) ? 0 : mediaBimestre.hashCode());
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
		DesempenhoBimestral other = (DesempenhoBimestral) obj;
			if (idBimestre == null) {
				if (other.idBimestre != null) {
					return false;
				}
			} else if (!idBimestre.equals(other.idBimestre)) {
				return false;
			}
			if (numBimestre == null) {
				if (other.numBimestre != null) {
					return false;
				}
			} else if (!numBimestre.equals(other.numBimestre)) {
				return false;
			}
			if (nota1 == null) {
				if (other.nota1 != null) {
					return false;
				}
			} else if (!nota1.equals(other.nota1)) {
				return false;
			}
			if (nota2 == null) {
				if (other.nota2 != null) {
					return false;
				}
			} else if (!nota2.equals(other.nota2)) {
				return false;
			}
			if (nota3 == null) {
				if (other.nota3 != null) {
					return false;
				}
			} else if (!nota3.equals(other.nota3)) {
				return false;
			}
			if (mediaBimestre == null) {
				if (other.mediaBimestre != null) {
					return false;
				}
			} else if (!mediaBimestre.equals(other.mediaBimestre)) {
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
