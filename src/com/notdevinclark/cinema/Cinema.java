package com.notdevinclark.cinema;

public class Cinema {
    public static void main(String[] args) {
        Theatre theatre = new Theatre(InputUtils.inputRowCount(), InputUtils.inputSeatCount());

        boolean keepGoing = true;

        while (keepGoing) {
            switch (InputUtils.inputMenuOption()) {
                case 1 -> theatre.printSeatLayout();
                case 2 -> {
                    boolean validInput;
                    do {
                        try {
                            int specificRow = InputUtils.inputRowNumber();
                            int specificSeat = InputUtils.inputSeatNumber();

                            validInput = theatre.seatLocationValid(specificRow, specificSeat);
                            theatre.reserveSeat(specificRow, specificSeat);
                        } catch (IllegalArgumentException e) {
                            System.out.printf("%s%n%n", e.getMessage());
                            validInput = false;
                        }
                    } while (validInput == false);
                }
                case 3 -> theatre.printStatistics();
                default -> keepGoing = false;
            }
        }
    }
}
