package com.cafeteria;

import java.util.List;
import java.util.Scanner;

import com.cafeteria.SQL.IngredienteDAO;
import com.cafeteria.dados.Ingrediente;

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
                List<Ingrediente> i = IngredienteDAO.selectAll();
                for(Ingrediente ing : i){
                    System.out.println(ing);
                }
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
