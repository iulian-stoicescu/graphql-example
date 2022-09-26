package com.graphql.tutorial.graphqlexample.service;

import com.graphql.tutorial.graphqlexample.model.Book;
import com.graphql.tutorial.graphqlexample.model.BookInput;
import com.graphql.tutorial.graphqlexample.model.BookType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private static final List<Book> books =
      new ArrayList<>(
          Arrays.asList(
              new Book(1, "Harry Potter and the Philosopher's Stone", 223, BookType.FANTASY, 1),
              new Book(2, "Moby Dick", 635, BookType.ADVENTURE, 2),
              new Book(3, "Interview with the vampire", 371, BookType.MYSTERY, 3)));

  public List<Book> getBooks(BookType bookType) {
    return bookType == null
        ? books
        : books.stream().filter(book -> book.getType() == bookType).collect(Collectors.toList());
  }

  public Book getBookById(int id) {
    return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
  }

  public Book createBook(BookInput bookInput, int authorId) {
    int id = getNextAvailableId();
    Book book =
        new Book(id, bookInput.getName(), bookInput.getPageCount(), bookInput.getType(), authorId);
    books.add(book);
    return book;
  }

  private int getNextAvailableId() {
    return Collections.max(books.stream().map(Book::getId).collect(Collectors.toList())) + 1;
  }
}
