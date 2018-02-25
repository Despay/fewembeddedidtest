import com.entities.Book;
import com.services.IBookService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestInsert extends AbstractTest {

    @Autowired
    IBookService bookService;

    String nameInserted = "lol";
    String nameInserted2 = "lol2";
    String nameUpdate = "mdr";


    @Before
    public void setUp() throws Exception {
        bookService.save(new Book(nameInserted));
    }

    @After
    public void tearDown() throws Exception {
        List<Book> books = bookService.findAll();
        System.out.println("Books deleted : " + books);
        bookService.delete(books);
    }

    @Test
    public void updateTest() {
        Book b = bookService.findByName(nameInserted);
        b.setName("mdr");
        Book b2 = bookService.findByName(nameInserted);
        assertNotNull(b2);
        assertEquals(b2.getName(), nameInserted);
    }

    @Test
    public void renameBookWithoutExplicitSaveAndWithoutTransactionnalTest() {
        bookService.renameBookWithoutExplicitSaveAndWithoutTransactionnal(nameInserted, nameUpdate);
        Book b = bookService.findByName(nameInserted);
        Book b2 = bookService.findByName(nameUpdate);
        assertNotNull(b);
        assertNull(b2);
        assertEquals(b.getName(),nameInserted);
    }

    @Test
    public void renameBookWithoutExplicitSaveAndWithTransactionnalTest() {
        bookService.renameBookWithoutExplicitSaveAndWithTransactionnal(nameInserted, nameUpdate);
        Book b = bookService.findByName(nameInserted);
        Book b2 = bookService.findByName(nameUpdate);
        assertNotNull(b);
        assertNull(b2);
        assertEquals(b.getName(),nameInserted);
    }

    @Test
    public void saveThenRenameArgEntityDoesNotUpdate() {
        bookService.saveThenRenameArgEntity(new Book(nameInserted2), nameUpdate);
        assertNotNull(bookService.findByName(nameInserted2));
    }


    @Test
    public void saveThenRenameReturnedEntityDoesNotUpdate() {
        bookService.saveThenRenameReturnedEntity(new Book(nameInserted2), nameUpdate);
        assertNotNull(bookService.findByName(nameInserted2));
    }

    @Test
    public void saveThenRenameArgEntityWithTransactionDoesNotUpdate() {
        bookService.saveThenRenameArgEntityWithTransaction(new Book(nameInserted2), nameUpdate);
        assertNotNull(bookService.findByName(nameInserted2));
    }


    @Test
    public void saveThenRenameReturnedEntityWithTransactionDoesNotUpdate() {
        bookService.saveThenRenameReturnedEntityWithTransaction(new Book(nameInserted2), nameUpdate);
        assertNotNull(bookService.findByName(nameInserted2));
    }
}
