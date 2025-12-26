package com.example.demo.util;

/**
 * Productivity score logic aligned with spec/tests:
 * - NaN/infinite => 0.0
 * - negative values normalized to 0
 * - formula: (hours * 10) + (tasks * 5) - (meetings * 5)
 * - clamp to [0.0, 100.0]
 * - round to 2 decimals
 */
public final class ProductivityCalculator {

    private ProductivityCalculator() {}

    public static double computeScore(double hoursLogged, int tasksCompleted, int meetingsAttended) {
        if (Double.isNaN(hoursLogged) || Double.isInfinite(hoursLogged)) {
            return 0.0;
        }
        double hours = Math.max(0.0, hoursLogged);
        int tasks = Math.max(0, tasksCompleted);
        int meetings = Math.max(0, meetingsAttended);

        double raw = hours * 10.0 + tasks * 5.0 - meetings * 5.0;
        double clamped = Math.max(0.0, Math.min(100.0, raw));
        double rounded = Math.round(clamped * 100.0) / 100.0;
        return rounded;
    }
}