import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // Gera 15 pedidos: 8 normais, 4 prioritários e 3 urgentes
        List<Pedido> chegada = new ArrayList<>();
        String[] itens = {"X-Burger", "X-Salada", "Cachorro-Quente", "Batata Frita",
                          "Refrigerante", "Suco", "Pizza Broto", "Misto Quente",
                          "Pastel", "Hambúrguer Artesanal"};
        Random rnd = new Random(42);

        int id = 1;
        for (int i = 0; i < 8; i++) {
            chegada.add(new Pedido(id++, "Cliente N" + i, itens[rnd.nextInt(itens.length)],
                    1 + rnd.nextInt(3), 10 + rnd.nextInt(40), "NORMAL"));
        }
        for (int i = 0; i < 4; i++) {
            chegada.add(new Pedido(id++, "Cliente P" + i, itens[rnd.nextInt(itens.length)],
                    1 + rnd.nextInt(3), 10 + rnd.nextInt(40), "PRIORITARIO"));
        }
        for (int i = 0; i < 3; i++) {
            chegada.add(new Pedido(id++, "Cliente U" + i, itens[rnd.nextInt(itens.length)],
                    1 + rnd.nextInt(3), 10 + rnd.nextInt(40), "URGENTE"));
        }

        // Embaralha 
        Collections.shuffle(chegada, new Random(7));

        System.out.println("Ordem de chegada dos pedidos:");
        for (Pedido p : chegada) System.out.println("  " + p);

        // Insere os pedidos na lista, de acordo com o tipoo
        ListaPedidos lista = new ListaPedidos();

        for (Pedido p : chegada) {
            switch (p.tipo) {
                case "URGENTE":     lista.inserirUrg(p);   break;
                case "PRIORITARIO": lista.inserirPri(p);  break;
                default:            lista.inserirNor(p);      break;
            }
        }

        System.out.println("\nFila de pedidos:");
        lista.imprimir();

        // primeiro pedido da fila
        System.out.println("\nAtendendo o primeiro pedido...");
        System.out.println("Pedido atendido: " + lista.removerInicio());
        lista.imprimir();

        // Cancela o último
        System.out.println("\nCancelando o último pedido...");
        System.out.println("Pedido cancelado: " + lista.removerFim());
        lista.imprimir();

        // Cancela o pedido da posição 3
        System.out.println("\nCancelando o pedido da posição 3...");
        System.out.println("Pedido cancelado: " + lista.removerPosicao(3));
        lista.imprimir();

        // Busca pelo id
        System.out.println("\nBuscando o pedido de id 5...");
        int pos = lista.buscarPorId(5);
        if (pos == -1) {
            System.out.println("Pedido 5 não encontrado (já foi atendido ou cancelado).");
        } else {
            System.out.println("Pedido 5 está na posição " + pos + " da fila.");
        }

        // Mostra os próximo 3 pedidos a partir da posição 2
        System.out.println();
        lista.exibirProximosN(2, 3);

        // Informações gerais
        System.out.println("\nInformações da fila:");
        System.out.println("Quantidade de pedidos: " + lista.tamanho());
        System.out.println("Fila vazia? " + lista.estaVazia());
        System.out.println("Primeiro pedido da fila: " + lista.obterCabeca());
        System.out.println("Último pedido da fila: " + lista.obterCauda());
    }
}