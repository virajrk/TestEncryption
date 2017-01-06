package java.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viraj on 10/5/2016.
 */

public class Sort {
    public List<Integer> listNum;

    public List<Integer> quickSort(List<Integer> listNum)
    {
        if(listNum.size() < 2)
        {
            return listNum;
        }

        Integer pivot = listNum.get(listNum.size()/2);
        List<Integer> less = new ArrayList<Integer>();
        List<Integer> more = new ArrayList<Integer>();

        for(Integer i : listNum)
        {
            if(i > pivot)
            {
                more.add(i);
            }
            else if (i < pivot)
            {
                less.add(i);
            }
        }

        final List<Integer> sorted = quickSort(less);
        sorted.add(pivot);
        sorted.addAll(quickSort(more));

        return sorted;
    }


    public void bubbleSort()
    {
        int total = listNum.size();
        for(int j=0; j < total -1 ; j++) {
            boolean swapped = false;
            for (int i = j; i < total - 1; i++) {
                if (listNum.get(i) > listNum.get(i + 1)) {
                    int numAtI = listNum.get(i);
                    listNum.set(i, listNum.get(i + 1));
                    listNum.set(i + 1, numAtI);
                    swapped = true;
                }
            }
            if(!swapped)
            {
                return;
            }
        }
    }

}
