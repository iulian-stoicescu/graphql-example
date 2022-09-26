package com.graphql.tutorial.graphqlexample.service;

import com.graphql.tutorial.graphqlexample.model.Author;
import com.graphql.tutorial.graphqlexample.model.Book;
import com.graphql.tutorial.graphqlexample.model.BookInput;
import com.graphql.tutorial.graphqlexample.model.BookType;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphQLService {

  private final BookService bookService;
  private final AuthorService authorService;

  @Autowired
  public GraphQLService(BookService bookService, AuthorService authorService) {
    this.bookService = bookService;
    this.authorService = authorService;
  }

  public List<Book> getBooks(BookType bookType) {
    return bookService.getBooks(bookType);
  }

  public Book getBookById(int id) {
    return bookService.getBookById(id);
  }

  public List<Author> getAuthors() {
    return authorService.getAuthors();
  }

  public Author getById(int id) {
    return authorService.getById(id);
  }

  public Book createBook(BookInput bookInput) {
    // don't allow multiple books with the same name to be added to the list
    Optional<Book> bookOptional =
        bookService.getBooks(null).stream()
            .filter(book -> book.getName().equals(bookInput.getName()))
            .findAny();
    if (bookOptional.isPresent()) {
      return bookOptional.get();
    }

    int authorId =
        authorService.getAuthorId(bookInput.getAuthorFirstName(), bookInput.getAuthorLastName());
    return bookService.createBook(bookInput, authorId);
  }
}
