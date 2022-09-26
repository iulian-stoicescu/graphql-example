package com.graphql.tutorial.graphqlexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  private int id;
  private String name;
  private int pageCount;
  private BookType type;
  private int authorId;
}
