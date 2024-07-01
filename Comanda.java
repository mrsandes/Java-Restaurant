import java.util.Scanner;

public abstract class Comanda {
    protected Pedidos[] consumo = new Pedidos[1];
    protected int numPedidos = 0;
    protected double total = 0;

    // adciona um pedido para a mesa desejada
    public void adcionaPedido(Scanner sc) {
        if (consumo[consumo.length - 1] != null) {
            Pedidos[] novoArray = new Pedidos[consumo.length + 1];
    
            for (int j = 0; j < consumo.length; j++) {
                novoArray[j] = consumo[j];
            }
    
            consumo = new Pedidos[novoArray.length];
    
            for (int j = 0; j < novoArray.length; j++) {
                consumo[j] = novoArray[j];
            }
        }
        
        consumo[consumo.length - 1] = new Pedidos();

        System.out.print("\t\tDigite o nome do pedido: ");
        consumo[consumo.length - 1].setNome(sc.nextLine());

        System.out.print("\t\tDigite o valor do pedido: ");
        consumo[consumo.length - 1].setValor(sc.nextDouble());

        total += (double)consumo[consumo.length - 1].getValor();
        numPedidos++;
    }

    // lista o consumo/conta da mesa desejada
    public void listarConsumo() {
        if (numPedidos != 0) {
            for (Pedidos pedidos : consumo) {
                System.out.println("\t\tNome: " + pedidos.getNome() + "R$" + pedidos.getValor());
            }
        }
    }

    // realiza o calculo da gorjeta do garçom
    public void calcula10porcento() {
        total *= 1.1;
    }

    // getters e setters
    public double getTotal() {
        return this.total;
    }
}