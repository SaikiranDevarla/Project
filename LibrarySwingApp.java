import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Author: " + author + " | ISBN: " + isbn + " | Available: " + isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Borrower {
    private String name;
    private String contactInfo;
    private int borrowerID;
    List<Book> borrowedBooks;

    public Borrower(String name, String contactInfo, int borrowerID) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowerID = borrowerID;
        this.borrowedBooks = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", borrowerID=" + borrowerID +
                '}';
    }
}

public class LibrarySwingApp {
    private JFrame frame;
    private List<Book> library;
    private List<Borrower> borrowers;

    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;
    private JList<String> displayList;
    private DefaultListModel<String> listModel;
    private JComboBox<Borrower> borrowerComboBox;

    public LibrarySwingApp() {
        library = new ArrayList<>();
        borrowers = new ArrayList<>();

        frame = new JFrame("Library Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        titleField = createTextField("Title: ", 20);
        authorField = createTextField("Author: ", 20);
        isbnField = createTextField("ISBN: ", 20);

        listModel = new DefaultListModel<>();
        displayList = new JList<>(listModel);
        displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        displayList.setVisibleRowCount(10);
        JScrollPane scrollPane = new JScrollPane(displayList);

        JButton addBookButton = createButton("Add Book");
        JButton displayBooksButton = createButton("Display Books");

        JLabel borrowerLabel = new JLabel("Select Borrower:");
        borrowerComboBox = new JComboBox<>();
        borrowerComboBox.addItem(new Borrower("John Doe", "john@example.com", 1));
        borrowerComboBox.addItem(new Borrower("Jane Smith", "jane@example.com", 2));

        JButton checkOutButton = createButton("Check Out");
        JButton checkInButton = createButton("Check In");

        addBookButton.addActionListener(e -> addBook());
        displayBooksButton.addActionListener(e -> displayBooks());
        checkOutButton.addActionListener(e -> checkOutBook());
        checkInButton.addActionListener(e -> checkInBook());

        panel.add(titleField);
        panel.add(authorField);
        panel.add(isbnField);
        panel.add(addBookButton);
        panel.add(displayBooksButton);
        panel.add(borrowerLabel);
        panel.add(borrowerComboBox);
        panel.add(checkOutButton);
        panel.add(checkInButton);
        panel.add(scrollPane);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JTextField createTextField(String label, int width) {
        JTextField textField = new JTextField(label, width);
        return textField;
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        return button;
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            showErrorMessage("Please fill in all fields.");
            return;
        }

        Book newBook = new Book(title, author, isbn);
        library.add(newBook);

        clearInputFields();
        showInfoMessage("Book added successfully!");
    }

    private void displayBooks() {
        if (library.isEmpty()) {
            showInfoMessage("Library is empty.");
        } else {
            listModel.clear();
            for (Book book : library) {
                listModel.addElement(book.toString());
            }
        }
    }

    private void checkOutBook() {
        Book selectedBook = getSelectedBook();
        Borrower selectedBorrower = (Borrower) borrowerComboBox.getSelectedItem();

        if (selectedBook == null) {
            showErrorMessage("Please select a book to check out.");
            return;
        }

        if (!selectedBook.isAvailable()) {
            showErrorMessage("Book is already checked out.");
            return;
        }

        selectedBook.setAvailable(false);
        selectedBorrower.borrowedBooks.add(selectedBook);

        displayBooks();
        showInfoMessage("Book checked out successfully!");
    }

    private void checkInBook() {
        Book selectedBook = getSelectedBook();
        Borrower selectedBorrower = (Borrower) borrowerComboBox.getSelectedItem();

        if (selectedBook == null) {
            showErrorMessage("Please select a book to check in.");
            return;
        }

        if (!selectedBorrower.borrowedBooks.contains(selectedBook)) {
            showErrorMessage("This book is not checked out to the selected borrower.");
            return;
        }

        selectedBook.setAvailable(true);
        selectedBorrower.borrowedBooks.remove(selectedBook);

        displayBooks();
        showInfoMessage("Book checked in successfully!");
    }

    private Book getSelectedBook() {
        int selectedIndex = displayList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < library.size()) {
            return library.get(selectedIndex);
        }
        return null;
    }

    private void clearInputFields() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySwingApp());
    }
}
