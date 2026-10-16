package dsa.m5_np;

import java.util.ArrayList;
import java.util.List;

public class VertexCoverApproximation {

    private boolean[][] graph;
    private int vertices;

    public VertexCoverApproximation(
            int vertices) {

        this.vertices = vertices;

        graph =
                new boolean[vertices][vertices];
    }

    // Add an undirected edge
    public void addEdge(
            int first,
            int second) {

        graph[first][second] = true;
        graph[second][first] = true;
    }

    // Find a 2-approximate vertex cover
    public List<Integer> findApproximateCover() {

        boolean[][] remaining =
                copyGraph();

        boolean[] selected =
                new boolean[vertices];

        List<Integer> cover =
                new ArrayList<>();

        while (true) {

            int first = -1;
            int second = -1;

            // Find any remaining edge
            for (int i = 0;
                    i < vertices;
                    i++) {

                for (int j = i + 1;
                        j < vertices;
                        j++) {

                    if (remaining[i][j]) {

                        first = i;
                        second = j;

                        break;
                    }
                }

                if (first != -1) {
                    break;
                }
            }

            // No edges remain
            if (first == -1) {
                break;
            }

            // Select both endpoints
            if (!selected[first]) {

                selected[first] = true;
                cover.add(first);
            }

            if (!selected[second]) {

                selected[second] = true;
                cover.add(second);
            }

            // Remove all edges connected
            // to the selected vertices
            removeIncidentEdges(
                    remaining,
                    first
            );

            removeIncidentEdges(
                    remaining,
                    second
            );
        }

        return cover;
    }

    // Remove all edges incident
    // to one vertex
    private void removeIncidentEdges(
            boolean[][] graph,
            int vertex) {

        for (int i = 0;
                i < vertices;
                i++) {

            graph[vertex][i] = false;
            graph[i][vertex] = false;
        }
    }

    // Copy original graph
    private boolean[][] copyGraph() {

        boolean[][] copy =
                new boolean[vertices][vertices];

        for (int i = 0;
                i < vertices;
                i++) {

            for (int j = 0;
                    j < vertices;
                    j++) {

                copy[i][j] =
                        graph[i][j];
            }
        }

        return copy;
    }

    // Verify that a set is a vertex cover
    public boolean isVertexCover(
            List<Integer> cover) {

        boolean[] selected =
                new boolean[vertices];

        for (int vertex : cover) {

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

    // Display vertex cover
    public void displayCover(
            List<Integer> cover) {

        System.out.println(
                "Approximate Vertex Cover:"
        );

        for (int vertex : cover) {

            System.out.print(
                    vertex + " "
            );
        }

        System.out.println();

        System.out.println(
                "Cover Size: "
                + cover.size()
        );
    }

    public int getVertexCount() {
        return vertices;
    }
}