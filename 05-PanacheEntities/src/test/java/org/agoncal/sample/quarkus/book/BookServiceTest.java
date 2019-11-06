package org.agoncal.sample.quarkus.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@QuarkusTest
public class BookServiceTest {

  @Inject
  private BookService bookService;

  // ======================================
  // =              Methods               =
  // ======================================

  @Test
  public void shouldCreateABook() throws Exception {

    // tag::shouldCreateABook[]
    Book book = new Book().title("Java EE 7").price(23.5F).isbn("1-84023-742-2").nbOfPages(354);

    book = bookService.createBook(book);
    assertNotNull(book.id, "Id should not be null");

    List<Book> allBooks = bookService.findAllBooks();
    assertTrue(allBooks.size() >= 1);
    // end::shouldCreateABook[]
  }

    @Test
    public void shouldCreateABookWithTagsAndChapters() throws Exception {

        Book book = new Book().title("Java EE 7").price(23.5F).isbn("1-84023-742-4").nbOfPages(354);
        book.tag("java ee").tag("java").tag("enterprise");

        // Persists the book to the database
        bookService.createBook(book);

        // Checks the book
        Book foundBook = bookService.findBook(book.id);
        assertEquals(3, foundBook.tags.size());
    }

    @Test
  public void shouldCreateH2G2Book() throws Exception {

    // Creates an instance of book
    Book book = new Book().title("H2G2").price(12.5F).description("The Hitchhiker's Guide to the Galaxy").isbn("1-84023-742-3").nbOfPages(354);

    // Persists the book to the database
    bookService.createBook( book);
    Assertions.assertNotNull(book.id, "Id should not be null");

    // Retrieves all the books from the database
    List<Book> allBooks = bookService.findAllBooks();

    // Retrieves all the H2G2 books from the database
    List<Book> h2g2Books = bookService.findAllH2G2Books();
    assertEquals(1, h2g2Books.size());

    assertTrue(allBooks.size() >= h2g2Books.size());
  }
}
