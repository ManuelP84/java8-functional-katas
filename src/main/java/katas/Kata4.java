package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        /*return ImmutableList.of(ImmutableMap.of("id", 5, "title", "Bad Boys", "boxart", new BoxArt(150, 200, "url")));*/
        return movieLists
                .stream()
                .flatMap(m -> m.getVideos().stream())
                .map(m -> Map.of("id", m.getId(), "title", m.getTitle(), "boxart", m.getBoxarts()
                        .stream()
                        .filter(box -> box.getWidth() == 150 && box.getHeight() == 200)
                        .findFirst()
                        .orElseThrow()
                ))
                .collect(Collectors.toList());



    }
}
