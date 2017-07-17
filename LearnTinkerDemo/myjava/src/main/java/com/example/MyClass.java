package com.example;

public class MyClass {

    public static void main(String[] args) {

        System.out.println("===============");
        try {
            Class cLazz = Class.forName("com.example.MyClass");
            System.out.println("类名：" + cLazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
