package gal.sinhote.adventofcode.year2024.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {

	public static void main(String[] argv) {
		if (argv.length < 1) {
			System.err.println("An input file must be provided!");
			return;
		}

		try {
			final EquationChecker equationChecker = new EquationChecker();
			final List<Equation> equations = Files.lines(Paths.get(argv[0]))
					.map(Equation::parseEquation)
					.collect(Collectors.toList());

			final long calibrationResult1 = equations.stream()
					.map(equation -> equationChecker.check(equation, Stream.of(OperatorPart1.values()).collect(Collectors.toSet())))
					.filter(result -> !result.matchingOperators().isEmpty())
					.map(EquationChecker.EquationCheckResult::equation)
					.collect(Collectors.summingLong(Equation::result));

			System.out.println("The total calibration result is " + calibrationResult1);

			final long calibrationResult2 = equations.stream()
					.map(equation -> equationChecker.check(equation, Stream.of(OperatorPart2.values()).collect(Collectors.toSet())))
					.filter(result -> !result.matchingOperators().isEmpty())
					.map(EquationChecker.EquationCheckResult::equation)
					.collect(Collectors.summingLong(Equation::result));

			System.out.println("The new total calibration result is " + calibrationResult2);

		} catch (IOException e) {
			System.err.println("Couldn't read input file " + argv[0]);
		}
	}
}