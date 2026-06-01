public class GrafoPonderadoMatrizAdjacencia extends GrafoMatrizAdjacencia {

    public GrafoPonderadoMatrizAdjacencia() {
        super();
    }


    // Adiciona uma aresta entre dois vértices. Como o grafo é não direcionado, a
    // ligação deve ser registrada nos dois sentidos.
    public void adicionarAresta(String origem, String destino, int peso){
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
            
            matrizAdjacencia[indiceOrigem][indiceDestino] = peso;
            matrizAdjacencia[indiceDestino][indiceOrigem] = peso;
            this.tamanho++;
        }
    }

    // Adiciona uma aresta entre dois vértices. Como o grafo é não direcionado, a
    // ligação deve ser registrada nos dois sentidos.
    // método override
    @Override
    public void adicionarAresta(String origem, String destino){
        this.adicionarAresta(origem, destino, 1);
    }

    // Retorna o grau de um vértice, isto é, o número de vértices adjacentes a ele.
    public int grau(String vertice) {
        
        if (mapaVertices.containsKey(vertice)) {
            int grau = 0;
            int indice = mapaVertices.get(vertice);
            for (int i = 0; i < matrizAdjacencia[indice].length; i++) {
                if (matrizAdjacencia[indice][i] != 0) grau++;
            }
            
            return grau;
        }

        return -1;
    }

    // Retorna uma representação textual do grafo
    // graph {
    // "A" -- "B"; -> sem peso
    // "A" -- "C" [label="3"]; -> com peso
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
                        String p = String.valueOf(matrizAdjacencia[i][j]);
                        s += "\"" + v1 + "\"-- \"" + v2 + " [label=\"" + p + "\"];\n";
                    }
                }
            }
        }

        s += "\n}";

        return s;
    }

}
