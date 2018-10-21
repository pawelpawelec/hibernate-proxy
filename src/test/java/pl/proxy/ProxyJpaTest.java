package pl.proxy;

import org.hibernate.LazyInitializationException;
import org.hibernate.proxy.HibernateProxy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import pl.domain.Author;
import pl.domain.AuthorRepository;
import pl.domain.Book;
import pl.domain.BookRepository;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = "insertData.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "deleteData.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProxyJpaTest {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @Test(expected = LazyInitializationException.class)
  public void testProxyBookLazyException() {
    Author author = authorRepository.findBook(1L);
    Assert.assertNotNull(author);
    Assert.assertNotNull(author.getBook());
    Assert.assertTrue(author.getBook() instanceof HibernateProxy);
    author.getBook().getTittle();
  }

  @Test
  public void testProxyBookFetchJoin() {
    Author author = authorRepository.findFetchBook(1L);
    Assert.assertNotNull(author);
    Assert.assertNotNull(author.getBook());
    author.getBook().getTittle();
  }

  @Test(expected = LazyInitializationException.class)
  public void testProxyAuthors() {
     bookRepository.findById(1L).map(Book::getAuthors).map(Collection::size);
  }
}
