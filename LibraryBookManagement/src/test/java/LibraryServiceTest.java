import org.example.Author;
import org.example.Book;
import org.example.LibraryService;
import org.example.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {
    private LibraryService libraryService;
    private Reader testReader;
    private Book testBook;
    private Author testAuthor;

    @BeforeEach
    public void initTest() {
        libraryService = new LibraryService();
        testAuthor = new Author(1, "Шевченко", "Тарас", "Григорович");
        testBook = new Book(1, "Кобзар", "«Кобзар» — це фундаментальна збірка поетичних творів " +
                "Тараса Шевченка, яка вважається національною святинею та духовним символом України. Книга відіграла " +
                "ключову роль у формуванні української національної свідомості, відродженні мови та становленні нової " +
                "української літератури." ,testAuthor);
        testReader = new Reader(1, "Осієвська", "Олександра", "Сергіївна");
        libraryService.addBook(testBook);
        libraryService.addReader(testReader);
    }

    @Test
    void testBorrowBook_shouldMarkBookAsUnavailable() {
        libraryService.borrowBook(testReader.getId(), testBook.getId());
        assertFalse(testBook.isAvailable());
        assertEquals(1, testReader.getBorrowedBooks().size());

    }

    @Test
    void testBorrowBook_shouldAddBookToReader() {
        libraryService.borrowBook(testReader.getId(), testBook.getId());
        assertTrue(testReader.getBorrowedBooks().contains(testBook));
    }

    @Test
    void testBorrowBook_shouldThrowWhenBookAlreadyBorrowed() {
        libraryService.borrowBook(testReader.getId(), testBook.getId());
        assertThrows(IllegalArgumentException.class, () -> libraryService.borrowBook(testReader.getId(), testBook.getId()));
    }

    @Test
    void testBorrowBook_shouldThrowWhenBookNotFound() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.borrowBook(testReader.getId(), 2));
    }

    @Test
    void testBorrowBook_shouldThrowWhenReaderNotFound() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.borrowBook(2, testBook.getId()));
    }

    @Test
    void returnBook_shouldMarkBookAsAvailable() {
        libraryService.borrowBook(testReader.getId(), testBook.getId());
        libraryService.returnBook(testReader.getId(), testBook.getId());
        assertTrue(testBook.isAvailable());
    }

    @Test
    void returnBook_shouldThrowWhenReaderBookIdIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.returnBook(testReader.getId(),2));
    }

    @Test
    void returnBook_shouldThrowWhenReaderReaderIdIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.returnBook(2,testBook.getId()));
    }

    @Test
    void returnBook_shouldThrowWhenReaderDidNotBorrow() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.returnBook(testReader.getId(), testBook.getId()));
    }

    @Test
    void addBook_shouldThrowWhenBookIsNull() {
        assertThrows(NullPointerException.class, () -> libraryService.addBook(null));
    }

    @Test
    void addBook_shouldThrowWhenDuplicateId() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.addBook(testBook));
    }

    @Test
    void addReader_shouldThrowWhenReaderIsNull() {
        assertThrows(NullPointerException.class, () -> libraryService.addReader(null));
    }

    @Test
    void addReader_shouldThrowWhenDuplicateId() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.addReader(testReader));
    }

    @Test
    void bookConstructor_shouldThrowWhenIdIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Book(-100, "Давня казка", "«Давня казка» Лесі Українки — це ліро-епічна поема, " +
                "яка розкриває конфлікт між митцем-патріотом і корисливим можновладцем. Твір утверджує ідею свободи " +
                "творчості, викриває паразитичну сутність експлуататорів та доводить, що справжня сила поетичного слова" +
                " здатна надихати людей на боротьбу.", testAuthor));
    }

    @Test
    void bookConstructor_shouldThrowWhenTitleIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Book(2, "", "«Давня казка» Лесі Українки — це ліро-епічна поема, " +
                "яка розкриває конфлікт між митцем-патріотом і корисливим можновладцем. Твір утверджує ідею свободи " +
                "творчості, викриває паразитичну сутність експлуататорів та доводить, що справжня сила поетичного слова" +
                " здатна надихати людей на боротьбу.", null));
    }

    @Test
    void bookConstructor_shouldThrowWhenTitleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Book(2, null, "«Давня казка» Лесі Українки — це ліро-епічна поема, " +
                "яка розкриває конфлікт між митцем-патріотом і корисливим можновладцем. Твір утверджує ідею свободи " +
                "творчості, викриває паразитичну сутність експлуататорів та доводить, що справжня сила поетичного слова" +
                " здатна надихати людей на боротьбу.", null));
    }


    @Test
    void bookConstructor_shouldThrowWhenAuthorIsNull() {
        assertThrows(NullPointerException.class, () -> new Book(2, "Давня казка", "«Давня казка» Лесі Українки — це ліро-епічна поема, " +
                "яка розкриває конфлікт між митцем-патріотом і корисливим можновладцем. Твір утверджує ідею свободи " +
                "творчості, викриває паразитичну сутність експлуататорів та доводить, що справжня сила поетичного слова" +
                " здатна надихати людей на боротьбу.", null));
    }

    @Test
    void readerConstructor_shouldThrowWhenIdIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Reader(-1, "Смічик", "Олексій", "Олександрович"));
    }

    @Test
    void readerConstructor_shouldThrowWhenSurnameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Reader(2, "", "Олексій", "Олександрович"));
    }

    @Test
    void readerConstructor_shouldThrowWhenSurnameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Reader(2, null, "Олексій", "Олександрович"));
    }

    @Test
    void readerConstructor_shouldThrowWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Reader(2, "Смічик", "", "Олександрович"));
    }

    @Test
    void readerConstructor_shouldThrowWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Reader(2, "Смічик", null, "Олександрович"));
    }

    @Test
    void readerConstructor_shouldThrowWhenPatronymicIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Reader(2, "Смічик", "Олексій", ""));
    }

    @Test
    void readerConstructor_shouldThrowWhenPatronymicIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Reader(2, "Смічик", "Олексій", null));
    }

    @Test
    void readerConstructor_shouldProtectBorrowedBooksFromMutation() {
        assertThrows(UnsupportedOperationException.class, () -> testReader.getBorrowedBooks().add(testBook));
    }

    @Test
    void authorConstructor_shouldThrowWhenIdIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Author(-1, "Франко", "Іван", "Якович"));
    }

    @Test
    void authorConstructor_shouldThrowWhenSurnameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Author(-1, "", "Іван", "Якович"));
    }

    @Test
    void authorConstructor_shouldThrowWhenSurnameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Author(-1, null, "Іван", "Якович"));
    }

    @Test
    void authorConstructor_shouldThrowWhenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Author(-1, "Франко", "", "Якович"));
    }

    @Test
    void authorConstructor_shouldThrowWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Author(-1, "Франко", null, "Якович"));
    }

    @Test
    void authorConstructor_shouldThrowWhenPatronymicIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Author(-1, "Франко", "Іван", ""));
    }

    @Test
    void authorConstructor_shouldThrowWhenPatronymicIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Author(-1, "Франко", "Іван", null));
    }
}
