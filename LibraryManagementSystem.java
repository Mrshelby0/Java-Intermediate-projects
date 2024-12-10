import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    // Book Class
    static class Book {
        private int id;
        private String title;
        private String author;
        private boolean isBorrowed;

        public Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isBorrowed = false;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isBorrowed() {
            return isBorrowed;
        }

        public void borrowBook() {
            this.isBorrowed = true;
        }

        public void returnBook() {
            this.isBorrowed = false;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Title: " + title + ", Author: " + author + 
                   ", Status: " + (isBorrowed ? "Borrowed" : "Available");
        }
    }

    // Library Class
    static class Library {
        private ArrayList<Book> books;

        public Library() {
            books = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
            System.out.println("Book added: " + book.getTitle());
        }

        public void removeBook(int id) {
            books.removeIf(book -> book.getId() == id);
            System.out.println("Book removed.");
        }

        public void searchBook(String query) {
            System.out.println("Search results for: " + query);
            for (Book book : books) {
                if (book.getTitle().toLowerCase().contains(query.toLowerCase()) || 
                    book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                    System.out.println(book);
                }
            }
        }

        public void borrowBook(int id) {
            for (Book book : books) {
                if (book.getId() == id) {
                    if (!book.isBorrowed()) {
                        book.borrowBook();
                        System.out.println("Book borrowed: " + book.getTitle());
                    } else {
                        System.out.println("Book is already borrowed.");
                    }
                    return;
                }
            }
            System.out.println("Book not found.");
        }

        public void returnBook(int id) {
            for (Book book : books) {
                if (book.getId() == id) {
                    if (book.isBorrowed()) {
                        book.returnBook();
                        System.out.println("Book returned: " + book.getTitle());
                    } else {
                        System.out.println("Book was not borrowed.");
                    }
                    return;
                }
            }
            System.out.println("Book not found.");
        }

        public void listBooks() {
            System.out.println("Books in the library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. List Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(id, title, author));
                }
                case 2 -> {
                    System.out.print("Enter Book ID to remove: ");
                    int id = scanner.nextInt();
                    library.removeBook(id);
                }
                case 3 -> {
                    System.out.print("Enter title or author to search: ");
                    String query = scanner.nextLine();
                    library.searchBook(query);
                }
                case 4 -> {
                    System.out.print("Enter Book ID to borrow: ");
                    int id = scanner.nextInt();
                    library.borrowBook(id);
                }
                case 5 -> {
                    System.out.print("Enter Book ID to return: ");
                    int id = scanner.nextInt();
                    library.returnBook(id);
                }
                case 6 -> library.listBooks();
                case 7 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
