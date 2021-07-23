package ua.leomovskii.linecalc;

import java.util.ArrayList;

public class Buffer {

	public ArrayList<Token> tokens;
	private int index;

	public Buffer(ArrayList<Token> tokens) {
		this.tokens = tokens;
	}

	public Token next() {
		return tokens.get(index++);
	}

	public void back() {
		index--;
	}

	public int getIndex() {
		return index;
	}
}