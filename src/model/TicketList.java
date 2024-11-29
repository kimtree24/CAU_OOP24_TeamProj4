package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// 전체 티켓 리스트 DB 관리
public class TicketList {
    private final List<Ticket> tickets = new ArrayList<>();
    private static final String TICKET_DB_PATH = "DB/TicketList.txt";

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        saveTicketToFile(ticket);
    }

    // 티켓 정보 저장
    private void saveTicketToFile(Ticket ticket) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TICKET_DB_PATH, true))) {
            writer.write(ticket.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}