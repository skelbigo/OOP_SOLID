package org.example.strategies;

public class PracticalStrategy implements EvaluationStrategy {
    @Override
    public String evaluate(int score) {
        return score >= 80 ? "Practical Work Accepted" : "Practical Work Needs Rework";
    }
}
