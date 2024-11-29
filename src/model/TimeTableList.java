package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


// 시간표 정보 DB에서 가져오는 것
public class TimeTableList {
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
                    String date = parts[0].trim();
                    int movieId = Integer.parseInt(parts[1].trim());
                    String time = parts[2].trim();
                    String theater = parts[3].trim();
                    timeTableEntries.add(new TimeTableEntry(date, movieId, time, theater));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TimeTableEntry> getTimeTableForMovie(int movieId) {
        List<TimeTableEntry> filteredEntries = new ArrayList<>();
        for (TimeTableEntry entry : timeTableEntries) {
            if (entry.getMovieId() == movieId) {
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