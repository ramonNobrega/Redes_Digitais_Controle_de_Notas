CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_aluno (
	id_aluno BIGINT(20) NOT NULL,
	CONSTRAINT pk_tb_aluno PRIMARY KEY(id_aluno)
);

CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_desempenho (
	id_desempenho INT(10) NOT NULL AUTO_INCREMENT,
	media_parcial DECIMAL(2,1) NOT NULL DEFAULT 0.0,
	prova_final DECIMAL(2,1) NOT NULL DEFAULT 0.0,
	media_final DECIMAL(2,1) NOT NULL DEFAULT 0.0,
	situacao VARCHAR(10) NOT NULL DEFAULT 'REPROVADO',
	aluno BIGINT(20),
	CONSTRAINT pk_tb_desempenho PRIMARY KEY(id_desempenho)
);

CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_desempenho_bimestral (
	id_bimestre INT(10) NOT NULL AUTO_INCREMENT,
	nu_bimestre INT(1) NOT NULL,
	nota1 DECIMAL(2,1) NOT NULL DEFAULT 0.0,
	nota2 DECIMAL(2,1) NOT NULL DEFAULT 0.0,
	nota3 DECIMAL(2,1) NOT NULL DEFAULT 0.0,
	media_bimestre DECIMAL(2,1) NOT NULL DEFAULT 0.0,
	aluno BIGINT(20),
	CONSTRAINT pk_tb_desempenho_bimestral PRIMARY KEY(id_bimestre)
);

CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_professor (
	id_professor BIGINT(20) NOT NULL,
	disciplina VARCHAR(100) NOT NULL,
	CONSTRAINT pk_tb_professor PRIMARY KEY(id_professor)
);

CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_turma (
	id_turma INT(10) NOT NULL,
	nm_turma VARCHAR(100) NOT NULL,
	CONSTRAINT pk_tb_turma PRIMARY KEY(id_turma)
);

CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_turma_tb_aluno (
	turma INT(10),
	aluno BIGINT(20),
	CONSTRAINT pk_tb_turma_tb_aluno PRIMARY KEY(turma, aluno)
);

CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_turma_tb_professor (
	turma INT(10),
	professor BIGINT(20),
	CONSTRAINT pk_tb_turma_tb_professor PRIMARY KEY(turma, professor)
);

ALTER TABLE redesdigitais_controledenotas.tb_aluno ADD CONSTRAINT fk_tb_user_tb_aluno FOREIGN KEY (id_aluno) REFERENCES tb_user (id_user);
ALTER TABLE redesdigitais_controledenotas.tb_desempenho ADD CONSTRAINT fk_tb_aluno_tb_desempenho FOREIGN KEY (aluno) REFERENCES tb_aluno (id_aluno);
ALTER TABLE redesdigitais_controledenotas.tb_desempenho_bimestral ADD CONSTRAINT fk_tb_aluno_tb_desempenho FOREIGN KEY (aluno) REFERENCES tb_aluno (id_aluno);
ALTER TABLE redesdigitais_controledenotas.tb_professor ADD CONSTRAINT fk_tb_user_tb_professor FOREIGN KEY (id_professor) REFERENCES tb_user (id_user);
ALTER TABLE redesdigitais_controledenotas.tb_turma_tb_aluno ADD CONSTRAINT fk_tb_aluno_tb_turma_tb_aluno FOREIGN KEY (aluno) REFERENCES tb_aluno (id_aluno);
ALTER TABLE redesdigitais_controledenotas.tb_turma_tb_aluno ADD CONSTRAINT fk_tb_turma_tb_turma_tb_aluno FOREIGN KEY (turma) REFERENCES tb_turma (id_turma);
ALTER TABLE redesdigitais_controledenotas.tb_turma_tb_professor ADD CONSTRAINT fk_tb_user_tb_turma_tb_professor FOREIGN KEY (professor) REFERENCES tb_user (id_user);
ALTER TABLE redesdigitais_controledenotas.tb_turma_tb_professor ADD CONSTRAINT fk_tb_turma_tb_turma_tb_professor FOREIGN KEY (turma) REFERENCES tb_turma (id_turma);
