package com.pluralsight;

import com.pluralsight.Book;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Book[] books = new Book[6];
        books[0] = new Book(1, "978-0000000001", "\"Side Pieces\" (Spider-Man/Deadpool #1.MU, 6-7, 11-12)");
        books[1] = new Book(2, "978-0000000002", "Friendly Neighborhood Spider-Man #17–23 \"Back in Black\"");
        books[2] = new Book(3, "978-0000000003", "Batman Beyond (6th Series) 31 Comic Book NM");
        books[3] = new Book(4, "978-0000000004", "The Infinity Gauntlet (1991) by Jim Starlin, George Pérez, and Ron Lim");
        books[4] = new Book(5, "978-0000000005", "Avengers: The Kang Dynasty (2001) by Kurt Busiek and Alan Davis");
        books[5] = new Book(6, "978-0000000006", "Crisis on Infinite Earths (Marv Wolfman & George Pérez)");

        boolean running = true;

        while (running) {
            System.out.println("\n--- Comic Book Store ---");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("\n--- Available Books ---");
                    boolean hasAvailable = false;
                    for (Book b : books) {
                        if (!b.isCheckedOut()) {
                            System.out.println("ID: " + b.getId() + " | ISBN: " + b.getIsbn() + " | Title: " + b.getTitle());
                            hasAvailable = true;
                        }
                    }

                    if (!hasAvailable) {
                        System.out.println("No books available.");
                        break;
                    }

                    System.out.print("Enter book IDs to check out (comma-separated) or X to go back: ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("X")) break;

                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();

                    String[] idsToCheckOut = input.split(",");
                    for (String idStr : idsToCheckOut) {
                        try {
                            int id = Integer.parseInt(idStr.trim());
                            Book selected = findBookById(books, id);
                            if (selected != null && !selected.isCheckedOut()) {
                                selected.checkOut(name);
                                System.out.println("Checked out book ID " + id + " successfully.");
                            } else {
                                System.out.println("Book ID " + id + " is invalid or already checked out.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input: " + idStr);
                        }
                    }
                    break;

                case "2":
                    System.out.println("\n--- Checked Out Books ---");
                    boolean anyOut = false;
                    for (Book b : books) {
                        if (b.isCheckedOut()) {
                            System.out.println("ID: " + b.getId() + " | ISBN: " + b.getIsbn() + " | Title: " + b.getTitle() + " | Checked out to: " + b.getCheckedOutTo());
                            anyOut = true;
                        }
                    }

                    if (!anyOut) {
                        System.out.println("No books currently checked out.");
                        break;
                    }

                    System.out.print("Enter C to check in books or X to return to menu: ");
                    String inChoice = scanner.nextLine();
                    if (inChoice.equalsIgnoreCase("X")) break;

                    if (inChoice.equalsIgnoreCase("C")) {
                        System.out.print("Enter book IDs to check in (comma-separated): ");
                        String returnInput = scanner.nextLine();
                        String[] idsToReturn = returnInput.split(",");

                        for (String idStr : idsToReturn) {
                            try {
                                int id = Integer.parseInt(idStr.trim());
                                Book checkInBook = findBookById(books, id);
                                if (checkInBook != null && checkInBook.isCheckedOut()) {
                                    checkInBook.checkIn();
                                    System.out.println("Checked in book ID " + id + " successfully.");
                                } else {
                                    System.out.println("Book ID " + id + " is invalid or not checked out.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input: " + idStr);
                            }
                        }
                    }
                    break;

                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    public static Book findBookById(Book[] books, int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }
}
