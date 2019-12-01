package com.baizhi.lambda;

//作用域
public class Test2 {
    final static String salutation = "Hello "; //正确，不可再次赋值
    //static String salutation = "Hello "; //正确，可再次赋值
    //String salutation = "Hello "; //报错
    //final String salutation = "Hello "; //报错

    public static void main(String args[]) {
        //final String salutation = "Hello "; //正确，不可再次赋值
        //String salutation = "Hello "; //正确，隐性为 final , 不可再次赋值

        // salution = "welcome to "
        GreetingService greetService1 = message ->
                System.out.println(salutation + message);
        greetService1.sayMessage("Shiyanlou");
    }

    interface GreetingService {
        void sayMessage(String message);
    }
}
