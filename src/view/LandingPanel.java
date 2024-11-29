package view;

import controller.MovieController;
import model.MovieList;

import javax.swing.*;
import java.awt.*;

public class LandingPanel extends JFrame {
    public LandingPanel() {
        // 기본 설정
        setTitle("TicketMachine");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 제목 라벨 추가
        JLabel titleLabel = new JLabel("Ticket Machine", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // 여백 설정
        add(titleLabel, BorderLayout.NORTH);

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 20, 20)); // 두 개의 버튼을 세로로 배치
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200)); // 여백 설정

        // 버튼 추가
        JButton ticketCheckButton = new JButton("티켓 확인하기");
        JButton bookTicketButton = new JButton("예매하기");

        // 티켓 확인하기 버튼 (아직 미구현)
        ticketCheckButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "티켓 확인 화면으로 이동합니다."));

        // 예매하기 버튼 클릭 시 ChooseMoviePanel으로 이동
        bookTicketButton.addActionListener(e -> {
            this.dispose(); // 현재 창 닫기
            MovieList movieList = new MovieList(); // Model 생성
            ChooseMoviePanel chooseMoviePanel = new ChooseMoviePanel(); // View 생성
            new MovieController(movieList, chooseMoviePanel); // Controller 생성 및 연결
        });

        // 버튼을 패널에 추가
        buttonPanel.add(ticketCheckButton);
        buttonPanel.add(bookTicketButton);

        // 버튼 패널을 중앙에 추가
        add(buttonPanel, BorderLayout.CENTER);

        // 화면 표시
        setVisible(true);
    }
}