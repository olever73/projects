package de.telran;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
/*
2. Необходимо спроектировать структуру данных, которая имеет следующие операции.
add(int number)
getMedian()
Обе операции должны быть по возможности эффективные, причем вторая более важна.
Медианный элемент списка элементов, это элемент, лежащий посередине, если список отсортировать. Или это арифметичское среднее двух срединных элементов, если их четное количество.
{5, 8, 2, 4, 3, 128} -> {2, 3, 4, 5, 8, 128} -> 4.5
(Использвать кучи: PriorityQueue)

 */

class MedianOfIntegerStream {

    private Queue<Integer> minHeap;
    private Queue<Integer> maxHeap;

    MedianOfIntegerStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    void add(int num) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }

    double getMedian() {
        int median;
        if (minHeap.size() > maxHeap.size()) {
            median = minHeap.peek();
        } else {
            median = (minHeap.peek() + maxHeap.peek()) / 2;
        }
        return median;
    }
}