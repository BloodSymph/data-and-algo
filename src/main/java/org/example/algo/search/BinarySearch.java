package org.example.algo.search;

public class BinarySearch {
    private int[] array;
    private int value;

    public BinarySearch() {
        this.value = -1;
        this.array = null;
    }

    //Алгоритм бинарного поиска, передаем на вход массив и искомое значение
    public int binarySearch(int[] array, int value) {
        //Создаём локальную переменную где будет лежать позиция среднего элемента
        int middle;
        //Создаём локальную переменную вывода
        int guess;
        //Найбольший элемент
        int height = array.length - 1;
        //Найменший элемент
        int low = 0;
        //Запускаем цикл при условии если найменьший индекс, меньше или равно найбольшего
        while (low <= height) {
            //Выщитываем с округлением позицию среднего элемента
            middle = Math.round((low + height) / 2);
            //В переменную вывода присваиваем значение индекса центрального элемента
            guess = array[middle];
            //Если переменная вывода равна искомому значению то возвращаем центральный элемент
            if (guess == value) return middle;
            //Если переменная вывода больше искомого значению то сдвигаемся от центрального элемента влево на 1
            if (guess > value) height = middle - 1;
            //В противном случае найменьший элемент сдвигаем в правую сторону на 1
            else low = middle + 1;
        }
        //Возвращаем -1 если ничего не найдено
        return -1;
    }
}
