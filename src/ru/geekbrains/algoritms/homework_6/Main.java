package ru.geekbrains.algoritms.homework_6;

public class Main {
    public static void main(String[] args) {
        MyTreeMap<Integer, String> map = new MyTreeMap<>();

        map.put(5, "five");
        map.put(4, "four");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

//        System.out.println(map.get(4));
//        map.put(4, "four four");

//        System.out.println(map.get(4));
//        System.out.println(map.size());

        System.out.println(map);

//        map.remove(1);
//        System.out.println(map);
    }
}