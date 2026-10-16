package dsa.m5_np;

import java.util.ArrayList;
import java.util.List;

public class IndependentSetToVertexCover {

    private boolean[][] graph;
    private int vertices;

    public IndependentSetToVertexCover(
            int vertices) {

        this.vertices = vertices;

        graph =
                new boolean[vertices][vertices];
    }

    // Add an edge
    public void addEdge(
            int first,
            int second) {

        graph[first][second] = true;
        graph[second][first] = true;
    }

    // Convert an independent set
    // into a vertex cover
    public List<Integer> convert(
            List<Integer> independentSet) {

        boolean[] isIndependent =
                new boolean[vertices];

        for (int vertex :
                independentSet) {

            if (vertex >= 0 &&
                    vertex < vertices) {

                isIndependent[vertex] = true;
            }
        }

        List<Integer> vertexCover =
                new ArrayList<>();

        for (int vertex = 0;
                vertex < vertices;
                vertex++) {

            if (!isIndependent[vertex]) {

                vertexCover.add(vertex);
            }
        }

        return vertexCover;
    }

    // Check whether a set is a vertex cover
    public boolean isVertexCover(
            List<Integer> vertexCover) {

        boolean[] selected =
                new boolean[vertices];

        for (int vertex :
                vertexCover) {

            if (vertex >= 0 &&
                    vertex < vertices) {

                selected[vertex] = true;
            }
        }

        for (int i = 0;
                i < vertices;
                i++) {

            for (int j = i + 1;
                    j < vertices;
                    j++) {

                if (graph[i][j]) {

                    if (!selected[i]
                            && !selected[j]) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    // Check whether a set is independent
    public boolean isIndependentSet(
            List<Integer> independentSet) {

        boolean[] selected =
                new boolean[vertices];

        for (int vertex :
                independentSet) {

            if (vertex >= 0 &&
                    vertex < vertices) {

                selected[vertex] = true;
            }
        }

        for (int i = 0;
                i < vertices;
                i++) {

            if (!selected[i]) {
                continue;
            }

            for (int j = i + 1;
                    j < vertices;
                    j++) {

                if (selected[j]
                        && graph[i][j]) {

                    return false;
                }
            }
        }

        return true;
    }

    // Display graph
    public void displayGraph() {

        System.out.println(
                "Graph:"
        );

        for (int i = 0;
                i < vertices;
                i++) {

            System.out.print(
                    i + " -> "
            );

            for (int j = 0;
                    j < vertices;
                    j++) {

                if (graph[i][j]) {

                    System.out.print(
                            j + " "
                    );
                }
            }

            System.out.println();
        }
    }

    // Display a set
    public void displaySet(
            String name,
            List<Integer> set) {

        System.out.print(
                name + ": "
        );

        for (int vertex : set) {

            System.out.print(
                    vertex + " "
            );
        }

        System.out.println();

        System.out.println(
                "Size: " + set.size()
        );
    }

    public int getVertexCount() {
        return vertices;
    }
}