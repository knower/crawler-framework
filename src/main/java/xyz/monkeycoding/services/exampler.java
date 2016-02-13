package xyz.monkeycoding.services;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by mx on 16/2/13.
 */
public class Exampler {

    private static final Logger logger = LoggerFactory.getLogger(Exampler.class);

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            CookieStore cookieStore = new BasicCookieStore();

            HttpClientContext localContext = HttpClientContext.create();
            localContext.setCookieStore(cookieStore);

            HttpGet httpGet = new HttpGet("http://ifeve.com/java-concurrency-constructs/");
            logger.info("Executing request " + httpGet.getRequestLine());

            CloseableHttpResponse response = httpClient.execute(httpGet, localContext);
            try {
                logger.info("-----------------Cookie-----------------------");
                logger.info(response.getStatusLine().toString());

                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    logger.info("Cookie None");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        logger.info("- " + cookies.get(i));
                    }
                }

                logger.info("-----------------Document-----------------------");
                Document doc = Jsoup.parse(EntityUtils.toString(response.getEntity()));

                Elements elements = doc.select("#main_content div .post");
                Element ele = elements.select(".title span").get(0);

                System.out.println(ele.text());

                Element eleCont = elements.select(".post_content").get(0);
                System.out.println(eleCont.html());

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }
}
