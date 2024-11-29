package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 영화 전체 리스트를 DB에서 가져오는 것
public class MovieList {
    private List<Movie> movies = new ArrayList<>();

    // 생성자: 텍스트 파일에서 데이터를 읽어와 초기화
    public MovieList() {
        loadMoviesFromFile("DB/MovieList.txt");
    }
    // 파일에서 영화 정보를 읽어와 Movie 객체로 변환
    private void loadMoviesFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // 데이터를 ','로 분리

                // 각각 맞춰서 저장
                if (parts.length == 6) {
                    Integer id = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    String genre = parts[2].trim();
                    String age = parts[3].trim();
                    Integer runningTime = Integer.parseInt(parts[4].trim());
                    String img = parts[5].trim();

                    // Movie 객체 생성 및 리스트에 추가
                    movies.add(new Movie(id, title, genre, age, runningTime, img));
                }
            }
        } catch (IOException e) {
            System.err.println("error : " + e.getMessage());
        }
    }

    // 영화 리스트 반환
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Movie movie : movies) {
            sb.append(movie).append("\n");
        }
        return sb.toString();
    }

}
