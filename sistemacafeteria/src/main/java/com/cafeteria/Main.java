package com.cafeteria;

import java.util.Scanner;

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

    public static int menu(Scanner s){
        System.err.println(".o°o.o°o.o° CAFETERIA °o.o°o.o°o.");
        System.err.println("1. Opções com Ingredientes");
        System.err.println("2. Opções com Produtos");
        System.err.println("3. Opções com Funcionários");
        System.err.println("4. Opções com Comanda/Pedido");
        System.err.println("5. Sair do Programa");
        
        int escolha = s.nextInt();

        switch (escolha) {
            case 1:
                System.out.println("Escolhas com Ingredientes");
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
