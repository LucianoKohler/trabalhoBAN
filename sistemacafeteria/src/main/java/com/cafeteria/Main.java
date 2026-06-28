package com.cafeteria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.cafeteria.SQL.ComandaDAO;
import com.cafeteria.SQL.FuncionarioDAO;
import com.cafeteria.SQL.IngredienteDAO;
import com.cafeteria.SQL.ProdutoDAO;
import com.cafeteria.dados.Comanda;
import com.cafeteria.dados.Funcionario;
import com.cafeteria.dados.Ingrediente;
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

    public static void mostraTodosIngredientes(){
        List<Ingrediente> i = IngredienteDAO.selectAll();
        for(Ingrediente ing : i){
            System.out.println(ing);
        }
    }

    public static void EscolhaIngrediente(Scanner s){
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

    public static void EscolhaProdutos(Scanner s){
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

                if(ProdutoDAO.insereProduto(novoProd)){
                    System.out.println("Deseja adicionar os ingredientes do produto? (1: Sim, 2: Não)");
                    int escolhaIngrediente = s.nextInt();
                    while(escolhaIngrediente == 1){
                        System.out.println("Escolha um ingrediente para adicionar à receita: ");

                    }
                }
                return;
            case 2:
                System.out.println("Digite o id do produto a ser removido:");
                int id = Integer.parseInt(s.nextLine());

                ProdutoDAO.deletaProdutoPorID(id);
                return;
            case 3:
                List<Produto> p = ProdutoDAO.selectAll();
                for(Produto prod : p){
                    System.out.println(prod);
                }
                return;
            case 4:
                System.out.println("Digite o id do produto a ser mostrado:");
                int idd = Integer.parseInt(s.nextLine());
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

    public static void EscolhaFuncionario(Scanner s){
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
                System.out.println("Digite a data de contratação do funcionário:");
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
                System.out.println("Digite o id do funcionário a ser removido:");
                int id = Integer.parseInt(s.nextLine());

                FuncionarioDAO.deletaFuncionarioPorID(id);
                return;
            case 3:
                List<Funcionario> f = FuncionarioDAO.selectAll();
                for(Funcionario func : f){
                    System.out.println(func);
                }
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

    public static void EscolhaComanda(Scanner s){
        System.out.println("1. Adicionar comanda");
        System.out.println("2. Remover comanda");
        System.out.println("3. Mostrar todos as comandas");
        System.out.println("4. Mostrar uma comanda");
        System.out.println("5. Voltar");
           
        int escolha = Integer.parseInt(s.nextLine());

        switch (escolha) {
            case 1:
                System.out.println("Digite o número da mesa da comanda:");
                int numero = Integer.parseInt(s.nextLine());

                ComandaDAO.criaComanda(numero);
                return;
            case 2:
                System.out.println("Digite o id da comanda a ser removida:");
                int id = Integer.parseInt(s.nextLine());

                ComandaDAO.deletaComandaPorID(id);
                return;
            case 3:
                List<Comanda> c = ComandaDAO.selectAll();
                for(Comanda com: c){
                    System.out.println(com);
                }
                return;
            case 4:
                System.out.println("Digite o id da comanda a ser mostrada:");
                int idd = Integer.parseInt(s.nextLine());          
                Comanda com = ComandaDAO.procuraComandaPorID(idd);

                System.out.println(com);
                return;
            case 5:
                return;
            default:
                System.out.println("Entrada inválida!");
                return;

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
                EscolhaIngrediente(s);
                return 0;
            case 2:
                System.out.println("Escolhas com Produtos");
                EscolhaProdutos(s);
                return 0;
            case 3:
                System.out.println("Escolhas Funcionários");
                EscolhaFuncionario(s);
                return 0;
            case 4:
                System.out.println("Escolhas com Comanda");
                EscolhaComanda(s);
                return 0;
            case 5:
                System.out.println("Escolhas com pedidos");
                return 0;
            case 6:
                System.out.println("Até mais!");
                return -1;
            default:
                System.out.println("Entrada inválida!");
                return 0;
        }
    }
}
