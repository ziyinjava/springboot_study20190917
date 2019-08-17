package com.ziyin;

/**
 * @author ziyin
 * @create 2019-08-03 9:54
 */
public class Demo1 {

	public static void changeValue(String str){
		str += "5";  //底层是SpringBuilder进行拼接 toString后,所以此时的str是新的地址值,只是改变了局部变量的值
	}

	public static void main(String[] args) {
		String str = "1234";  //不可变类
		changeValue(str);
		System.out.println(str);
	}
}
