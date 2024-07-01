public class ComandaBebida extends Comanda {
    // lista o consumo/conta da mesa desejada
    @Override
    public void listarConsumo() {
        System.out.println("\tDrinks: ");
        if (numPedidos != 0) {
            for (Pedidos pedidos : consumo) {
                System.out.println("\t\t" + pedidos.getNome() + " R$" + pedidos.getValor());
            }
        }
    }
}
