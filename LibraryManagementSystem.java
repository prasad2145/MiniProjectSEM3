import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Book class
class Book {
    private int bookID;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(int bookID, String title, String author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public int getBookID() {
        return bookID;
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
        return "Book ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Borrowed: " + isBorrowed;
    }
}

// User class
class User {
    private String name;
    private int prn;
    private String branch;
    private int semester;
    private String phoneNumber;

    public User(String name, int prn, String branch, int semester, String phoneNumber) {
        this.name = name;
        this.prn = prn;
        this.branch = branch;
        this.semester = semester;
        this.phoneNumber = phoneNumber;
    }

    public int getPRN() {
        return prn;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public int getSemester() {
        return semester;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "PRN: " + prn + ", Name: " + name + ", Branch: " + branch + ", Semester: " + semester + ", Phone: " + phoneNumber;
    }
}

// Borrowing Record class
class BorrowingRecord {
    private User user;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public BorrowingRecord(User user, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "User: " + user.getName() + ", Book: " + book.getTitle() + ", Due Date: " + dueDate;
    }
}

// Library class
class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private ArrayList<BorrowingRecord> borrowingRecords;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        borrowingRecords = new ArrayList<>();
    }

    // Add book to library
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    // Add user to library
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully.");
    }

    // Search book by title
    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Search user by PRN
    public User searchUserByPRN(int prn) {
        for (User user : users) {
            if (user.getPRN() == prn) {
                return user;
            }
        }
        return null;
    }

    // Borrow book
    public void borrowBook(int prn, String bookTitle) {
        User user = searchUserByPRN(prn);
        Book book = searchBookByTitle(bookTitle);

        if (user != null && book != null && !book.isBorrowed()) {
            LocalDate borrowDate = LocalDate.now();
            LocalDate dueDate = borrowDate.plusDays(14); // 14-day due date
            book.borrowBook();
            borrowingRecords.add(new BorrowingRecord(user, book, borrowDate, dueDate));
            System.out.println("Book borrowed successfully. Due date: " + dueDate);
        } else {
            System.out.println("Borrowing failed. Either the user or book was not found, or the book is already borrowed.");
        }
    }

    // Return book
    public void returnBook(int prn, String bookTitle) {
        User user = searchUserByPRN(prn);
        Book book = searchBookByTitle(bookTitle);

        if (user != null && book != null && book.isBorrowed()) {
            book.returnBook();
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Return failed. Either the user or book was not found, or the book was not borrowed.");
        }
    }

    // Display all books
    public void displayBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Display all users
    public void displayUsers() {
        System.out.println("Users in the library:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    // Display borrowing records
    public void displayBorrowingRecords() {
        System.out.println("Borrowing Records:");
        for (BorrowingRecord record : borrowingRecords) {
            System.out.println(record);
        }
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Sample data
        library.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book(2, "1984", "George Orwell"));
        library.addUser(new User("Aditya Jadhav", 12345, "CSE(AIML)", 3, "9876543210"));
        library.addUser(new User("Prasad Danole", 12346, "CSE(AIML)", 3, "9876573210"));
        library.addUser(new User("Rakesh Naik", 12347, "CSE(AIML)", 3, "9876543211"));
        library.addUser(new User("Aniruddh Latawade", 12348, "CSE(AIML)", 3, "9877543210"));

        // Menu
        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Display Users");
            System.out.println("7. Display Borrowing Records");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    int bookID;
                    while (true) {
                        if (scanner.hasNextInt()) { // Check if the input is an integer
                            bookID = scanner.nextInt();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a numeric value for book ID.");
                            scanner.next(); // Clear the invalid input
                        }
                    }
                    scanner.nextLine();  // Consume newline left-over
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookID, bookTitle, author));
                    break;

                case 2:
                    System.out.print("Enter user name: ");
                    scanner.nextLine(); // Consume the newline left-over
                    String userName = scanner.nextLine(); // Use nextLine() to allow spaces
                    System.out.print("Enter PRN: ");
                    int prn = scanner.nextInt();
                    System.out.print("Enter branch: ");
                    scanner.nextLine(); // Consume newline left-over
                    String branch = scanner.nextLine();
                    System.out.print("Enter semester: ");
                    int semester = scanner.nextInt();
                    System.out.print("Enter phone number: ");
                    scanner.nextLine(); // Consume newline left-over
                    String phoneNumber = scanner.nextLine();
                    library.addUser(new User(userName, prn, branch, semester, phoneNumber));
                    break;

                case 3:
                    System.out.print("Enter user PRN: ");
                    prn = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter book title: ");
                    bookTitle = scanner.nextLine();
                    library.borrowBook(prn, bookTitle);
                    break;
                case 4:
                    System.out.print("Enter user PRN: ");
                    prn = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter book title: ");
                    bookTitle = scanner.nextLine();
                    library.returnBook(prn, bookTitle);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    library.displayUsers();
                    break;
                case 7:
                    library.displayBorrowingRecords();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
