package PubSubPatter;

import java.util.concurrent.BlockingQueue;

/**
 * Created by czn on 2016/2/28.
 * 索引类
 */
public class Indexer implements Runnable {

    private BlockingQueue<Integer> dataQueue;

    public Indexer(BlockingQueue<Integer> dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        Integer i;
        while (!Thread.interrupted()) {
            try {
                i = dataQueue.take();
                System.out.println("索引: " + i);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
