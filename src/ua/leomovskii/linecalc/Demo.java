package ua.leomovskii.linecalc;

public class Demo {

	public static void main(String[] args) {
		String expr1 = "2 + 2 * 2";
		String expr2 = "(11,3 + 7,2 * 5) - 8 * (7 - 3 * (4 + 5.2))";
		String expr3 = "13 / 3 / 2";

		System.out.println(expr1 + " = " + LineCalculator.run(expr1));
		System.out.println(expr2 + " = " + LineCalculator.run(expr2));
		System.out.println(expr3 + " = " + LineCalculator.run(expr3));
	}

}