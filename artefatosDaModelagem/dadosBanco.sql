-----------------------------------------
--             INGREDIENTES            --
-----------------------------------------
INSERT INTO Ingrediente (nome, descricao, quantidade, unidade_medida) VALUES 

('Café', 'Pó de café', 5000, 'g'),
('Leite', 'Leite integral', 20, 'L'),
('Água', 'Água filtrada', 50000, 'mL'),
('Chocolate em pó', 'Chocolate para bebidas e sobremesas', 2000, 'g'),
('Açúcar', 'Açúcar refinado', 5000, 'g'),
('Farinha de trigo', 'Farinha para bolos e massas', 10000, 'g'),
('Ovos', 'Ovos de galinha', 300, 'un'),
('Manteiga', 'Manteiga sem sal', 3000, 'g'),
('Fermento', 'Fermento para bolos', 500, 'g'),
('Cenoura', 'Cenoura fresca', 3000, 'g'),
('Cacau', 'Cacau em pó', 1000, 'g'),
('Cream Cheese', 'Cream cheese para Red Velvet', 2000, 'g'),
('Leite condensado', 'Leite condensado para sobremesas', 2000, 'g'),
('Creme de leite', 'Creme de leite para sobremesas', 2000, 'g'),
('Bolacha maisena', 'Base de tortas e sobremesas', 3000, 'g'),
('Sorvete de creme', 'Sorvete utilizado em bebidas geladas', 5000, 'g'),
('Água com gás', 'Base para soda italiana', 10000, 'mL'),
('Xarope de frutas', 'Xarope para soda italiana', 2000, 'mL'),
('Pão italiano', 'Pão para sanduíches', 50, 'un'),
('Presunto', 'Fatiado para sanduíches', 2000, 'g'),
('Queijo mussarela', 'Queijo para sanduíches', 2000, 'g'),
('Tomate', 'Tomate fresco', 2000, 'g'),
('Alface', 'Alface fresca', 1000, 'g');

-----------------------------------------
--               PRODUTOS              --
-----------------------------------------

INSERT INTO Produto (nome, preco, categoria) VALUES 

('Café filtrado', 6.50, 'BEBIDA_QUENTE')
('Café filtrado com Leite', 7.50, 'BEBIDA_QUENTE')
('Capuccino', 10.00, 'BEBIDA_QUENTE')
('Chocolate quente', 12.00, 'BEBIDA_QUENTE')
('Bolo Red Velvet', 15.40, 'BOLO')
('Bolo de cenoura', 13.40, 'BOLO')
('Torta alemã', 15.40, 'BOLO')
('Icemaltine', 16.00, 'BEBIDA_FRIA')
('Soda Italiana', 15.00, 'BEBIDA_FRIA')
('Sanduíche Italiano', 14.50, 'OUTRO')
('Água', 4.50, 'BEBIDA_FRIA')
('Água com gás', 5.50, 'BEBIDA_FRIA')

-----------------------------------------
--       RECEITAS (ITEM_PRODUTO)       --
-----------------------------------------

-- Café filtrado
(1, 1, 10, 'g de café'),
(24, 1, 200, 'mL de água'),

-- Café filtrado com leite
(1, 2, 10, 'g de café'),
(24, 2, 100, 'mL de água'),
(2, 2, 100, 'mL de leite'),

-- Capuccino
(1, 3, 10, 'g de café'),
(2, 3, 150, 'mL de leite'),
(3, 3, 15, 'g de chocolate em pó'),
(4, 3, 10, 'g de açúcar'),

-- Chocolate quente
(2, 4, 200, 'mL de leite'),
(3, 4, 20, 'g de chocolate em pó'),
(4, 4, 10, 'g de açúcar'),

-- Bolo Red Velvet
(5, 5, 250, 'g de farinha'),
(6, 5, 3, 'ovos'),
(7, 5, 100, 'g de manteiga'),
(10, 5, 20, 'g de cacau'),
(11, 5, 150, 'g de cream cheese'),

-- Bolo de cenoura
(5, 6, 250, 'g de farinha'),
(6, 6, 3, 'ovos'),
(9, 6, 200, 'g de cenoura'),
(3, 6, 40, 'g de chocolate em pó'),

-- Torta alemã
(14, 7, 200, 'g de bolacha'),
(13, 7, 200, 'g de creme de leite'),
(12, 7, 200, 'g de leite condensado'),
(7, 7, 100, 'g de manteiga'),

-- Icemaltine
(15, 8, 150, 'g de sorvete'),
(16, 8, 30, 'g de achocolatado'),
(2, 8, 150, 'mL de leite'),

-- Soda Italiana
(17, 9, 300, 'mL de água com gás'),
(18, 9, 50, 'mL de xarope'),

-- Sanduíche Italiano
(19, 10, 1, 'unidade'),
(20, 10, 50, 'g de presunto'),
(21, 10, 50, 'g de queijo'),
(22, 10, 30, 'g de tomate'),
(23, 10, 20, 'g de alface');