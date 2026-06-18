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

    @BeforeEach
    public void initTest() {
        libraryService = new LibraryService();
        Author testAuthor = new Author(1, "Шевченко", "Тарас", "Григорович");
        testBook = new Book(1, "Кобзар", "«Кобзар» — це фундаментальна збірка поетичних творів " +
                "Тараса Шевченка, яка вважається національною святинею та духовним символом України. Книга відіграла " +
                "ключову роль у формуванні української національної свідомості, відродженні мови та становленні нової " +
                "української літератури." ,testAuthor, true);
        testReader = new Reader(1, "Осієвська", "Олександра", "Cергіївна");
        libraryService.addBook(testBook);
        libraryService.addReader(testReader);
    }

    @Test
    void testBorrowBook() {
        libraryService.borrowBook(testReader.getId(), testBook.getId());

        assertFalse(testBook.isAvailable());
        assertTrue(testReader.getBorrowedBooks().contains(testBook));
        assertEquals(1, testReader.getBorrowedBooks().size());

        assertThrows(IllegalArgumentException.class, () -> libraryService.borrowBook(testReader.getId(), testBook.getId()));
    }

    @Test
    void testReturnBook() {
        libraryService.borrowBook(testReader.getId(), testBook.getId());
        libraryService.returnBook(testReader.getId(), testBook.getId());

        assertTrue(testBook.isAvailable());
        assertFalse(testReader.getBorrowedBooks().contains(testBook));
        assertThrows(IllegalArgumentException.class, () -> libraryService.returnBook(testReader.getId(), testBook.getId()));
    }
}
