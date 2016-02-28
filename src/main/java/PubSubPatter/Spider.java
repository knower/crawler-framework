package PubSubPatter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by czn on 2016/2/28.
 */
public class Spider implements Runnable {

    private BlockingQueue<Integer> dataQueue;
    private static int i = 0;

    public Spider(BlockingQueue<Integer> dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                dataQueue.add(new Integer(++i));
                System.out.println("生产：" + i);
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
