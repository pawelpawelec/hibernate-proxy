package pl.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book {

  @Id
  private Long isbn;
  private String tittle;

  @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
  private List<Author> authors;
}
