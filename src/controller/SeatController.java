package controller;

import model.Movie;
import model.SeatList;
import model.TimeTableList;
import view.ChooseSeatPanel;
import view.PaymentPanel;

public class SeatController {
    private final SeatList seatList;
    private final ChooseSeatPanel view;
    private final TimeTableList.TimeTableEntry selectedTime;
    private final Movie selectedMovie;

    public SeatController(SeatList seatList, ChooseSeatPanel view, Movie selectedMovie, TimeTableList.TimeTableEntry selectedTime) {
        this.seatList = seatList;
        this.view = view;
        this.selectedMovie = selectedMovie;
        this.selectedTime = selectedTime;

        // View에 Controller 연결
        this.view.setController(this);
    }

    public void onNextButtonClicked(String selectedSeats) {
        System.out.println("Selected seats: " + selectedSeats);
        // 다음 화면으로 이동 (PaymentPanel)
        view.dispose(); // 현재 화면 닫기
        PaymentPanel paymentPanel = new PaymentPanel(selectedMovie, selectedTime, selectedSeats);
        new PaymentController(paymentPanel, selectedMovie, selectedTime, selectedSeats);
    }
}