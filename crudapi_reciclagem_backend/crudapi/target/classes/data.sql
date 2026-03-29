-- Dados de exemplo para testar a integração Frontend-Backend
-- Limpar dados existentes antes de inserir
DELETE FROM materias;
DELETE FROM recompensas;
DELETE FROM conteudos_educativos;
DELETE FROM pontos_coleta;
-- MATERIAIS
INSERT INTO materias (nome, categoria, unidade, valor)
VALUES ('Garrafa PET', 'PLASTICO', 'KG', 2.50);
INSERT INTO materias (nome, categoria, unidade, valor)
VALUES ('Papel Sulfite', 'PAPEL', 'KG', 0.80);
INSERT INTO materias (nome, categoria, unidade, valor)
VALUES ('Papelão', 'PAPEL', 'KG', 0.60);
INSERT INTO materias (nome, categoria, unidade, valor)
VALUES ('Vidro', 'VIDRO', 'KG', 0.40);
INSERT INTO materias (nome, categoria, unidade, valor)
VALUES ('Lata de Alumínio', 'METAL', 'KG', 4.00);
INSERT INTO materias (nome, categoria, unidade, valor)
VALUES ('Ferro', 'METAL', 'KG', 0.50);
INSERT INTO materias (nome, categoria, unidade, valor)
VALUES ('Celular', 'ELETRONICO', 'UNIDADE', 5.00);
INSERT INTO materias (nome, categoria, unidade, valor)
VALUES ('Bateria', 'ELETRONICO', 'UNIDADE', 2.00);
INSERT INTO materias (nome, categoria, unidade, valor)
VALUES ('Resíduo Orgânico', 'ORGANICO', 'KG', 0.10);
-- RECOMPENSAS
INSERT INTO recompensas (
    nome,
    descricao,
    pontos_necessarios,
    ativo,
    parceiro_nome
  )
VALUES (
    'Vale Compras 50',
    'Desconto de R$ 50 em lojas parceiras',
    500,
    true,
    'Lojas Parceiras'
  );
INSERT INTO recompensas (
    nome,
    descricao,
    pontos_necessarios,
    ativo,
    parceiro_nome
  )
VALUES (
    'Vale Transporte',
    'Crédito de R$ 100 em transporte público',
    1000,
    true,
    'Transporte Municipal'
  );
INSERT INTO recompensas (
    nome,
    descricao,
    pontos_necessarios,
    ativo,
    parceiro_nome
  )
VALUES (
    'Doação para ONG',
    'Converter pontos em doação',
    300,
    true,
    'ONG EcoAmigos'
  );
INSERT INTO recompensas (
    nome,
    descricao,
    pontos_necessarios,
    ativo,
    parceiro_nome
  )
VALUES (
    'Kit Sustentável',
    'Kit com produtos sustentáveis',
    800,
    true,
    'EcoShop'
  );
INSERT INTO recompensas (
    nome,
    descricao,
    pontos_necessarios,
    ativo,
    parceiro_nome
  )
VALUES (
    'Desconto Cinema',
    'Ingresso de cinema com 50% desconto',
    400,
    true,
    'Cinemas Unidos'
  );
INSERT INTO recompensas (
    nome,
    descricao,
    pontos_necessarios,
    ativo,
    parceiro_nome
  )
VALUES (
    'Vale Livros',
    'R$ 60 em livrarias parceiras',
    600,
    true,
    'Livraria Cultural'
  );
-- CONTEÚDOS EDUCATIVOS
INSERT INTO conteudos_educativos (titulo, tipo, resumo, url)
VALUES (
    'Guia de Reciclagem',
    'ARTIGO',
    'Aprenda o que pode e não pode ser reciclado. Conheça os principais materiais recicláveis e como separar corretamente.',
    'https://www.ecorecicla.com/guia'
  );
INSERT INTO conteudos_educativos (titulo, tipo, resumo, url)
VALUES (
    'Impacto Ambiental',
    'VIDEO',
    'Entenda como a reciclagem ajuda o planeta',
    'https://www.youtube.com/watch?v=exemplo'
  );
INSERT INTO conteudos_educativos (titulo, tipo, resumo, url)
VALUES (
    'Compostagem Doméstica',
    'TUTORIAL',
    'Tutorial completo para começar em casa. Passo a passo para criar sua composteira doméstica.',
    'https://www.ecorecicla.com/compostagem'
  );
INSERT INTO conteudos_educativos (titulo, tipo, resumo, url)
VALUES (
    'Economia Circular',
    'ARTIGO',
    'O futuro da sustentabilidade. Entenda o conceito de economia circular e sua importância.',
    'https://www.ecorecicla.com/economia-circular'
  );
INSERT INTO conteudos_educativos (titulo, tipo, resumo, url)
VALUES (
    'Reciclagem de Eletrônicos',
    'VIDEO',
    'Cuidados especiais com e-lixo',
    'https://www.youtube.com/watch?v=exemplo2'
  );
INSERT INTO conteudos_educativos (titulo, tipo, resumo, url)
VALUES (
    'Reduzir, Reutilizar, Reciclar',
    'GUIA',
    'Os 3 Rs da sustentabilidade. Aprenda a aplicar os 3 Rs no seu dia a dia.',
    'https://www.ecorecicla.com/3rs'
  );