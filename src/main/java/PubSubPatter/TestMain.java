package PubSubPatter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by czn on 2016/2/28.
 */
public class TestMain {
    public static void main(String[] args) {
        BlockingQueue<Integer> dataQueue = new LinkedBlockingDeque<Integer>();

        Thread pt = new Thread(new Spider(dataQueue));
        pt.start();

        Thread ct = new Thread(new Indexer(dataQueue));
        ct.start();
    }
}
