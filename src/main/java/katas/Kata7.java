package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        /*return ImmutableList.of(ImmutableMap.of("id", 5, "title", "Bad Boys", "boxart", "url"));*/

        return movieLists
                .stream()
                .flatMap(movie -> movie.getVideos().stream())
                .map(movie -> Map.of("id", movie.getId(), "title", movie.getTitle(), "boxart", movie
                        .getBoxarts()
                        .stream()
                        .reduce((item1, item2)-> item1.getHeight()*item1.getWidth() < item2.getWidth()*item2.getHeight() ? item1:item2)
                        .map(m -> m.getUrl())
                )).collect(Collectors.toList());
    }
}
