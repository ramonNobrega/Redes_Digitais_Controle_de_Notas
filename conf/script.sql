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

CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_role (
	nm_role VARCHAR(30) NOT NULL,
	ts_last_update DATETIME,
	ds_role VARCHAR(255),
	fl_active BIT(1) NOT NULL,
	CONSTRAINT pk_tb_role PRIMARY KEY(nm_role)
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

CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_user (
	id_user BIGINT(19) NOT NULL,
	nm_password VARCHAR(32),
	ts_last_update DATETIME,
	nm_user VARCHAR(255) NOT NULL,
	fl_active BIT(1) NOT NULL,
	nm_email VARCHAR(255),
	CONSTRAINT pk_tb_user PRIMARY KEY(id_user)
);

CREATE TABLE IF NOT EXISTS redesdigitais_controledenotas.tb_user_role (
	nm_role VARCHAR(30) NOT NULL,
	id_user BIGINT(19) NOT NULL,
	CONSTRAINT pk_tb_user_role PRIMARY KEY(id_user, nm_role)
);


ALTER TABLE redesdigitais_controledenotas.tb_aluno ADD CONSTRAINT fk_tb_user_tb_aluno FOREIGN KEY (id_aluno) REFERENCES tb_user (id_user);
ALTER TABLE redesdigitais_controledenotas.tb_desempenho ADD CONSTRAINT fk_tb_aluno_tb_desempenho FOREIGN KEY (aluno) REFERENCES tb_aluno (id_aluno);
ALTER TABLE redesdigitais_controledenotas.tb_desempenho_bimestral ADD CONSTRAINT fk_tb_aluno_tb_desempenho FOREIGN KEY (aluno) REFERENCES tb_aluno (id_aluno);
ALTER TABLE redesdigitais_controledenotas.tb_professor ADD CONSTRAINT fk_tb_user_tb_professor FOREIGN KEY (id_professor) REFERENCES tb_user (id_user);
ALTER TABLE redesdigitais_controledenotas.tb_turma_tb_aluno ADD CONSTRAINT fk_tb_aluno_tb_turma_tb_aluno FOREIGN KEY (aluno) REFERENCES tb_aluno (id_aluno);
ALTER TABLE redesdigitais_controledenotas.tb_turma_tb_aluno ADD CONSTRAINT fk_tb_turma_tb_turma_tb_aluno FOREIGN KEY (turma) REFERENCES tb_turma (id_turma);
ALTER TABLE redesdigitais_controledenotas.tb_turma_tb_professor ADD CONSTRAINT fk_tb_user_tb_turma_tb_professor FOREIGN KEY (professor) REFERENCES tb_user (id_user);
ALTER TABLE redesdigitais_controledenotas.tb_turma_tb_professor ADD CONSTRAINT fk_tb_turma_tb_turma_tb_professor FOREIGN KEY (turma) REFERENCES tb_turma (id_turma);
ALTER TABLE redesdigitais_controledenotas.tb_user ADD CONSTRAINT uk_user_01 UNIQUE (nm_email);
ALTER TABLE redesdigitais_controledenotas.tb_user_role ADD CONSTRAINT FK_tb_user_role_id_user FOREIGN KEY (id_user) REFERENCES tb_user (id_user);
ALTER TABLE redesdigitais_controledenotas.tb_user_role ADD CONSTRAINT FK_tb_user_role_nm_role FOREIGN KEY (nm_role) REFERENCES tb_role (nm_role);
