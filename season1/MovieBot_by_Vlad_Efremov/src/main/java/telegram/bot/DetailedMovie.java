package telegram.bot;

import java.util.List;

public class DetailedMovie extends Movie {

    private List<String> genres;
    private List<String> productionCompanies;
    private List<String> productionCountries;
    private String originTitle;
    private String runtime;
    private String budget;
    private String revenue;

    public DetailedMovie() {

    }

    public DetailedMovie(Movie m) {
        super(m);
    }

    public DetailedMovie(List<String> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public void setProductionCompanies(List<String> productionCompanies) {

        this.productionCompanies = productionCompanies;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setRuntime(String runtime) {

        this.runtime = runtime;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public void setOriginTitle(String originTitle) {

        this.originTitle = originTitle;
    }

    public void setProductionCountries(List<String> productionCountries) {

        this.productionCountries = productionCountries;
    }

    public void setGenres(List<String> genres) {

        this.genres = genres;
    }


    @Override

    public String toString() {
        String string = super.toString();

        int size = genres.size();
        if (size != 0) {
            string += "Жанр: ";
            for (int i = 0; i < size - 1; i++)
                string += genres.get(i) + ", ";
            string += genres.get(size - 1) + "\n";
        }

        size = productionCompanies.size();
        if (size != 0) {
            string += "Производства компаний: ";
            for (int i = 0; i < size - 1; i++)
                string += productionCompanies.get(i) + ", ";
            string += productionCompanies.get(size - 1) + "\n";
        }

        size = productionCountries.size();
        if (size != 0) {
            string += "Страны производства: ";
            for (int i = 0; i < size - 1; i++)
                string += productionCountries.get(i) + ", ";
            string += productionCountries.get(size - 1) + "\n";
        }

        if (originTitle != null)
            string += "Оригинальное название: " + originTitle + "\n";
        if (budget != null && !budget.equals("0"))
            string += "Бюджет: " + budget + " \n";
        if (revenue != null && !revenue.equals("0"))
            string += "Сборы: " + revenue + " \n";
        if (runtime != null && !runtime.equals("0"))
            string += "Продолжительность: " + runtime + " мин.\n";


        return string;
    }
}
