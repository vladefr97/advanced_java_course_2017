package telegram.bot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieFinder {

    private static final MovieJSONGetter MovieJSONGetter = new MovieJSONGetter();//object that gets JSON string
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private String JSONstring;
    private JSONObject jsonObject;


    public MovieFinder(String JSONstring) {


    }

    public List<Movie> getMovies(String movieTitle) {

        JSONstring = getJSONString("search/movie", "query=" + movieTitle + "&language=ru");

        return getMoviesFromJSON(JSONstring);

    }

    public List<Movie> getPopularMovies() {

        JSONstring = getJSONString("movie/popular", "language=ru&page=1");

        return getMoviesFromJSON(JSONstring);
    }

    public List<Movie> getTopRatedMovies() {

        JSONstring = getJSONString("movie/top_rated", "language=ru&page=1");

        return getMoviesFromJSON(JSONstring);
    }

    public List<Movie> getUpcomingMovies() {

        JSONstring = getJSONString("movie/upcoming", "language=ru&page=1&region=ru");
        return getMoviesFromJSON(JSONstring);
    }

    public List<Movie> getMoviesInTheatres() {

        JSONstring = getJSONString("moviev/now_playing", "language=ru&page=1&region=ru");
        return getMoviesFromJSON(JSONstring);
    }

    public List<DetailedMovie> getDetailedMovies(List<Integer> movieIDs) {//

        List<DetailedMovie> detailedMovies = new ArrayList<>();
        int size = movieIDs.size();

        for (int i = 0; i < size; i++) {
            JSONstring = getJSONString("movie/" + movieIDs.get(i), "language=ru");
            if (JSONstring != null)
                detailedMovies.add(getDetailedMovieFromJSON(JSONstring));
        }

        return detailedMovies;
    }

    public MovieFinder() {
        JSONstring = null;
    }

    public void setJSONstring(String JSONstring) {
        this.JSONstring = JSONstring;
    }

    public String getJSONstring() {
        return JSONstring;
    }

    private String getJSONString(String query, String queryOptions) {

        return MovieJSONGetter.getJSONString(query, queryOptions);
    }

    private List<Movie> getMoviesFromJSON(String JSONstring) {
        jsonObject = new JSONObject(JSONstring);


        JSONArray array = jsonObject.getJSONArray("results");

        Movie[] movies = new Movie[array.length()];
        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < movies.length; i++) {
            String string = array.optString(i);
            movies[i] = GSON.fromJson(string, Movie.class);
            movieList.add(movies[i]);

        }

        return movieList;

    }

    private DetailedMovie getDetailedMovieFromJSON(String JSONstring) {
        jsonObject = new JSONObject(JSONstring);
        JSONObject jsonObj;

        List<String> detailedParemeters = new ArrayList<>();
        DetailedMovie detailedMovie = new DetailedMovie(GSON.fromJson(JSONstring, Movie.class));

        JSONArray array = jsonObject.getJSONArray("genres");
        int size = array.length();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                jsonObj = new JSONObject(array.optString(i));
                detailedParemeters.add(jsonObj.getString("name"));

            }
            detailedMovie.setGenres(detailedParemeters);
        }


        array = jsonObject.getJSONArray("production_companies");
        size = array.length();
        detailedParemeters = new ArrayList<>();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                jsonObj = new JSONObject(array.optString(i));
                detailedParemeters.add(jsonObj.getString("name"));
            }
            detailedMovie.setProductionCompanies(detailedParemeters);
        }


        array = jsonObject.getJSONArray("production_countries");
        size = array.length();
        detailedParemeters = new ArrayList<>();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                jsonObj = new JSONObject(array.optString(i));
                detailedParemeters.add(jsonObj.getString("name"));
            }
            detailedMovie.setProductionCountries(detailedParemeters);
        }


        String string = jsonObject.optString("budget");
        if (string != null)
            detailedMovie.setBudget(string);

        string = jsonObject.optString("revenue");
        if (string != null)
            detailedMovie.setRevenue(string);

        string = jsonObject.optString("runtime");
        if (string != null)
            detailedMovie.setRuntime(string);

        string = jsonObject.optString("original_title");
        if (string != null)
            detailedMovie.setOriginTitle(string);


        return detailedMovie;

    }


}
