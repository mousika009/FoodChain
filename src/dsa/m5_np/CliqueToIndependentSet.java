package dsa.m5_np;

import java.util.ArrayList;
import java.util.List;

public class CliqueToIndependentSet {

    private boolean[][] graph;
    private boolean[][] complement;

    private int vertices;

    public CliqueToIndependentSet(
            int vertices) {

        this.vertices = vertices;

        graph =
                new boolean[vertices][vertices];

        complement =
                new boolean[vertices][vertices];
    }

    // Add an edge to the original graph
    public void addEdge(
            int first,
            int second) {

        graph[first][second] = true;
        graph[second][first] = true;
    }

    // Create complement graph
    public void buildComplement() {

        for (int i = 0;
                i < vertices;
                i++) {

            for (int j = 0;
                    j < vertices;
                    j++) {

                if (i == j) {

                    complement[i][j] = false;

                } else {

                    complement[i][j] =
                            !graph[i][j];
                }
            }
        }
    }

    // Check whether a set of vertices
    // is an independent set
    private boolean isIndependent(
            List<Integer> selected) {

        for (int i = 0;
                i < selected.size();
                i++) {

            for (int j = i + 1;
                    j < selected.size();
                    j++) {

                int first =
                        selected.get(i);

                int second =
                        selected.get(j);

                if (complement[first][second]) {

                    return false;
                }
            }
        }

        return true;
    }

    // Find an independent set
    // of required size
    public List<Integer> findIndependentSet(
            int requiredSize) {

        List<Integer> selected =
                new ArrayList<>();

        if (findSet(
                0,
                requiredSize,
                selected)) {

            return new ArrayList<>(
                    selected
            );
        }

        return new ArrayList<>();
    }

    // Backtracking search
    private boolean findSet(
            int start,
            int requiredSize,
            List<Integer> selected) {

        if (selected.size()
                == requiredSize) {

            return true;
        }

        for (int vertex = start;
                vertex < vertices;
                vertex++) {

            selected.add(vertex);

            if (isIndependent(
                    selected)) {

                if (findSet(
                        vertex + 1,
                        requiredSize,
                        selected)) {

                    return true;
                }
            }

            selected.remove(
                    selected.size() - 1
            );
        }

        return false;
    }

    // Display original graph
    public void displayGraph() {

        System.out.println(
                "Original Graph:"
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

    // Display complement graph
    public void displayComplement() {

        System.out.println(
                "Complement Graph:"
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

                if (complement[i][j]) {

                    System.out.print(
                            j + " "
                    );
                }
            }

            System.out.println();
        }
    }

    // Display independent set
    public void displayIndependentSet(
            List<Integer> independentSet) {

        if (independentSet.isEmpty()) {

            System.out.println(
                    "No required independent set found."
            );

            return;
        }

        System.out.println(
                "Independent Set:"
        );

        for (int vertex :
                independentSet) {

            System.out.print(
                    vertex + " "
            );
        }

        System.out.println();
    }

    public boolean[][] getComplement() {
        return complement;
    }
}