package gal.sinhote.adventofcode.year2024.day7;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Equation {
	private final long result;
	private final List<Integer> operands;

	public static Equation parseEquation(final String line) {
		final String[] strResultAndOperands = line.split(":\\s*");
		final String[] strOperands = strResultAndOperands[1].split("\\s+");

		return new Equation(strResultAndOperands[0], strOperands);
	}

	public Equation(final String result, final String... arguments) {
		if (arguments.length < 2) {
			throw new IllegalArgumentException("An equation requires are least two arguments!");
		}
		this.result = Long.parseLong(result);
		operands = Stream.of(arguments)
				.map(Integer::valueOf)
				.collect(Collectors.collectingAndThen(toList(), Collections::unmodifiableList));
	}

	public Equation(final long result, final int... arguments) {
		if (arguments.length < 3) {
			throw new IllegalArgumentException("An equation requires are least two arguments!");
		}
		this.result = result;
		operands = IntStream.of(arguments)
				.boxed()
				.collect(Collectors.collectingAndThen(toList(), Collections::unmodifiableList));
	}

	public long result() {
		return result;
	}

	public List<Integer> operands() {
		return operands;
	}
}
