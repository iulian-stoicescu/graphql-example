package com.graphql.tutorial.graphqlexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInput {

  private String name;
  private int pageCount;
  private BookType type;
  private String authorFirstName;
  private String authorLastName;
}
