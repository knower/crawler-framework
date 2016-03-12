package rss;

import de.nava.informa.core.ParseException;
import de.nava.informa.impl.basic.Channel;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.impl.basic.Item;
import de.nava.informa.parsers.FeedParser;

import java.io.IOException;

/**
 * Created by czn on 2016/3/12.
 */
public class RssDemo {
    public static void main(String[] args) throws IOException, ParseException {
        ChannelBuilder builder = new ChannelBuilder();
        String url = "http://news.yahoo.com/rss/topstories";
        Channel channel = (Channel) FeedParser.parse(builder, url);
        System.out.println("Title: " + channel.getTitle());
        System.out.println("Description: " + channel.getDescription());
        System.out.println("Content: ");
        for (Object x : channel.getItems()) {
            Item anItem = (Item) x;
            System.out.println("title: " + anItem.getTitle() + "\n Descritpion: ");
            System.out.println(anItem.getDescription());
            System.out.println("===================================================\n");
        }
    }
}
