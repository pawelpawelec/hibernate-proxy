package pl.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

  @Id
  private Long id;
  private String name;
  private String surname;
  @Column(name = "BOOK_ID")
  private Long bookId;
  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name = "BOOK_ID", insertable = false, updatable = false)
  private Book book;
}
