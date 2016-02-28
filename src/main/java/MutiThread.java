import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by czn on 2016/2/28.
 */
public class MutiThread {
    public static void main(String[] args) throws Exception {
        int threads = 4;
        ExecutorService es = Executors.newFixedThreadPool(threads);

        Set<Future<String>> set = new HashSet<Future<String>>();

        URL bd = new URL("http://www.baidu.com");
        URL qq = new URL("http://www.qq.com");
        URL jd = new URL("http://www.jd.com");
        URL mg = new URL("http://www.mogujie.com");

        List<URL> urls = new ArrayList<URL>();
        urls.add(bd);
        urls.add(qq);
        urls.add(jd);
        urls.add(mg);

        for (final URL url : urls) {
            DownLoadCall task = new DownLoadCall(url);
            Future<String> future = es.submit(task);
            set.add(future);
        }

        for (Future<String> future : set) {
            String content = future.get();
            System.out.println(content);
        }

        es.shutdown();
    }
}
