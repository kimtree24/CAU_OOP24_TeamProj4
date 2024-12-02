package view;

import model.Movie;
import model.TimeTableList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ChooseTimePanel extends JFrame {
    private final Movie selectedMovie; // 상영시간표가 표시될 영화
    private final JPanel timePanel; // 시간표 목록 표시
    private final List<JRadioButton> radioButtons = new ArrayList<>(); // 상영시간 선택을 위한 버튼 저장
    private Consumer<TimeTableList.TimeTableEntry> onTimeSelectedListener;
    private ButtonGroup buttonGroup = new ButtonGroup(); // 한 번에 하나의 버튼만 선택 가능
    private List<TimeTableList.TimeTableEntry> timeTableEntries; // 상영시간표 항목 저장
    // 추가
    private JComboBox<String> dateComboBox; //날짜 선택

    // 생성자
    public ChooseTimePanel(Movie movie) {
        this.selectedMovie = movie;

        // 기본 프레임 설정
        setTitle("Choose Time");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Select Time for: " + movie.getTitle(), JLabel.CENTER); // 영화 제목 표시
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 추가
        // 날짜 선택
        dateComboBox = new JComboBox<>();
        dateComboBox.addActionListener(e -> onDateSelected());
        add(dateComboBox, BorderLayout.CENTER);

        // 시간표를 표시할 패널
        timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS)); // 세로로 정렬
        JScrollPane scrollPane = new JScrollPane(timePanel); // 스크롤
        add(scrollPane, BorderLayout.CENTER);

        // Next 버튼
        // 선택된 시간 확인
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            TimeTableList.TimeTableEntry selectedTime = null;
            for (int i = 0; i < radioButtons.size(); i++) {
                if (radioButtons.get(i).isSelected()) {
                    selectedTime = timeTableEntries.get(i);
                    break;
                }
            }

            if (onTimeSelectedListener != null) {
                onTimeSelectedListener.accept(selectedTime); // 이벤트 리스너 호출
            }
        });

        // 버튼을 붙여넣음
        add(nextButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // 선택된 날짜 관리
    private void onDateSelected() {
        String date = dateComboBox.getSelectedItem().toString();
        if (selectedMovie != null) {
            List<TimeTableList.TimeTableEntry> filteredTimes = new TimeTableList().getTimeTableForMovieAndDate(selectedMovie.getId(), date);
            renderTimeTable(filteredTimes);
        }
    }

    // 선택된 Movie 반환
    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    // 타임테이블 DB에서 가져온 것 바탕으로 화면에 표시
    public void renderTimeTable(List<TimeTableList.TimeTableEntry> timeTable) {
        this.timeTableEntries = timeTable;
        timePanel.removeAll();
        radioButtons.clear();
        buttonGroup = new ButtonGroup();

        for (TimeTableList.TimeTableEntry entry : timeTable) {
            JRadioButton radioButton = new JRadioButton(entry.toString());
            radioButtons.add(radioButton);
            buttonGroup.add(radioButton);
            timePanel.add(radioButton);
        }

        timePanel.revalidate();
        timePanel.repaint();
    }

    public void setTimeSelect(Consumer<TimeTableList.TimeTableEntry> listener) {
        this.onTimeSelectedListener = listener;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // 추가
    // 날자 선택
    public void populateDateComboBox(List<String> dates) {
        for (String date : dates) {
            dateComboBox.addItem(date);
        }
    }
}