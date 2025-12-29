package com.example.demo.util;

public class ProductivityCalculator {
    public static double computeScore(double hours, int tasks, int meetings) {
        // Handle invalid values
        if (Double.isNaN(hours) || Double.isInfinite(hours) || hours < 0 || tasks < 0 || meetings < 0) {
            return 0.0;
        }
        
        // Base calculation
        double score = (hours * 10) + (tasks * 5) - (meetings * 2); // Penalty for too many meetings
        
        // Clamp to 0-100 and round to 2 decimal places
        score = Math.min(100.0, Math.max(0.0, score));
        return Math.round(score * 100.0) / 100.0;
    }
}