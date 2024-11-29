package controller;

import model.Ticket;
import model.TicketList;
import view.PrintTicketPanel;

public class TicketController {
    private final TicketList ticketList;
    private final PrintTicketPanel view;

    public TicketController(PrintTicketPanel view) {
        this.ticketList = new TicketList();
        this.view = view;
        this.view.setController(this);
    }

    public void generateTicket(String posterPath, String title, String genre, String age, String runtime,
                               String date, String time, String theater, String seats,
                               String ticketType, String discounts) {
        // 고유 티켓 ID 생성
        String ticketId = "TICKET-" + System.currentTimeMillis();

        // 티켓 생성
        Ticket ticket = new Ticket(ticketId, posterPath, title, genre, age, runtime,
                date, time, theater, seats, ticketType, discounts);

        // 티켓 리스트에 추가 및 파일 저장
        ticketList.addTicket(ticket);

        // View에 티켓 정보 표시
        view.displayTicket(ticket);
    }
}