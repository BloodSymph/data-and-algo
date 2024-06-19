package org.example.algo.sort;

public final class SelectionSort {

    public SelectionSort() {
    }

    //Алгоритм сортировки выбором: на вход передаём не отсортированный массив
    public int[] selectionSort(int[] array) {
        //В первом цикле обходи весь массив идекс i
        for (int i = 0; i < array.length; i++) {
            //Создаём переменную с минимальным значением по идексу i
            int minIndex = i;
            //Во втором цикле обходи весь массив идекс счётчика j = i + 1
            for (int j = i + 1; j < array.length; j++) {
                //Если элемент по индексу i меньше элемента по minIndex
                if (array[i] < array[minIndex]) {
                    //То минимальному индексу присваиваем индекс j
                    minIndex = j;
                }
            }
            //Создаём временную переменную в которую заносим элемент по индексу i
            int temp = array[i];
            //Элементу по индексу i присваиваем элемент по индексу minIndex
            array[i] = array[minIndex];
            //Перезаписываем значение array[minIndex] на временную переменную
            array[minIndex] = temp;
        }
        //Возвращаем отсортированный массив
        return array;
    }
}
