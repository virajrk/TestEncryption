package java.datastructure;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class QueueDemo {

    static Queue<String> stringQueue;
    public static void main(String args[])
    {
        stringQueue = new ConcurrentLinkedDeque<String>();

        stringQueue.add("first");
        stringQueue.add("second");
        stringQueue.add("third");

        String stringPeek = stringQueue.peek();
        String stringPollOutput = stringQueue.poll();

        System.out.println("Peek : " + stringPeek + "\nPollFirst : " + stringPollOutput);
    }
}
