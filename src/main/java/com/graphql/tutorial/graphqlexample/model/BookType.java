package com.graphql.tutorial.graphqlexample.model;

public enum BookType {
  ADVENTURE("ADVENTURE"),
  FANTASY("FANTASY"),
  MYSTERY("MYSTERY");

  private final String name;

  BookType(String name) {
    this.name = name;
  }

  public static BookType fromString(String name) {
    for (BookType bookType : BookType.values()) {
      if (bookType.getName().equals(name)) {
        return bookType;
      }
    }

    throw new IllegalArgumentException("No BookType value was found for name: " + name);
  }

  public String getName() {
    return name;
  }
}
