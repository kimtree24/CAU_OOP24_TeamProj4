package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


// 좌석 리스트 DB에서 가져와서 예약 여부 관리하려고 했는데 실패함...ㅠ
public class SeatList {
    private final List<Seat> seats = new ArrayList<>();

    public SeatList() {
        loadSeats("DB/SeatList.txt");
    }

    private void loadSeats(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String date = parts[0].trim();
                    String movieId = parts[1].trim();
                    String time = parts[2].trim();
                    String theater = parts[3].trim();

                    for (int i = 4; i < parts.length; i++) {
                        String[] seatInfo = parts[i].split(":");
                        String seatNumber = seatInfo[0].trim();
                        boolean reserved = "true".equalsIgnoreCase(seatInfo[1].trim());
                        seats.add(new Seat(date, movieId, time, theater, seatNumber, reserved));
                    }
                }
            }
            System.out.println("SeatList loaded with " + seats.size() + " seats.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("error from seat " + filePath);
        }
    }

    public List<Seat> getSeatsForMovieAndTime(String date, String movieId, String time) {
        List<Seat> filteredSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.getDate().equals(date) && seat.getMovieId().equals(movieId) && seat.getTime().equals(time)) {
                filteredSeats.add(seat);
            }
        }
        System.out.println("Filtered seats: " + filteredSeats.size());
        return filteredSeats;
    }
}