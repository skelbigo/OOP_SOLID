package org.example.strategies;

public class FinalProjectStrategy implements EvaluationStrategy {
    @Override
    public String evaluate(int score) {
        return "Final Project Graded: " + score + "/100";
    }
}
