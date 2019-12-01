package com.baizhi.lambda;

import java.util.Arrays;

//方法引用
public class Test3 {
    //    public static void main(String[] args) {
//        List<String> names = new ArrayList<>();
//
//        names.add("Peter");
//        names.add("Linda");
//        names.add("Smith");
//        names.add("Zack");
//        names.add("Bob");
//
//        //     通过System.out::println引用了输出的方法
//        names.forEach(System.out::println);
//    }
    public static void main(String args[]) {
        int[] arr = {1, 23, 4, 4, 22, 34, 45, 11, 33};
        System.out.println("最小数：" + Arrays.stream(arr).min());
        System.out.println("数组去重乘2求和：" + Arrays.stream(arr).distinct().map((i) -> i * 2).count());
    }

}
