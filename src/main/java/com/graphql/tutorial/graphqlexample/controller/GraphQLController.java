package com.graphql.tutorial.graphqlexample.controller;

import com.graphql.tutorial.graphqlexample.model.Author;
import com.graphql.tutorial.graphqlexample.model.Book;
import com.graphql.tutorial.graphqlexample.model.BookInput;
import com.graphql.tutorial.graphqlexample.model.BookType;
import com.graphql.tutorial.graphqlexample.service.GraphQLService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitResult;

@Controller
public class GraphQLController {

  private final GraphQLService graphQLService;

  Sinks.Many<Book> sink = Sinks.many().multicast().onBackpressureBuffer();

  @Autowired
  public GraphQLController(GraphQLService graphQLService) {
    this.graphQLService = graphQLService;
  }

  @QueryMapping
  public List<Book> books(@Argument BookType bookType) {
    return graphQLService.getBooks(bookType);
  }

  @QueryMapping
  public Book bookById(@Argument int id) {
    return graphQLService.getBookById(id);
  }

  @QueryMapping
  public List<Author> authors() {
    return graphQLService.getAuthors();
  }

  @QueryMapping
  public Author authorById(@Argument int id) {
    return graphQLService.getById(id);
  }

  @SchemaMapping
  public Author author(Book book) {
    return graphQLService.getById(book.getAuthorId());
  }

  @MutationMapping
  public Book createBook(@Argument BookInput bookInput) {
    Book book = graphQLService.createBook(bookInput);
    emitBook(book);
    return book;
  }

  @SubscriptionMapping
  public Flux<Book> booksSubscription() {
    graphQLService.getBooks(null).forEach(this::emitBook);
    return sink.asFlux();
  }

  private void emitBook(Book book) {
    EmitResult result = sink.tryEmitNext(book);
    if (result.isFailure()) {
      System.out.println("Result emission failed for book with name: " + book.getName());
    } else {
      System.out.println("Result emission succeeded for book with name: " + book.getName());
    }
  }
}
