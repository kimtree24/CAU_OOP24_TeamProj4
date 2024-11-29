package view;

import controller.PaymentController;
import controller.SeatController;
import model.Movie;
import model.TimeTableList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChooseSeatPanel extends JFrame {
    private final JPanel seatPanel;
    private final List<JButton> seatButtons = new ArrayList<>(); // 좌석 버튼 리스트
    private final List<String> selectedSeats = new ArrayList<>();
    private SeatController controller;
    private final Movie selectedMovie;
    private final TimeTableList.TimeTableEntry selectedTime;

    public ChooseSeatPanel(Movie selectedMovie, TimeTableList.TimeTableEntry selectedTime) {
        this.selectedMovie = selectedMovie;
        this.selectedTime = selectedTime;

        // 기본 프레임 설정
        setTitle("Choose Your Seat");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 상단에 스크린 표시
        JLabel screenLabel = new JLabel("SCREEN", JLabel.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(screenLabel, BorderLayout.NORTH);

        // 좌석 배치 패널 생성
        seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(5, 6, 10, 10));
        seatPanel.setBackground(Color.LIGHT_GRAY);
        add(seatPanel, BorderLayout.CENTER);

        // 버튼 생성하며 좌석처럼 보이게
        for (int i = 0; i < 30; i++) {
            JButton seatButton = new JButton("Seat" + (i + 1));
            seatButton.addActionListener(e -> {
                if (selectedSeats.contains(seatButton.getText())) {
                    // 선택 취소
                    selectedSeats.remove(seatButton.getText());
                    seatButton.setBackground(null);
                } else {
                    // 선택
                    selectedSeats.add(seatButton.getText());
                    seatButton.setBackground(Color.GREEN);
                }
                //System.out.println("Selected seats: " + selectedSeats); // 디버깅 로그
            });
            seatButtons.add(seatButton);
            seatPanel.add(seatButton);
        }

        // Next 버튼 생성
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select at least one seat.");
            } else {
                //System.out.println("Final selected seats: " + selectedSeats);
                if (controller != null) {
                    controller.onNextButtonClicked(String.join(", ", selectedSeats));
                } else {
                    JOptionPane.showMessageDialog(this, "Controller is not set."); // 컨트롤러가 설정되지 않았을 때의 처리
                }
            }
        });
        add(nextButton, BorderLayout.SOUTH);

        // 화면에 출력
        setVisible(true);

        // 변경사항 발생시 다시 렌더링
        seatPanel.revalidate();
        seatPanel.repaint();
    }

    // 컨트롤러 연결
    public void setController(SeatController controller) {
        this.controller = controller;
        System.out.println("Controller set in ChooseSeatPanel.");
    }
}