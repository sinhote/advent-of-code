package gal.sinhote.adventofcode.year2024.day7;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class EquationChecker {

	public static final class EquationCheckResult {
		private final Equation equation;
		private final List<List<Operator>> matchingOperators;

		public EquationCheckResult(final Equation equation, final List<List<Operator>> matchingOperators) {
			this.equation = requireNonNull(equation);
			this.matchingOperators = matchingOperators.stream().map(Collections::<Operator>unmodifiableList)
					.collect(collectingAndThen(toList(), Collections::unmodifiableList));
		}

		public Equation equation() {
			return equation;
		}

		public List<List<Operator>> matchingOperators() {
			return matchingOperators;
		}
	}

	public EquationCheckResult check(final Equation equation, final Set<? extends Operator> availableOperators) {
		final Stream<Stream<Operator>> operators = selfCartesianProduct(equation.operands().size()-1, availableOperators);

		return operators
				.map(choiceStream -> choiceStream.collect(toList()))
				.filter(choices -> doOperatorsMatch(equation, choices))
				.collect(collectingAndThen(toList(), choiceList -> new EquationCheckResult(equation, choiceList)));
	}

	private boolean doOperatorsMatch(final Equation equation, final List<? extends Operator> operatorChoice) {
		long acc = equation.operands().get(0);
		for (int i = 1; i < equation.operands().size(); i++) {
			acc = operatorChoice.get(i-1).calculate(acc, equation.operands().get(i));
		}
		return acc == equation.result();
	}

	private <T> Stream<Stream<T>> selfCartesianProduct(final int repetitions, final Collection<? extends T> set) {
		return selfCartesianProduct(0, repetitions, set);
	}

	private <T> Stream<Stream<T>> selfCartesianProduct(final int index, final int repetitions, Collection<? extends T> sets) {
		if (index == repetitions) {
			return Stream.of(Stream.empty());
		}

		return sets.stream().flatMap(element -> selfCartesianProduct(index+1, repetitions, sets)
				.map(stream -> Stream.concat(asList(element).stream(), stream)));
	}
}
