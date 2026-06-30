-- RESET TOTAL
TRUNCATE TABLE info_produto, item_pedido, Pedido, Comanda, Produto, Ingrediente, Funcionario RESTART IDENTITY CASCADE;

-----------------------------------------
--             FUNCIONÁRIOS            --
-----------------------------------------

INSERT INTO Funcionario (nome, salario, data_contratacao, cargo) VALUES

('Lucas Silva', 2100.00, '2025-01-15', 'barista'),
('Mariana Costa', 2150.00, '2025-03-10', 'barista'),
('Pedro Henrique', 2100.00, '2025-06-01', 'barista'),
('Ana Beatriz', 4500.00, '2024-10-01', 'gerente'),
('Rodrigo Souza', 4200.00, '2025-02-20', 'gerente'),
('Carlos Augusto', 1850.00, '2025-01-20', 'faxineiro'),
('Maria Madalena', 1850.00, '2025-04-15', 'faxineiro');

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

('Café filtrado', 6.50, 'bebida quente'),
('Café filtrado com Leite', 7.50, 'bebida quente'),
('Capuccino', 10.00, 'bebida quente'),
('Chocolate quente', 12.00, 'bebida quente'),
('Bolo Red Velvet', 15.40, 'bolo'),
('Bolo de cenoura', 13.40, 'bolo'),
('Torta alemã', 15.40, 'bolo'),
('Icemaltine', 16.00, 'bebida fria'),
('Soda Italiana', 15.00, 'bebida fria'),
('Sanduíche Italiano', 14.50, 'outros'),
('Água', 4.50, 'bebida fria'),
('Água com gás', 5.50, 'bebida fria');

-----------------------------------------
--       RECEITAS (INFO_PRODUTO)       --
-----------------------------------------

INSERT INTO info_produto (FK_ingrediente, FK_produto, quantidade, observacao) VALUES

-- Café filtrado
(1, 1, 10, NULL),
(3, 1, 200, 'Água ideal entre 92ºC e 96ºC'),

-- Café filtrado com leite
(1, 2, 10, NULL),
(3, 2, 100, NULL),
(2, 2, 100, 'Aquecer sem deixar ferver'),

-- Capuccino
(1, 3, 10, NULL),
(2, 3, 150, 'Vaporizar para criar microespuma'),
(4, 3, 15, NULL),
(5, 3, 10, NULL),

-- Chocolate quente
(2, 4, 200, NULL),
(4, 4, 20, NULL),
(5, 4, 10, NULL),

-- Bolo Red Velvet
(6, 5, 250, NULL),
(7, 5, 3, 'Usar em temperatura ambiente'),
(8, 5, 100, NULL),
(11, 5, 20, NULL),
(12, 5, 150, 'Bater bem gelado para a cobertura'),

-- Bolo de cenoura
(6, 6, 250, NULL),
(7, 6, 3, NULL),
(10, 6, 200, NULL),
(4, 6, 40, NULL),

-- Torta alemã
(15, 7, 200, NULL),
(14, 7, 200, 'Usar lata gelada e sem o soro'),
(13, 7, 200, NULL),
(8, 7, 100, 'Ponto pomada (temperatura ambiente)'),

-- Icemaltine
(16, 8, 150, NULL),
(4, 8, 30, NULL),
(2, 8, 150, NULL),

-- Soda Italiana
(17, 9, 300, 'Completar copo com gelo antes da água'),
(18, 9, 50, NULL),

-- Sanduíche Italiano
(19, 10, 1, 'Tostar levemente na chapa'),
(20, 10, 50, NULL),
(21, 10, 50, NULL),
(22, 10, 30, NULL),
(23, 10, 20, 'Secar bem para não amolecer o pão');

-----------------------------------------
--              COMANDAS               --
-----------------------------------------

INSERT INTO Comanda (numero_mesa, data_hora_abertura, status_pgto) VALUES
(4, '2026-06-29 09:15:00', 'paga'),   
(12, '2026-06-29 10:30:00', 'paga'),  
(7, CURRENT_TIMESTAMP, 'aberta');

-----------------------------------------
--              PEDIDOS                --
-----------------------------------------

INSERT INTO Pedido (FK_comanda, FK_funcionario, data_hora_abertura, status_pedido) VALUES
-- Comanda 1 (Mesa 4)
(1, 1, '2026-06-29 09:20:00', 'atendido'),
(1, 2, '2026-06-29 09:45:00', 'atendido'),

-- Comanda 2 (Mesa 12)
(2, 3, '2026-06-29 10:35:00', 'atendido'),  

-- Comanda 3 (Mesa 7 - ABERTA)
(3, NULL, CURRENT_TIMESTAMP, 'pendente');                     

-----------------------------------------
--         ITENS DO PEDIDO             --
-----------------------------------------

INSERT INTO Item_pedido (FK_pedido, FK_produto, quantidade, preco_unitario, observacao) VALUES

-- Pedido 1 (Mesa 4)
(1, 3, 2, 10.00, 'Com bastante espuma'),  -- 2x Capuccino
(1, 5, 1, 15.40, NULL),                   -- 1x Red Velvet

-- Pedido 2 (Mesa 4)
(2, 12, 1, 5.50, 'Gelo e limão'),         -- 1x Água com gás

-- Pedido 3 (Mesa 12)
(3, 10, 1, 14.50, 'Sem tomate'),          -- 1x Sanduíche Italiano
(3, 9, 1, 15.00, 'Sabor maçã verde'),     -- 1x Soda Italiana

-- Pedido 4 (Mesa 7)
(4, 10, 2, 14.50, NULL),                  -- 1x Sanduíche Italiano
(4, 11, 1, 4.50, 'Natural');              -- 1x Água
