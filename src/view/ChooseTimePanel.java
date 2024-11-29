package view;

import model.Movie;
import model.TimeTableList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ChooseTimePanel extends JFrame {
    private final Movie selectedMovie;
    private final JPanel timePanel;
    private final List<JRadioButton> radioButtons = new ArrayList<>();
    private Consumer<TimeTableList.TimeTableEntry> onTimeSelectedListener;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private List<TimeTableList.TimeTableEntry> timeTableEntries;

    public ChooseTimePanel(Movie movie) {
        this.selectedMovie = movie;

        // 기본 프레임 설정
        setTitle("Choose Time");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Select Time for: " + movie.getTitle(), JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 시간표를 표시할 패널
        timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(timePanel);
        add(scrollPane, BorderLayout.CENTER);

        // Next 버튼
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
}