package com.example.demo.util;

public class ProductivityCalculator {

    public static double computeScore(double hoursLogged, int tasksCompleted, int meetingsAttended) {
        double h = Math.max(0, hoursLogged);
        int t = Math.max(0, tasksCompleted);
        int m = Math.max(0, meetingsAttended);
        double score = (h * 10) + (t * 5) + (m * 2);
        if (score > 100) return 100;
        if (score < 0) return 0;
        return score;
    }
}
