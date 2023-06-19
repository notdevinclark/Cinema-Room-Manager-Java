package com.notdevinclark.cinema;

import java.util.Arrays;

public class Theatre {
    private final int rows;
    private final int seatsPerRow;
    private final int totalSeats;
    private final char[][] layout;

    static final int MAX_SEATS = 60;
    static final int MAX_PRICE = 10;
    static final int LOWER_PRICE = 8;
    static final char EMPTY_SEAT_CHAR = 'S';
    static final char PURCHASED_SEAT_CHAR = 'B';

    public Theatre(int rows, int seatsPerRow) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Number of rows should be greater than or equal to 1");
        }

        if (seatsPerRow <= 0) {
            throw new IllegalArgumentException("Number of seats per row should be greater than or equal to 1");
        }

        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.totalSeats = rows * seatsPerRow;
        this.layout = new char[rows][seatsPerRow];
        for (int index = 0; index < rows; index++) {
            Arrays.fill(layout[index], EMPTY_SEAT_CHAR);
        }
    }

    public int profit() {
        if (totalSeats > MAX_SEATS) {
            int frontHalf = rows / 2;
            int backHalf = rows - frontHalf;
            return (frontHalf * seatsPerRow * MAX_PRICE) + (backHalf * seatsPerRow * LOWER_PRICE);
        }

        return totalSeats * MAX_PRICE;
    }

    public int priceForSeat(int specificRow) {
        if (totalSeats > MAX_SEATS) {
            int frontHalf = rows / 2;
            if (specificRow > frontHalf) {
                return LOWER_PRICE;
            }
        }

        return MAX_PRICE;
    }

    public void reserveSeat(int specificRow, int specificSeat) {
        int rowAdjustedForArrayIndex = specificRow - 1;
        int seatAdjustedForArrayIndex = specificSeat - 1;
        layout[rowAdjustedForArrayIndex][seatAdjustedForArrayIndex] = PURCHASED_SEAT_CHAR;
        System.out.printf("Ticket price: $%d%n", priceForSeat(specificRow));
    }

    public void printSeatLayout() {
        System.out.println("Cinema:");

        for (int i = 0; i <= rows; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j <= seatsPerRow; j++) {
                if (i == 0 && j == 0) {
                    row.append("  ");
                } else if (i == 0) {
                    row.append(String.format("%d ", j));
                } else if (j == 0) {
                    row.append(String.format("%d ", i));
                } else {
                    row.append(String.format("%c ", layout[i - 1][j - 1]));
                }
            }

            System.out.println(row);
        }
    }

    public void printStatistics() {
        System.out.printf("Number of purchased tickets: %d%n", numberOfPurchasedSeats());
        System.out.printf("Percentage: %.2f%%%n", percentageOfSoldSeats());
        System.out.printf("Current income: $%d%n", currentIncomeFromSales());
        System.out.printf("Total income: $%d%n", profit());
    }

    public boolean seatLocationValid(int rowInput, int seatNumberInput) {
        if ((rowInput < 1 || rowInput > rows) || (seatNumberInput < 1 || seatNumberInput > seatsPerRow)) {
            throw new IllegalArgumentException("Wrong input!");
        }

        if (layout[rowInput - 1][seatNumberInput - 1] == PURCHASED_SEAT_CHAR) {
            throw new IllegalArgumentException("That ticket has already been purchased");
        }

        return true;
    }

    private int numberOfPurchasedSeats() {
        int purchasedSeatCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                if (layout[i][j] == 'B') {
                    purchasedSeatCount++;
                }
            }
        }
        return purchasedSeatCount;
    }

    private float percentageOfSoldSeats() {
        return (float) (((float) numberOfPurchasedSeats() / totalSeats) * 100.00);
    }

    private int currentIncomeFromSales() {
        int purchasedSeatIncome = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                if (layout[i][j] == 'B') {
                    purchasedSeatIncome += priceForSeat(i + 1); // priceForSeat need adjustment since this number is from an array
                }
            }
        }
        return purchasedSeatIncome;
    }
}
