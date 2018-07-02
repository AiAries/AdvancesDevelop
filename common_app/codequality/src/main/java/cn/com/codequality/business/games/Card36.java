package cn.com.codequality.business.games;

import java.util.Scanner;

public class Card36 {
	/**不带括号运算
	 * 等于36的结构的count 总数是==5643
	 * 等于24的结构的count 总数是==10048
	 * 包含括号运算的
	 * 等于24的结构的count 总数是==26585
	 */
	public static  int rightResult = 36;
	public static  int count = 0;
	public static  boolean isContainBracket = true;
	public static  String express ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// get four card
		float[] cards = new float[4];
//		for (int i = 1; i < 14; i++) {
//			cards[0] = i;
//			for (int j = 1; j < 14; j++) {
//				cards[1] = j;
//				for (int k = 1; k < 14; k++) {
//					cards[2] = k;
//					for (int m = 1; m < 14; m++) {
//						cards[3] = m;
//						getCard36FromAll(cards,isContainBracket);
//					}
//				}
//			}
//		}

		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < cards.length; i++) {
			System.out.printf("请输入第%d数", i + 1);
			cards[i] = scan.nextInt();
			System.out.println();
		}
		getCard36ByInputNum(cards,isContainBracket);
		System.out.printf("等于%d的结构的count 总数是=="+count,rightResult);
	}
	/**
	 * (8-(5*4))*3	213
	 * 8-((5*4)*3)	231
	 * ((8-5)*4)*3	123
	 * 		(8-5)*(4*3)  132
	 *
	 * 		(8-9/3)* 6
	 * 8-(9/3* 6)	231
	 * (8-9)/3* 6	123
	 * 8-(9/3)* 6	231
	 * 8-9/(3* 6) 321
	 * sign
	 * // + --> 0
	 // - --> 1
	 // * --> 2
	 // / --> 3
	 *
	 * @return
	 */
	public static boolean calc(float[] tempGroup, int[] signs) {
		String[] expressions = new String[9];
		expressions[0] = "("+tempGroup[0]+getSign(signs[0])
				+tempGroup[1]+")"+getSign(signs[1])
				+tempGroup[2]+getSign(signs[2])
				+tempGroup[3];
		expressions[1] = tempGroup[0]+getSign(signs[0])
				+"("+tempGroup[1]+getSign(signs[1])
				+tempGroup[2]+")"+getSign(signs[2])
				+tempGroup[3];
		expressions[2] = tempGroup[0]+getSign(signs[0])
				+tempGroup[1]+getSign(signs[1])
				+"("+tempGroup[2]+getSign(signs[2])
				+tempGroup[3]+")";
		expressions[3] = "("+tempGroup[0]+getSign(signs[0])
				+tempGroup[1]+getSign(signs[1])
				+tempGroup[2]+")"+getSign(signs[2])
				+tempGroup[3];
		expressions[4] = tempGroup[0]+getSign(signs[0])
				+"("+tempGroup[1]+getSign(signs[1])
				+tempGroup[2]+getSign(signs[2])
				+tempGroup[3]+")";
		expressions[5] = "("+tempGroup[0]+getSign(signs[0])
				+tempGroup[1]+")"+getSign(signs[1])
				+"("+tempGroup[2]+getSign(signs[2])
				+tempGroup[3]+")";
		expressions[6] = "(("+tempGroup[0]+getSign(signs[0])
				+tempGroup[1]+")"+getSign(signs[1])
				+tempGroup[2]+")"+getSign(signs[2])
				+tempGroup[3];
		expressions[7] = tempGroup[0]+getSign(signs[0])
				+"(("+tempGroup[1]+getSign(signs[1])
				+tempGroup[2]+")"+getSign(signs[2])
				+tempGroup[3]+")";
		expressions[8] = "("+tempGroup[0]+getSign(signs[0])
				+"("+tempGroup[1]+getSign(signs[1])
				+tempGroup[2]+"))"+getSign(signs[2])
				+tempGroup[3];
		for (int i = 0; i < expressions.length; i++) {
			String expression = expressions[i];
			double result = Calculator.conversion(expression);
			if (result==rightResult) {
				System.out.println(expression + " = " + result);
				System.out.println();
				count++;
				express = expression;
				return true;
			}
		}
		return false;
	}
	public static boolean calc2(float[] tempGroup, int[] signs) {
		String expression = ""+tempGroup[0]+getSign(signs[0])
				+tempGroup[1]+getSign(signs[1])
				+tempGroup[2]+getSign(signs[2])
				+tempGroup[3];
		double result = Calculator.conversion(expression);
		if (result==rightResult) {
			System.out.println(expression + " = " + result);
			System.out.println();
			count++;
			express = expression;
			return true;
		}
		return false;

	}
	public static String getSign(int sign) {
		String signStr = "";
		switch (sign) {
			case 0:
				signStr = "+";
				break;
			case 1:
				signStr = "-";
				break;
			case 2:
				signStr = "*";
				break;
			case 3:
				signStr = "/";
				break;
			default:
				break;
		}
		return signStr;
	}

	public static boolean getCard36ByInputNum(float[] cards,boolean isContainBracket){
		// 36 + - * /
		// int parseInt = Integer.parseInt("2*3");
		// System.out.println(parseInt);
		// 四个数的排列组合的所有可能性
		boolean isHasResult = false;
		float[] tempGroup = new float[4];
		out:for (int i = 0; i < cards.length; i++) {
			tempGroup[0] = cards[i];
			for (int j = 0; j < cards.length; j++) {
				if (i == j) {
					continue;
				}
				tempGroup[1] = cards[j];
				for (int k = 0; k < cards.length; k++) {
					if (i == k || j == k) {
						continue;
					}
					tempGroup[2] = cards[k];
					for (int k2 = 0; k2 < cards.length; k2++) {
						if (i == k2 || j == k2 || k == k2) {
							continue;
						}
						tempGroup[3] = cards[k2];

						//算数运算符号的组合运算
						int[] signs = new int[3];
						for (int i3 = 0; i3 < 4; i3++) {
							signs[0] = i3;
							for (int j3 = 0; j3 < 4; j3++) {
								signs[1] = j3;
								for (int k3 = 0; k3 < 4; k3++) {
									signs[2] = k3;
									if (isContainBracket) {
										isHasResult = calc(tempGroup,signs);
									}else {
										isHasResult =calc2(tempGroup,signs);
									}
									if (isHasResult) {
										break out;
									}
								}
							}
						}
					}
				}
			}
		}
		return isHasResult;
	}
	public static void getCard36FromAll(float[] cards,boolean isContainBracket){
		//算数运算符号的组合运算
		int[] signs = new int[3];
		for (int i3 = 0; i3 < 4; i3++) {
			signs[0] = i3;
			for (int j3 = 0; j3 < 4; j3++) {
				signs[1] = j3;
				for (int k3 = 0; k3 < 4; k3++) {
					signs[2] = k3;
					if (isContainBracket) {
						calc(cards,signs);
					}
					else {
						calc2(cards, signs);
					}
				}
			}
		}
	}
}
