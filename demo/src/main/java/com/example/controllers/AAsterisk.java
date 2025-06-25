package com.example.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class AAsterisk {

    public static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Pair && this.first == ((Pair) obj).first && this.second == ((Pair) obj).second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    private static class Details {
        double value;
        int i, j;

        private Details(double value, int i, int j) {
            this.value = value;
            this.i = i;
            this.j = j;
        }
    }

    private static class Cell {
        Pair parent;
        double f, g, h;

        Cell() {
            parent = new Pair(-1, -1);
            f = g = h = -1;
        }

        private Cell(Pair parent, double f, double g, double h) {
            this.parent = parent;
            this.f = f;
            this.g = g;
            this.h = h;
        }
    }

    private boolean isValid(int[][] grid, int rows, int cols, Pair point) {
        return (point.first >= 0 && point.first < rows && point.second >= 0 && point.second < cols);
    }

    private boolean isUnBlocked(int[][] grid, Pair point) {
        return grid[point.first][point.second] == 0 || grid[point.first][point.second] == 9
                || grid[point.first][point.second] == 4 || grid[point.first][point.second] == 2;
    }

    private boolean isDestination(Pair position, Pair dest) {
        return position.equals(dest);
    }

    // Distância estimada entre src e dest usando a distância Manhattan
    private double calculateHValue(Pair src, Pair dest) {
        return Math.abs(src.first - dest.first) + Math.abs(src.second - dest.second);
    }

    // Função que retorna o caminho completo do início até o destino
    public List<Pair> aStarPath(int[][] grid, Pair start, Pair dest) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (!isValid(grid, rows, cols, start) || !isValid(grid, rows, cols, dest) || !isUnBlocked(grid, start)
                || !isUnBlocked(grid, dest)) {
            System.out.println("Origem ou destino inválidos!");
            return Collections.emptyList();
        }

        PriorityQueue<Details> openList = new PriorityQueue<>((o1, o2) -> Double.compare(o1.value, o2.value));
        Cell[][] cellDetails = new Cell[rows][cols];

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                cellDetails[r][c] = new Cell();

        cellDetails[start.first][start.second].f = 0.0;
        cellDetails[start.first][start.second].g = 0.0;
        cellDetails[start.first][start.second].h = 0.0;
        cellDetails[start.first][start.second].parent = new Pair(start.first, start.second);

        openList.add(new Details(0.0, start.first, start.second));

        boolean[][] closedList = new boolean[rows][cols];

        while (!openList.isEmpty()) {
            Details current = openList.poll();
            int i = current.i;
            int j = current.j;

            closedList[i][j] = true;

            if (isDestination(new Pair(i, j), dest)) {
                return tracePath(cellDetails, dest);
            }

            for (int addX = -1; addX <= 1; addX++) {
                for (int addY = -1; addY <= 1; addY++) {
                    if (addX == 0 && addY == 0)
                        continue;
                    if (addX != 0 && addY != 0)
                        continue;

                    int newX = i + addX;
                    int newY = j + addY;

                    Pair neighbor = new Pair(newX, newY);

                    if (isValid(grid, rows, cols, neighbor) && isUnBlocked(grid, neighbor) && !closedList[newX][newY]) {
                        double gNew = cellDetails[i][j].g + 1.0;
                        double hNew = calculateHValue(neighbor, dest);
                        double fNew = gNew + hNew;

                        if (cellDetails[newX][newY].f == -1 || cellDetails[newX][newY].f > fNew) {
                            cellDetails[newX][newY].f = fNew;
                            cellDetails[newX][newY].g = gNew;
                            cellDetails[newX][newY].h = hNew;
                            cellDetails[newX][newY].parent = new Pair(i, j);

                            openList.add(new Details(fNew, newX, newY));
                        }
                    }
                }
            }
        }

        System.out.println("Falha em encontrar o destino.");
        return Collections.emptyList();
    }

    // Função para rastrear o caminho a partir do destino até o início
    private List<Pair> tracePath(Cell[][] cellDetails, Pair dest) {
        List<Pair> path = new ArrayList<>();
        Pair current = dest;
        path.add(current);

        while (!cellDetails[current.first][current.second].parent.equals(current)) {
            current = cellDetails[current.first][current.second].parent;
            path.add(current);
        }

        Collections.reverse(path);
        return path;
    }

    // Exemplo rápido para testar
    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 0, 1, 0 },
                { 1, 1, 0, 1, 0, 0, 1, 0 },
                { 1, 1, 0, 1, 0, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 1, 1, 1 },
                { 0, 1, 1, 1, 0, 1, 1, 0 },
                { 1, 1, 0, 1, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 0 }
        };

        AAsterisk app = new AAsterisk();
        Pair start = new Pair(0, 0);
        Pair dest = new Pair(6, 6);

        List<Pair> path = app.aStarPath(grid, start, dest);

        if (!path.isEmpty()) {
            System.out.println("Caminho:");
            for (Pair p : path) {
                System.out.print("-> (" + p.first + "," + p.second + ") ");
            }
        } else {
            System.out.println("Nenhum caminho encontrado.");
        }
    }
}
