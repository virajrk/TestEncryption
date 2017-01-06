package java.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Viraj on 10/4/2016.
 */

public class Test1 {

    private static Sort sort;

    public static void main(String args[]) {
        System.out.println("Test");

        final List<Integer> expected = Arrays.asList(1, 2, 5, 9, 10, 56, 98);
        final List<Integer> numbers = Arrays.asList(2, 5, 56, 10, 98, 9 , 1);

        final int[] intNums = {2, 5, 56, 10, 98, 9 , 1};

        //doBubbleSort(numbers);

        doQuickSort(numbers);

    }

    public static void doQuickSort(List<Integer> numbers) {
        sort = new Sort();

        List<Integer> sorted = sort.quickSort(numbers);

        System.out.print("Bubble Sort -");
        for(Integer i : sorted)
        {
            System.out.print(" " + i);
        }
        System.out.println(".");

    }

    public static void doBubbleSort(List<Integer> numbers)
    {
        sort = new Sort();
        sort.listNum = numbers;
        sort.bubbleSort();

        System.out.print("Bubble Sort -");
        for(Integer i : numbers)
        {
            System.out.print(" " + i);
        }
        System.out.println(".");
    }

    public void customSort(List<Integer> numbers)
    {
        System.out.println("\nWith Custome Ordering -");

        Collections.sort(numbers, new ReverseNumericalOrder());
        for(int i : numbers)
        {
            System.out.print(i + " ");
        }
    }
}
