package com.ziyin;

/**
 * @author ziyin
 * @create 2019-08-03 9:59
 */
public class Demo2 {
	static Boolean foo(char c) {
		System.out.print(c);
		return true;
	}

	//ABDCBDCB
	public static void main(String[] args) {
		int i = 0;
		for (foo('A');foo('B') && (i < 2); foo('C')){
			i++;
			foo('D');
		}
	}
}
