package techown.login.business.games;

public class TestCalc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 3, b = 5, c = 6;
		a = 9 * 6 - 9 + 9;
		// String expression = "(0*1--3)-5/-4-(3*(-2.13))";
		String expression = "(6-3)-9*9";
		double result = Calculator.conversion(expression);
		System.out.println(expression + " = " + result);
		System.out.println();
	}

}
