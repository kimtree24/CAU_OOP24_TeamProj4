package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


// 시간표 정보 DB에서 가져오는 것
public class TimeTableList {
    // 모든 영화의 상영시간표 데이터 저장
    private final List<TimeTableEntry> timeTableEntries = new ArrayList<>();

    public TimeTableList() {
        loadTimeTable("DB/TimeTable.txt");
    }

    private void loadTimeTable(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String date = parts[0].trim(); // 영화 상영날짜
                    int movieId = Integer.parseInt(parts[1].trim()); // 영화 아이디
                    String time = parts[2].trim(); // 영화 상영시간
                    String theater = parts[3].trim(); // 영화 상영극장
                    timeTableEntries.add(new TimeTableEntry(date, movieId, time, theater));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 영화 아이디에 해당하는 상영시간표 필터링하여 반환
    public List<TimeTableEntry> getTimeTableForMovie(int movieId) {
        List<TimeTableEntry> filteredEntries = new ArrayList<>();
        for (TimeTableEntry entry : timeTableEntries) {
            if (entry.getMovieId() == movieId) {
                filteredEntries.add(entry);
            }
        }
        return filteredEntries;
    }

    // 해당 영화의 상영 날짜를 필터링하여 반환
    public List<TimeTableEntry> getTimeTableForMovieAndDate(int movieId, String date) {
        List<TimeTableEntry> filteredEntries = new ArrayList<>();
        for (TimeTableEntry entry : timeTableEntries) {
            if (entry.getMovieId() == movieId && entry.getDate().equals(date)) {
                filteredEntries.add(entry);
            }
        }
        return filteredEntries;
    }

    public static class TimeTableEntry {
        private final String date;
        private final int movieId;
        private final String time;
        private final String theater;

        public TimeTableEntry(String date, int movieId, String time, String theater) {
            this.date = date;
            this.movieId = movieId;
            this.time = time;
            this.theater = theater;
        }

        public String getDate() {
            return date;
        }

        public int getMovieId() {
            return movieId;
        }

        public String getTime() {
            return time;
        }

        public String getTheater() {
            return theater;
        }

        @Override
        public String toString() {
            return date + " - " + time + " (" + theater + ")";
        }
    }
}