package model;

// 결제 관련 데이터 관리
public class Payment {
    private String ticketType;
    private boolean militaryDiscount;
    private boolean seniorDiscount;
    private boolean otherDiscount;

    private static final int ADULT_PRICE = 20000;
    private static final int TEEN_PRICE = 15000;
    private static final int CHILD_PRICE = 10000;

    public Payment(String ticketType, boolean militaryDiscount, boolean seniorDiscount, boolean otherDiscount) {
        this.ticketType = ticketType;
        this.militaryDiscount = militaryDiscount;
        this.seniorDiscount = seniorDiscount;
        this.otherDiscount = otherDiscount;
    }

    // 할인 적용
    public int calculateTotalPrice() {
        int basePrice = switch (ticketType) {
            case "adult" -> ADULT_PRICE;
            case "teen" -> TEEN_PRICE;
            case "child" -> CHILD_PRICE;
            default -> 0;
        };

        double discount = 0.0;

        // 성인 할인 적용
        if (ticketType.equals("adult")) {
            if (militaryDiscount) discount += 0.3;
            if (seniorDiscount) discount += 0.3;
        }

        // 모든 티켓에 적용 가능한 기타 할인
        if (otherDiscount) discount += 0.1;

        double finalPrice = basePrice * (1 - discount);
        return (int) finalPrice;
    }
}