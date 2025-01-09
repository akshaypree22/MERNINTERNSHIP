import csv
from copy import deepcopy

class Book:
    def __init__(self, title, author, isbn):
        self.title = title
        self.author = author
        self.isbn = isbn
        self.is_available = True

    def __str__(self):
        return f"Title: {self.title}, Author: {self.author}, ISBN: {self.isbn}, Available: {self.is_available}"

class User:
    def __init__(self, user_id, name):
        self.user_id = user_id
        self.name = name
        self.borrowed_books = []

    def borrow_book(self, book):
        if book.is_available:
            copied_book = deepcopy(book)
            self.borrowed_books.append(copied_book)
            book.is_available = False
            print(f"{self.name} has borrowed '{book.title}' (copy).")
        else:
            print(f"Sorry, '{book.title}' is not available.")

    def return_book(self, book):
        original_book = next((b for b in self.borrowed_books if b.isbn == book.isbn), None)
        if original_book:
            self.borrowed_books.remove(original_book)
            book.is_available = True
            print(f"{self.name} has returned '{book.title}' (copy).")
        else:
            print(f"{self.name} did not borrow '{book.title}'.")

    def __str__(self):
        return f"User ID: {self.user_id}, Name: {self.name}, Borrowed Books: {[book.title for book in self.borrowed_books]}"

class Library:
    def __init__(self):
        self.books = []
        self.users = []
        self.total_books_borrowed = 0
        self.total_books_returned = 0
        self.load_books()

    def load_books(self):
        try:
            with open('books.csv', mode='r', newline='') as file:
                reader = csv.reader(file)
                for row in reader:
                    title, author, isbn, is_available = row
                    book = Book(title, author, isbn)
                    book.is_available = is_available == 'True'
                    self.books.append(book)
            print("Books loaded from file.")
        except FileNotFoundError:
            print("Books file not found, starting with an empty library.")

    def save_books(self):
        with open('books.csv', mode='w', newline='') as file:
            writer = csv.writer(file)
            for book in self.books:
                writer.writerow([book.title, book.author, book.isbn, book.is_available])
        print("Books saved to file.")

    def add_book(self, book):
        self.books.append(book)
        self.save_books()
        print(f"Book '{book.title}' added to the library.")

    def remove_book(self, isbn):
        book = self.find_book(isbn)
        if book:
            self.books.remove(book)
            self.save_books()
            print(f"Book '{book.title}' removed from the library.")
        else:
            print(f"Book with ISBN {isbn} not found.")

    def find_book(self, isbn):
        for book in self.books:
            if book.isbn == isbn:
                return book
        print(f"Book with ISBN {isbn} not found.")
        return None

    def register_user(self, user):
        self.users.append(user)
        print(f"User '{user.name}' registered.")

    def get_user(self, user_id):
        for user in self.users:
            if user.user_id == user_id:
                return user
        return None

    def user_borrow_book(self, user_id, isbn):
        user = self.get_user(user_id)
        if not user:
            print(f"User with ID {user_id} is not registered.")
            name = input(f"Enter name for new user with ID {user_id}: ")
            user = User(user_id, name)
            self.register_user(user)
        book = self.find_book(isbn)
        if book:
            if book.is_available:
                user.borrow_book(book)
                self.total_books_borrowed += 1
                self.save_books()
            else:
                print(f"Sorry, '{book.title}' is not available.")
        else:
            print(f"Book with ISBN {isbn} not found.")

    def user_return_book(self, user_id, isbn):
        user = self.get_user(user_id)
        if not user:
            print(f"User with ID {user_id} is not registered.")
            name = input(f"Enter name for new user with ID {user_id}: ")
            user = User(user_id, name)
            self.register_user(user)
        book = self.find_book(isbn)
        if book:
            if not book.is_available:
                user.return_book(book)
                self.total_books_returned += 1
                self.save_books()
            else:
                print(f"The book '{book.title}' was not borrowed.")
        else:
            print(f"Book with ISBN {isbn} not found.")

    def __str__(self):
        available_books = sum(1 for book in self.books if book.is_available)
        return (f"Library contains {len(self.books)} books ({available_books} available):\n" +
                "\n".join([str(book) for book in self.books]) +
                f"\n\nLibrary has {len(self.users)} users:\n" +
                "\n".join([str(user) for user in self.users]) +
                f"\n\nTotal books borrowed: {self.total_books_borrowed}\n" +
                f"Total books returned: {self.total_books_returned}")

def main():
    library = Library()
    
    while True:
        print("\nLibrary Menu:")
        print("1. Add a book")
        print("2. Remove a book")
        print("3. Register a user")
        print("4. Borrow a book")
        print("5. Return a book")
        print("6. Display library status")
        print("7. Exit")

        choice = input("Enter your choice: ")

        if choice == "1":
            title = input("Enter book title: ")
            author = input("Enter book author: ")
            isbn = input("Enter book ISBN: ")
            book = Book(title, author, isbn)
            library.add_book(book)
        elif choice == "2":
            isbn = input("Enter ISBN of the book to remove: ")
            library.remove_book(isbn)
        elif choice == "3":
            user_id = input("Enter user ID: ")
            name = input("Enter user name: ")
            user = User(user_id, name)
            library.register_user(user)/y4
        elif choice == "4":
            user_id = input("Enter user ID: ")
            isbn = input("Enter ISBN of the book to borrow: ")
            library.user_borrow_book(user_id, isbn)
        elif choice == "5":;gwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
            user_id = input("Enter user ID: ")
            isbn = input("Enter ISBN of the book to return: ")
            library.user_return_book(user_id, isbn)
        elif choice == "6":
            print(library)
        elif choice == "7":
            print("Exiting the library system.")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
    y+ccccc0[\4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaojkgt3]