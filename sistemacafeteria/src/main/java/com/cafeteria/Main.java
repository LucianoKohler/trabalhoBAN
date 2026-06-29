package com.cafeteria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.cafeteria.SQL.InfoProdutoDAO;
import com.cafeteria.SQL.ComandaDAO;
import com.cafeteria.SQL.FuncionarioDAO;
import com.cafeteria.SQL.IngredienteDAO;
import com.cafeteria.SQL.PedidoDAO;
import com.cafeteria.SQL.ProdutoDAO;
import com.cafeteria.dados.Comanda;
import com.cafeteria.dados.Funcionario;
import com.cafeteria.dados.Ingrediente;
import com.cafeteria.dados.Pedido;
import com.cafeteria.dados.Produto;


public class Main {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);

        while(true){
            int escolha = menu(s);
            if(escolha == -1){
                break;
            }
        }

        s.close();

    }

    /* AUXILIARES + FUNÇÕES SOBRE INGREDIENTES */
    public static void mostraTodosIngredientes(){
        List<Ingrediente> i = IngredienteDAO.selectAll();
        for(Ingrediente ing : i){
            System.out.println(ing);
        }
        System.out.println();
    }
    public static int escolheIngrediente(Scanner s){
        System.out.println("Escolha um ingrediente: ");
        mostraTodosIngredientes();
        System.out.print("Sua escolha: ");
        return s.nextInt();
    }
    public static void menuEscolhaIngrediente(Scanner s){
        System.out.println("1. Adicionar ingrediente");
        System.out.println("2. Remover ingrediente");
        System.out.println("3. Mostrar todos os ingredientes");
        System.out.println("4. Mostrar um ingrediente");
        System.out.println("5. Voltar");
           
        int escolha = Integer.parseInt(s.nextLine());

        switch (escolha) {
            case 1:
                Ingrediente novoIng = new Ingrediente();
                System.out.println("Digite o nome do ingrediente:");
                String nome = s.nextLine();
                novoIng.setNome(nome);
                System.out.println("Digite a descrição do ingrediente:");
                String desc = s.nextLine();
                novoIng.setDescricao(desc);
                System.out.println("Digite a quantidade do ingrediente:");
                float quant = Float.parseFloat(s.nextLine());
                novoIng.setQuantidade(quant);
                System.out.println("Digite a unidade de medida da quantidade:");
                String unidade = s.nextLine();
                novoIng.setUnidade_medida(unidade);

                IngredienteDAO.insereIngrediente(novoIng);

                return;
            case 2:
                mostraTodosIngredientes();
                System.out.println("Digite o id do ingrediente a ser removido:");
                int id = Integer.parseInt(s.nextLine());

                IngredienteDAO.deletaIngredientePorID(id);
                return;
            case 3:
                mostraTodosIngredientes();
                return;
            case 4:
                System.out.println("Digite o id do ingrediente a ser mostrado:");
                int idd = Integer.parseInt(s.nextLine());
                Ingrediente ing = IngredienteDAO.procuraIngredientePorID(idd);

                System.out.println(ing);
                return;
            case 5:
                return;
            default:
                System.out.println("Entrada inválida!");
                return;

        }
    }

    /* AUXILIARES + FUNÇÕES SOBRE PRODUTOS */
    public static void mostraTodosProdutos(){
        List<Produto> list = ProdutoDAO.selectAll();
        for(Produto p : list){
            System.out.println(p);
        }
        System.out.println();
    }
    public static int escolheProduto(Scanner s){
        System.out.println("Escolha um produto: ");
        mostraTodosProdutos();
        System.out.print("Sua escolha: ");
        return s.nextInt();
    }
    public static void menuEscolhaProduto(Scanner s){
        System.out.println("1. Adicionar produto");
        System.out.println("2. Remover produto");
        System.out.println("3. Mostrar todos os produtos");
        System.out.println("4. Mostrar um produto");
        System.out.println("5. Voltar");
           
        int escolha = Integer.parseInt(s.nextLine());

        switch (escolha) {
            case 1:
                Produto novoProd = new Produto();
                System.out.println("Digite o nome do produto:");
                String nome = s.nextLine();
                novoProd.setNome(nome);
                System.out.println("Digite o preço do produto:");
                float preco = Float.parseFloat(s.nextLine());
                novoProd.setPreco(preco);
                System.out.println("Digite a categoria do produto:");
                String categoria = s.nextLine();
                novoProd.setCategoria(categoria);

                int idNovoProduto = ProdutoDAO.insereProduto(novoProd);

                if(idNovoProduto != -1){
                    System.out.println("Deseja adicionar os ingredientes do produto? (1: Sim, 2: Não)");
                    int escolhaIngrediente = s.nextInt();
                    while(escolhaIngrediente == 1){
                        int ingredienteID = escolheIngrediente(s);
                        if(IngredienteDAO.procuraIngredientePorID(ingredienteID) != null) {
                            System.out.println("Insira a quantidade de ingrediente necessário: ");
                            float qtd = s.nextFloat();
                            System.out.print("Insira observações sobre o uso do ingrediente (ou deixe vazio): ");
                            String desc = s.nextLine();
                            InfoProdutoDAO.criaInfoProduto(qtd, desc, ingredienteID, idNovoProduto);
                            System.out.println("Adicionar outro ingrediente? (1: Sim, 2: Não)");
                            escolhaIngrediente = s.nextInt();
                        }
                    }
                }
                return;
            case 2:
                int id = escolheProduto(s);
                ProdutoDAO.deletaProdutoPorID(id);
                return;
            case 3:
                mostraTodosProdutos();
                return;
            case 4:
                System.out.println("Digite o id do produto a ser mostrado:");
                int idd = s.nextInt();
                Produto prod = ProdutoDAO.procuraProdutoPorID(idd);
                System.out.println(prod);
                return;
            case 5:
                return;
            default:
                System.out.println("Entrada inválida!");
                return;

        }
    }

    /* AUXILIARES + FUNÇÕES SOBRE FUNCIONÁRIOS */
    public static void mostraTodosFuncionarios(){
        List<Funcionario> list = FuncionarioDAO.selectAll();
        for(Funcionario f : list){
            System.out.println(f);
        }
        System.out.println();
    }
    public static int escolheFuncionario(Scanner s){
        System.out.println("Escolha um Funcionário: ");
        mostraTodosFuncionarios();
        System.out.print("Sua escolha: ");
        return s.nextInt();
    }
    public static void menuEscolhaFuncionario(Scanner s){
        System.out.println("1. Adicionar funcionário");
        System.out.println("2. Remover funcionário");
        System.out.println("3. Mostrar todos os funcionários");
        System.out.println("4. Mostrar um funcionário");
        System.out.println("5. Voltar");
           
        int escolha = Integer.parseInt(s.nextLine());

        switch (escolha) {
            case 1:
                Funcionario novoFunc = new Funcionario();
                System.out.println("Digite o nome do funcionário:");
                String nome = s.nextLine();
                novoFunc.setNome(nome);
                System.out.println("Digite o salário do funcionário:");
                float salario = Float.parseFloat(s.nextLine());
                novoFunc.setSalario(salario);
                System.out.println("Digite a data de contratação do funcionário no formato dd/mm/aaaa:");
                String entrada = s.nextLine();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(entrada, fmt);
                novoFunc.setData_contratacao(data);
                System.out.println("Digite o cargo do funcionário:");
                String cargo = s.nextLine();
                novoFunc.setCargo(cargo);

                FuncionarioDAO.insereFuncionario(novoFunc);
                return;
            case 2:
                int id = escolheFuncionario(s);
                FuncionarioDAO.deletaFuncionarioPorID(id);
                return;
            case 3:
                mostraTodosFuncionarios();
                return;
            case 4:
                System.out.println("Digite o id do funcionário a ser mostrado:");
                int idd = Integer.parseInt(s.nextLine());
                Funcionario func = FuncionarioDAO.procuraFuncionarioPorID(idd);

                System.out.println(func);
                return;
            case 5:
                return;
            default:
                System.out.println("Entrada inválida!");
                return;

           }
    }

    /* AUXILIARES + FUNÇÕES SOBRE COMANDAS */
    public static void mostraTodasComandasAbertas(){
        List<Comanda> list = ComandaDAO.selectAll("ABERTA");
        for(Comanda c : list){
            System.out.println(c);
        }
        System.out.println();
    }
    public static void mostraTodasComandasPagas(){
        List<Comanda> list = ComandaDAO.selectAll("PAGA");
        for(Comanda c : list){
            System.out.println(c);
        }
        System.out.println();
    }
    public static void mostraTodasComandas(){
        List<Comanda> list = ComandaDAO.selectAll("");
        for(Comanda c : list){
            System.out.println(c);
        }
            System.out.println();
        }
    public static int escolheComanda(Scanner s){
        System.out.println("Escolha uma Comanda: ");
        mostraTodasComandas();
        System.out.print("Sua escolha: ");
        return s.nextInt();
    }
    public static int escolheComandaAberta(Scanner s){
        System.out.println("Escolha uma Comanda: ");
        mostraTodasComandasAbertas();
        System.out.print("Sua escolha: ");
        return s.nextInt();

    }
    public static void menuEscolhaComanda(Scanner s){
        System.out.println("1. Criar comanda");
        System.out.println("2. Fazer checkout de uma comanda");
        System.out.println("3. Remover comanda");
        System.out.println("4. Mostrar todas as comandas abertas");
        System.out.println("5. Mostrar todas as comandas pagas");
        System.out.println("6. Mostrar uma comanda");
        System.out.println("7. Voltar");
           
        int escolha = s.nextInt();

        switch (escolha) {
            case 1:
                System.out.println("Digite o número da mesa da comanda:");
                int numero = s.nextInt();

                ComandaDAO.criaComanda(numero);
                return;
            case 2:
                System.out.println("Escolha a comanda à se fazer checkout:");
                int id = s.nextInt();
                ComandaDAO.alteraComanda("status_pgto", id, "PAGA");
                return;
            case 3:
                System.out.println("Escolha uma comanda para remover: ");
                int idd = escolheComanda(s);
                ComandaDAO.deletaComandaPorID(idd);
                return;
            case 4:
                mostraTodasComandasAbertas();
                return;
            case 5:
                mostraTodasComandasPagas();
                return;
            case 6:
                System.out.println("Insira o ID da comanda a ser mostrada: ");
                int iddd = s.nextInt();
                ComandaDAO.procuraComandaPorID(iddd);
                return;
            case 7:
                return;
            default:
                System.out.println("Entrada inválida!");
                return;

           }
    }

    /* AUXILIARES + FUNÇÕES SOBRE PEDIDOS */
    public static void mostraTodosPedidosPendentes(){
        List<Pedido> list = PedidoDAO.selectAll("PENDENTE");
        for(Pedido p : list){
            System.out.println(p);
        }
        System.out.println();
    }
    public static void mostraTodosPedidosConcluidos(){
        List<Pedido> list = PedidoDAO.selectAll("ATENDIDO");
        for(Pedido p : list){
            System.out.println(p);
        }
        System.out.println();
    }
    public static int escolhePedido(Scanner s){
        System.out.println("Escolha um pedido: ");
        mostraTodosPedidosPendentes();
        System.out.print("Sua escolha: ");
        return s.nextInt();
    }
    public static int escolhePedidoPendente(Scanner s){
        System.out.println("Escolha um pedido: ");
        mostraTodosPedidosPendentes();
        System.out.print("Sua escolha: ");
        return s.nextInt();
    }
    public static void menuEscolhaPedido(Scanner s){
        System.out.println("1. Fazer um pedido");
        System.out.println("2. Atender um pedido");
        System.out.println("3. Cancelar um pedido");
        System.out.println("4. Deletar um pedido");
        System.out.println("5. Mostrar todos os pedidos pendentes");
        System.out.println("6. Mostrar todos os pedidos concluídos");
        System.out.println("7. Alterar itens de um pedido");
        System.out.println("8. Voltar");

        int escolha = s.nextInt();

        switch (escolha) {
            case 1:
                System.out.println("Qual comanda fez o pedido?");
                int idComanda = escolheComandaAberta(s);
                int novoPedidoID = PedidoDAO.criaPedido(idComanda);
                int escolhaPedido = 1;
                while(escolhaPedido == 1){
                    System.out.println("Insira o produto pedido: ");
                    // FAZER A LÓGICA
                    System.out.println("Quer adicionar outro produto no pedido?");
                }
                break;
            case 2:
                System.out.println("Escolha o pedido à ser concluído: ");
                int id = escolhePedidoPendente(s);
                PedidoDAO.alteraPedido("status_pedido", id, "ATENDIDO");
                break;
                case 3:
                System.out.println("Escolha o pedido à ser cancelado: ");
                int idd = escolhePedidoPendente(s);
                PedidoDAO.alteraPedido("status_pedido", idd, "CANCELADO");
                break;
            case 4:
                System.out.println("Escolha o pedido à ser deletado: ");
                int iddd = escolhePedido(s);
                PedidoDAO.deletaPedidoPorID(iddd);
                break;
            case 5:
                mostraTodosPedidosPendentes();
                break;
                case 6:
                mostraTodosPedidosConcluidos();
                break;
            case 7:
                System.out.println("Falta fazer essa bomba...");
                break;
        
            default:
                System.out.println("Escolha inválida.");
                break;
        }

    }

    public static int menu(Scanner s){
        System.out.println(".o°o.o°o.o° CAFETERIA °o.o°o.o°o.");
        System.out.println("1. Opções com Ingredientes");
        System.out.println("2. Opções com Produtos");
        System.out.println("3. Opções com Funcionários");
        System.out.println("4. Opções com Comanda");
        System.out.println("5. Opções com Pedido");
        System.out.println("6. Sair do Programa");
        
        int escolha = Integer.parseInt(s.nextLine());

        switch (escolha) {
            case 1:
                System.out.println("Escolhas com Ingredientes");
                menuEscolhaIngrediente(s);
                return 0;
            case 2:
                System.out.println("Escolhas com Produtos");
                menuEscolhaProduto(s);
                return 0;
            case 3:
                System.out.println("Escolhas Funcionários");
                menuEscolhaFuncionario(s);
                return 0;
            case 4:
                System.out.println("Escolhas com Comanda");
                return 0;
            case 5:
                System.out.println("Escolhas com Pedidos!");
                return -1;
            case 6:
                System.out.println("Até mais!");
                return -1;
            default:
                System.out.println("Entrada inválida!");
                return 0;
        }
    }
}
