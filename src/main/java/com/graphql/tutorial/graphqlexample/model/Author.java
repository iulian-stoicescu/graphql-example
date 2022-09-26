package com.graphql.tutorial.graphqlexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

  private int id;
  private String firstName;
  private String lastName;
}
