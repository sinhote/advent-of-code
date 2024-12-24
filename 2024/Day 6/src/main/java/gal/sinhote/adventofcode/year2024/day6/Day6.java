package gal.sinhote.adventofcode.year2024.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class Day6 {

	public static void main(String[] argv) {
		if (argv.length < 1) {
			System.err.println("An input file must be provided!");
			return;
		}

		try {
			final GuardMap guardMap = new GuardMap();
			Files.lines(Paths.get(argv[0])).forEach(guardMap::addLine);

			final List<GuardBearing> trajectory = guardMap.getTrajectory();

			final long noOfUniquePositions = trajectory.stream()
					.map(GuardBearing::position)
					.distinct()
					.count();
			System.out.println("The guard will visit " + noOfUniquePositions + " positions before leaving the map");

			final Set<Position> obstaclesCausingLoops = guardMap.findLoops(trajectory);
			System.out.println("There are " + obstaclesCausingLoops.size() + " positions causing the guard to go on a loop");

		} catch (IOException e) {
			System.err.println("Couldn't read input file " + argv[0]);
		}
	}
}