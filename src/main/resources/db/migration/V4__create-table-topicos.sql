CREATE TABLE topicos (
                         id           BIGINT          NOT NULL AUTO_INCREMENT,
                         titulo       VARCHAR(100)    NOT NULL,
                         mensagem     TEXT            NOT NULL,
                         data_criacao DATETIME        NOT NULL DEFAULT NOW(),
                         status       VARCHAR(20)     NOT NULL DEFAULT 'ABERTO',
                         autor_id     BIGINT          NOT NULL,
                         curso_id     BIGINT          NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (autor_id) REFERENCES usuarios(id),
                         FOREIGN KEY (curso_id) REFERENCES cursos(id)
);