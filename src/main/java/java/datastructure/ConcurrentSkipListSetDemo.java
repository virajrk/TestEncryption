package java.datastructure;

import com.google.gson.Gson;

import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by Viraj on 10/26/2016.
 */
public class ConcurrentSkipListSetDemo {

    static ConcurrentSkipListSet concurrentSkipListSet;
    static Gson gson = new Gson();

    public static void main(String args[])
    {
        concurrentSkipListSet = new ConcurrentSkipListSet<String>();

        for(int i=0; i<100; i++)
            concurrentSkipListSet.add(UUID.randomUUID().toString());

        for(Object str : concurrentSkipListSet.descendingSet())
        {
            System.out.println(str);
        }
        System.out.println("================================================");

        System.out.println(gson.toJson(concurrentSkipListSet));
    }
}
