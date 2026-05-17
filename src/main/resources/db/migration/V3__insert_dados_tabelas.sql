INSERT INTO usu_usuario (usu_tx_nome, usu_tx_email, usu_tx_login, usu_tx_senha, usu_tx_perfil, usu_tx_suap_id)
VALUES ('Coordenador Admin', 'admin@ifs.edu.br', 'admin', 'senha123', 'COORDENADOR', 'SUAP-COORD-01'),
       ('Professor Silva (Java/Spring)', 'silva@ifs.edu.br', 'silva.prof', 'senha123', 'PROFESSOR', 'SUAP-PROF-01'),
       ('Professora Helena (Mobile/Flutter)', 'helena@ifs.edu.br', 'helena.prof', 'senha123', 'PROFESSOR',
        'SUAP-PROF-02'),
       ('Professor Roberto (Redes/Infra)', 'roberto@ifs.edu.br', 'roberto.prof', 'senha123', 'PROFESSOR',
        'SUAP-PROF-03'),
       ('Luis Eduardo Santos Batista', 'luis.batista@aluno.ifs.edu.br', 'luis.eduardo', 'senha123', 'ALUNO',
        'SUAP-ALU-01'),
       ('Ana Oliveira', 'ana.oliveira@aluno.ifs.edu.br', 'ana.oliveira', 'senha123', 'ALUNO', 'SUAP-ALU-02'),
       ('Carlos Mendes', 'carlos.mendes@aluno.ifs.edu.br', 'carlos.mendes', 'senha123', 'ALUNO', 'SUAP-ALU-03'),
       ('Beatriz Souza', 'beatriz.souza@aluno.ifs.edu.br', 'beatriz.souza', 'senha123', 'ALUNO', 'SUAP-ALU-04');


INSERT INTO pel_periodo_letivo (pel_nr_ano, pel_nr_semestre, pel_tx_descricao)
VALUES (2025, 2, 'Período 2025.2'),
       (2026, 1, 'Período 2026.1'),
       (2026, 2, 'Período 2026.2');


INSERT INTO cur_curso (cur_tx_nome, cur_tx_codigo_suap)
VALUES ('Sistemas de Informação', 'CURSO-SI-IFS'),
       ('Redes de Computadores', 'CURSO-RC-IFS');


-- Associando usuários (IDs 5 a 8) aos Cursos
INSERT INTO alu_aluno (usu_nr_id, cur_nr_id, alu_tx_matricula, alu_tx_suap_id)
VALUES (5, 1, '20261SI001', 'ALU-001'), -- Luis (SI)
       (6, 1, '20261SI002', 'ALU-002'), -- Ana (SI - Equipe)
       (7, 1, '20261SI003', 'ALU-003'), -- Carlos (SI - Equipe)
       (8, 2, '20261RC001', 'ALU-004');

INSERT INTO pro_professor (usu_nr_id, pro_tx_matricula_siape, pro_tx_suap_id)
VALUES (2, 'SIAPE111', 'PROF-001'), -- Silva
       (3, 'SIAPE222', 'PROF-002'), -- Helena
       (4, 'SIAPE333', 'PROF-003');

INSERT INTO dis_disciplina (dis_tx_nome, dis_tx_codigo, dis_tx_codigo_suap, dis_nr_carga_horaria)
VALUES ('Programação Web (Spring Boot)', 'DISC-PW', 'SUAP-PW', 80),
       ('Programação Mobile (Flutter e Dart)', 'DISC-PM', 'SUAP-PM', 80),
       ('Infraestrutura e Redes de Computadores', 'DISC-IR', 'SUAP-IR', 60),
       ('Qualidade de Software (Normas ISO)', 'DISC-QS', 'SUAP-QS', 60);


INSERT INTO tur_turma (dis_nr_id, pro_nr_id, pel_nr_id, tur_tx_descricao, tur_tx_codigo_suap)
VALUES (1, 1, 2, 'Turma A - Programação Web 2026.1', 'TURMA-PW-20261'),    -- Spring Boot com Prof Silva
       (2, 2, 2, 'Turma A - Programação Mobile 2026.1', 'TURMA-PM-20261'), -- Flutter com Prof Helena
       (3, 3, 2, 'Turma Única - Redes 2026.1', 'TURMA-REDES-20261');

INSERT INTO mat_matricula_turma (alu_nr_id, tur_nr_id, mat_tx_situacao, mat_dt_matricula)
VALUES (1, 1, 'MATRICULADO', '2026-02-15'), -- Luis em Web/Spring
       (2, 1, 'MATRICULADO', '2026-02-15'), -- Ana em Web/Spring
       (3, 1, 'MATRICULADO', '2026-02-16'), -- Carlos em Web/Spring
       (1, 2, 'MATRICULADO', '2026-02-15'), -- Luis em Mobile/Flutter
       (1, 3, 'MATRICULADO', '2026-02-15'), -- Luis em Redes
       (4, 3, 'MATRICULADO', '2026-02-16');

INSERT INTO not_nota (mat_nr_id, not_tx_descricao, not_nr_valor, not_nr_peso, not_dt_avaliacao)
VALUES (1, 'POC Projeto SmartCar - Backend', 9.50, 1.00, '2026-04-10'), -- Luis (Web)
       (2, 'POC Projeto SmartCar - Backend', 9.50, 1.00, '2026-04-10'), -- Ana (Web)
       (3, 'POC Projeto SmartCar - Backend', 9.50, 1.00, '2026-04-10'), -- Carlos (Web)
       (4, 'App de Lista de Tarefas', 10.00, 1.00, '2026-04-15'),       -- Luis (Mobile)
       (5, 'Topologia no Cisco Packet Tracer', 8.50, 1.00, '2026-03-20');

INSERT INTO fal_falta (mat_nr_id, fal_dt_aula, fal_nr_quantidade, fal_tx_justificativa)
VALUES (5, '2026-03-05', 2, 'Manutenção do Servidor Dell PowerEdge'), -- Luis (Redes)
       (3, '2026-04-12', 1, NULL);

INSERT INTO cht_chat_turma (tur_nr_id, cht_tx_titulo)
VALUES (1, 'Fórum Principal - Projetos Web'),
       (3, 'Dúvidas de Infraestrutura e TCP/IP');


INSERT INTO msg_chat_mensagem (cht_nr_id, usu_nr_id, msg_tx_mensagem)
VALUES (1, 2, 'Boa noite, pessoal! Lembrem-se que a entrega da API está próxima.'),
       (1, 5,
        'Professor, nossa equipe ajustou a lógica do SmartCar. A venda acima da tabela FIPE agora é tratada como um cenário positivo para o vendedor na avaliação.'),
       (1, 6, 'Isso mesmo, já configuramos as models e o repositório.'),
       (1, 2, 'Excelente abordagem técnica para o negócio. Aguardo o pull request.'),
       (2, 5, 'Professor Roberto, o Wireshark não está capturando os pacotes no laboratório hoje.');
