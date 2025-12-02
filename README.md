# Java-Swing-Project_-Book-List-

This project is a standalone desktop application developed in Java using the Swing library to efficiently collect, display, and manage a list of books and their categories.

Key Features & Functionality
GUI-Based List Management: A user-friendly desktop interface built with Java Swing for inputting and manipulating book records.

Data Structure: Uses a Vector<String> to store book entries, formatted as concatenated strings containing the Book Name and its Category.

Dynamic List Display: The stored data is dynamically displayed and updated in a JList within a JScrollPane, providing a live, scrolling view of the bibliography.

Input and Control:

Input includes a JTextField for the book name and a JComboBox offering over 15 common book categories.

Add New: Adds the current book name and selected category to the list.

Remove Item: Removes the selected book entry from the list (enabled via a ListSelectionListener).

Clear Items: Clears all entries from the list after user confirmation (JOptionPane).

Form Logic & Usability: Implements a KeyListener to enable the Add New button only when the book name field is non-empty, and allows record submission via the Enter key for efficiency.
