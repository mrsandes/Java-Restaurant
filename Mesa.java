import java.util.Scanner;

public class Mesa {
    private boolean liberada = true;
    private int cadeiras = 4;
    private int numClientes = 0;
    private ComandaBebida comandaBebida = new ComandaBebida();
    private ComandaComida comandaComida = new ComandaComida();
    private Cliente[] clientes = new Cliente[cadeiras]; 

    // construtor
    public Mesa() {
        // inicializa o vetor de clientes
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente();
        }
    }

    // adciona um cliente para a mesa desejada
    public void adicionaCliente(Scanner sc) {
        liberada = false;

        System.out.print("\tDigite o nome do cliente: ");
        clientes[numClientes].setNome(sc.nextLine());

        System.out.print("\tDigite o email do cliente: ");
        clientes[numClientes].setEmail(sc.nextLine());

        numClientes++;
    }

    // adciona um pedido para a mesa desejada
    public void adicionaPedido(Scanner sc) {
        int op;

        do {
            System.out.print("\tDeseja adicionar um drink (1) ou um prato (2)? ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    comandaBebida.adcionaPedido(sc);
                    break;

                case 2:
                    comandaComida.adcionaPedido(sc);
                    break;
            }
        } while (op < 1 || op > 2);

    }

    // lista o consumo/conta da mesa desejada
    public void listarConsumo() {
        comandaBebida.listarConsumo();
        comandaComida.listarConsumo();
    }

    // realiza o calculo da gorjeta do garçom
    public void calcula10porcento() {
        comandaBebida.calcula10porcento();
        comandaComida.calcula10porcento();
    }

    // divide a conta entre o número de pessoas da mesa
    public double dividirConta() {
        return comandaBebida.getTotal() / numClientes + comandaComida.getTotal() / numClientes;
    }

    // getters e setters
    public double getTotal() {
        return comandaBebida.getTotal() + comandaComida.getTotal();
    }

    public Cliente[] getClientes() {
        return this.clientes;
    }

    public boolean getLiberada() {
        return this.liberada;
    }

    public int getCadeiras() {
        return this.cadeiras;
    }

    public int getNumClientes() {
        return this.numClientes;
    }
}