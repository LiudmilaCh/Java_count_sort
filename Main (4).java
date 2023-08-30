/******************************************************************************

Counting sort algorithm
Liudmila Chizhikova (c)
автор Чижикова Людмила Андреевна
Реализован алгоритм подсчета на языке программирования Java
Запись результата сортировки в текстовый файл

*******************************************************************************/
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


//======================== главный, main класс программы ===========================
public class Main {
    
//глобальные переменные для размера массива и самого массива
public static int n= 100;
public static int[] arr = new int[n];

//========================= метод для сортировки массива =============================
    public static void countingSort(int[] arr, int max) {
        int len = arr.length;
        int[] count = new int[max + 1]; // Массив для хранения счета каждого элемента 

        //Хранение подсчета каждого элемента входного массива 
        for (int i = 0; i < len; i++) {
            count[arr[i]]++;
        }

        // Изменяем массив подсчета , чтоб он содержал актуальный номер позиции каждого элемента в отсортированном массиве впоследствии
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[len]; //Выходной массив для хранения результата сортировки

        // Создаем массив для записи 
        for (int i = len - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        //Копируем отсортированный массив в исходный
        for (int i = 0; i < len; i++) {
            arr[i] = output[i];
        }
    }
    
    //метод сохранения данных в текстовый файл
        public static void savefile(){
        try {
            FileWriter writer = new FileWriter("SortedResult.txt");

            for (int i = 0; i < arr.length; i++) {
                writer.write(String.valueOf(arr[i]));
                writer.write(System.lineSeparator());
            }

            writer.close();
            System.out.println("Массив успешно сохранен в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении массива в файл.");
            e.printStackTrace();
        }
    
    }
//===========================Main метод =======================================
    public static void main(String[] args) {
   
        System.out.print("Введите размер массива для сортировки (менее 100)");
        System.out.print("\n");
      
            Scanner scannum = new Scanner(System.in);
            n = scannum.nextInt();
            String numberofn = "";
            switch(n){
            case 1: case 21: case 31: case 41: case 51: case 61: case 71: case 81: case 91: {
               numberofn = "элемент";
               break; 
            }
            case 2: case 3: case 4: case 22: case 23: case 24:  case 32: case 33: case 34: case 42: case 43: case 44: case 52: case 53: case 54: {
               numberofn = "элемента"; 
                break;
            }
            case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: {
                numberofn = "элементов";
                break;
            }
            }
                
        System.out.print("Размер массива "+ n + numberofn);    
        System.out.print("\n");
        System.out.print ("Введите последовательно целые числа массива, которые необходимо отсортировать: ");
        System.out.print("\n");
        Scanner scan = new Scanner(System.in);
          for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        
        scan.close();
        
        int max = Arrays.stream(arr).max().getAsInt(); // Ищем максимальный элемент массива
        countingSort(arr, max);

        System.out.println("Отсортированный массив элементов: " + Arrays.toString(arr));
        
                 savefile(); //вызов метода для сохранения отсортированного массива

    }
    
}