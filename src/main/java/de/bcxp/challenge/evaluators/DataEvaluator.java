package de.bcxp.challenge.evaluators;

import java.util.List;

/**
 * Interface for evaluating data and returning a result.
 *
 * @param <T> The type of data to evaluate.
 * @param <R> The type of the result after evaluation.
 */
public interface DataEvaluator<T, R> {
    /**
     * Evaluates the provided data and returns a result.
     *
     * @param data The list of data to evaluate.
     * @return The result of the evaluation.
     */
    R evaluate(List<T> data);
}
