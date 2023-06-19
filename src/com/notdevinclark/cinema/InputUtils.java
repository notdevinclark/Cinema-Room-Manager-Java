package com.notdevinclark.cinema;

import java.util.Scanner;

interface InputUtils {
    Scanner scanner = new Scanner(System.in);

    static int inputRowCount() {
        System.out.println("Enter the number of rows: ");
        return scanner.nextInt();
    }

    static int inputSeatCount() {
        System.out.println("Enter the number of seats in each row: ");
        return scanner.nextInt();
    }

    static int inputRowNumber() {
        System.out.println("Enter a row number: ");
        return scanner.nextInt();
    }

    static int inputSeatNumber() {
        System.out.println("Enter a seat number in that row: ");
        return scanner.nextInt();
    }

    static int inputMenuOption() {
        String prompt = """
            
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
            """;

        System.out.println(prompt);
        return scanner.nextInt();
    }
}
