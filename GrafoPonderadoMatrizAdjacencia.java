//Mateus Juares Felipe - 16891602
//Jão Pedro Neves - 14713404

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafoPonderadoMatrizAdjacencia extends GrafoMatrizAdjacencia {

    public GrafoPonderadoMatrizAdjacencia() {
        super();
    }


    // Adiciona uma aresta entre dois vértices. Como o grafo é não direcionado, a
    // ligação deve ser registrada nos dois sentidos.
    public void adicionarAresta(String origem, String destino, int peso){
        
        adicionarVertice(origem);
        adicionarVertice(destino);

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
            // Cria um array novo com os vértices para ordenar ele alfabéticamente
            List<String> verticesOrdenados = new ArrayList<>();
            for (int i = 0; i < this.capacidade; i++){
                if (vertices[i] != null) verticesOrdenados.add(vertices[i]);
            }

            Collections.sort(verticesOrdenados);

            // Printar os vertices isolados que não se ligam com ngm mas que existem
            for (int i = 0; i < verticesOrdenados.size(); i++) {
                
                String v1 = verticesOrdenados.get(i);

                if (grau(v1) == 0) s += "    \"" + v1 + "\";\n";
            }

            for (int i = 0; i < verticesOrdenados.size(); i++) {
                String v1 = verticesOrdenados.get(i);
                
                for (int j = i; j < verticesOrdenados.size(); j++) {
                    String v2 = verticesOrdenados.get(j);

                    if (existeAresta(v1, v2)) {
                        int indice1 = mapaVertices.get(v1);
                        int indice2 = mapaVertices.get(v2);
                        String p = String.valueOf(matrizAdjacencia[indice1][indice2]);
                        s += "    \"" + v1 + "\" -- \"" + v2 + "\" [label=\"" + p + "\"];\n";
                    }
                }
            }

        }

        s += "}";

        return s;
    }


}
