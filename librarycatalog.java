import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}

public class librarycatalog {
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Library Catalog System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Search by Title");
            System.out.println("3. Search by Author");
            System.out.println("4. List All Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1: addBook(); break;
                case 2: searchByTitle(); break;
                case 3: searchByAuthor(); break;
                case 4: listBooks(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added successfully.");
    }

    static void searchByTitle() {
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.println("Found: " + book.title + " by " + book.author);
                found = true;
            }
        }
        if (!found) System.out.println("No book found with that title.");
    }

    static void searchByAuthor() {
        System.out.print("Enter author to search: ");
        String author = scanner.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.author.equalsIgnoreCase(author)) {
                System.out.println("Found: " + book.title + " by " + book.author);
                found = true;
            }
        }
        if (!found) System.out.println("No book found by that author.");
    }

    static void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in catalog.");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                System.out.println("- " + book.title + " by " + book.author);
            }
        }
    }
}