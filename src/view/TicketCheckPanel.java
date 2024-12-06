package view;

import controller.TicketCheckController;

import javax.swing.*;
import java.awt.*;

public class TicketCheckPanel extends JFrame {
    private JTextField ticketNumberField; // 티켓 번호 입력 필드
    private JPanel ticketInfoPanel; // 티켓 정보를 표시하는 패널
    private JLabel posterLabel; // 영화 포스터
    private JLabel[] infoLabels; // 티켓 정보 라벨들
    private TicketCheckController controller; // Controller

    public TicketCheckPanel() {
        // 기본 설정
        setTitle("Conform Ticket Reservation");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 제목 라벨 추가
        JLabel titleLabel = new JLabel("Conform Ticket Reservation", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // 여백 설정
        add(titleLabel, BorderLayout.NORTH);


        // 상단 패널: 티켓 번호 입력
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel ticketNumberLabel = new JLabel("Enter Ticket Number: ");
        ticketNumberField = new JTextField();
        JButton checkButton = new JButton("Check");
        inputPanel.add(ticketNumberLabel, BorderLayout.WEST);
        inputPanel.add(ticketNumberField, BorderLayout.CENTER);
        inputPanel.add(checkButton, BorderLayout.EAST);

        // 중앙 패널: 티켓 정보 출력
        ticketInfoPanel = new JPanel();
        ticketInfoPanel.setLayout(new BoxLayout(ticketInfoPanel, BoxLayout.Y_AXIS));
        ticketInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 포스터 라벨
        posterLabel = new JLabel();
        posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ticketInfoPanel.add(posterLabel);

        // 정보 라벨 초기화
        infoLabels = new JLabel[10]; // 10개의 정보 라벨
        for (int i = 0; i < infoLabels.length; i++) {
            infoLabels[i] = new JLabel();
            ticketInfoPanel.add(infoLabels[i]);
        }

        // 하단 패널: Home 버튼
        JPanel homePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton homeButton = new JButton("Home");
        homePanel.add(homeButton);

        // 프레임에 패널 추가
        add(inputPanel, BorderLayout.NORTH); // 입력 필드와 버튼
        add(ticketInfoPanel, BorderLayout.CENTER); // 티켓 정보 출력 영역
        add(homePanel, BorderLayout.SOUTH); // Home 버튼

        // 버튼 동작 정의
        checkButton.addActionListener(e -> controller.checkTicket(ticketNumberField.getText().trim())); // 컨트롤러에 위임
        homeButton.addActionListener(e -> controller.returnToHome()); // 컨트롤러에 위임

        // 프레임 표시
        setVisible(true);
    }

    // Controller 연결 메서드
    public void setController(TicketCheckController controller) {
        this.controller = controller;
    }

    // 티켓 정보를 업데이트하는 메서드
    public void updateTicketInfo(String[] fields) {
        // 포스터 이미지 업데이트
        ImageIcon movieImage = new ImageIcon(fields[1]); // 포스터 경로
        Image scaledImage = movieImage.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        posterLabel.setIcon(new ImageIcon(scaledImage));

        // 정보 라벨 업데이트
        infoLabels[0].setText("Ticket ID: " + fields[0]);
        infoLabels[1].setText("Title: " + fields[2]);
        infoLabels[2].setText("Genre: " + fields[3]);
        infoLabels[3].setText("Age: " + fields[4]);
        infoLabels[4].setText("Runtime: " + fields[5] + " mins");
        infoLabels[5].setText("Date: " + fields[6]);
        infoLabels[6].setText("Time: " + fields[7]);
        infoLabels[7].setText("Theater: " + fields[8]);
        infoLabels[8].setText("Seat: " + fields[9]);
        infoLabels[9].setText("Ticket Type: " + fields[10]);
    }

    // 메시지 박스 표시 메서드
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
