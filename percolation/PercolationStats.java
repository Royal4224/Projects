/* *****************************************************************************
 *  Name:    Roy Wang
 *  NetID:   rwang
 *  Precept: P00
 *
 *  Description:  Percolation Stats
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double confidence_95;
    private final double[] threshold;
    private final int n;
    private final double trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0.");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials must be greater than 0.");
        }
        this.trials = trials;
        this.n = n;
        confidence_95 = 1.96;
        threshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            threshold[i] = findThresh();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - confidence_95 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + confidence_95 * stddev() / Math.sqrt(trials);
    }

    private double findThresh() {
        int count = 0;
        int row, col;
        Percolation trial = new Percolation(n);
        while (!trial.percolates()) {
            do {
                row = StdRandom.uniform(n) + 1;
                col = StdRandom.uniform(n) + 1;
            } while (trial.isOpen(row, col));
            trial.open(row, col);
            count++;
        }
        return (((double) count) / (n * n));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats nums = new PercolationStats(n, t);
        System.out.println("mean = " + nums.mean());
        System.out.println("stddev = " + nums.stddev());
        System.out.println(
                "95% confidence interval = [" + nums.confidenceLo() + ", " + nums.confidenceHi()
                        + "]");
    }

}
