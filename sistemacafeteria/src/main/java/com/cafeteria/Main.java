package com.cafeteria;

import java.util.List;
import java.util.Scanner;

import com.cafeteria.SQL.IngredienteDAO;
import com.cafeteria.SQL.ProdutoDAO;
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
        // Ingrediente i = IngredienteDAO.procuraIngredientePorNome("Café");

        // if(i == null){
        //     System.err.println("Ingrediente não encontrado");
        // }else{
        //     System.err.println(i.getNome());
        // }
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

    public static int menu(Scanner s){
        System.out.println(".o°o.o°o.o° CAFETERIA °o.o°o.o°o.");
        System.out.println("1. Opções com Ingredientes");
        System.out.println("2. Opções com Produtos");
        System.out.println("3. Opções com Funcionários");
        System.out.println("4. Opções com Comanda/Pedido");
        System.out.println("5. Sair do Programa");
        
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
                return 0;
            case 4:
                System.out.println("Escolhas com Comanda/pedidos");
                return 0;
            case 5:
                System.out.println("Até mais!");
                return -1;
            default:
                System.out.println("Entrada inválida!");
                return 0;
        }
    }
}
