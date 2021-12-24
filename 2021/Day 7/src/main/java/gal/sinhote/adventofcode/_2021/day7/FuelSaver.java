package gal.sinhote.adventofcode._2021.day7;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FuelSaver {

	List<Integer> submarineLocations = new ArrayList<>();
	int optimalConsumption = -1;
	int optimalConsumption2 = -1;

	public FuelSaver(Collection<Integer> submarineLocations) {
		Objects.requireNonNull(submarineLocations);

		if (submarineLocations.isEmpty()) {
			throw new IllegalArgumentException("At least one submarine location must be given");
		}

		if (submarineLocations.stream().anyMatch(l -> l < 0)) {
			throw new IllegalArgumentException("Submarine locations must be non-negative integers");
		}

		this.submarineLocations.addAll(submarineLocations);
	}

	public static FuelSaver fromFile(Path theFile) throws IOException {
		try (BufferedReader br = Files.newBufferedReader(theFile)) {
			String inputLine = br.readLine();
			if (inputLine != null) {
				return new FuelSaver(
						Stream.of(inputLine.split(",")).map(Integer::parseInt).collect(toList()));
			} else {
				throw new IllegalArgumentException("Inputfile must not be empty");
			}
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Input file contained an incorrect submarine position: they must be positive integers", nfe);
		}
	}

	public int getOptimalConsumption() {
		if (optimalConsumption == -1) {
			int maxX = submarineLocations.stream().mapToInt(i -> i).max().getAsInt();

			optimalConsumption = IntStream.rangeClosed(0, maxX)
					.map(p -> submarineLocations.stream().mapToInt(l -> Math.abs(p - l)).sum()).min().getAsInt();
		}

		return optimalConsumption;
	}

	public int getOptimalConsumptionPart2() {
		if (optimalConsumption2 == -1) {
			int maxX = submarineLocations.stream().mapToInt(i -> i).max().getAsInt();

			optimalConsumption2 = IntStream.rangeClosed(0, maxX)
					.map(p -> submarineLocations.stream().mapToInt(l -> IntStream.rangeClosed(0, Math.abs(p - l)).sum()).sum()).min().getAsInt();
		}

		return optimalConsumption2;
	}
}
