# Funcionamento geral

O sistema funciona de maneira simples, onde um restaurante possui mesas, que possui uma comanda de bebida e uma de comida, que são classes filhas de comanda, essas classes filhas apenas mudam a maneira como o método listar consumo é implementado, cada comanda também possui pedidos. Além da comanda, as mesas também possuem clientes.

O código permite realizar operações de adicionar clientes, adicionar pedidos, fechar conta, listar mesa e acessar mesa, além da opção de sair e limpar o terminal.

#### Trecho do arquivo TesteRestaurante.java linha 22

     System.out.print("\n(1) Adcionar cliente\n" +
                                          "(2) Adicionar pedido  \n" +
                                          "(3) Listar consumo    \n" +
                                          "(4) Fechar conta      \n" +
                                          "(5) Menu por mesa     \n" +
                                          "(6) Listar mesas      \n" +
                                          "(7) Sair              \n" +
                                          "(8) Limpar terminal   \n");
    System.err.print("\nDigite a operação desejada: ");

Todas as operações estão associadas entre as classes e são realizadas em camadas, onde a chamada do método na classe restaurante, realiza a chamada do mesmo método em outras classes, pelo fato de um restaurante possuir outras classes e essas outras classes possuírem outras.

#### Trecho do arquivo Restaurante.java linha 3

    public class Restaurante {
        private int pos;
        private String nome;
        private String endereco;
        private int numMesas = 10;
        private Mesa[] mesas = new Mesa[numMesas];
    }
    
#### Trecho do arquivo Mesa.java linha 3

    public class Mesa {
        private boolean liberada = true;
        private int cadeiras = 4;
        private int numClientes = 0;
        private ComandaBebida comandaBebida = new ComandaBebida();
        private ComandaComida comandaComida = new ComandaComida();
        private Cliente[] clientes = new Cliente[cadeiras]; 
    }

Repare que, a classe Restaurante possui uma instância de Mesa, e, pelo fato de ser privada à classe restaurante, para fazer o acesso aos método, é necessário realizar suas chamadas por entre as camadas de visibilidade.

#### Trecho do arquivo TesteRestaurante.java linha 35

    case 1:
            restaurante.adicionaCliente(sc);
            break;


#### Trecho do arquivo Restaurante.java linha 63

    public void adicionaCliente(Scanner sc) {
        listarMesas();
    
        System.out.print("Onde deseja adcionar um novo cliente? ");
        pos = sc.nextInt() - 1;
        sc.nextLine();
    
        if (mesas[pos].getNumClientes() == mesas[pos].getCadeiras()) {
            System.out.println("Mesa cheia");
        }
    
        else {
             mesas[pos].adicionaCliente(sc);
        }
    }

#### Trecho do arquivo Mesa.java linha 20

    public void adicionaCliente(Scanner sc) {
        liberada = false;
    
        System.out.print("\tDigite o nome do cliente: ");
        clientes[numClientes].setNome(sc.nextLine());
    
        System.out.print("\tDigite o email do cliente: ");
        clientes[numClientes].setEmail(sc.nextLine());
    
        numClientes++;
    }

A chamada do método adicionaCliente passa por várias classes até chegar naquela que possui clientes, para assim poder adicioná-lo da forma correta e respeitando a visibilidade dos atributos.	

Para evitar a escrita desnecessária, a operação “Menu por mesa” cria uma instância de Mesa que possui os atributos de uma mesa do restaurante, para que as outras operações possam ser executadas sem a necessidade de selecionar a mesa desejada a cada nova operação.

#### Trecho do arquivo Main.java linha 48
    case 5:
        restaurante.listarMesas();
        System.err.print("Digite a mesa desejada: ");
        indice = sc.nextInt() - 1;
        sc.nextLine();
    
        if (indice > restaurante.getNumMesas() - 1) {
            System.out.println("Mesa não existe");
            break;
        }
                        
        Mesa mesaAux = restaurante.getMesa(indice);
    
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
