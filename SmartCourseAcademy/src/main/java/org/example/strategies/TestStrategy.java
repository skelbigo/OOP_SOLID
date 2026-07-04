package org.example.strategies;

public class TestStrategy implements EvaluationStrategy {
    @Override
    public String evaluate(int score) {
        return score >= 60 ? "Test Passed (" + score + " pts)" : "Test Failed (" + score + " pts)";
    }
}
