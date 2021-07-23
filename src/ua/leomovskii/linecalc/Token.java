package ua.leomovskii.linecalc;

import java.util.ArrayList;
import java.util.Arrays;

public class Token {

	TokenType type;
	String data;

	public Token(TokenType type, String data) {
		this.type = type;
		this.data = data;
	}

	public Token(TokenType type, Character opCode) {
		this.type = type;
		this.data = opCode.toString();
	}

	public static ArrayList<Token> asTokenList(String expression) {
		ArrayList<Token> list = new ArrayList<Token>();
		int index = 0;

		while (index < expression.length()) {
			char c = expression.charAt(index);
			switch (c) {
			case '(':
				list.add(new Token(TokenType.BRACKET_LEFT, c));
				index++;
				continue;
			case ')':
				list.add(new Token(TokenType.BRACKET_RIGHT, c));
				index++;
				continue;
			case '+':
				list.add(new Token(TokenType.OP_PLUS, c));
				index++;
				continue;
			case '-':
				list.add(new Token(TokenType.OP_MINUS, c));
				index++;
				continue;
			case '*':
				list.add(new Token(TokenType.OP_MUL, c));
				index++;
				continue;
			case '/':
				list.add(new Token(TokenType.OP_DIV, c));
				index++;
				continue;
			case '%':
				list.add(new Token(TokenType.OP_REM, c));
				index++;
				continue;
			default:
				if (charIsNumPart(c)) {
					StringBuilder sb = new StringBuilder();
					boolean isDouble = false;

					do {
						if (c == '.' || c == ',') {
							if (!isDouble) {
								isDouble = true;
								sb.append('.');
							}

						} else
							sb.append(c);

						index++;

						if (index >= expression.length())
							break;
						c = expression.charAt(index);
					} while (charIsNumPart(c));

					list.add(new Token(TokenType.NUMBER, sb.toString()));

				} else {
					if (c != ' ')
						throw new RuntimeException("Unexpected character \'" + c + "\' at index " + index);
					index++;
				}
			}
		}
		list.add(new Token(TokenType.END, ""));

		return list;
	}

	private static boolean charIsNumPart(char c) {
		return Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ',').contains(c);
	}

}