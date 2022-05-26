package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        /*return ImmutableList.of(ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl"));*/

        return movieLists
                .stream()
                .flatMap(movie -> movie.getVideos()
                        .stream())
                .map(movie -> ImmutableMap.of(
                        "id", movie.getId(), "title", movie.getTitle(), "time", movie
                        .getInterestingMoments()
                        .stream()
                        .filter(moment -> moment.getType().equals("Middle")).findFirst().map(m -> m.getTime()).orElse(new Date()),
                        "boxart", movie
                                        .getBoxarts()
                                        .stream()
                                        .reduce((item1, item2)-> item1.getHeight()*item1.getWidth() < item2.getWidth()*item2.getHeight() ? item1:item2)
                                        .map(m -> m.getUrl()).orElse("")
                        )
                ).collect(Collectors.toList());









                        /*.getInterestingMoments()
                        .stream()
                        .filter( m -> m.getType("Middle")))).collect(Collectors.toList());*/
    }
}
