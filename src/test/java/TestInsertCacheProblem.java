import com.entities.Author;
import com.entities.Book;
import com.services.IAuthorService;
import com.services.IBookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestInsertCacheProblem extends AbstractTest {

    @Autowired
    IBookService bookService;

    @Autowired
    IAuthorService authorService;

    @Before
    public void before() {
        authorService.deleteAll();
        bookService.deleteAll();
    }

    @Test
    public void insertTwoTest() {
        long initialCount = bookService.count();
        System.out.println("Start count : " + initialCount);
        bookService.save(new Book("lol"));
        bookService.save(new Book("lol2"));
        System.out.println("Final count : " + bookService.count());
        assertEquals(initialCount + 2, bookService.count());

    }

    @Test
    public void insertTwoInSameTr() {
        long initialCount = bookService.count();
        System.out.println("Start count : " + initialCount);
        bookService.saveTwoBooksWithNames("aze1", "aze2");
        System.out.println("Final count : " + bookService.count());
        assertEquals(initialCount + 2, bookService.count());
    }

    @Test
    public void insertTwoInSameTrWithAuthor() {
        authorService.deleteAll();
        bookService.deleteAll();
        String nameAuthor = "Auteurrrr";
        Author author = authorService.save(new Author(nameAuthor));
        authorService.save(author);

        long initialCount = bookService.count();
        System.out.println("Start count : " + initialCount);
        bookService.saveTwoBooksWithNamesWithAuthorWithName("aze1", "aze2",nameAuthor);
        System.out.println("Final count : " + bookService.count());
        assertEquals(initialCount + 2, bookService.count());
    }

    @Test
    public void updateTwoPreviouslyInsertedRecords() {
        insertTwoInSameTrWithAuthor();
        String nameAuthor = "Updated";
        Author author = authorService.save(new Author(nameAuthor));
        bookService.updateBookAuthorName("aze1", nameAuthor);
        assertEquals(bookService.findByName("aze1").getAuthor().getName(), nameAuthor);
    }

    @Test
    public void saveAuthorAndBooks() {
        String nameAuthor = "authorTest";
        String b1 = "b1", b2 = "b2";
        bookService.saveAuthorAndBooks(nameAuthor, b1,b2);
        assertNotNull(authorService.getByName(nameAuthor));
        assertNotNull(bookService.findByName(b1).getAuthor());
        assertEquals(bookService.findByName(b1).getAuthor().getName(), nameAuthor);
        assertNotNull(bookService.findByName(b2).getAuthor());
        assertEquals(bookService.findByName(b2).getAuthor().getName(), nameAuthor);
    }

}
