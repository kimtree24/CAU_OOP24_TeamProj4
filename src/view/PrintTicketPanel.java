package view;

import controller.TicketController;
import model.Ticket;

import javax.swing.*;
import java.awt.*;

// 최종 티켓 출력 화면
public class PrintTicketPanel extends JFrame {
    private TicketController controller;

    public PrintTicketPanel() {
        setTitle("Your Ticket");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    public void setController(TicketController controller) {
        this.controller = controller;
    }

    public void displayTicket(Ticket ticket) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 영화 포스터
        ImageIcon movieImage = new ImageIcon(ticket.getPosterPath());
        Image scaledImage = movieImage.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        JLabel posterLabel = new JLabel(new ImageIcon(scaledImage));
        panel.add(posterLabel);

        // 영화 정보
        panel.add(new JLabel("Ticket ID: " + ticket.getTicketId()));
        panel.add(new JLabel("Title: " + ticket.getTitle()));
        panel.add(new JLabel("Genre: " + ticket.getGenre()));
        panel.add(new JLabel("Age: " + ticket.getAge()));
        panel.add(new JLabel("Runtime: " + ticket.getRuntime()));
        panel.add(new JLabel("Date: " + ticket.getDate()));
        panel.add(new JLabel("Time: " + ticket.getTime()));
        panel.add(new JLabel("Theater: " + ticket.getTheater()));
        panel.add(new JLabel("Seats: " + ticket.getSeats()));
        panel.add(new JLabel("Ticket Type: " + ticket.getTicketType()));
        panel.add(new JLabel("Discounts: " + ticket.getDiscounts()));

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Home");
        JButton printButton = new JButton("Print");
        backButton.addActionListener(e -> {
            this.dispose();
            new LandingPanel();
        });
        printButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Ticket is printed", "Print Ticket", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(backButton);
        buttonPanel.add(printButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}