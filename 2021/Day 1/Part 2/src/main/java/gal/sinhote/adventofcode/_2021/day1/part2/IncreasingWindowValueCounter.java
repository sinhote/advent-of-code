package gal.sinhote.adventofcode._2021.day1.part2;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Receive a sequence of numerical value. For a window of N elements, calculate
 * how many windows sum more than the next window. Any element is the start of a
 * new window, provided there are at least N-1 subsequent elements.
 *
 * <p>
 * For instance, for a window of size 2, the first window would contain the
 * first and second elements in the sequence; the second window would contain
 * the second and third elements of the sequence; and so on. For a window of
 * length 4, the first window would contain the 1st, 2nd, 3rd and 4th elements
 * in the sequence; the second window would contain the 2nd, 3rd, 4th and 5th
 * elements in the sequence; and so on.
 *
 * @param <T> the type of values
 */
public class IncreasingWindowValueCounter {

	Deque<Integer> slidingWindow;

	int windowSize;
	long currentSum;
	long count = 0;

	/**
	 * Initialize an instance of this class with the first value in the sequence.
	 *
	 * @param initialValue the first value in the numerical sequence
	 * @param windowSize   the size of the sliding window used
	 */
	public IncreasingWindowValueCounter(int initialValue, int windowSize) {
		if (windowSize <= 0) {
			throw new IllegalArgumentException("Window size must be a positive integer");
		}

		this.windowSize = windowSize;

		slidingWindow = new ArrayDeque<>(windowSize);
		slidingWindow.add(initialValue);
		currentSum = initialValue;
	}

	/**
	 * Provide the next element in the sequence.
	 *
	 * <p>
	 * When the sequence contains at least one full window, compare the sum of the
	 * previous window with the sum of the new window (resulting from removing the
	 * oldest element in the previous window and adding the new element
	 * {@code nextElement}, provided as an argument to this method). If the sum of
	 * the new window is bigger than the sum of the previous window, increment the
	 * counter.
	 *
	 * @param nextElement the next element in the sequence
	 */
	public void nextElement(int nextElement) {
		if (slidingWindow.size() >= windowSize) {
			int oldestElement = slidingWindow.removeFirst();

			if (nextElement > oldestElement) {
				count++;
			}

			currentSum -= oldestElement;
		}

		slidingWindow.add(nextElement);
		currentSum += nextElement;
	}

	/**
	 * Process the elements in the provided stream in order, i.e., group them in
	 * windows and count how many windows sum more than their preceding window.
	 *
	 * @param <V>
	 * @param elements a {@link Stream} containing the next elements in the sequence
	 */
	public void nextElements(Stream<Integer> elements) {
		Objects.requireNonNull(elements);

		elements.forEachOrdered(this::nextElement);
	}

	/**
	 * Process the elements in the provided collection in order, i.e., group them in
	 * windows and count how many windows sum more than their preceding window.
	 *
	 * @param <V>
	 * @param elements a {@link Collection} containing the next elements in the
	 *                 sequence
	 */
	public void nextElements(Collection<Integer> elements) {
		Objects.requireNonNull(elements);

		for (int value : elements) {
			nextElement(value);
		}
	}

	public long getCount() {
		return count;
	}
}
