package ua.leomovskii.linecalc;

public enum TokenType {
	BRACKET_LEFT, // '('
	BRACKET_RIGHT, // ')'
	OP_PLUS, // '+'
	OP_MINUS, // '-'
	OP_MUL, // '*'
	OP_DIV, // '/'
	OP_REM, // '%'
	NUMBER, // double
	END
}