package com.手撕算法.牛客网;
import java.util.Scanner;

/**
 * 描述
 * 分子为1的分数称为埃及分数。现输入一个真分数(分子比分母小的分数，叫做真分数)，请将该分数分解为埃及分数。如：8/11 = 1/2+1/5+1/55+1/110。
 * 注：真分数指分子小于分母的分数，分子和分母有可能gcd不为1！
 * 如有多个解，请输出任意一个。
 */
public class 将真分数分解为埃及分数 {

    /**
     数学家斐波那契提出的一种求解***分数的贪心算法，准确的算法表述应该是这样的：
     设某个真分数的分子为a，分母为b;
     把c=(b/a+1)作为分解式中第一个***分数的分母；
     将a-b%a作为新的a；
     将b*c作为新的b；
     如果a等于1，则最后一个***分数为1/b,算法结束；
     如果a大于1但是a能整除b，则最后一个***分数为1/(b/a),算法结束；
     否则重复上面的步骤。
     **/
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String res = "";
            String[] arr = in.nextLine().split("/");
            String pre = "";
            int a = Integer.valueOf(arr[0]);
            int b = Integer.valueOf(arr[1]);
            while (true){
                int c = b/a+1;
                res += "1/";
                res += c;
                a = a - b % a;
                b = b * c;
                res += "+";
                if (a == 1){
                    res += "1/";
                    res += b;
                    break;
                }else if (a > 1 && b % a == 0){
                    res += "1/";
                    res += b/a;
                    break;
                }
            }
            System.out.println(res);
        }
    }
}
