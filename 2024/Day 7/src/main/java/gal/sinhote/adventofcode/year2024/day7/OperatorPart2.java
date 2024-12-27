package gal.sinhote.adventofcode.year2024.day7;

public enum OperatorPart2 implements Operator {
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
	},

	CONCAT {
		@Override
		public long calculate(final long operand1, final int operand2) {
			return Long.parseLong(Long.toString(operand1) + Integer.toString(operand2));
		}
	};

	@Override
	public abstract long calculate(final long operand1, final int operand2);
}
