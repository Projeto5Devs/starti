CREATE TABLE endereco (
  id_endereco bigint NOT NULL AUTO_INCREMENT,
  bairro varchar(50)  DEFAULT NULL,
  cep varchar(10)  DEFAULT NULL,
  cidade varchar(50)  DEFAULT NULL,
  complemento varchar(30)  DEFAULT NULL,
  numero varchar(10)  DEFAULT NULL,
  rua varchar(100) DEFAULT NULL,
  uf varchar(2) DEFAULT NULL,
  PRIMARY KEY (id_endereco)
);

CREATE TABLE usuario (
  id_usuario bigint NOT NULL,
  password varchar(255)  DEFAULT NULL,
  ultimo_login date DEFAULT NULL,
  username varchar(255)  DEFAULT NULL,
  PRIMARY KEY (id_usuario)
);

CREATE TABLE IF NOT EXISTS empresa (
  id_empresa bigint NOT NULL,
  cnpj varchar(20) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  telefone varchar(255) DEFAULT NULL,
  website varchar(255) DEFAULT NULL,
  senha varchar(255) DEFAULT NULL,
  ultimo_login datetime DEFAULT NULL,
  nome_fantasia varchar(80) DEFAULT NULL,
  razao_social varchar(45) DEFAULT NULL,
  segmento varchar(45) DEFAULT NULL,
  endereco_id_endereco bigint DEFAULT NULL,
  usuario_id_usuario bigint DEFAULT NULL,
  PRIMARY KEY (id_empresa),
  UNIQUE KEY UK_nfu2qgep9eyw4f7jpxoxix8ci (email),
  KEY FK2l08mepet3wqfi840klxgv80y (endereco_id_endereco),
  KEY FK3mbkb5hcrdwg5dpo98j3htj9 (usuario_id_usuario),
  CONSTRAINT FK2l08mepet3wqfi840klxgv80y FOREIGN KEY (endereco_id_endereco) REFERENCES endereco (id_endereco),
  CONSTRAINT FK3mbkb5hcrdwg5dpo98j3htj9 FOREIGN KEY (usuario_id_usuario) REFERENCES usuario (id_usuario)
);



CREATE TABLE IF NOT EXISTS pessoa_fisica (
  id_pessoa_fisica bigint NOT NULL,
  email varchar(255) DEFAULT NULL,
  telefone varchar(255) DEFAULT NULL,
  website varchar(255) DEFAULT NULL,
  cpf varchar(14) DEFAULT NULL,
  data_de_nascimento datetime NOT NULL,
  senha varchar(255) DEFAULT NULL,
  ultimo_login datetime DEFAULT NULL,
  nome varchar(20) DEFAULT NULL,
  sobrenome varchar(60) DEFAULT NULL,
  endereco_id_endereco bigint DEFAULT NULL,
  usuario_id_usuario bigint DEFAULT NULL,
  PRIMARY KEY (id_pessoa_fisica),
  UNIQUE KEY UK_d70aayxv20yf3y8kofcx7fhbg (email),
  KEY FK2uw6p8x4ma9vgrhwhmh4f813s (endereco_id_endereco),
  KEY FK8jgipjg82e3xb9sk4cpofi8l0 (usuario_id_usuario),
  CONSTRAINT FK2uw6p8x4ma9vgrhwhmh4f813s FOREIGN KEY (endereco_id_endereco) REFERENCES endereco (id_endereco),
  CONSTRAINT FK8jgipjg82e3xb9sk4cpofi8l0 FOREIGN KEY (usuario_id_usuario) REFERENCES usuario (id_usuario)
);

CREATE TABLE IF NOT EXISTS vaga (
  id_vaga bigint NOT NULL AUTO_INCREMENT,
  cargo_vaga varchar(150) DEFAULT NULL,
  descricao_vaga varchar(255) DEFAULT NULL,
  modalidade_vaga varchar(15) DEFAULT NULL,
  prazo_vaga datetime NOT NULL,
  salario_vaga double DEFAULT NULL,
  empresa_id_empresa bigint NOT NULL,
  PRIMARY KEY (id_vaga),
  KEY FKfaeiy9ql9lhdoxmuuorwm5w19 (empresa_id_empresa),
  CONSTRAINT FKfaeiy9ql9lhdoxmuuorwm5w19 FOREIGN KEY (empresa_id_empresa) REFERENCES empresa (id_empresa)
);

CREATE TABLE IF NOT EXISTS inscricao (
  pfisica_id_pfisica bigint NOT NULL,
  vaga_id_vaga bigint NOT NULL,
  data_inscricao datetime DEFAULT NULL,
  pessoafisica_id_pessoa_fisica bigint NOT NULL,
  PRIMARY KEY (pfisica_id_pfisica,vaga_id_vaga),
  KEY FK9n35fjm9iegmtwewtovh70li1 (pessoafisica_id_pessoa_fisica),
  KEY FKi91r7ign2rnas6dtj2xgbp4b8 (vaga_id_vaga),
  CONSTRAINT FK9n35fjm9iegmtwewtovh70li1 FOREIGN KEY (pessoafisica_id_pessoa_fisica) REFERENCES pessoa_fisica (id_pessoa_fisica),
  CONSTRAINT FKi91r7ign2rnas6dtj2xgbp4b8 FOREIGN KEY (vaga_id_vaga) REFERENCES vaga (id_vaga)
);