package gal.sinhote.adventofcode.year2024.day5;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RuleBasedPageComparator implements Comparator<Integer> {

	final Map<Integer, Set<Integer>> rules = new HashMap<>();

	public void addOrderingRule(final int before, final int after) {
		if (rules.containsKey(after) && rules.get(after).contains(before)) {
			throw new IllegalArgumentException("Tried to add the rule for page " + before +
					" to go before page " + after + ", but the opposite was found");
		}
		rules.computeIfAbsent(before, k -> new HashSet<>()).add(after);
	}

	@Override
	public int compare(Integer int1, Integer int2) {
		Objects.requireNonNull(int1);
		Objects.requireNonNull(int2);

		if (rules.containsKey(int1) && rules.get(int1).contains(int2)) {
			return -1;
		} else if (rules.containsKey(int2) && rules.get(int2).contains(int1)) {
			return 1;
		}
		return 0;
	}

	public boolean isSorted(final Collection<Integer> collection) {
		boolean isSorted = true;
		if (!collection.isEmpty()) {
			final Iterator<Integer> iterator = collection.iterator();
			int previous = iterator.next();
			while(iterator.hasNext()) {
				int current = iterator.next();
				if (compare(previous, current) > 0) {
					isSorted = false;
					break;
				}
				previous = current;
			}
		}
		return isSorted;
	}
}
