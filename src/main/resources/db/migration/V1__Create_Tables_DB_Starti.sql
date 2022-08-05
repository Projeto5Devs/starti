CREATE TABLE endereco (
  id_endereco bigint NOT NULL AUTO_INCREMENT,
  bairro varchar(50)  NOT NULL,
  cep varchar(10)  NOT NULL,
  cidade varchar(50)  NOT NULL,
  complemento varchar(30)  DEFAULT NULL,
  numero varchar(10) NOT NULL,
  rua varchar(100) NOT NULL,
  uf varchar(2) NOT NULL,
  PRIMARY KEY (id_endereco)
);

CREATE TABLE usuario (
  id_usuario bigint NOT NULL,
  password varchar(255)  NOT NULL,
  ultimo_login date DEFAULT NULL,
  username varchar(255)  NOT NULL,
  PRIMARY KEY (id_usuario),
  UNIQUE KEY UK_863n1y3x0jalatoir4325ehal (username)
);

CREATE TABLE IF NOT EXISTS empresa (
  id_empresa bigint NOT NULL,
  cnpj varchar(20) NOT NULL,
  email varchar(255) DEFAULT NULL,
  telefone varchar(255) DEFAULT NULL,
  website varchar(255) DEFAULT NULL,
  nome_fantasia varchar(80) NOT NULL,
  razao_social varchar(45) NOT NULL,
  segmento varchar(45) NOT NULL,
  id_endereco bigint NOT NULL,
  id_usuario bigint NOT NULL,
  PRIMARY KEY (id_empresa),
  UNIQUE KEY UK_nfu2qgep9eyw4f7jpxoxix8ci (email),
  KEY FK2l08mepet3wqfi840klxgv80y (id_endereco),
  KEY FK3mbkb5hcrdwg5dpo98j3htj9 (id_usuario),
  CONSTRAINT FK2l08mepet3wqfi840klxgv80y FOREIGN KEY (id_endereco) REFERENCES endereco (id_endereco),
  CONSTRAINT FK3mbkb5hcrdwg5dpo98j3htj9 FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
);



CREATE TABLE IF NOT EXISTS pessoa_fisica (
  id_pessoafisica bigint NOT NULL,
  email varchar(255) DEFAULT NULL,
  telefone varchar(255) DEFAULT NULL,
  website varchar(255) DEFAULT NULL,
  cpf varchar(14) NOT NULL,
  data_de_nascimento datetime NOT NULL,
  nome varchar(20) NOT NULL,
  sobrenome varchar(60) NOT NULL,
  id_endereco bigint NOT NULL,
  id_usuario bigint NOT NULL,
  PRIMARY KEY (id_pessoafisica),
  UNIQUE KEY UK_d70aayxv20yf3y8kofcx7fhbg (email),
  KEY FK2uw6p8x4ma9vgrhwhmh4f813s (id_endereco),
  KEY FK8jgipjg82e3xb9sk4cpofi8l0 (id_usuario),
  CONSTRAINT FK2uw6p8x4ma9vgrhwhmh4f813s FOREIGN KEY (id_endereco) REFERENCES endereco (id_endereco),
  CONSTRAINT FK8jgipjg82e3xb9sk4cpofi8l0 FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
);

CREATE TABLE IF NOT EXISTS vaga (
  id_vaga bigint NOT NULL AUTO_INCREMENT,
  cargo_vaga varchar(150) NOT NULL,
  descricao_vaga varchar(255) NOT NULL,
  modalidade_vaga varchar(15) NOT NULL,
  tipo_vaga varchar(15) NOT NULL,
  prazo_vaga datetime NOT NULL,
  salario_vaga double DEFAULT NULL,
  id_empresa bigint NOT NULL,
  PRIMARY KEY (id_vaga),
  KEY FKfaeiy9ql9lhdoxmuuorwm5w19 (id_empresa),
  CONSTRAINT FKfaeiy9ql9lhdoxmuuorwm5w19 FOREIGN KEY (id_empresa) REFERENCES empresa (id_empresa)
);

CREATE TABLE IF NOT EXISTS inscricao (
  id_pessoafisica bigint NOT NULL,
  id_vaga bigint NOT NULL,
  data_inscricao datetime DEFAULT NULL,
  PRIMARY KEY (id_pessoafisica,id_vaga),
  KEY FKaaylbb47jsr0ydv1c19bvfkkd (id_vaga),
  CONSTRAINT FKaaylbb47jsr0ydv1c19bvfkkd FOREIGN KEY (id_vaga) REFERENCES vaga (id_vaga),
  CONSTRAINT FKff1ulmqak54cv8w6g7fm7vb21 FOREIGN KEY (id_pessoafisica) REFERENCES pessoa_fisica (id_pessoafisica)
);