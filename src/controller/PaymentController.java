package controller;

import model.Movie;
import model.Payment;
import model.TimeTableList;
import view.PaymentPanel;
import view.PrintTicketPanel;

public class PaymentController {
    private final PaymentPanel view;
    private final Movie selectedMovie;
    private final TimeTableList.TimeTableEntry selectedTime;
    private final String selectedSeats;

    // 이전 단계에서 선택된 값들을 객체로 생성자에 넣어줌
    public PaymentController(PaymentPanel view, Movie selectedMovie, TimeTableList.TimeTableEntry selectedTime, String selectedSeats) {
        this.view = view;
        this.selectedMovie = selectedMovie;
        this.selectedTime = selectedTime;
        this.selectedSeats = selectedSeats;
        this.view.setController(this); // View에 Controller 연결
    }

    // 할인율 계산해서 view에 전달
    public void calculatePayment(String ticketType, boolean militaryDiscount, boolean seniorDiscount, boolean otherDiscount) {
        Payment payment = new Payment(ticketType, militaryDiscount, seniorDiscount, otherDiscount);
        int totalPrice = payment.calculateTotalPrice();

        // 결과를 View에 전달
        view.displayTotalPrice(totalPrice);
    }

    // 여태까지 입력된 값 바탕으로 티켓 생성
    public void onConfirmPayment(String ticketType, boolean militaryDiscount, boolean seniorDiscount,
                                 boolean otherDiscount, int totalPrice) {
        // 티켓 생성 및 표시
        // 티켓 출력 패널 생성
        PrintTicketPanel printTicketPanel = new PrintTicketPanel();
        new TicketController(printTicketPanel).generateTicket(
                selectedMovie.getImg(),
                selectedMovie.getTitle(),
                selectedMovie.getGenre(),
                selectedMovie.getAge(),
                String.valueOf(selectedMovie.getRunningTime()),
                selectedTime.getDate(),
                selectedTime.getTime(),
                selectedTime.getTheater(),
                selectedSeats,
                ticketType,
                getDiscounts(militaryDiscount, seniorDiscount, otherDiscount)
        );
        view.dispose(); // 현재 화면 닫기
    }

    // 할인여부 가져오는 메서드
    private String getDiscounts(boolean militaryDiscount, boolean seniorDiscount, boolean otherDiscount) {
        StringBuilder discounts = new StringBuilder();
        if (militaryDiscount) {
            discounts.append("Military ");
        }
        if (seniorDiscount) {
            discounts.append("Senior ");
        }
        if (otherDiscount) {
            discounts.append("Other ");
        }
        return discounts.toString().trim();
    }
}