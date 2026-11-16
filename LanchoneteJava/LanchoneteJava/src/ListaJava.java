import java.util.LinkedList;

public class ListaJava {

    private LinkedList<Pedido> lista = new LinkedList<>();

    public int tamanho()              { return lista.size(); }
    public boolean estaVazia()        { return lista.isEmpty(); }
    public Pedido obterCabeca()       { return lista.isEmpty() ? null : lista.getFirst(); }
    public Pedido obterCauda()        { return lista.isEmpty() ? null : lista.getLast(); }

    //Inserções 
    /** Urgente: início, +20% */
    public void inserirUrg(Pedido p) {
        p.valor *= 1.20;
        p.tipo = "URGENTE";
        lista.addFirst(p);
    }

    /** Normal: fim */
    public void inserirNor(Pedido p) {
        p.tipo = "NORMAL";
        lista.addLast(p);
    }

    /** Prioritário: posição mediana, +10% */
    public void inserirPri(Pedido p) {
        p.valor *= 1.10;
        p.tipo = "PRIORITARIO";
        int meio = lista.size() / 2;
        lista.add(meio, p);
    }

    //Remoções
    public Pedido removerInicio()   { return lista.isEmpty() ? null : lista.removeFirst(); }
    public Pedido removerFim()      { return lista.isEmpty() ? null : lista.removeLast(); }

    public Pedido removerPosicao(int k) {
        if (k < 0 || k >= lista.size()) return null;
        return lista.remove(k);
    }

    //Buscas
    public int buscarPorId(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).id_pedido == id) return i;
        }
        return -1;
    }

    public Pedido buscarPorPosicao(int pos) {
        if (pos < 0 || pos >= lista.size()) return null;
        return lista.get(pos);
    }

    public void exibirProximosN(int pos, int n) {
        if (pos < 0 || pos >= lista.size()) { System.out.println("Posição inválida."); return; }
        System.out.println("Próximos " + n + " pedidos a partir de " + pos);
        for (int i = pos; i < lista.size() && i < pos + n; i++) {
            System.out.println(i + "º -> " + lista.get(i));
        }
    }

    //Mostra na tela
    public void imprimir() {
        if (lista.isEmpty()) { System.out.println("[Lista vazia]"); return; }
        System.out.println("Fila de Pedidos (ordem de atendimento)");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + "º -> " + lista.get(i));
        }
        System.out.println("(tamanho=" + lista.size() + ")");
    }
}