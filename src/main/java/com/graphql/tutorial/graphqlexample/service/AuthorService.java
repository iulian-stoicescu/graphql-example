package com.graphql.tutorial.graphqlexample.service;

import com.graphql.tutorial.graphqlexample.model.Author;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private static final List<Author> authors =
      new ArrayList<>(
          Arrays.asList(
              new Author(1, "Joanne", "Rowling"),
              new Author(2, "Herman", "Melville"),
              new Author(3, "Anne", "Rice")));

  public List<Author> getAuthors() {
    return authors;
  }

  public Author getById(int id) {
    return authors.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
  }

  public int getAuthorId(String firstName, String lastName) {
    Optional<Author> authorOptional =
        authors.stream()
            .filter(
                author ->
                    author.getFirstName().equals(firstName)
                        && author.getLastName().equals(lastName))
            .findAny();
    if (authorOptional.isPresent()) {
      return authorOptional.get().getId();
    } else {
      int id = getNextAvailableId();
      authors.add(new Author(id, firstName, lastName));
      return id;
    }
  }

  private int getNextAvailableId() {
    return Collections.max(authors.stream().map(Author::getId).collect(Collectors.toList())) + 1;
  }
}
