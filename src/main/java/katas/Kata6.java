package katas;

import model.Movie;
import util.DataUtil;

import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        return movies
                .stream()
                .flatMap( movie -> movie.getBoxarts().stream())
                .reduce((item1, item2) -> item1.getHeight()*item1.getWidth() > item2.getWidth()*item2.getHeight() ? item1:item2)
                .map(m -> m.getUrl()).orElse("");
        /*return "someUrl";*/
    }
}
