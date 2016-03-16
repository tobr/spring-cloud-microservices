package eu.tobr.cloud;

public class Movie {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MovieTitle{" +
                "title='" + title + '\'' +
                '}';
    }
}
