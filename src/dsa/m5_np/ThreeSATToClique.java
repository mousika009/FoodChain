package dsa.m5_np;

import java.util.ArrayList;
import java.util.List;

public class ThreeSATToClique {

    // Represents one literal
    // from one clause
    public static class LiteralNode {

        int clause;
        int literal;

        public LiteralNode(
                int clause,
                int literal) {

            this.clause = clause;
            this.literal = literal;
        }

        @Override
        public String toString() {

            if (literal < 0) {

                return "C"
                        + (clause + 1)
                        + ": NOT X"
                        + Math.abs(literal);

            } else {

                return "C"
                        + (clause + 1)
                        + ": X"
                        + literal;
            }
        }
    }

    // Graph representation
    private boolean[][] graph;

    private LiteralNode[] nodes;

    // Convert 3-SAT formula into graph
    public void buildGraph(
            int[][] clauses) {

        int clauseCount =
                clauses.length;

        int nodeCount =
                clauseCount * 3;

        graph =
                new boolean[nodeCount][nodeCount];

        nodes =
                new LiteralNode[nodeCount];

        int index = 0;

        // Create one vertex for
        // every literal
        for (int clause = 0;
                clause < clauseCount;
                clause++) {

            for (int j = 0;
                    j < 3;
                    j++) {

                nodes[index] =
                        new LiteralNode(
                                clause,
                                clauses[clause][j]
                        );

                index++;
            }
        }

        // Add edges between
        // compatible literals
        for (int i = 0;
                i < nodeCount;
                i++) {

            for (int j = i + 1;
                    j < nodeCount;
                    j++) {

                if (isCompatible(
                        nodes[i],
                        nodes[j])) {

                    graph[i][j] = true;
                    graph[j][i] = true;
                }
            }
        }
    }

    // Two literals are compatible if:
    //
    // 1. They belong to different clauses.
    // 2. They are not opposite literals.
    private boolean isCompatible(
            LiteralNode first,
            LiteralNode second) {

        if (first.clause ==
                second.clause) {

            return false;
        }

        if (first.literal ==
                -second.literal) {

            return false;
        }

        return true;
    }

    // Check whether two vertices
    // are connected
    public boolean hasEdge(
            int first,
            int second) {

        if (graph == null) {
            return false;
        }

        return graph[first][second];
    }

    // Find a clique of required size
    public List<LiteralNode> findClique(
            int requiredSize) {

        List<Integer> selected =
                new ArrayList<>();

        if (findCliqueRecursive(
                0,
                requiredSize,
                selected)) {

            List<LiteralNode> result =
                    new ArrayList<>();

            for (int index : selected) {

                result.add(
                        nodes[index]
                );
            }

            return result;
        }

        return new ArrayList<>();
    }

    // Backtracking search for clique
    private boolean findCliqueRecursive(
            int start,
            int requiredSize,
            List<Integer> selected) {

        if (selected.size()
                == requiredSize) {

            return true;
        }

        for (int i = start;
                i < nodes.length;
                i++) {

            if (canAdd(
                    i,
                    selected)) {

                selected.add(i);

                if (findCliqueRecursive(
                        i + 1,
                        requiredSize,
                        selected)) {

                    return true;
                }

                selected.remove(
                        selected.size() - 1
                );
            }
        }

        return false;
    }

    // Check whether a vertex can be
    // added to the current clique
    private boolean canAdd(
            int vertex,
            List<Integer> selected) {

        for (int existing :
                selected) {

            if (!graph[vertex][existing]) {
                return false;
            }
        }

        return true;
    }

    // Display graph
    public void displayGraph() {

        if (graph == null) {

            System.out.println(
                    "Graph has not been built."
            );

            return;
        }

        System.out.println(
                "3-SAT to CLIQUE Graph:"
        );

        for (int i = 0;
                i < nodes.length;
                i++) {

            System.out.print(
                    i + " -> "
                    + nodes[i]
                    + " : "
            );

            for (int j = 0;
                    j < nodes.length;
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

    // Display a found clique
    public void displayClique(
            List<LiteralNode> clique) {

        if (clique.isEmpty()) {

            System.out.println(
                    "No required clique found."
            );

            return;
        }

        System.out.println(
                "Clique:"
        );

        for (LiteralNode node :
                clique) {

            System.out.println(
                    node
            );
        }
    }

    // Get number of graph vertices
    public int getVertexCount() {

        if (nodes == null) {
            return 0;
        }

        return nodes.length;
    }
}