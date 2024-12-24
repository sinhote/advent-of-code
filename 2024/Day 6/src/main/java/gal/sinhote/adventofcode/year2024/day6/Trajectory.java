package gal.sinhote.adventofcode.year2024.day6;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trajectory {

	public enum StopReason {
		OUT_OF_BOUNDS,
		LOOP;
	}

	public final StopReason stopReason;
	public final List<GuardBearing> steps;

	public Trajectory(final StopReason stopReason, final List<GuardBearing> steps) {
		this.stopReason = requireNonNull(stopReason);
		this.steps = unmodifiableList(new ArrayList<>(requireNonNull(steps)));
	}

	@Override
	public int hashCode() {
		return Objects.hash(stopReason, steps);
	}

	@Override
	public boolean equals(final Object o) {
		return o instanceof Trajectory && stopReason == ((Trajectory) o).stopReason && steps.equals(((Trajectory) o).steps);
	}
}
