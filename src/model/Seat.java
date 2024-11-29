package model;

// 좌석 관련 데이터 관리
public class Seat {
    private final String date;
    private final String movieId;
    private final String time;
    private final String theater;
    private final String seatNumber;
    private final boolean reserved;

    public Seat(String date, String movieId, String time, String theater, String seatNumber, boolean reserved) {
        this.date = date;
        this.movieId = movieId;
        this.time = time;
        this.theater = theater;
        this.seatNumber = seatNumber;
        this.reserved = reserved;
    }

    public String getDate() {
        return date;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTime() {
        return time;
    }

    public String getTheater() {
        return theater;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    // 예약 여부도 관리 하고 싶었는데... 실패함..ㅠ
    public boolean isReserved() {
        return reserved;
    }
}