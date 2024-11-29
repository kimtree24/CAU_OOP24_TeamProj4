package view;

import controller.PaymentController;
import model.Movie;
import model.TimeTableList;

import javax.swing.*;
import java.awt.*;

// 할인 및 티켓 고르는 화면
public class PaymentPanel extends JFrame {
    private PaymentController controller;

    private final JComboBox<String> ticketTypeComboBox;
    private final JCheckBox militaryDiscountCheckBox;
    private final JCheckBox seniorDiscountCheckBox;
    private final JCheckBox otherDiscountCheckBox;
    private final JLabel totalPriceLabel;
    private final JButton confirmButton;

    public PaymentPanel(Movie selectedMovie, TimeTableList.TimeTableEntry selectedTime, String selectedSeats) {
        // 기본 프레임 설정
        setTitle("Payment Options");
        setSize(400, 300);
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        // UI 구성 요소
        add(new JLabel("Ticket Type:"));
        ticketTypeComboBox = new JComboBox<>(new String[]{"adult", "teen", "child"});
        add(ticketTypeComboBox);

        add(new JLabel("Military discount (30%):"));
        militaryDiscountCheckBox = new JCheckBox();
        add(militaryDiscountCheckBox);

        add(new JLabel("Senior discount (30%):"));
        seniorDiscountCheckBox = new JCheckBox();
        add(seniorDiscountCheckBox);

        add(new JLabel("ETC discount (10%):"));
        otherDiscountCheckBox = new JCheckBox();
        add(otherDiscountCheckBox);

        add(new JLabel("")); // 빈 라벨 추가
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> onCalculateButtonClicked());
        add(calculateButton);

        totalPriceLabel = new JLabel("Total price : 0₩");
        add(totalPriceLabel);

        // Confirm 버튼 추가
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> onConfirmButtonClicked());
        add(confirmButton);

        setVisible(true);
    }

    public void setController(PaymentController controller) {
        this.controller = controller;
    }

    private void onCalculateButtonClicked() {
        String ticketType = (String) ticketTypeComboBox.getSelectedItem();
        boolean militaryDiscount = militaryDiscountCheckBox.isSelected();
        boolean seniorDiscount = seniorDiscountCheckBox.isSelected();
        boolean otherDiscount = otherDiscountCheckBox.isSelected();

        if (controller != null) {
            controller.calculatePayment(ticketType, militaryDiscount, seniorDiscount, otherDiscount);
        }
    }

    private void onConfirmButtonClicked() {
        String ticketType = (String) ticketTypeComboBox.getSelectedItem();
        boolean militaryDiscount = militaryDiscountCheckBox.isSelected();
        boolean seniorDiscount = seniorDiscountCheckBox.isSelected();
        boolean otherDiscount = otherDiscountCheckBox.isSelected();
        int totalPrice = Integer.parseInt(totalPriceLabel.getText().replaceAll("[^0-9]", ""));
        if (controller != null) {
            controller.onConfirmPayment(ticketType, militaryDiscount, seniorDiscount, otherDiscount, totalPrice);
        }
    }

    public void displayTotalPrice(int totalPrice) {
        totalPriceLabel.setText("Total price : " + totalPrice + "₩");
    }

}