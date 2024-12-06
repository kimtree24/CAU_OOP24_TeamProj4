package controller;

import view.TicketCheckPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TicketCheckController {
    private final TicketCheckPanel view; // View

    public TicketCheckController(TicketCheckPanel view) {
        this.view = view;
        this.view.setController(this); // View와 Controller 연결
    }

    // 티켓 번호 확인 로직
    public void checkTicket(String ticketNumber) {
        if (ticketNumber.isEmpty()) {
            view.showMessage("Please enter a ticket number.");
            return;
        }

        String[] ticketInfo = findTicketInfo(ticketNumber);

        if (ticketInfo != null) {
            view.updateTicketInfo(ticketInfo); // View에 정보 업데이트
        } else {
            view.showMessage("No ticket found for the given number. Please try again.");
        }
    }

    // 파일에서 티켓 정보 검색
    private String[] findTicketInfo(String ticketNumber) {
        String filePath = "DB/TicketList.txt"; // TicketList.txt의 상대 경로

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(","); // CSV 형식 분리
                if (fields.length > 0 && fields[0].equals(ticketNumber)) {
                    return fields; // 티켓 정보 배열 반환
                }
            }
        } catch (IOException e) {
            view.showMessage("Error reading the ticket file. Please try again later.");
        }

        return null;
    }

    // LandingPanel로 돌아가는 로직
    public void returnToHome() {
        view.dispose(); // 현재 창 닫기
        new view.LandingPanel(); // LandingPanel로 돌아감
    }
}
