package bookManagementService;
/*

Functional requirements
1. Search for a book
  - Users can search for a book based on various criteria - Title, author or genre.
2. Read a Book
  - User can select a book from search result and start reading it online
3. User Registration and Management
  - System needs to support Registration and Management.
  - Registered user's can be extended to subscription.
4. Concurrency Constraints
  - Only one active user can interact with the system at a time
  - Each user can have only one active book assigned for reading at any given time

  -- Core Entities and design considerations
  1. Strategy pattern - Create different Strategies to search for a book
  2. State Pattern - To ensure that the search system is accessed by a single user at any given
  time, and the same state should be extended for Book.
  3. Decorator Pattern - To change the type of the user Dynamically.
*/


public class Main {
  public static void main(String[] args) {

  }
}
