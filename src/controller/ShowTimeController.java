package controller;

import model.Movie;
import model.SeatList; // 추가
import model.TimeTableList;
import view.ChooseSeatPanel;
import view.ChooseTimePanel;

public class ShowTimeController {
    private final TimeTableList timeTableList;
    private final ChooseTimePanel view;
    private final Movie selectedMovie;

    public ShowTimeController(TimeTableList timeTableList, ChooseTimePanel view, Movie selectedMovie) {
        this.timeTableList = timeTableList;
        this.view = view;
        this.selectedMovie = selectedMovie;

        // View에 이벤트 리스너 등록
        this.view.setTimeSelect(selectedTime -> onTimeSelected(selectedMovie, selectedTime));

        // 초기 시간표 데이터 로드
        initialize();
    }

    private void initialize() {
        view.renderTimeTable(timeTableList.getTimeTableForMovie(selectedMovie.getId()));
    }

    private void onTimeSelected(Movie movie, TimeTableList.TimeTableEntry selectedTime) {
        if (selectedTime == null) {
            view.showMessage("Please select a time.");
            return;
        }

        // 다음 화면으로 이동
        ChooseSeatPanel chooseSeatPanel = new ChooseSeatPanel(movie, selectedTime);
        SeatController seatController = new SeatController(new SeatList(), chooseSeatPanel, movie, selectedTime); // 수정수정
        this.view.dispose(); // 현재 창 닫기
    }
}