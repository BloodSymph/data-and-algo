package org.example.algo.sort;

public final class InsertionSort {

    public InsertionSort() {
    }
    //Алгоритм сортировки вставками: на вход передаём не отсортированный массив
    public int[] insertionSort(int[] array) {
        //Создаём цикл из начальным счётчиком 1 и обходим массив
        for (int i = 1; i < array.length; i++) {
            //Создаём отсортированную переменную которой присваиваем i - 1
            int sorted = i - 1;
            //Запускаем цикл при условии что переменная сортед больше -1 и array[sorted] > array[sorted + 1]
            while (sorted > -1 && array[sorted] > array[sorted + 1]) {
                //Создаём временную переменную в которую заносим элемент по индексу сортед
                int temp = array[sorted];
                //Элементу по индексу сортед присваиваем элемент по индексу сортед + 1
                array[sorted] = array[sorted + 1];
                //Перезаписываем значение array[sorted + 1] на временную переменную
                array[sorted + 1] = temp;
                //инкрементируем счётчик сортед
                sorted--;
            }
        }
        //Возвращаем отсортированный массив
        return array;
    }
}
