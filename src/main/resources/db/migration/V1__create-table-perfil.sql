CREATE TABLE perfil (
                        id      BIGINT          NOT NULL AUTO_INCREMENT,
                        nome    VARCHAR(100)    NOT NULL,
                        PRIMARY KEY (id)
);

INSERT INTO perfil (nome) VALUES ('PROFESSOR');
INSERT INTO perfil (nome) VALUES ('ALUNO');
INSERT INTO perfil (nome) VALUES ('ADMINISTRADOR');

CREATE TABLE usuarios_perfis (
                                 usuario_id  BIGINT  NOT NULL,
                                 perfil_id   BIGINT  NOT NULL,
                                 PRIMARY KEY (usuario_id, perfil_id)
);