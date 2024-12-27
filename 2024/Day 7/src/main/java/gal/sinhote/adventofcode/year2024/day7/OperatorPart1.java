package gal.sinhote.adventofcode.year2024.day7;

public enum OperatorPart1 implements Operator {
	PLUS {
		@Override
		public long calculate(final long operand1, final int operand2) {
			return operand1 + operand2;
		}
	},

	TIMES {
		@Override
		public long calculate(final long operand1, final int operand2) {
			return operand1 * operand2;
		}
	};
}
