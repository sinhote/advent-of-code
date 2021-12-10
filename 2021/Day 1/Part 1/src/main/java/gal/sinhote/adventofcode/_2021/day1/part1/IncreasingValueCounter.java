package gal.sinhote.adventofcode._2021.day1.part1;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Hold a single value in a sequence of numerical values and count how many of the provided values were bigger than the previous in the sequence
 *
 * @param <T> the type of values
 */
public class IncreasingValueCounter<T extends Number & Comparable<T>> {

	T currentValue;
	long count = 0;

	/**
	 * Initialize an instance of this class with the first value in the sequence.
	 *
	 * @param initialValue the first value in the numerical sequence
	 * @throws NullPointerException if the provided argument is {@code null}
	 */
	public IncreasingValueCounter(T initialValue) {
		Objects.requireNonNull(initialValue);

		currentValue = initialValue;
	}

	/**
	 * Provide the next element in the sequence. If the element is bigger than the previous one in the sequence, increment the counter.
	 *
	 * @param nextElement the next element in the sequence
	 * @throws NullPointerException if the provided argument is {@code null}
	 */
	public void nextElement(T nextElement) {
		// Not strictly necessary, but here for clarity
		Objects.requireNonNull(nextElement);

		if (currentValue.compareTo(nextElement) < 0) {
			count++;
		}
		currentValue = nextElement;
	}

	/**
	 * Process the elements in the provided stream in order, i.e., count which elements in the stream are bigger than their predecessors.
	 *
	 * @param <V>
	 * @param elements a {@link Stream} containing the next elements in the sequence
	 */
	public <V extends T> void nextElements(Stream<V> elements) {
		Objects.requireNonNull(elements);

		elements.forEachOrdered(this::nextElement);
	}

	/**
	 * Process the elements in the provided collection, i.e., count which elements in the collection are bigger than their predecessors.
	 *
	 * @param <V>
	 * @param elements a {@link Collection} containing the next elements in the sequence
	 */
	public <V extends T> void nextElements(Collection<V> elements) {
		Objects.requireNonNull(elements);

		for(V value : elements) {
			nextElement(value);
		}
	}

	public long getCount() {
		return count;
	}

	public T getCurrentValue() {
		return currentValue;
	}
}
