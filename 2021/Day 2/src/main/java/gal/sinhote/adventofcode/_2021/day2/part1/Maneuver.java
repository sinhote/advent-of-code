package gal.sinhote.adventofcode._2021.day2.part1;

import java.util.Objects;

/**
 * Represents a submarine maneuver, such as "go 5 units forward" or "go 10 units
 * deeper""
 */
public class Maneuver {

	private Movement movement;
	private int amount;

	private Maneuver(Movement theMovement, int amount) {
		Objects.requireNonNull(theMovement);

		this.movement = theMovement;
		this.amount = amount;
	}

	public Movement getMovement() {
		return movement;
	}

	public int getAmount() {
		return amount;
	}

	public static Maneuver parseManeuver(String maneuverStr) {
		try {
			String[] parts = maneuverStr.split("\\s+", 2);
			return new Maneuver(Movement.parseMovement(parts[0]).orElseThrow(), Integer.parseInt(parts[1]));
		} catch (Exception e) {
			throw new IllegalArgumentException("Error parsing Maneuver: " + e.getMessage(), e);
		}
	}
}
