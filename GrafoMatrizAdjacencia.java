import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class GrafoMatrizAdjacencia extends Grafo {

    protected int[][] matrizAdjacencia;
    protected String[] vertices;

    protected Map<String, Integer> mapaVertices; // Mapeamento de vértices para índices na matriz
    protected int numVertices; // Contador de vértices para atribuir índices
    protected int capacidade; // Capacidade máxima da matriz
    protected List<Integer> indicesRemovidos; // Lista de índices de vértices removidos para reutilização


    public GrafoMatrizAdjacencia() {
        super();
        this.capacidade = 100; // Capacidade inicial da matriz
        this.matrizAdjacencia = new int[capacidade][capacidade];
        this.vertices = new String[capacidade];
        this.mapaVertices = new HashMap<>();
        this.numVertices = 0;
        this.indicesRemovidos = new ArrayList<>();
    }

    // Adiciona um novo vértice ao grafo, caso ele ainda não exista.
    @Override
    public void adicionarVertice(String vertice){
        if (numVertices == capacidade) {
            System.out.println("Tamanho máximo já");
            return;
        } else {
            if (!mapaVertices.containsKey(vertice)) {
                int proxIndice;
                
                if(!indicesRemovidos.isEmpty()) {
                    proxIndice = indicesRemovidos.remove(0);
                } else {
                    proxIndice = numVertices;
                    numVertices++;
                }

                mapaVertices.put(vertice, proxIndice);
                vertices[proxIndice] = vertice;
                this.ordem++;
            }
        }
    }

    // Remove um vértice do grafo. Ao remover o vértice, todas as arestas incidentes
    // a ele também devem ser removidas.
    @Override
    public void removerVertice(String vertice){
        if (numVertices == 0) {
            System.out.println("O grafo ta vazio");
            return;
        }

        if (mapaVertices.containsKey(vertice)) {
            int indice = mapaVertices.get(vertice);

            for (int i = 0; i < capacidade; i++) {
                if (matrizAdjacencia[indice][i] != 0) this.tamanho--;
                matrizAdjacencia[indice][i] = 0;
                matrizAdjacencia[i][indice] = 0;
            }

            mapaVertices.remove(vertice);
            indicesRemovidos.add(indice);
            vertices[indice] = null;

            this.ordem--;
        } else {
            System.out.println("Vértice não encontrado");
        }

        
    }

    // Adiciona uma aresta entre dois vértices. Como o grafo é não direcionado, a
    // ligação deve ser registrada nos dois sentidos.
    public void adicionarAresta(String origem, String destino){
        if (!existeVertice(origem) || !existeVertice(destino)) {
            System.out.println("Um ou ambos os vértices não existem no grafo.");
            return;
        }
        if (existeAresta(origem, destino)) {
            System.out.println("Aresta já existe");
            return;
        } else {
            int indiceOrigem = mapaVertices.get(origem);
            int indiceDestino = mapaVertices.get(destino);
            
            matrizAdjacencia[indiceOrigem][indiceDestino] = 1;
            matrizAdjacencia[indiceDestino][indiceOrigem] = 1;
            this.tamanho++;
        }
    }

    // Remove a aresta entre dois vértices, caso ela exista.
    public void removerAresta(String origem, String destino){
        if (!existeVertice(origem) || !existeVertice(destino)) {
            System.out.println("Um ou ambos os vértices não existem no grafo.");
            return;
        }
        if (!existeAresta(origem, destino)) {
            System.out.println("Aresta não existe");
            return;
        } else {
            int indiceOrigem = mapaVertices.get(origem);
            int indiceDestino = mapaVertices.get(destino);

            matrizAdjacencia[indiceOrigem][indiceDestino] = 0;
            matrizAdjacencia[indiceDestino][indiceOrigem] = 0;
            this.tamanho--;
        }
    }

    // Verifica se um determinado vértice pertence ao grafo.
    public boolean existeVertice(String vertice) {
        return mapaVertices.containsKey(vertice);
    }

    // Verifica se existe uma aresta entre dois vértices.
    public boolean existeAresta(String origem, String destino) {
        if (mapaVertices.containsKey(origem) && mapaVertices.containsKey(destino)){
            int indiceOrigem = mapaVertices.get(origem);
            int indiceDestino = mapaVertices.get(destino);

            return matrizAdjacencia[indiceOrigem][indiceDestino] != 0;
        }

        return false;
    }

    // Retorna o grau de um vértice, isto é, o número de vértices adjacentes a ele.
    public int grau(String vertice) {
        
        if (mapaVertices.containsKey(vertice)) {
            int grau = 0;
            int indice = mapaVertices.get(vertice);
            for (int i = 0; i < matrizAdjacencia[indice].length; i++) {
                if (matrizAdjacencia[indice][i] == 1) grau++;
            }
            
            return grau;
        }

        return -1;
    }

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
    // }
    @Override
    public String toString() {
        String s = "graph {\n";

        if (this.ordem > 0){
            for (int i = 0; i < this.capacidade; i++){
                for (int j = i; j < this.capacidade; j++){
                    if (matrizAdjacencia[i][j] != 0 && vertices[i] != null && vertices[j] != null) {
                        String v1 = vertices[i];
                        String v2 = vertices[j];
                        s += "\"" + v1 + "\"-- \"" + v2 + "\";\n";
                    }
                }
            }
        }

        s += "\n}";

        return s;
    }

}
