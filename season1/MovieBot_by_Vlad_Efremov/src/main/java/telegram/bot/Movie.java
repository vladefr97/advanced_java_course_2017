package telegram.bot;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;


public class Movie implements Comparable<Movie> {


    @SerializedName("vote_average")
    private double rating;
    private String title;
    @SerializedName("release_date")
    private String releaseDate;
    private String overview;
    @SerializedName("poster_path")
    private String posterPath;
    private static final String BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500/";
    private int id;
    private Comparator<Movie> comparator;

    public Movie() {
    }

    public Movie(Movie m) {
        this.rating = m.rating;
        this.title = m.title;
        this.releaseDate = m.releaseDate;
        this.overview = m.overview;
        this.posterPath = m.posterPath;
        this.id = m.id;
    }

    public Movie(Comparator<Movie> comparator) {
        this.comparator = comparator;
    }


    public void setComparator(Comparator<Movie> comparator) {
        this.comparator = comparator;
    }



    @Override
    public int compareTo(Movie movie) {
        if (comparator == null)
            return Double.compare(this.rating, movie.rating);
        else {
            return comparator.compare(this, movie);
        }

    }


    public String getPoster() {
        return BASE_POSTER_URL + posterPath;
    }

    @Override
    public String toString() {
        return title + "\n" + "ID: " + id + "\n" + "Дата выхода: " + releaseDate + "\n" + "Рейтинг: " + rating + "\n" + overview + "\n";
    }

    public String getTitle() {
        return title;
    }

    public int getId() {

        return id;
    }

    public String getReleaseDate() {

        return releaseDate;
    }

    public double getRating() {

        return rating;
    }
}
