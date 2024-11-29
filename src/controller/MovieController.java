package controller;

import model.MovieList;
import view.ChooseMoviePanel;

public class MovieController {
    private final MovieList movieList; // 파일에서 전체 영화 목록 받아오기
    private final ChooseMoviePanel view;

    public MovieController(MovieList movieList, ChooseMoviePanel view) {
        this.movieList = movieList;
        this.view = view;

        // View에 Controller 등록
        this.view.setController(this);
        this.view.updateMovieInfo(movieList.getMovies().get(0)); // 첫 번째 영화 표시
    }

    // 다음 영화로 넘기기 위한 메서드
    public void showNextMovie(int currentIndex) {
        if (currentIndex < movieList.getMovies().size() - 1) {
            view.updateMovieInfo(movieList.getMovies().get(currentIndex + 1));
        } else {
            view.showMessage("This is the last movie.");
        }
    }

    // 이전 영화로 넘기기 위한 메서드
    public void showPreviousMovie(int currentIndex) {
        if (currentIndex > 0) {
            view.updateMovieInfo(movieList.getMovies().get(currentIndex - 1));
        } else {
            view.showMessage("This is the first movie.");
        }
    }

    public MovieList getMovieList(){
        return this.movieList;
    }
}