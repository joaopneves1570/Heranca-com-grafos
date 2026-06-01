//Mateus Juares Felipe - 16891602
//Jão Pedro Neves - 14713404

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GrafoListaAdjacencia grafoLista = new GrafoListaAdjacencia();
        GrafoMatrizAdjacencia grafoMatriz = new GrafoMatrizAdjacencia();
        GrafoPonderadoMatrizAdjacencia grafoPonderado = new GrafoPonderadoMatrizAdjacencia();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {

            String linha = scanner.nextLine().trim();

            if (linha.isEmpty()) {
                continue;
            }

            String[] partes = linha.split("\\s+");

            char comando = partes[0].charAt(0);

            String v1, v2;
            int peso;
            
            switch (comando) {

                case 'i':
                    v1 = partes[1];
                    v2 = partes[2];
                    peso = Integer.parseInt(partes[3]);

                    // lista
                    grafoLista.adicionarAresta(v1, v2);

                    // matriz
                    grafoMatriz.adicionarAresta(v1, v2);

                    // ponderado
                    grafoPonderado.adicionarAresta(v1, v2, peso);

                    break;

                case 'd':

                    // d A

                    if(partes.length == 2){
                        v1 = partes[1];
                        grafoLista.removerVertice(v1);
                        grafoMatriz.removerVertice(v1);
                        grafoPonderado.removerVertice(v1);

                    }

                    // d A B
                    else if (partes.length == 3) {
                        v1 = partes[1];
                        v2 = partes[2];
                        grafoLista.removerAresta(v1, v2);
                        grafoMatriz.removerAresta(v1, v2);
                        grafoPonderado.removerAresta(v1, v2);
                    }

                    break;

                case 'p':

                    System.out.println("Lista de Adjacencia");
                    System.out.println(grafoLista);

                    System.out.println("Matriz de Adjacencia");
                    System.out.println(grafoMatriz);

                    System.out.println("Ponderado - Matriz de Adjacencia");
                    System.out.println(grafoPonderado);


                    break;

                default:
                    System.out.println("Comando inválido");
            }
        }

        scanner.close();
    }
}