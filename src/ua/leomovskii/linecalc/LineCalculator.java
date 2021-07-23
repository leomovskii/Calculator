package ua.leomovskii.linecalc;

import java.util.ArrayList;

public class LineCalculator {

	public static String run(String expression) {
		try {
			ArrayList<Token> tokens = Token.asTokenList(expression);
			Buffer tokensBuffer = new Buffer(tokens);
			double d = expr(tokensBuffer);

			String out = String.valueOf(d);

			if (out.endsWith(".0"))
				return out.substring(0, out.length() - 2);
			return out;

		} catch (RuntimeException e) {
			return "Error! Invalid expression.";
		}
	}

	private static double expr(Buffer buff) {
		Token token = buff.next();

		if (token.type == TokenType.END)
			return 0d;
		else {
			buff.back();
			return plusminus(buff);
		}
	}

	private static double plusminus(Buffer buff) {
		double value = multdiv(buff);
		while (true) {
			Token token = buff.next();
			switch (token.type) {
			case OP_PLUS:
				value += multdiv(buff);
				break;
			case OP_MINUS:
				value -= multdiv(buff);
				break;
			default:
				buff.back();
				return value;
			}
		}
	}

	private static double multdiv(Buffer buff) {
		double value = factor(buff);
		while (true) {
			Token token = buff.next();
			switch (token.type) {
			case OP_MUL:
				value *= factor(buff);
				break;
			case OP_DIV:
				value /= factor(buff);
				break;
			case OP_REM:
				value %= factor(buff);
				break;
			default:
				buff.back();
				return value;
			}
		}
	}

	private static double factor(Buffer buff) {
		Token token = buff.next();

		switch (token.type) {
		case NUMBER:
			return Double.parseDouble(token.data);
		case BRACKET_LEFT:
			double value = expr(buff);
			token = buff.next();
			if (token.type != TokenType.BRACKET_RIGHT)
				throw new RuntimeException("Unexpected token: " + token.data + " at position: " + buff.getIndex());
			return value;
		default:
			throw new RuntimeException("Unexpected token: " + token.data + " at position: " + buff.getIndex());
		}
	}
}