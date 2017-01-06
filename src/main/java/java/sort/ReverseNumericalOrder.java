package java.sort;

import java.util.Comparator;

/**
 * Created by Viraj on 10/4/2016.
 */
public class ReverseNumericalOrder implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}
