CREATE TABLE usu_usuario (
    usu_nr_id       BIGINT AUTO_INCREMENT NOT NULL,
    usu_tx_nome     VARCHAR(150) NOT NULL,
    usu_tx_email    VARCHAR(150) NOT NULL,
    usu_tx_login    VARCHAR(100) NOT NULL,
    usu_tx_senha    VARCHAR(255),
    usu_tx_perfil   VARCHAR(30) NOT NULL,
    usu_tx_suap_id  VARCHAR(100),
    usu_tx_status   CHAR(1)  DEFAULT 'A' NOT NULL,
    usu_dt_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT usu_usuario_pk PRIMARY KEY (usu_nr_id),
    CONSTRAINT usu_email_uk UNIQUE (usu_tx_email),
    CONSTRAINT usu_login_uk UNIQUE (usu_tx_login)
);

CREATE TABLE pel_periodo_letivo
(
    pel_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    pel_nr_ano INT NOT NULL,
    pel_nr_semestre  INT NOT NULL,
    pel_tx_descricao VARCHAR(50) NOT NULL,
    pel_tx_status CHAR(1) DEFAULT 'A' NOT NULL,
    CONSTRAINT pel_periodo_letivo_pk PRIMARY KEY (pel_nr_id),
    CONSTRAINT pel_periodo_letivo_uk UNIQUE (pel_nr_ano, pel_nr_semestre)
);

CREATE TABLE cur_curso
(
    cur_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    cur_tx_nome VARCHAR(150) NOT NULL,
    cur_tx_codigo_suap VARCHAR(100),
    cur_tx_status CHAR(1) DEFAULT 'A' NOT NULL,
    CONSTRAINT cur_curso_pk PRIMARY KEY (cur_nr_id)
);

CREATE TABLE alu_aluno
(
    alu_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    usu_nr_id BIGINT NOT NULL,
    cur_nr_id BIGINT,
    alu_tx_matricula VARCHAR(50) NOT NULL,
    alu_tx_suap_id VARCHAR(100),
    alu_tx_status CHAR(1) DEFAULT 'A' NOT NULL,
    CONSTRAINT alu_aluno_pk PRIMARY KEY (alu_nr_id),
    CONSTRAINT alu_matricula_uk UNIQUE (alu_tx_matricula),
    CONSTRAINT alu_usuario_fk FOREIGN KEY (usu_nr_id) REFERENCES usu_usuario (usu_nr_id),
    CONSTRAINT alu_curso_fk FOREIGN KEY (cur_nr_id) REFERENCES cur_curso (cur_nr_id)
);

CREATE TABLE pro_professor
(
    pro_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    usu_nr_id BIGINT NOT NULL,
    pro_tx_matricula_siape VARCHAR(50),
    pro_tx_suap_id VARCHAR(100),
    pro_tx_status CHAR(1) DEFAULT 'A' NOT NULL,
    CONSTRAINT pro_professor_pk PRIMARY KEY (pro_nr_id),
    CONSTRAINT pro_usuario_fk FOREIGN KEY (usu_nr_id) REFERENCES usu_usuario (usu_nr_id)
);

CREATE TABLE dis_disciplina
(
    dis_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    dis_tx_nome VARCHAR(150) NOT NULL,
    dis_tx_codigo VARCHAR(50),
    dis_tx_codigo_suap VARCHAR(100),
    dis_nr_carga_horaria INT,
    dis_tx_status CHAR(1) DEFAULT 'A' NOT NULL,
    CONSTRAINT dis_disciplina_pk PRIMARY KEY (dis_nr_id)
);

CREATE TABLE tur_turma
(
    tur_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    dis_nr_id BIGINT NOT NULL,
    pro_nr_id BIGINT,
    pel_nr_id BIGINT NOT NULL,
    tur_tx_descricao VARCHAR(150) NOT NULL,
    tur_tx_codigo_suap VARCHAR(100),
    tur_tx_status CHAR(1) DEFAULT 'A' NOT NULL,
    CONSTRAINT tur_turma_pk PRIMARY KEY (tur_nr_id),
    CONSTRAINT tur_disciplina_fk FOREIGN KEY (dis_nr_id) REFERENCES dis_disciplina (dis_nr_id),
    CONSTRAINT tur_professor_fk FOREIGN KEY (pro_nr_id) REFERENCES pro_professor (pro_nr_id),
    CONSTRAINT tur_periodo_fk FOREIGN KEY (pel_nr_id) REFERENCES pel_periodo_letivo (pel_nr_id)
);

