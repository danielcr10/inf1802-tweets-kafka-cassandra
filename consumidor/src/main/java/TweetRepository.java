import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import twitter4j.GeoLocation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TweetRepository {
    private static final String TABLE_NAME = "Tweets";
    private Session session;

    public TweetRepository(Session session) {
        this.session = session;
    }

    public void createTable() {
        System.out.println("createTable – init");


        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME).append("(")
                .append("usr text, ")
                .append("ttext text, ")
                .append("date text, ")
                .append("id bigint PRIMARY KEY, ")
                .append("source text, ")
                .append("isTruncated boolean, ")
                .append("latitude double, ")
                .append("longitude double, ")
                .append("isFavorited boolean, ")
                .append("contributors text);");

        final String query = sb.toString();
        System.out.println("createTable – command: " + query.toUpperCase());

        session.execute(query);
        System.out.println("createTable – end");
    }

    //    Create table byUser
    public void createTableByUser() {
        System.out.println("createTable – init");


        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(TABLE_NAME+"ByUser").append("(")
                .append("usr text, ")
                .append("ttext text, ")
                .append("date text, ")
                .append("id bigint, ")
                .append("source text, ")
                .append("isTruncated boolean, ")
                .append("latitude double, ")
                .append("longitude double, ")
                .append("isFavorited boolean, ")
                .append("contributors text, ")
                .append("PRIMARY KEY ((id, usr))")
                .append(");");

        final String query = sb.toString();
        System.out.println("createTable – command: " + query.toUpperCase());

        session.execute(query);
        System.out.println("createTable – end");
    }


    public void insertTweet(Tweet tweet) {
        System.out.println("insertTweet – init");

        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME).append("(usr, ttext, date, id, source, isTruncated,latitude, longitude, isFavorited, contributors) ")
                .append("VALUES ('").append(tweet.getUsername()).append("', '")
                .append(tweet.getTweetText()).append("', '")
                .append(LocalDate.fromMillisSinceEpoch(tweet.getDateSent().getTime())).append("', ")
                .append(tweet.getId()).append(", '")
                .append(tweet.getSource()).append("', ")
                .append(tweet.isTruncated()).append(", ")
                .append(tweet.getGeolocation().getLatitude()).append(", ")
                .append(tweet.getGeolocation().getLongitude()).append(", ")
                .append(tweet.isFavorited()).append(", '")
                .append(tweet.getContributors()).append("');");
        final String query = sb.toString();
        System.out.println("insetTweet – command: " + query.toUpperCase());
        session.execute(query);

        System.out.println("insertTweet – end");

    }

    public void insertTweetByUser(Tweet tweet){
        System.out.println("insertTweetByUser – init");

        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append(TABLE_NAME+"ByUser").append("(usr, ttext, date, id, source, isTruncated, latitude, longitude, isFavorited, contributors) ")
                .append("VALUES ('").append(tweet.getUsername()).append("', '")
                .append(tweet.getTweetText()).append("', '")
                .append(LocalDate.fromMillisSinceEpoch(tweet.getDateSent().getTime())).append("', ")
                .append(tweet.getId()).append(", '")
                .append(tweet.getSource()).append("', ")
                .append(tweet.isTruncated()).append(", ")
                .append(tweet.getGeolocation().getLatitude()).append(", ")
                .append(tweet.getGeolocation().getLongitude()).append(", ")
                .append(tweet.isFavorited()).append(", '")
                .append(tweet.getContributors()).append("');");
        final String query = sb.toString();
        System.out.println("insetTweet – command: " + query.toUpperCase());
        session.execute(query);

        System.out.println("insertTweetByUser – end");

    }

    public List<Tweet> selectAll() {
        System.out.println("selectAll – init");

        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
        System.out.println("SELECT * FROM "+TABLE_NAME.toUpperCase());

        final String query = sb.toString();
        System.out.println("selectAll – command: " + query.toUpperCase());
        ResultSet rs = session.execute(query);
        List<Tweet> tweets = new ArrayList<Tweet>();
        for (Row r : rs) {
            GeoLocation geo = new GeoLocation(r.getDouble("latitude"), r.getDouble("longitude"));
            Tweet tt = new Tweet(r.getString("usr"),
                    r.getString("ttext"),
                    convertToDate(r.getDate("date")),
                    r.getLong("id"),
                    r.getString("source"),
                    r.getBool("isTruncated"),
                    geo,
                    r.getBool("isFavorited"),
                    r.getString("contributors"));
            System.out.println("\n@" + tt.getUsername() + ":" + " " + tt.getTweetText());
            System.out.println("Dados do tweet - Data: " + tt.getDateSent() +
                    " \nid: " + tt.getId() +
                    " \nsource: " + tt.getSource() +
                    " \nisTruncated?: " + tt.isTruncated() +
                    " \nisFavorited?: " + tt.isFavorited() +
                    " \ngeolocalizacao - latitude: " + tt.getGeolocation().getLatitude() +
                    " \ngeolocalizacao - longitude: " + tt.getGeolocation().getLongitude() +
                    " \ncontributors:" + tt.getContributors());

            tweets.add(tt);
        }

        System.out.println("\nselectAll – end\n");
        return tweets;
    }

    public List<Tweet> selectAllByUser() {
        System.out.println("selectAllByUser – init");

        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME+"ByUser");

        final String query = sb.toString();
        System.out.println("selectAllByUser – command: " + query.toUpperCase());
        ResultSet rs = session.execute(query);
        List<Tweet> tweets = new ArrayList<Tweet>();
        for (Row r : rs) {
            GeoLocation geo = new GeoLocation(r.getDouble("latitude"), r.getDouble("longitude"));
            Tweet tt = new Tweet(r.getString("usr"),
                    r.getString("ttext"),
                    convertToDate(r.getDate("date")),
                    r.getLong("id"),
                    r.getString("source"),
                    r.getBool("isTruncated"),
                    geo,
                    r.getBool("isFavorited"),
                    r.getString("contributors"));
            System.out.println("\n@" + tt.getUsername() + ":" + " " + tt.getTweetText());
            System.out.println("Dados do tweet - Data: " + tt.getDateSent() +
                    " \nid: " + tt.getId() +
                    " \nsource: " + tt.getSource() +
                    " \nisTruncated?: " + tt.isTruncated() +
                    " \nisFavorited?: " + tt.isFavorited() +
                    " \ngeolocalizacao - latitude: " + tt.getGeolocation().getLatitude() +
                    " \ngeolocalizacao - longitude: " + tt.getGeolocation().getLongitude() +
                    " \ncontributors:" + tt.getContributors());

            tweets.add(tt);
        }
        System.out.println("selectAllByUser – end");
        return tweets;
    }

    public Date convertToDate(LocalDate dateToConvert) {
        return new Date(dateToConvert.getMillisSinceEpoch());
    }

    public List<Tweet> selectAllByUser(String user) {
        System.out.println("selectAllByUser – init");

        StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME+"ByUser")
                .append(" WHERE usr = '").append(user).append("' ALLOW FILTERING;");

        final String query = sb.toString();
        System.out.println("selectAllByUser – command: " + query.toUpperCase());
        ResultSet rs = session.execute(query);
        List<Tweet> tweets = new ArrayList<Tweet>();
        for (Row r : rs) {
            GeoLocation geo = new GeoLocation(r.getDouble("latitude"), r.getDouble("longitude"));
            Tweet tt = new Tweet(r.getString("usr"),
                    r.getString("ttext"),
                    convertToDate(r.getDate("date")),
                    r.getLong("id"),
                    r.getString("source"),
                    r.getBool("isTruncated"),
                    geo,
                    r.getBool("isFavorited"),
                    r.getString("contributors"));
            System.out.println("\n@" + tt.getUsername() + ":" + " " + tt.getTweetText());
            System.out.println("Dados do tweet - Data: " + tt.getDateSent() +
                    " \nid: " + tt.getId() +
                    " \nsource: " + tt.getSource() +
                    " \nisTruncated?: " + tt.isTruncated() +
                    " \nisFavorited?: " + tt.isFavorited() +
                    " \ngeolocalizacao - latitude: " + tt.getGeolocation().getLatitude() +
                    " \ngeolocalizacao - longitude: " + tt.getGeolocation().getLongitude() +
                    " \ncontributors:" + tt.getContributors());

            tweets.add(tt);
        }
        System.out.println("selectAllByUser – end\n");
        return tweets;
    }


    public void deleteTweet(long id) {
        System.out.println("deleteTweet – init");
        StringBuilder sb = new StringBuilder("DELETE FROM ")
                .append(TABLE_NAME).append(" WHERE id = ")
                .append(id).append(";");

        final String query = sb.toString();
        System.out.println("deleteTweet – command: " + query.toUpperCase());
        session.execute(query);
        System.out.println("deleteTweet – end\n");

    }

    public void deleteTweetByUser(String user) {
        System.out.println("deleteTweetByUser – init");
        StringBuilder sb = new StringBuilder("DELETE FROM ")
                .append(TABLE_NAME+"ByUser").append(" WHERE usr = '")
                .append(user)
                .append("';");

        final String query = sb.toString();
        System.out.println("deleteTweet – command: " + query.toUpperCase());
        session.execute(query);
        System.out.println("deleteTweetByUser – end\n");

    }

    public void deleteTable(String tableName) {
        System.out.println("deleteTable – init");

        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);

        final String query = sb.toString();
        System.out.println("deleteTable – command: " + query.toUpperCase());
        session.execute(query);

        System.out.println("deleteTable – end");

    }

}