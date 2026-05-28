//Mateus Juares Felipe - 16891602
//Jão Pedro Neves -

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GrafoListaAdjacencia extends Grafo {

    private Map<String, Set<String>> adjacencias;
    // String = vértice / key;
    // Set<String> = conjunto de vértices adjacentes a ele / value

    public GrafoListaAdjacencia() {

        adjacencias = new TreeMap<>();
        tamanho = 0;
        ordem = 0;
    }

    @Override
    public void adicionarVertice(String vertice) {

        if (!existeVertice(vertice)) {

            adjacencias.put(vertice, new TreeSet<>()); // adiciona o vertice como key e um conjunto vazio como value
            ordem++; // numero de vertices +1
        }
    }

    @Override
    public void removerVertice(String vertice) {

        if (!existeVertice(vertice)) {
            return;
        }

        for (String vizinho : adjacencias.get(vertice)) {
            adjacencias.get(vizinho).remove(vertice);
            tamanho--; // numero de arestas -1
        }

        adjacencias.remove(vertice);

        ordem--; // numero de vertices -1
    }

    @Override
    public void adicionarAresta(String origem, String destino) {

        adicionarVertice(origem);
        adicionarVertice(destino);

        if (!existeAresta(origem, destino)) {

            adjacencias.get(origem).add(destino);
            adjacencias.get(destino).add(origem);

            tamanho++; // numero de arestas +1
        }
    }

    @Override
    public void removerAresta(String origem, String destino) {

        if (!existeAresta(origem, destino)) {
            return;
        }

        adjacencias.get(origem).remove(destino);
        adjacencias.get(destino).remove(origem);

        tamanho--; // numero de arestas -1
    }

    @Override
    public boolean existeVertice(String vertice) {

        return adjacencias.containsKey(vertice); // verifica se a key "vertice" existe no Map adjacencias
    }

    @Override
    public boolean existeAresta(String origem, String destino) {
        if (!existeVertice(origem)) {
            return false;
        }

        return adjacencias.get(origem).contains(destino); // verifica se o value da key "origem" contém o "destino"
    }

    @Override
    public int grau(String vertice) {

        if (!existeVertice(vertice)) {
            return 0;
        }

        return adjacencias.get(vertice).size(); // retorna o numero de vertices adjacentes ao vertice
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(); // Função StringBuilder para construir a string de saída

        sb.append("graph {\n");

        for (String v1 : adjacencias.keySet()) {

            for (String v2 : adjacencias.get(v1)) {

                if (v1.compareTo(v2) < 0) {

                    sb.append("\"")
                            .append(v1)
                            .append("\" -- \"")
                            .append(v2)
                            .append("\";\n");
                }
            }
        }

        sb.append("}");

        return sb.toString();
    }
}