CREATE TABLE mat_matricula_turma
(
    mat_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    alu_nr_id BIGINT NOT NULL,
    tur_nr_id BIGINT NOT NULL,
    mat_tx_situacao VARCHAR(50),
    mat_dt_matricula DATE,
    mat_tx_status CHAR(1) DEFAULT 'A' NOT NULL,
    CONSTRAINT mat_matricula_turma_pk PRIMARY KEY (mat_nr_id),
    CONSTRAINT mat_aluno_turma_uk UNIQUE (alu_nr_id, tur_nr_id),
    CONSTRAINT mat_aluno_fk FOREIGN KEY (alu_nr_id) REFERENCES alu_aluno (alu_nr_id),
    CONSTRAINT mat_turma_fk FOREIGN KEY (tur_nr_id) REFERENCES tur_turma (tur_nr_id)
);

CREATE TABLE not_nota
(
    not_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    mat_nr_id BIGINT NOT NULL,
    not_tx_descricao VARCHAR(100) NOT NULL,
    not_nr_valor DECIMAL(5, 2),
    not_nr_peso DECIMAL(5, 2),
    not_dt_avaliacao DATE,
    not_tx_codigo_suap VARCHAR(100),
    CONSTRAINT not_nota_pk PRIMARY KEY (not_nr_id),
    CONSTRAINT not_matricula_fk FOREIGN KEY (mat_nr_id) REFERENCES mat_matricula_turma (mat_nr_id)
);

CREATE TABLE fal_falta
(
    fal_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    mat_nr_id BIGINT NOT NULL,
    fal_dt_aula DATE NOT NULL,
    fal_nr_quantidade INT DEFAULT 1 NOT NULL,
    fal_tx_justificativa TEXT,
    fal_tx_codigo_suap VARCHAR(100),
    CONSTRAINT fal_falta_pk PRIMARY KEY (fal_nr_id),
    CONSTRAINT fal_matricula_fk FOREIGN KEY (mat_nr_id) REFERENCES mat_matricula_turma (mat_nr_id)
);

CREATE TABLE cht_chat_turma
(
    cht_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    tur_nr_id BIGINT NOT NULL,
    cht_tx_titulo VARCHAR(150),
    cht_tx_status CHAR(1) DEFAULT 'A' NOT NULL,
    cht_dt_criacao DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT cht_chat_turma_pk PRIMARY KEY (cht_nr_id),
    CONSTRAINT cht_turma_fk FOREIGN KEY (tur_nr_id) REFERENCES tur_turma (tur_nr_id)
);

CREATE TABLE msg_chat_mensagem
(
    msg_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    cht_nr_id BIGINT NOT NULL,
    usu_nr_id BIGINT NOT NULL,
    msg_tx_mensagem TEXT NOT NULL,
    msg_dt_envio DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    msg_tx_status CHAR(1) DEFAULT 'A' NOT NULL,
    CONSTRAINT msg_chat_mensagem_pk PRIMARY KEY (msg_nr_id),
    CONSTRAINT msg_chat_fk FOREIGN KEY (cht_nr_id) REFERENCES cht_chat_turma (cht_nr_id),
    CONSTRAINT msg_usuario_fk FOREIGN KEY (usu_nr_id) REFERENCES usu_usuario (usu_nr_id)
);

CREATE TABLE int_integracao_suap
(
    int_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    int_tx_tipo_importacao VARCHAR(50) NOT NULL,
    int_dt_inicio DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    int_dt_fim DATETIME,
    int_tx_status VARCHAR(30) NOT NULL,
    int_tx_mensagem TEXT,
    int_nr_quantidade_registros INT DEFAULT 0,
    CONSTRAINT int_integracao_suap_pk PRIMARY KEY (int_nr_id)
);

CREATE TABLE inl_integracao_suap_log
(
    inl_nr_id BIGINT AUTO_INCREMENT NOT NULL,
    int_nr_id BIGINT NOT NULL,
    inl_tx_entidade VARCHAR(100) NOT NULL,
    inl_tx_registro_suap_id VARCHAR(100),
    inl_tx_operacao VARCHAR(30),
    inl_tx_status VARCHAR(30) NOT NULL,
    inl_tx_mensagem TEXT,
    inl_dt_log DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT inl_integracao_suap_log_pk PRIMARY KEY (inl_nr_id),
    CONSTRAINT inl_integracao_fk FOREIGN KEY (int_nr_id) REFERENCES int_integracao_suap (int_nr_id)
);