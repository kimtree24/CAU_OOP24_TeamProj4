package view;

import controller.MovieController;
import controller.ShowTimeController;
import model.Movie;
import model.TimeTableList;

import javax.swing.*;
import java.awt.*;

// 영화 선택하는 화면
// JFrame 상속해서 화면 구성
public class ChooseMoviePanel extends JFrame {
    private final JLabel titleLabel;
    private final JLabel genreLabel;
    private final JLabel ageLabel;
    private final JLabel runningTimeLabel;
    private final JLabel imageLabel;
    private int currentIndex = 0;
    private MovieController controller;

    public ChooseMoviePanel() {
        // 기본 프레임 설정
        setTitle("Choose Movie");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 영화 정보를 표시할 패널
        JPanel movieInfoPanel = new JPanel();
        movieInfoPanel.setLayout(new BoxLayout(movieInfoPanel, BoxLayout.Y_AXIS));
        movieInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 영화 정보 라벨 객체 생성
        titleLabel = new JLabel();
        genreLabel = new JLabel();
        ageLabel = new JLabel();
        runningTimeLabel = new JLabel();
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 폰트 관련
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        genreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        runningTimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // 패널에 라벨 추가
        movieInfoPanel.add(titleLabel);
        movieInfoPanel.add(Box.createVerticalStrut(10));
        movieInfoPanel.add(genreLabel);
        movieInfoPanel.add(Box.createVerticalStrut(10));
        movieInfoPanel.add(ageLabel);
        movieInfoPanel.add(Box.createVerticalStrut(10));
        movieInfoPanel.add(runningTimeLabel);

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Prev 버튼 생성
        JButton prevButton = new JButton("Prev");
        prevButton.addActionListener(e -> {
            if (controller != null) {
                controller.showPreviousMovie(currentIndex);
                currentIndex = Math.max(0, currentIndex - 1);
            }
        });

        // Next 버튼 생성
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            if (controller != null) {
                controller.showNextMovie(currentIndex);
                currentIndex = Math.min(currentIndex + 1, controller.getMovieList().getMovies().size() - 1); //수정수정
            }
        });

        // 선택하기 버튼 생성
        JButton selectButton = new JButton("Confirm");
        selectButton.addActionListener(e -> {
            // 현재 선택한 영화를 Movie 객체에 저장
            Movie selectedMovie = controller.getMovieList().getMovies().get(currentIndex);

            // 시간표 선택 패널로 넘어가기 위한 준비
            // 시간표 패널 생성
            ChooseTimePanel chooseTimePanel = new ChooseTimePanel(selectedMovie);
            // 이하는 시간표 패널에서 사용할 것들 결합
            TimeTableList timeTableList = new TimeTableList();
            new ShowTimeController(timeTableList, chooseTimePanel, selectedMovie);
            this.dispose(); // 현재 창 닫기
        });

        // 버튼 패널에 버튼 추가
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(selectButton);

        // 메인 프레임에 각 패널들 추가
        add(imageLabel, BorderLayout.NORTH);
        add(movieInfoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 화면 출력
        setVisible(true);
    }

    // 컨트롤러 연결
    public void setController(MovieController controller) {
        this.controller = controller;
    }

    // 현재 선택된 영화 출력하는 메서드
    public void updateMovieInfo(Movie movie) {
        titleLabel.setText("Title: " + movie.getTitle());
        genreLabel.setText("Genre: " + movie.getGenre());
        ageLabel.setText("Age: " + movie.getAge());
        runningTimeLabel.setText("Running Time: " + movie.getRunningTime() + " mins");

        ImageIcon movieImage = new ImageIcon(movie.getImg());
        Image scaledImage = movieImage.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}