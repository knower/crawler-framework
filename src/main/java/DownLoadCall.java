import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by czn on 2016/2/28.
 */
public class DownLoadCall implements Callable<String> {

    private URL url;

    public DownLoadCall(URL u) {
        this.url = u;
    }

    @Override
    public String call() throws Exception {
        System.out.println("等待url " + url);
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        String content = "content url finish " + System.currentTimeMillis();
        return content;
    }
}
