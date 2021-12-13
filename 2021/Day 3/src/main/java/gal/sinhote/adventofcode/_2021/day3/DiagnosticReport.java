package gal.sinhote.adventofcode._2021.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticReport {

	private static final long MASK = 0x01;

	/** Holds the number of 1s found in each position */
	List<Long> onesPerPosition = new ArrayList<>();

	/** Store the diagnostic numbers added */
	List<Long> diagnosticNumbers = new ArrayList<>();

	/**
	 * Add another diagnostic number to the report.
	 *
	 * @param number
	 */
	public void addDiagnosticNumber(long number) {
		diagnosticNumbers.add(number);

		// Shift the number right in every iteration until there are no "1"s left, i.e.
		// until the number is 0
		// i counts the number index we are in; first index 0, then index 1 and so on
		for (int i = 0; number != 0; i++, number >>>= 1) {
			if (onesPerPosition.size() <= i) {
				// As the code makes no assumptions about the length of the number, the list
				// needs to be filled with as many elements as there are bits in the longest
				// binary number inserted
				onesPerPosition.add(0L);
			}
			if ((number & MASK) != 0) {
				onesPerPosition.set(i, onesPerPosition.get(i) + 1);
			}
		}
	}

	/**
	 * Add another binary diagnostic number to the report.
	 *
	 * @param number
	 * @throws NumberFormatException if the provided string cannot be parsed as a
	 *                               binary number
	 */
	public void addDiagnosticNumber(String number) throws NumberFormatException {
		addDiagnosticNumber(Long.parseLong(number, 2));
	}

	/**
	 * Read all binary numbers in the provided {@code Path}, one per line, and add
	 * them to the report
	 *
	 * @param thePath a path to a text file containing binary numbers to add to the
	 *                report, one per line
	 * @throws IOException if the path cannot be read
	 */
	public void addAllDiagnosticNumbers(Path thePath) throws IOException {
		try (BufferedReader br = Files.newBufferedReader(thePath)) {
			br.lines().forEach(this::addDiagnosticNumber);
		}
	}

	/**
	 * The <i>gamma rate</i> is a binary number so that every bit is the most
	 * frequent bit in that position across all the diagnostic numbers introduced
	 * via {@link addDiagnosticNumber}.
	 *
	 * @return the gamma rate
	 */
	public long getGammaRate() {
		long gammaRate = 0L;

		for (int i = 0, mask = 1; i < onesPerPosition.size(); i++, mask <<= 1) {
			/*
			 * If TN(0, i) is the total number of "0"s in the position i, and TN(1, i) is
			 * the total number of "1"s in the position i, we have to set a particular bit
			 * to "1" if: TN(1, i) > TN(0, i) [1] for every i found in the list.
			 *
			 * The number of "0"s in a position is the total amount of diagnostic numbers
			 * added ("size") minus the number of "1"s in that position:
			 *
			 * TN(0, i) = size - TN(1, i) [2]
			 *
			 * Substituting [2] in [1]:
			 *
			 * TN(1, i) > size - TN(1, i)
			 *
			 * , or equivalently:
			 *
			 * 2*TN(1, i) > size
			 */
			if (2 * onesPerPosition.get(i) >= diagnosticNumbers.size()) {
				gammaRate |= mask;
			}
		}

		return gammaRate;
	}

	/**
	 * @return the <i>gamma rate</i> as a String, padded with zeroes to the length
	 *         of the biggest diagnostic number found in this report
	 */
	public String getGammaRateAsStr() {
		return String.format("%1$" + onesPerPosition.size() + "s", Long.toBinaryString(getGammaRate())).replace(' ',
				'0');
	}

	/**
	 * The <i>epsilon rate</i> is a binary number so that every bit is the least
	 * frequent bit in that position across all the diagnostic numbers introduced
	 * via {@link addDiagnosticNumber}.
	 *
	 * <p>
	 * This means that the <i>epsilon rate</i> is, by definition, the 1's complement
	 * of the <i>gamma rate</i> (i.e for every position, the bit in the <i>epsilon
	 * rate</i> is the exact opposite of that in the <i>gamma rate</i>).
	 *
	 * @return the <i>epsilon rate</i>
	 */
	public long getEpsilonRate() {
		long gammaRate = getGammaRate();

		// Calculate mask to limit the number of returned bits to those significant bits
		// of the gamma rate at most
		long mask = (long) Math.pow(2, onesPerPosition.size()) - 1;
		return ~gammaRate & mask;
	}

	/**
	 * @return the <i>gamma rate</i> as a String, padded with zeroes to the length
	 *         of the biggest diagnostic number found in this report
	 */
	public String getEpsilonRateAsStr() {
		return String.format("%1$" + onesPerPosition.size() + "s", Long.toBinaryString(getEpsilonRate())).replace(' ',
				'0');
	}

	public long getO2GeneratorRating() {

		List<Long> filteredList = new ArrayList<>(diagnosticNumbers);

		for (long mask = (long) Math.pow(2, onesPerPosition.size() - 1); mask != 0
				&& filteredList.size() > 1; mask >>>= 1) {
			long numberOfOnes = countNumberOfOnes(filteredList, mask);
			List<Long> temp = new ArrayList<>();
			for (Long diagnosticNumber : filteredList) {
				if ((2 * numberOfOnes >= filteredList.size() && (mask & diagnosticNumber) != 0)
						|| (2 * numberOfOnes < filteredList.size() && (mask & diagnosticNumber) == 0)) {
					temp.add(diagnosticNumber);
				}
			}
			filteredList = temp;
		}

		return filteredList.get(0);
	}

	private long countNumberOfOnes(List<Long> theList, long theMask) {
		return theList.stream().filter(number -> (number & theMask) != 0).count();
	}

	public String getO2GeneratorRatingAsStr() {
		return String.format("%1$" + onesPerPosition.size() + "s", Long.toBinaryString(getO2GeneratorRating()))
				.replace(' ', '0');
	}

	public long getCO2ScrubberRating() {

		List<Long> filteredList = new ArrayList<>(diagnosticNumbers);

		for (long mask = (long) Math.pow(2, onesPerPosition.size() - 1); mask != 0
				&& filteredList.size() > 1; mask >>>= 1) {
			long numberOfOnes = countNumberOfOnes(filteredList, mask);
			List<Long> temp = new ArrayList<>();
			for (Long diagnosticNumber : filteredList) {
				if ((2 * numberOfOnes < filteredList.size() && (mask & diagnosticNumber) != 0)
						|| (2 * numberOfOnes >= filteredList.size() && (mask & diagnosticNumber) == 0)) {
					temp.add(diagnosticNumber);
				}
			}
			filteredList = temp;
		}

		return filteredList.get(0);
	}

	public String getCO2ScrubberRatingAsStr() {
		return String.format("%1$" + onesPerPosition.size() + "s", Long.toBinaryString(getCO2ScrubberRating()))
				.replace(' ', '0');
	}

	/**
	 * @return the number of diagnostic numbers added to this report
	 */
	public int getSize() {
		return diagnosticNumbers.size();
	}
}
