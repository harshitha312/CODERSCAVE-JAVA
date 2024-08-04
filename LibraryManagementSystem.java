import java.time.LocalDate;
import java.util.*;
public class LibraryManagementSystem {
    static class Book {
        private String bookId;
        private String title;
        private String author;
        private boolean isAvailable;
        public Book(String bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.isAvailable = true;
        }
        public String getBookId() {
            return bookId;
        }
        public void setBookId(String bookId) {
            this.bookId = bookId;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getAuthor() {
            return author;
        }
        public void setAuthor(String author) {
            this.author = author;
        }
        public boolean isAvailable() {
            return isAvailable;
        }
        public void setAvailable(boolean available) {
            isAvailable = available;
        }
        @Override
        public String toString() {
            return "Book{" +
                    "bookId='" + bookId + '\'' +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", isAvailable=" + isAvailable +
                    '}';
        }
    }
    static class User {
        private String userId;
        private String name;
        private String email;
        public User(String userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
        }
        public String getUserId() {
            return userId;
        }
        public void setUserId(String userId) {
            this.userId = userId;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        @Override
        public String toString() {
            return "User{" +
                    "userId='" + userId + '\'' +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
    static class Transaction {
        private User user;
        private Book book;
        private LocalDate borrowDate;
        private LocalDate returnDate;
        public Transaction(User user, Book book, LocalDate borrowDate) {
            this.user = user;
            this.book = book;
            this.borrowDate = borrowDate;
            this.returnDate = null;
        }
        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }
        public Book getBook() {
            return book;
        }
        public void setBook(Book book) {
            this.book = book;
        }
        public LocalDate getBorrowDate() {
            return borrowDate;
        }
        public void setBorrowDate(LocalDate borrowDate) {
            this.borrowDate = borrowDate;
        }
        public LocalDate getReturnDate() {
            return returnDate;
        }
        public void setReturnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
        }
        @Override
        public String toString() {
            return "Transaction{" +
                    "user=" + user +
                    ", book=" + book +
                    ", borrowDate=" + borrowDate +
                    ", returnDate=" + returnDate +
                    '}';
        }
    }
    static class Library {
        private Map<String, Book> books = new HashMap<>();
        private Map<String, User> users = new HashMap<>();
        private List<Transaction> transactions = new ArrayList<>();
        public void addBook(Book book) {
            books.put(book.getBookId(), book);
        }
        public void removeBook(String bookId) {
            books.remove(bookId);
        }
        public void addUser(User user) {
            users.put(user.getUserId(), user);
        }
        public void removeUser(String userId) {
            users.remove(userId);
        }
        public void borrowBook(String userId, String bookId, LocalDate borrowDate) {
            User user = users.get(userId);
            Book book = books.get(bookId);
            if (user == null || book == null || !book.isAvailable()) {
                System.out.println("Borrowing failed.");
                return;
            }
            book.setAvailable(false);
            transactions.add(new Transaction(user, book, borrowDate));
            System.out.println("Book borrowed successfully.");
        }
        public void returnBook(String userId, String bookId, LocalDate returnDate) {
            for (Transaction transaction : transactions) {
                if (transaction.getUser().getUserId().equals(userId) &&
                    transaction.getBook().getBookId().equals(bookId) &&
                    transaction.getReturnDate() == null) 
                    {
                        transaction.setReturnDate(returnDate);
                        Book book = transaction.getBook();
                        book.setAvailable(true);
                        System.out.println("Book returned successfully.");
                        return;
                    }
            }
            System.out.println("Return failed.");
        }
        public void listBooks() {
            books.values().forEach(System.out::println);
        }
        public void listUsers() {
            users.values().forEach(System.out::println);
        }
        public void listTransactions() {
            transactions.forEach(System.out::println);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Add User");
            System.out.println("4. Remove User");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. List Books");
            System.out.println("8. List Users");
            System.out.println("9. List Transactions");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    break;
                case 2:
                    System.out.print("Enter Book ID to Remove: ");
                    bookId = scanner.nextLine();
                    library.removeBook(bookId);
                    break;
                case 3:
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    library.addUser(new User(userId, name, email));
                    break;
                case 4:
                    System.out.print("Enter User ID to Remove: ");
                    userId = scanner.nextLine();
                    library.removeUser(userId);
                    break;
                case 5:
                    System.out.print("Enter User ID: ");
                    userId = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter Borrow Date (YYYY-MM-DD): ");
                    LocalDate borrowDate = LocalDate.parse(scanner.nextLine());
                    library.borrowBook(userId, bookId, borrowDate);
                    break;
                case 6:
                    System.out.print("Enter User ID: ");
                    userId = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter Return Date (YYYY-MM-DD): ");
                    LocalDate returnDate = LocalDate.parse(scanner.nextLine());
                    library.returnBook(userId, bookId, returnDate);
                    break;
                case 7:
                    library.listBooks();
                    break;
                case 8:
                    library.listUsers();
                    break;
                case 9:
                    library.listTransactions();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
