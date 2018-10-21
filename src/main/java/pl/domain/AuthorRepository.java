package pl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  @Query("select a from Author a" +
          " where a.id=:id")
  Author findBook(@Param("id") Long id);

  @Query("select a from Author a" +
          " join fetch a.book where a.id=:id")
  Author findFetchBook(@Param("id") Long id);
}
