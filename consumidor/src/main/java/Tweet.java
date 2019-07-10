import java.util.Date;

import twitter4j.GeoLocation;

public class Tweet {
    private String Username;
    private String TweetText;
    private Date DateSent;
    private long Id;
    private String Source;
    private boolean IsTruncated;
    private GeoLocation Geolocation;
    private boolean IsFavorited;
    private String Contributors;

    public Tweet() {}

    public Tweet(String username, String tweetText, Date dateSent,
                 long id, String source, boolean isTruncated,
                 GeoLocation geolocation, boolean isFavorited, String contributors) {
        Username = username;
        TweetText = tweetText;
        DateSent = dateSent;
        Id = id;
        Source = source;
        IsTruncated = isTruncated;
        Geolocation = geolocation;
        IsFavorited = isFavorited;
        Contributors = contributors;
    }

    public void setUsername(String username) { this.Username = username; }

    public void setTweetText(String tweetText) { this.TweetText = tweetText; }

    public void setDateSent(Date dateSent) { this.DateSent = dateSent; }

    public String getUsername() { return Username; }

    public Date getDateSent() { return DateSent; }

    public String getTweetText() { return TweetText; }

    public String toString() { return "@" + this.Username +
            ": " + this.TweetText; }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public boolean isTruncated() {
        return IsTruncated;
    }

    public void setTruncated(boolean truncated) {
        IsTruncated = truncated;
    }

    public GeoLocation getGeolocation() {
        return Geolocation;
    }

    public void setGeolocation(GeoLocation geolocation) {
        Geolocation = geolocation;
    }

    public boolean isFavorited() {
        return IsFavorited;
    }

    public void setFavorited(boolean favorited) {
        IsFavorited = favorited;
    }

    public String getContributors() {
        return Contributors;
    }

    public void setContributors(String contributors) {
        Contributors = contributors;
    }
}