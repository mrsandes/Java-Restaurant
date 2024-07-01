import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // inicializa o scanner
        Scanner sc = new Scanner(System.in);

        // cria a instância da classe restaurante
        Restaurante restaurante = new Restaurante("buxin xei", "buxinxeilândia", 5);

        // cria as variáveis auxiliares
        int op, indice;

        System.out.println("\nBem vindo ao sistema de comandas do restaurante " + restaurante.getNome() + "!!!");

        // menu em loop
        do {
            System.out.print("\n(1) Adcionar cliente\n" +
                             "(2) Adicionar pedido  \n" +
                             "(3) Listar consumo    \n" +
                             "(4) Fechar conta      \n" +
                             "(5) Menu por mesa     \n" +
                             "(6) Listar mesas      \n" +
                             "(7) Sair              \n" +
                             "(8) Limpar terminal   \n");
            System.err.print("\nDigite a operação desejada: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    restaurante.adicionaCliente(sc);
                    break;

                case 2:
                    restaurante.adicionaPedido(sc);
                    break;

                case 3:
                    restaurante.listarConsumo(sc);
                    break;

                case 4:
                    restaurante.fechaConta(sc);
                    break;

                // implementação de um menu por mesa sem a necessidade de acessar a mesa a cada nova operação
                case 5:
                    restaurante.listarMesas();
                    System.err.print("Digite a mesa desejada: ");
                    indice = sc.nextInt() - 1;
                    sc.nextLine();

                    // verifica a existência da mesa
                    if (indice > restaurante.getNumMesas() - 1) {
                        System.out.println("Mesa não existe");
                        break;
                    }
                    
                    // passa os valores da mesa desejada para uma variável auxiliar
                    Mesa mesaAux = restaurante.getMesa(indice);

                    // novo menu em loop para a mesa escolhida
                    do {
                        System.out.print("\n(1) Adcionar cliente\n" +
                                        "(2) Adicionar pedido  \n" +
                                        "(3) Listar consumo    \n" +
                                        "(4) Sair              \n");
                        System.err.print("\nDigite a operação desejada para a mesa " + (indice + 1) + ": ");
                        op = sc.nextInt();
                        sc.nextLine();

                        switch (op) {
                            case 1:
                                if (mesaAux.getNumClientes() != mesaAux.getCadeiras()) {
                                    mesaAux.adicionaCliente(sc);
                                }
                                
                                else {
                                    System.out.println("Mesa cheia");
                                }

                                break;
                            
                            case 2:
                                if (mesaAux.getNumClientes() != 0) {
                                    mesaAux.adicionaPedido(sc);
                                }
                                
                                else {
                                    System.out.println("Mesa não possui clientes");
                                }

                                break;

                            case 3:
                                if (mesaAux.getTotal() != 0) {
                                    mesaAux.listarConsumo();
                                }
                                
                                else {
                                    System.out.println("Mesa não possui pedidos");
                                }
                                
                                break;

                            default:
                                System.out.println("Digite uma opção válida");
                                break;
                        }
                    } while (op != 4);

                    break;

                case 6:
                    System.out.println();
                    restaurante.listarMesas();
                    break;

                case 7:
                    break;
                
                // limpa as informações do terminal 
                case 8:
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;

                default:
                    System.out.println("Digite uma opção válida");
                    break;
            }
        } while (op != 7);
        
        sc.close();
    }
}