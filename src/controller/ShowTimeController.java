package controller;

import model.Movie;
import model.SeatList;
import model.TimeTableList;
import view.ChooseSeatPanel;
import view.ChooseTimePanel;

import java.util.ArrayList;
import java.util.List;

public class ShowTimeController {
    private final TimeTableList timeTableList; // 영화 상영시간표
    private final ChooseTimePanel view; // 상영시간을 사용자에게 보여줌
    private final Movie selectedMovie; // 사용자가 선택한 영화

    // 생성자
    public ShowTimeController(TimeTableList timeTableList, ChooseTimePanel view, Movie selectedMovie) {
        this.timeTableList = timeTableList; // 영화 상영 시간표
        this.view = view; // 상영시간 선택 UI
        this.selectedMovie = selectedMovie; // 사용자가 선택한 영화

        // View에 이벤트 리스너 등록
        this.view.setTimeSelect(selectedTime -> onTimeSelected(selectedMovie, selectedTime));

        // 초기 시간표 데이터 로드
        initialize();
    }

    // 수정
    // TimeTableList에서 selectedMovie의 아이디로 상영시간표를 가져옴
    private void initialize() {
//        view.renderTimeTable(timeTableList.getTimeTableForMovie(selectedMovie.getId()));
        List<TimeTableList.TimeTableEntry> timeTableEntries = timeTableList.getTimeTableForMovie(selectedMovie.getId());
        List<String> dates = new ArrayList<>();

        for (TimeTableList.TimeTableEntry entry : timeTableEntries) {
            String date = entry.getDate();
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }

        view.populateDateComboBox(dates);

        if (!dates.isEmpty()) {
            String firstDate = dates.get(0);
            List<TimeTableList.TimeTableEntry> filterdTimes = timeTableList.getTimeTableForMovieAndDate(selectedMovie.getId(), firstDate);
            view.renderTimeTable(filterdTimes);
        }

//        if (!dates.isEmpty()) {
//            dateComboBox.setSelectedIndex(0);
//        }
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