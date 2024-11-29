package model;


// 유저 선택한 티켓 하나 관리
public class Ticket {
    private String ticketId; // 티켓 고유번호
    private String posterPath; // 영화 포스터 경로
    private String title; // 영화 제목
    private String genre; // 영화 장르
    private String age; // 관람 연령
    private String runtime; // 상영 시간
    private String date; // 영화 날짜
    private String time; // 영화 시간
    private String theater; // 영화관 정보
    private String seats; // 선택된 좌석
    private String ticketType; // 티켓 타입 (성인, 청소년, 아동)
    private String discounts; // 할인 정보

    public Ticket(String ticketId, String posterPath, String title, String genre, String age, String runtime,
                  String date, String time, String theater, String seats, String ticketType, String discounts) {
        this.ticketId = ticketId;
        this.posterPath = posterPath;
        this.title = title;
        this.genre = genre;
        this.age = age;
        this.runtime = runtime;
        this.date = date;
        this.time = time;
        this.theater = theater;
        this.seats = seats;
        this.ticketType = ticketType;
        this.discounts = discounts;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAge() {
        return age;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTheater() {
        return theater;
    }

    public String getSeats() {
        return seats;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getDiscounts() {
        return discounts;
    }

    @Override
    public String toString() {
        return ticketId + "," + posterPath + "," + title + "," + genre + "," + age + "," + runtime + ","
                + date + "," + time + "," + theater + "," + seats + "," + ticketType + "," + discounts;
    }
}