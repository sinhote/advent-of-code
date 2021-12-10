package gal.sinhote.adventofcode._2021.day2.part1;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Movement {
	FORWARD, UP, DOWN;

	private static final Map<String, Movement> strToMovement;

	static {
		strToMovement = Collections.unmodifiableMap(Stream.of(Movement.values()).collect(Collectors.toMap(Movement::name, Function.identity())));
	}

	public static Optional<Movement> parseMovement(String movementStr) {
		Objects.requireNonNull(movementStr);

		return Optional.ofNullable(strToMovement.get(movementStr.toUpperCase()));
	}
}
