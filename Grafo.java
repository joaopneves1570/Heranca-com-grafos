//Mateus Juares Felipe - 16891602
//Jão Pedro Neves -

abstract public class Grafo {

    protected int tamanho; // numero de arestas
    protected int ordem; // numero de vertices

    // Adiciona um novo vértice ao grafo, caso ele ainda não exista.
    public abstract void adicionarVertice(String vertice);

    // Remove um vértice do grafo. Ao remover o vértice, todas as arestas incidentes
    // a ele também devem ser removidas.
    public abstract void removerVertice(String vertice);

    // Adiciona uma aresta entre dois vértices. Como o grafo é não direcionado, a
    // ligação deve ser registrada nos dois sentidos.
    public abstract void adicionarAresta(String origem, String destino);

    // Remove a aresta entre dois vértices, caso ela exista.
    public abstract void removerAresta(String origem, String destino);

    // Verifica se um determinado vértice pertence ao grafo.
    public abstract boolean existeVertice(String vertice);

    // Verifica se existe uma aresta entre dois vértices.
    public abstract boolean existeAresta(String origem, String destino);

    // Retorna o grau de um vértice, isto é, o número de vértices adjacentes a ele.
    public abstract int grau(String vertice);

    // Retorna a ordem do grafo, isto é, o número de vértices.
    public int ordem() {
        return this.ordem;
    }

    // Retorna o tamanho do grafo, isto é, o número de arestas.
    public int tamanho() {
        return this.tamanho;
    }

    // Retorna uma representação textual do grafo
    // graph {
    // "A" -- "B"; -> sem peso
    // "A" -- "C" [label="3"]; -> com peso
    // }
    @Override
    public abstract String toString();

}