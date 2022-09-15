/* *****************************************************************************
 *  Name:    Roy Wang
 *  NetID:   rwang21
 *  Precept: P00
 *
 *  Description:  Determines if there is a path from top to bottom of a grid.
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n; // size of desired grid.
    private final int top; // virtual top
    private final int bot; // virtual bottom
    private final boolean[][] sites; // keeps track of open/closed sites
    private int countOpen; // counts number of open sites
    private final WeightedQuickUnionUF grid; // wquf with bottom for Percolates method
    private final WeightedQuickUnionUF gridnoB; // wquf that prevents backwash

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
        top = n * n;
        bot = n * n + 1;
        countOpen = 0;
        sites = new boolean[n][n];
        grid = new WeightedQuickUnionUF((n * n + 2));
        gridnoB = new WeightedQuickUnionUF((n * n + 2));
    }

    // compensates openzero for the 1 -> n indexing
    public void open(int r, int c) {
        openzero(r - 1, c - 1);
    }

    // opens the site (row, col) if it is not open already
    private void openzero(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n)
            throw new IllegalArgumentException();
        int num = index(r, c);
        if (!isopenzero(r, c)) {
            sites[r][c] = true;
            countOpen++;

            if (r == 0) {
                grid.union(top, num);
                gridnoB.union(top, num);
            }
            if (r == n - 1) {
                grid.union(bot, num);
            }

            if (isopenzero((r - 1), c)) {
                int index = index(r - 1, c);
                grid.union(index, num);
                gridnoB.union(index, num);
            }
            if (isopenzero((r + 1), c)) {
                int index = index(r + 1, c);
                grid.union(index, num);
                gridnoB.union(index, num);
            }
            if (isopenzero(r, (c - 1))) {
                int index = index(r, c - 1);
                grid.union(index, num);
                gridnoB.union(index, num);
            }
            if (isopenzero(r, (c + 1))) {
                int index = index(r, c + 1);
                grid.union(index, num);
                gridnoB.union(index, num);
            }
        }
    }

    // is the site (row, col) open?
    // compensates isopenzero for the 1 -> n indexing
    public boolean isOpen(int r, int c) {
        if (r <= 0 || r > n || c <= 0 || c > n)
            throw new IllegalArgumentException();
        return isopenzero(r - 1, c - 1);
    }

    private boolean isopenzero(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n && sites[r][c];
    }

    // is the site (row, col) full?
    // compensates fullzero for the 1 -> n indexing
    public boolean isFull(int r, int c) {
        return fullzero(r - 1, c - 1);
    }


    private boolean fullzero(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n)
            throw new IllegalArgumentException();
        return gridnoB.find(index(r, c)) == gridnoB.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.find(top) == grid.find(bot);
    }

    // finds the numbers at the given index
    private int index(int row, int col) {
        return n * (row) + (col);
    }

    // test client (optional)
    public static void main(String[] args) {
        // empty, intentional
        // not in use
    }

}
