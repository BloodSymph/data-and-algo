package org.example.algo.sort;

public final class BubbleSort {

    public BubbleSort() {
    }

    //Алгоритм пузырьковой сортировки: на вход передаём не отсортированный массив
    public int[] bubbleSort(int[] array) {
        //В первом цикле обходи весь массив идекс i
        for (int i = 0; i < array.length; i++) {
            //Во втором цикле обходи весь массив идекс j
            for (int j = 0; j < array.length; j++) {
                //Если элемент по индексу j+1 меньше элемента по индексу J
                if (array[j + 1] < array[j]) {
                    //Создаём временную переменную в которую заносим элемент по индексу j
                    int temp = array[j];
                    //Элементу по индексу j присваиваем элемент по индексу j+1
                    array[j] = array[j + 1];
                    //Перезаписываем значение array[j + 1] на временную переменную
                    array[j + 1] = temp;
                }
            }
        }
        //Возвращаем отсортированный массив
        return array;
    }
}
