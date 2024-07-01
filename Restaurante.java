import java.util.Scanner;

public class Restaurante {
    private int pos;
    private String nome;
    private String endereco;
    private int numMesas = 10;
    private Mesa[] mesas = new Mesa[numMesas];

    // construtor
    public Restaurante(String nome, String endereco, int numMesas) {
        // inicializa o vetor de mesas do restaurante 
        for (int i = 0; i < mesas.length; i++) {
            mesas[i] = new Mesa();
        }

        // inicializa os atributos do restaurante
        this.nome = nome;
        this.endereco = endereco;
        this.numMesas = numMesas;
    }

    // faz a listagem das mesas assim como os nomes dos clientes de cada uma
    public void listarMesas() {
        System.out.println("\n\tMesas vazias:");

        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i].getLiberada()) {
                System.out.println("\t\tmesa: " + (i + 1));
            }
        }

        System.out.println("\n\tMesas com pessoas:");

        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i].getNumClientes() > 0 && mesas[i].getNumClientes() < mesas[i].getCadeiras()) {
                System.out.println("\t\tmesa: " + (i + 1));

                for (Cliente cliente : mesas[i].getClientes()) {
                    if (cliente.getNome() != null) {
                        System.out.println("\t\t\t" + cliente.getNome());
                    }
                }
            }
        }

        System.out.println("\n\tMesas cheias:");

        for (int i = 0; i < mesas.length; i++) {
            if (mesas[i].getNumClientes() == mesas[i].getCadeiras()) {
                System.out.println("\t\tmesa: " + (i + 1));

                for (Cliente cliente : mesas[i].getClientes()) {
                    System.out.println("\t\t\t" + cliente.getNome());
                }
            }
        }

        System.out.println();
    }

    // adciona um cliente para a mesa desejada
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

    // adciona um pedido para a mesa desejada
    public void adicionaPedido(Scanner sc) {
        listarMesas();
        
        System.out.print("Onde deseja adcionar um novo pedido? ");
        pos = sc.nextInt() - 1;
        sc.nextLine();

        if (mesas[pos].getLiberada()) {
            System.out.println("Mesa não possui clientes");
        }
        
        else {
            mesas[pos].adicionaPedido(sc);
        }
    }

    // lista o consumo/conta da mesa desejada
    public void listarConsumo(Scanner sc) {
        listarMesas();

        System.out.print("Qual mesa deseja ver a lista de consumo? ");
        pos = sc.nextInt() - 1;
        sc.nextLine();

        if (mesas[pos].getTotal() == 0) {
            System.out.println("Mesa não possui pedidos");
        }

        else {
            mesas[pos].listarConsumo();
        }
    }

    // fecha a conta da mesa desejada
    public void fechaConta(Scanner sc) {
        listarMesas();

        System.out.print("Qual mesa deseja fechar a conta? ");
        pos = sc.nextInt() - 1;
        sc.nextLine();

        if (mesas[pos].getNumClientes() == 0) {
            System.out.println("Mesa não possui clientes");
        }
        
        else {
            mesas[pos].calcula10porcento();
            System.out.println("\tValor da conta: " + mesas[pos].getTotal());
            System.out.println("\tValor divido entre " + mesas[pos].getNumClientes() + " clientes: " + mesas[pos].dividirConta());

            mesas[pos] = new Mesa();
        }
    }

    // divide a conta entre o número de pessoas da mesa
    public void dividirConta() {
        mesas[pos].dividirConta();
    }

    // getters e setters
    public Mesa getMesa(int indice) {
        return this.mesas[indice];
    }

    public String getNome() {
        return this.nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public int getNumMesas() {
        return numMesas;
    }
}