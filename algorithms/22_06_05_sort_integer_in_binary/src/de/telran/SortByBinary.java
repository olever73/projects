package de.telran;
import java.util.*;
/*
1.	Имеется список положительных Integer (>0). Необходимо отсортировать их таким образом, что сначала идут числа с наименьшем количеством единиц в двоичной записи числа. Если по этому параметру числа совпадают, то сначала идут меньшие числа. Пример {5, 8, 2, 4, 3, 128} -> {101, 1000, 10, 100, 11, 10000000} -> {10, 100, 1000, 10000000, 11, 101} ->{2, 4, 8, 128, 3, 5}
128. Каунтер = 0. Если 128 не делится на 2, то мы добавляем к каунтеру 1. После чего делим 128 на 2 и повторяем таким образом, пока число > 0.
Красивый компаратор

 */

public class SortByBinary {
    public static Integer[] sort(Integer list[]) {
        Arrays.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                int c;
                c = (Integer.toBinaryString(x).replace("0", "")).length() - (Integer.toBinaryString(y).replace("0", "")).length();
                if (c == 0) c = (Integer.toBinaryString(x)).length() - (Integer.toBinaryString(y)).length();
                if (c == 0) c = x.compareTo(y);
                return c;
            }
        });
        return (list);
    }


    public static void main(String[] args) {

    }
}

