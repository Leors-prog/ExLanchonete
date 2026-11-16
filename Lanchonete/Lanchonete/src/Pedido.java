
public class Pedido {
    int id_pedido;
    String nome_cliente;
    String item;
    int quantidade;
    double valor;
    String tipo; // "URGENTE", "PRIORITARIO", "NORMAL"
    Pedido proximo;

    public Pedido(int id_pedido, String nome_cliente, String item,
                  int quantidade, double valor, String tipo) {
        this.id_pedido = id_pedido;
        this.nome_cliente = nome_cliente;
        this.item = item;
        this.quantidade = quantidade;
        this.valor = valor;
        this.tipo = tipo;
        this.proximo = null;
    }

    @Override
    public String toString() {
        return String.format("Pedido#%d | %s | %s x%d | R$ %.2f | %s",
                id_pedido, nome_cliente, item, quantidade, valor, tipo);
    }
}