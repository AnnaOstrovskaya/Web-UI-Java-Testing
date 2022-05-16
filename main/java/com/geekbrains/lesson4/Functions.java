package com.geekbrains.lesson4;

public class Functions {

    public static boolean isPrime(Integer number) {
        //проверяем является ли число простым (простые 1, 2, 3, 4, 5) простыми числами являются те которые делятся без
        // остатка на 1 и себя.
        if (number <= 0) return false;
        if (number <= 3) return true;

        for (int i = 2; i < number; i++) {
            if (number % i == 0) return false;    // % провряет есть ли остаток при делении на number
        }
        return true;
    }

    //напишем метод который проверяет является ли число палиндромом(число которое читается одинаково в обе стороны)
    public static boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }

        if (word.charAt(0) != word.charAt(word.length() - 1)) {
            return false;
        }

        return isPalindrome(word.substring(1, word.length() - 1));


    }

}


