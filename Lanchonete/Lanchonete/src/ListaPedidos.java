public class ListaPedidos {

    private Pedido cabeca;
    private Pedido cauda;
    private int tamanho;

    public ListaPedidos() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    
    public int tamanho() { return tamanho; }
    public boolean estaVazia() { return tamanho == 0; }
    public Pedido obterCabeca() { return cabeca; }
    public Pedido obterCauda() { return cauda; }

    //Inserções

    /** Urgente: insere no início (+20% no valor) */
    public void inserirUrg(Pedido p) {
        p.valor = p.valor * 1.20;
        p.tipo = "URGENTE";
        if (estaVazia()) {
            cabeca = p;
            cauda = p;
        } else {
            p.proximo = cabeca;
            cabeca = p;
        }
        tamanho++;
    }

    /** Normal: insere no fim */
    public void inserirNor(Pedido p) {
        p.tipo = "NORMAL";
        if (estaVazia()) {
            cabeca = p;
            cauda = p;
        } else {
            cauda.proximo = p;
            cauda = p;
        }
        tamanho++;
    }

    /**
     * Prioritário: insere na posição MEDIANA (+10% no valor).
     * Mediana = tamanho / 2 (índice onde entra o novo nó).
     */
    public void inserirPri(Pedido p) {
        p.valor = p.valor * 1.10;
        p.tipo = "PRIORITARIO";

        if (estaVazia()) {
            cabeca = p;
            cauda = p;
            tamanho++;
            return;
        }

        int meio = tamanho / 2; // posição de inserção
        if (meio == 0) {
            p.proximo = cabeca;
            cabeca = p;
        } else {
            Pedido anterior = cabeca;
            for (int i = 0; i < meio - 1; i++) {
                anterior = anterior.proximo;
            }
            p.proximo = anterior.proximo;
            anterior.proximo = p;
            if (p.proximo == null) cauda = p; // ajusta cauda se necessário
        }
        tamanho++;
    }

    // Rmove

    /** Remove do início (pedido atendido) */
    public Pedido removerInicio() {
        if (estaVazia()) return null;
        Pedido removido = cabeca;
        cabeca = cabeca.proximo;
        removido.proximo = null;
        tamanho--;
        if (tamanho == 0) cauda = null;
        return removido;
    }

    /** Remove do fim (cancelamento) */
    public Pedido removerFim() {
        if (estaVazia()) return null;
        if (tamanho == 1) {
            Pedido removido = cabeca;
            cabeca = null;
            cauda = null;
            tamanho--;
            return removido;
        }
        Pedido atual = cabeca;
        while (atual.proximo != cauda) {
            atual = atual.proximo;
        }
        Pedido removido = cauda;
        atual.proximo = null;
        cauda = atual;
        tamanho--;
        return removido;
    }

    /** Remove na posição k (0-based) */
    public Pedido removerPosicao(int k) {
        if (k < 0 || k >= tamanho) return null;
        if (k == 0) return removerInicio();
        if (k == tamanho - 1) return removerFim();

        Pedido anterior = cabeca;
        for (int i = 0; i < k - 1; i++) {
            anterior = anterior.proximo;
        }
        Pedido removido = anterior.proximo;
        anterior.proximo = removido.proximo;
        removido.proximo = null;
        tamanho--;
        return removido;
    }

    // Busca

    /** Busca por id_pedido (retorna índice ou -1) */
    public int buscarPorId(int id) {
        Pedido atual = cabeca;
        int i = 0;
        while (atual != null) {
            if (atual.id_pedido == id) return i;
            atual = atual.proximo;
            i++;
        }
        return -1;
    }

    /** Busca por posição (retorna o Pedido) */
    public Pedido buscarPorPosicao(int pos) {
        if (pos < 0 || pos >= tamanho) return null;
        Pedido atual = cabeca;
        for (int i = 0; i < pos; i++) atual = atual.proximo;
        return atual;
    }

    /** Próximos N pedidos a partir de uma posição */
    public void exibirProximosN(int pos, int n) {
        if (pos < 0 || pos >= tamanho) {
            System.out.println("Posição inválida.");
            return;
        }
        System.out.println("Próximos " + n + " pedidos (a partir de " + pos + ")");
        Pedido atual = buscarPorPosicao(pos);
        int cont = 0;
        while (atual != null && cont < n) {
            System.out.println((pos + cont) + "º -> " + atual);
            atual = atual.proximo;
            cont++;
        }
    }

    // Mostra na tela

    public void imprimir() {
        if (estaVazia()) {
            System.out.println("[Lista vazia]");
            return;
        }
        System.out.println("Fila de Pedidos (ordem de atendimento)");
        Pedido atual = cabeca;
        int i = 0;
        while (atual != null) {
            System.out.println(i + "º -> " + atual);
            atual = atual.proximo;
            i++;
        }
        System.out.println("(tamanho=" + tamanho + ")");
    }
}