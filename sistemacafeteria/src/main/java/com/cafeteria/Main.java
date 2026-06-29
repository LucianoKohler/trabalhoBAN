package com.cafeteria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cafeteria.SQL.InfoProdutoDAO;
import com.cafeteria.SQL.ComandaDAO;
import com.cafeteria.SQL.FuncionarioDAO;
import com.cafeteria.SQL.IngredienteDAO;
import com.cafeteria.SQL.ItemPedidoDAO;
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

        int e = 1;
        while(e == 1){
            e = menu(s);
        }

        s.close();

    }

    /* AUXILIARES + FUNÇÕES SOBRE INGREDIENTES */
    public static void mostraTodosIngredientes(){
        System.out.println("MOSTRANDO TODOS OS INGREDIENTES: ");
        List<Ingrediente> i = IngredienteDAO.selectAll();
        for(Ingrediente ing : i){
            System.out.println("\t" + ing);
        }
        System.out.println();
    }
    public static Ingrediente escolheIngrediente(Scanner s){
        System.out.println("Escolha um ingrediente: ");
        mostraTodosIngredientes();
        System.out.print("Sua escolha: ");
        return IngredienteDAO.procuraIngredientePorID(leInt(s));
    }
    public static void menuEscolhaIngrediente(Scanner s){
        System.out.println("1. Adicionar ingrediente");
        System.out.println("2. Remover ingrediente");
        System.out.println("3. Mostrar todos os ingredientes");
        System.out.println("4. Mostrar um ingrediente");
        System.out.println("5. Voltar");
           
        int escolha = Integer.parseInt(leString(s));

        switch (escolha) {
            case 1:
                Ingrediente novoIng = new Ingrediente();
                System.out.println("Digite o nome do ingrediente:");
                String nome = leString(s);
                novoIng.setNome(nome);
                System.out.println("Digite a descrição do ingrediente:");
                String desc = leString(s);
                novoIng.setDescricao(desc);
                System.out.println("Digite a quantidade do ingrediente:");
                float quant = Float.parseFloat(leString(s));
                novoIng.setQuantidade(quant);
                System.out.println("Digite a unidade de medida da quantidade:");
                String unidade = leString(s);
                novoIng.setUnidade_medida(unidade);

                IngredienteDAO.insereIngrediente(novoIng);

                return;
            case 2:
                System.out.println("Digite o id do ingrediente a ser removido:");
                Ingrediente ing = escolheIngrediente(s);
                if(ing == null){
                    System.out.println("Ingrediente inválido");
                }else{    
                    IngredienteDAO.deletaIngredientePorID(ing.getId());
                }
                return;
            case 3:
                mostraTodosIngredientes();
                return;
            case 4:
                System.out.println("Digite o id do ingrediente a ser mostrado:");
                Ingrediente i = IngredienteDAO.procuraIngredientePorID(leInt(s));
                if(i == null){
                    System.out.println("Produto não encontrado");
                }else{
                    System.out.println("Ingrediente encontrado: ");
                    System.out.println("\t" + i);
                }

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
    public static Produto escolheProduto(Scanner s){
        System.out.println("Escolha um produto: ");
        mostraTodosProdutos();
        System.out.print("Sua escolha: ");
        return ProdutoDAO.procuraProdutoPorID(leInt(s));
    }
    public static void menuEscolhaProduto(Scanner s){
        System.out.println("1. Adicionar produto");
        System.out.println("2. Remover produto");
        System.out.println("3. Mostrar todos os produtos");
        System.out.println("4. Mostrar a receita de um produto");
        System.out.println("5. Voltar");
           
        int escolha = Integer.parseInt(leString(s));

        switch (escolha) {
            case 1:
                Produto novoProd = new Produto();
                System.out.println("Digite o nome do produto:");
                String nome = leString(s);
                novoProd.setNome(nome);
                System.out.println("Digite o preço do produto:");
                float preco = Float.parseFloat(leString(s));
                novoProd.setPreco(preco);
                System.out.println("Digite a categoria do produto:");
                String categoria = leString(s);
                novoProd.setCategoria(categoria);

                int idNovoProduto = ProdutoDAO.insereProduto(novoProd);

                if(idNovoProduto != -1){
                    System.out.println("Deseja adicionar os ingredientes do produto? (1: Sim, 2: Não)");
                    int escolhaIngrediente = leInt(s);
                    while(escolhaIngrediente == 1){
                        Ingrediente ing = escolheIngrediente(s);
                        if(ing != null) {
                            System.out.println("Insira a quantidade de ingrediente necessário: ");
                            float qtd = leFloat(s);
                            System.out.print("Insira observações sobre o uso do ingrediente (ou deixe vazio): ");
                            String desc = leString(s);
                            InfoProdutoDAO.criaInfoProduto(qtd, desc, ing.getId(), idNovoProduto);
                            System.out.println("Adicionar outro ingrediente? (1: Sim, 2: Não)");
                            escolhaIngrediente = leInt(s);
                        }else{
                            System.out.println("Ingrediente inválido!");
                        }
                    }
                }
                return;
            case 2:
                Produto p = escolheProduto(s);
                if(p == null){
                    System.out.println("Produto inválido");
                }else{
                    ProdutoDAO.deletaProdutoPorID(p.getId());
                }
                return;
            case 3:
                mostraTodosProdutos();
                return;
            case 4:
                System.out.println("Digite o id do produto a ser listado:");
                Produto prod = escolheProduto(s);
                if(prod == null){
                    System.out.println("Produto inválido");
                }else{
                    System.out.println("Receita de: " + prod.getNome());
                    List<Ingrediente>ings = InfoProdutoDAO.buscaIngredientesDeUmProduto(prod.getId());
                    for(Ingrediente i : ings){
                        System.out.println("\t" + i.getNome());
                    }
                }
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
    public static Funcionario escolheFuncionario(Scanner s){
        System.out.println("Escolha um Funcionário: ");
        mostraTodosFuncionarios();
        System.out.print("Sua escolha: ");
        return FuncionarioDAO.procuraFuncionarioPorID(leInt(s));
    }
    public static void menuEscolhaFuncionario(Scanner s){
        System.out.println("1. Adicionar funcionário");
        System.out.println("2. Remover funcionário");
        System.out.println("3. Mostrar todos os funcionários");
        System.out.println("4. Mostrar um funcionário");
        System.out.println("5. Voltar");
           
        int escolha = Integer.parseInt(leString(s));

        switch (escolha) {
            case 1:
                Funcionario novoFunc = new Funcionario();
                System.out.println("Digite o nome do funcionário:");
                String nome = leString(s);
                novoFunc.setNome(nome);
                System.out.println("Digite o salário do funcionário:");
                float salario = Float.parseFloat(leString(s));
                novoFunc.setSalario(salario);
                System.out.println("Digite a data de contratação do funcionário no formato dd/mm/aaaa:");
                String entrada = leString(s);
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(entrada, fmt);
                novoFunc.setData_contratacao(data);
                System.out.println("Digite o cargo do funcionário:");
                String cargo = leString(s);
                novoFunc.setCargo(cargo);

                FuncionarioDAO.insereFuncionario(novoFunc);
                return;
            case 2:
                Funcionario f = escolheFuncionario(s);
                FuncionarioDAO.deletaFuncionarioPorID(f.getId());
                return;
            case 3:
                mostraTodosFuncionarios();
                return;
            case 4:
                System.out.println("Digite o id do funcionário a ser mostrado:");
                int idd = Integer.parseInt(leString(s));
                Funcionario func = FuncionarioDAO.procuraFuncionarioPorID(idd);
                if(func == null){
                    System.out.println("Funcionário não encontrado");
                }else{
                    System.out.println(func);
                }
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
    public static Comanda escolheComanda(Scanner s){
        System.out.println("Escolha uma Comanda: ");
        mostraTodasComandas();
        System.out.print("Sua escolha: ");
        return ComandaDAO.procuraComandaPorID(leInt(s));
    }
    public static Comanda escolheComandaAberta(Scanner s){
        System.out.println("Escolha uma Comanda: ");
        mostraTodasComandasAbertas();
        System.out.print("Sua escolha: ");
        Comanda c = ComandaDAO.procuraComandaPorID(leInt(s));
        if(c.getStatus_pgto().equals("ABERTA")){
            return c;
        }
        return null;
    }
    public static void menuEscolhaComanda(Scanner s){
        System.out.println("1. Criar comanda");
        System.out.println("2. Fazer checkout de uma comanda");
        System.out.println("3. Remover comanda");
        System.out.println("4. Mostrar todas as comandas abertas");
        System.out.println("5. Mostrar todas as comandas pagas");
        System.out.println("6. Mostrar uma comanda");
        System.out.println("7. Voltar");
           
        int escolha = leInt(s);

        switch (escolha) {
            case 1:
                System.out.println("Digite o número da mesa da comanda:");
                int numero = leInt(s);

                ComandaDAO.criaComanda(numero);
                return;
            case 2:
                Comanda c = escolheComandaAberta(s);

                if(c == null){
                    System.out.println("Comanda não encontrada");
                }else{
                    double total = ComandaDAO.calcularTotal(c.getId());
                    System.out.println("Total: R$ " + total);
                    ComandaDAO.alteraComanda("status_pgto", c.getId(), "PAGA");
                    System.out.println("Checkout concluído!");
                }
                return;
            case 3:
                System.out.println("Escolha uma comanda para remover: ");
                Comanda com = escolheComanda(s);
                ComandaDAO.deletaComandaPorID(com.getId());
                return;
            case 4:
                mostraTodasComandasAbertas();
                return;
            case 5:
                mostraTodasComandasPagas();
                return;
            case 6:
                System.out.println("Insira o ID da comanda a ser mostrada: ");
                int cId = leInt(s);
                Comanda encontrada = ComandaDAO.procuraComandaPorID(cId);
                if(encontrada == null){
                    System.out.println("Comanda não encontrada");
                }else{
                    System.out.println(encontrada);
                }
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
    public static Pedido escolhePedido(Scanner s){
        System.out.println("Escolha um pedido: ");
        mostraTodosPedidosPendentes();
        System.out.print("Sua escolha: ");
        return PedidoDAO.procuraPedidoPorID(leInt(s));
    }
    public static Pedido escolhePedidoPendente(Scanner s){
        System.out.println("Escolha um pedido: ");
        mostraTodosPedidosPendentes();
        System.out.print("Sua escolha: ");
        return PedidoDAO.procuraPedidoPorID(leInt(s));
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

        int escolha = leInt(s);

        switch (escolha) {
            case 1:
                System.out.println("Qual comanda fez o pedido?");
                Comanda c = escolheComandaAberta(s);
                if(c == null){
                    System.out.println("Comanda inválida");
                    return;
                }
                int novoPedidoID = PedidoDAO.criaPedido(c.getId());
                int escolhaPedido = 1;
                while(escolhaPedido == 1){
                    System.out.println("Insira o produto pedido: ");
                    Produto p = escolheProduto(s);
                    if(p == null){
                        System.out.println("Escolha de produto inválida!");
                        continue;
                    }
                    System.out.println("Você quer quantos desse item?");
                    int qtd = leInt(s);
                    System.out.println("Insira observações sobre o item (ou deixe vazio):");
                    String obs = leString(s);

                    ItemPedidoDAO.criaItemPedido(qtd, p.getPreco(), obs, novoPedidoID, p.getId());

                    System.out.println("Criado! Quer adicionar outro produto no pedido?");
                    escolhaPedido = leInt(s);
                }
                break;
            case 2:
                System.out.println("Escolha o pedido à ser concluído: ");
                Pedido p = escolhePedidoPendente(s);
                if(p == null || p.getStatus_pedido() == "ATENDIDO"){
                    System.out.println("Pedido inválido");
                    return;
                }
                System.out.println("Que funcionário atenderá o pedido? ");
                Funcionario f = escolheFuncionario(s);
                if(f == null){
                    System.out.println("Funcionário inválido");
                    return;
                }
                    PedidoDAO.alteraPedido("FK_funcionario", p.getId(), String.valueOf(f.getId()));
                    PedidoDAO.alteraPedido("status_pedido", p.getId(), "ATENDIDO");
                
                    break;
                case 3:
                System.out.println("Escolha o pedido à ser cancelado: ");
                Pedido p1 = escolhePedidoPendente(s);
                if(p1 == null || p1.getStatus_pedido() == "ATENDIDO"){
                    System.out.println("Pedido inválido");
                }else{
                    PedidoDAO.alteraPedido("status_pedido", p1.getId(), "CANCELADO");
                }
                break;
            case 4:
                System.out.println("Escolha o pedido à ser deletado: ");
                Pedido p2 = escolhePedido(s);
                if(p2 == null){
                    System.out.println("Pedido inválido");
                }else{
                    PedidoDAO.deletaPedidoPorID(p2.getId());
                }
                break;
            case 5:
                mostraTodosPedidosPendentes();
                break;
                case 6:
                mostraTodosPedidosConcluidos();
                break;
            case 7:
                System.out.println("Escolha um pedido para alterar a lista: ");
                Pedido p3 = escolhePedidoPendente(s);
                if(p3 == null || p3.getStatus_pedido() == "ATENDIDO"){
                    System.out.println("Pedido inválido");
                }else{
                    System.out.println("O que você quer alterar do pedido? ");
                    System.out.println("1. Quantidade");
                    System.out.println("2. Observação");
                    System.out.println("3. Produto");
                    System.out.print("Sua escolha: ");
                    int escolhaAlteracao = leInt(s);
                    if(escolhaAlteracao == 1) {
                        System.out.println("Insira a nova quantidade: ");
                        String novaQtd = leString(s);
                        ItemPedidoDAO.alteraItemPedido("quantidade", p3.getId(), novaQtd);
                    }else if(escolhaAlteracao == 2){
                        System.out.println("Insira a nova observação (ou deixe em branco):");
                        String novaObs = leString(s);
                        ItemPedidoDAO.alteraItemPedido("observacao", p3.getId(), novaObs);
                    }else{
                        System.out.println("Escolha o novo item: ");
                        Produto prod = escolheProduto(s);
                        if(prod == null){
                            System.out.println("Item novo inválido.");
                        }else{
                            ItemPedidoDAO.alteraItemPedido("FK_produto", p3.getId(), String.valueOf(prod.getId()));                        }
                    }
                }
                break;
        
            default:
                System.out.println("Escolha inválida.");
                break;
        }

    }

    public static int menu(Scanner s){
        System.out.println("---------------------------------");
        System.out.println(".o°o.o°o.o° CAFETERIA °o.o°o.o°o.");
        System.out.println("1. Opções com Ingredientes");
        System.out.println("2. Opções com Produtos");
        System.out.println("3. Opções com Funcionários");
        System.out.println("4. Opções com Comanda");
        System.out.println("5. Opções com Pedido");
        System.out.println("6. Sair do Programa");
        
        int escolha = Integer.parseInt(leString(s));

        switch (escolha) {
            case 1:
                menuEscolhaIngrediente(s);
                break;
            case 2:
                menuEscolhaProduto(s);
                break;
            case 3:
                menuEscolhaFuncionario(s);
                break;
            case 4:
                menuEscolhaComanda(s);
                break;
            case 5:
                menuEscolhaPedido(s);
                break;
            case 6:
                System.out.println("Até mais!");
                return 0;
            default:
                System.out.println("Entrada inválida!");
                break;
        }
        return 1;
    }

    public static int leInt(Scanner s){
        int n = s.nextInt();
        s.nextLine();
        return n;
    }

    public static float leFloat(Scanner s){
        float n = s.nextFloat();
        s.nextLine();
        return n;
    }

    public static String leString(Scanner s){
        String n = s.nextLine();
        return n;
    }
}
