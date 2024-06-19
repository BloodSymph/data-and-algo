package org.example.algo.search;

public final class LinearSearch {

    private int[] array;

    private int value;

    public LinearSearch() {
        this.array = null;
        this.value = -1;
    }

    //Алгоритм линейного поиска, передаем на вход массив и искомое значение
    public int linearSearch(int[] array, int value) {
        //Циклом проходимся по всему массиву
        for (int i = 0; i < array.length; i++) {
            //Если переданное значение найдено
            if (value == array[i]) {
                //Возвращаем индекс искомого элемента массива
                return i;
            }
        }
        //Если ничего не найдено возвращаем -1
        return -1;
    }
}
