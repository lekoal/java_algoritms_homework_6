package ru.geekbrains.algoritms.homework_6;

public class Main {
    public static void main(String[] args) {

        int countOfTrees = 100000; // Количество генерируемых деревьев
        double trueBalanced = 0; // Счётчик сбалансированных деревьев
        double falseBalanced = 0; // Счётчик несбалансированных деревьев
        for (int i = 0; i < countOfTrees; i++) {
            if (createTree()) {
                trueBalanced++;
            } else {
                falseBalanced++;
            }
        }
        System.out.println("Percent of balanced trees is: " + (trueBalanced / (trueBalanced + falseBalanced) * 100)); // Вывод сообщения в консоль с процентов сбалансированных деревьев


//        System.out.println(map.get(4));
//        map.put(4, "four four");

//        System.out.println(map.get(4));

//        map.put(6, 6);
//        map.put(4, 4);
//        map.put(7, 7);
//        map.put(3, 3);
//        map.put(8, 8);


//        System.out.println(map.size());
//        System.out.println(map.height());
//        System.out.println(map.get(3));


    }

    public static boolean createTree() { // Метод генерирования деревьев с глубиной, равной шести
        MyTreeMap<Integer, Integer> map = new MyTreeMap<>();
        int max = 100;
        int min = -100;
        while (map.height() < 6) {
            map.put((int) (Math.random() * max * 2 + 1) + (min - 1), (int) (Math.random() * max * 2 + 1) + (min - 1));
        }
        return map.isBalanced();
    }

}