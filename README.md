# LLD Basics
## Things to remember for a good design
   1. Always try to avoid mutation as much as possible. What is mutation - For any state update the current object and circulate the updated object. But, instead you can create a new object which becomes the ground truth object.
   2.
## Solid Principles
 - **Single Responsibilty**: The main goal of this principle is reducing complexity. A class should have only one reason to change.
 
    Example: Employee(name, getName(), printTimeSheetReport()) the issue with this class is its trying to solve two problems - Mantain employee information and the generate Time sheet reports. Now is a good time to divide this class and make sure each class has single responsibility.

- **One/Closed Principle** : Classes should be open for extension and closed for modification. Prevent existing code from breaking when new functionality is added.
    
    Example: `Order` class that calculates shipping costs and all the shipping methods are hard coded inside the class. If you need to build a new shipping method, you would have to have to change `Order` and risk breaking it. We can solve this problem with Strategy design pattern by extracting shipping methods and placing them in a common interface.

- **Liskov Substitution**: When a class is extended, we should be able to pass subclass objects in place of the parent class without breaking the code.

    Example: `Document` conatains a method save() which is used by the subclass `ReadOnlyDocument`. In the subclass implementation save() method throws an error. This would break the Liskov Substitution principle if we try to replace Document with ReadOnlyDocument.

- **Interface Segregation**: Clients should be forced to depend on methods that are not used.

    Example: Issue we have an interface for implementing services offered a cloud provider. Since, we have used AWS before, all the methods in interface are the services provided by AWS. Howerver, this causes a problem when a new cloud provider is added into the mix. All these services may not be available in the new provider.

- **Dependency Inversion**: High-level classes should depend on low-level classes. Both should depend on Abstractions. Abstractions should be free of details. Details should use Abstractions. Closely related to Open/closed principle.

    Example: High level budget reporting class uses low-level database class for reading and persisting its data. When a new version of databse server gets released it affects the high-level class, which shouldn't care about the storage details.
    Instead of having dependency with concrete class implementation, its better to make the high-level class implement the interface of a Database, abstracting away the type of database it's interacting with.

# Important Design Patterns in Use

1. **Factory Method (example - FedEx delivery system)**: It is a creational design pattern that provides an interface for creating objects of a superclass, but allows subclasses to alter the type of objects that will be created. Still need a decision to make which concrete creator to use based on usecase or this can be deligated to another factory.

    Example: Let's you have logistic company that transports goods via `Trucks`, making your logic highly coupled with truck class. Now as you add new ways of transportatio, new classes get added and the client must make a decision to create objects for transportation.

    ```Java
    class Truck {
        public void deliver() {
            System.out.println("Delivering by road in a truck");
        }
    }   

    class Ship {
        public void deliver() {
            System.out.println("Delivering by sea in a ship");
        }
    }

    // Client code
    class LogisticsApp {
        public void planDelivery(String transportType) {
            // Example of tight coupling, Every time a new transportation class gets created this function will change introducing bugs
            if (transportType.equals("truck")) {
                Truck truck = new Truck();
                truck.deliver();
            } else if (transportType.equals("ship")) {
                Ship ship = new Ship();
                ship.deliver();
            }
        }
    }
    ```
    Solution: Factory method solves this problem by 
    
    1. Creating a common interface for all the the Transportation types
    2. Creating an Abstract Creator class with a factory method
    3. Implement concrete creator classes that override the factory method of the Abstract creator class
    ```Java
    // Common interface for Transportation
    interface Transport{
        public void deliver();
    }

    // Concrete implementations of Transport Interface
    class Truck implements Transport{
        @override
        public void deliver(){
            System.out.println("Delivering by road in a Truck");
        

    class Ship implements Transport{
        @override
        public void deliver(){
            System.out.println("Delivering by sea in a Ship");
        }
    }

    // Abstract Creator 
    abstract class LogisticsCompany{
         // Factory Method
        protected abstract Transport createTransport();
    
        // Template method that uses the factory method
        public void planDelivery() {
            Transport transport = createTransport();
            transport.deliver();
        }
    }
    // Concrete Creators
    class RoadLogistics extends LogisticsCompany {
        protected Transport createTransport() {
            return new Truck();
        }
    }

    // Concrete Creators
    class SeaLogistics extends LogisticsCompany {
        protected Transport createTransport() {
            return new Ship();
        }
    }
    class LogisticsFactory {
        public static LogisticsCompany createLogistics(String type) {
            if ("road".equals(type)) return new RoadLogistics();
            if ("sea".equals(type)) return new SeaLogistics();
            return new RoadLogistics(); // default
        }
    }
    // Client code 
    class Client {
        public static void main(String[] args) {
            LogisticsCompany logistics = LogisticsFactory.createLogistics(type);
            logistics.planDelivery();
        }
    }
    ```

2. **Abstract Factory**: A creational design pattern that lets you produce families of related objects without specifying their concrete classes. Useful when you want to create a family of related objects - let's say all Apple devices (iPhone, iMac, Mac, iWatch) etc..

    Example: I have an electronic device store and I sell goods like mobile, Laptops and PCs. Now there are variants for these devices, produced by `Microsoft`, `Apple` and `Samsung`. I need a way to create individual device object so that objects match if they are part of the same company. Customer's don't want to buy mismatching products from different companies, they are loyal to a single company. Now let's a new company called Google from california started producing all the above products. To accomodate this addition I would have to touch my existing code, same with a new product as well(Let's say a smart-watch).

    ```Java
    /** Example of a bad design using anti-pattern method*/

    class ElectronicStore {
        // Create a mobile based on manufacturer
        public Mobile createMobile(String manufacturer) {
            if (manufacturer.equals("Apple")) {
                return new AppleMobile();
            } else if (manufacturer.equals("Samsung")) {
                return new SamsungMobile();
            } else if (manufacturer.equals("Microsoft")) {
                return new MicrosoftMobile();
            }
            // If Google is added later, we'd have to modify this method
            return null;
        }
        
        // Create a laptop based on manufacturer
        public Laptop createLaptop(String manufacturer) {
            if (manufacturer.equals("Apple")) {
                return new AppleLaptop();
            } else if (manufacturer.equals("Samsung")) {
                return new SamsungLaptop();
            } else if (manufacturer.equals("Microsoft")) {
                return new MicrosoftLaptop();
            }
            // If Google is added later, we'd have to modify this method
            return null;
        }
        
        // Create a PC based on manufacturer
        public PC createPC(String manufacturer) {
            if (manufacturer.equals("Apple")) {
                return new ApplePC();
            } else if (manufacturer.equals("Samsung")) {
                return new SamsungPC();
            } else if (manufacturer.equals("Microsoft")) {
                return new MicrosoftPC();
            }
            // If Google is added later, we'd have to modify this method
            return null;
        }
        
        // Create a package for a customer with products from the same manufacturer
        public void createTechPackage(String manufacturer) {
            Mobile mobile = createMobile(manufacturer);
            Laptop laptop = createLaptop(manufacturer);
            PC pc = createPC(manufacturer);
            
            System.out.println("Creating a tech package for " + manufacturer + " with:");
            mobile.displayInfo();
            laptop.displayInfo();
            pc.displayInfo();
        }
    }

    // Basic interfaces
    interface Mobile {
        void displayInfo();
    }

    interface Laptop {
        void displayInfo();
    }

    interface PC {
        void displayInfo();
    }

    // Concrete implementations for each product from each manufacturer
    class AppleMobile implements Mobile {
        public void displayInfo() {
            System.out.println("Apple iPhone");
        }
    }

    class SamsungMobile implements Mobile {
        public void displayInfo() {
            System.out.println("Samsung Galaxy");
        }
    }

    class MicrosoftMobile implements Mobile {
        public void displayInfo() {
            System.out.println("Microsoft Surface Duo");
        }
    }

    // ... similarly for Laptop and PC implementations for each manufacturer

    // Client code
    public class Main {
        public static void main(String[] args) {
            ElectronicStore store = new ElectronicStore();
            store.createTechPackage("Apple");
        }
    }
    
    ```
    Solution: Abstract factory solves this by creating - 
    
    1. Abstract factory: Interface declaring methods to create objects for each product type
    2. Concrete Factories: Implementation of abstract factory for each manufacturer.
    3. Abstract Products: Interfaces for each type of product
    4. Concrete Products: Specific implementation of each product for a manufacturer

    ```Java
    // code example

    // Product interfaces
    interface Mobile{
        void displayInfo();
    }

    interface Laptop{
        void displayInfo();
    }

    interface PC{
        void displayInfo();
    }

    // Abstract factory to create products
    interface ElectroicDeviceManufacturer{
        Mobile createMobile();
        PC createPC();
        Laptop createLaptop();
    }

    // Concrete products implementing product interface
    class ApplePhone implements Mobile{
        public void displayInfo() {
            System.out.println("Apple iPhone");
        }
    }

     class SamsungPhone implements Mobile{
        public void displayInfo() {
            System.out.println("Samsung Phone");
        }
    }

     class MicrosoftPhone implements Mobile{
        public void displayInfo() {
            System.out.println("Microsoft Phone");
        }
    }
    // similary create classes for laptops and PCs

    // concrete Factories
    class AppleFactory implements ElectroicDeviceManufacturer {
        public Mobile createMobile(){
            return new AppleMobile();
        }
        public Laptop createLaptop(){
            return new AppleLaptop();
        }
        public PC createPC(){
            return new ApplePC();
        }
    }

    class SamsungFactory implements ElectroicDeviceManufacturer {
        public Mobile createMobile(){
            return new SamsungMobile();
        }
        public Laptop createLaptop(){
            return new SamsungLaptop();
        }
        public PC createPC(){
            return new SamsungPC();
        }
    }

    class MSFactory implements ElectroicDeviceManufacturer {
        public Mobile createMobile(){
            return new MicrosoftMobile();
        }
        public Laptop createLaptop(){
            return new MicrosoftLaptop();
        }
        public PC createPC(){
            return new MicrosoftPC();
        }
    }

    // Client code

    class ElectronicStore {
        private ElectronicsManufacturer manufacturer;
        
        public void setManufacturer(ElectronicsManufacturer manufacturer) {
            this.manufacturer = manufacturer;
        }
        
        public void createTechPackage() {
            Mobile mobile = manufacturer.createMobile();
            Laptop laptop = manufacturer.createLaptop();
            PC pc = manufacturer.createPC();
            
            System.out.println("Creating a tech package with:");
            mobile.displayInfo();
            laptop.displayInfo();
            pc.displayInfo();
        }
    }

    class Main {
        public static void main(String[] args) {
            // Create store
            ElectronicStore store = new ElectronicStore();
            
            // Customer wants Apple products
            store.setManufacturer(new AppleManufacturer());
            store.createTechPackage();
            
            // Another customer wants Samsung products
            store.setManufacturer(new SamsungManufacturer());
            store.createTechPackage();
        }
    }

    ```

    No design pattern is perfect for all situations. The Abstract Factory pattern works well when:

    1. You have a relatively stable set of product types
    2. The number of manufacturers and products won't grow excessively.
    3. Family consistency is a critical requirement

4. **Builder design Pattern**: A creational design pattern that let's you construct complex objects step by step. This pattern allows you to produce different types and representations of an object using same construction code.

    Example: Your local burrito place hired you to implement their system and inherited code that let's you build a burrito with a sequence of actions. However now you have noticed a flaw in the existing implementation. Once the burrito was assembled and billed, client is still able to add toppings and proteins into it.

    ```Java

    class Burrito {
    private List<String> proteins = new ArrayList<>();
    private List<String> toppings = new ArrayList<>();
    private String tortilla;
    private boolean isGuacIncluded;
    private boolean isAssembled = false;
    private boolean isBilled = false;
    
    public void setTortilla(String tortilla) {
        this.tortilla = tortilla;
    }
    
    public void addProtein(String protein) {
        // No validation - problem!
        proteins.add(protein);
    }
    
    public void addTopping(String topping) {
        // No validation - problem!
        toppings.add(topping);
    }
    
    public void addGuacamole() {
        // No validation - problem!
        this.isGuacIncluded = true;
    }
    
    public void assemble() {
        this.isAssembled = true;
        System.out.println("Burrito has been assembled!");
    }
    
    public void bill() {
        this.isBilled = true;
        System.out.println("Burrito has been billed for $" + calculatePrice());
    }
    
    private double calculatePrice() {
        // Price calculation logic
        return 8.99 + (proteins.size() * 2.50) + (isGuacIncluded ? 1.50 : 0);
    }
    }

    // Client code
    public class BurritoShop {
        public static void main(String[] args) {
            Burrito burrito = new Burrito();
            
            burrito.setTortilla("Wheat");
            burrito.addProtein("Chicken");
            burrito.addTopping("Lettuce");
            burrito.addTopping("Cheese");
            
            burrito.assemble();
            burrito.bill();
            
            // Problem: Customer can still modify after billed!
            burrito.addProtein("Steak");  // This should not be allowed
            burrito.addTopping("Salsa");  // This should not be allowed
        }
    }
    ```
    Solution: Builder pattern helps solves the above problem by 
    
    1. Create a Product class
    2. Create the builder class
    3. Implement construction steps inside the Builder

    ```Java

    // Creating product class
    public class Burrito{
        // final immutable fields
        private final String tortilla;
        private final List<String> proteins;
        private final List<String> toppings;
        private final boolean hasGuacamole;

        // private constructor - only builder can create this instance
        private Burrito(Builder builder){
            this.tortilla = builder.tortilla;
            this.proteins = new ArrayList<>(Builder.proteins);
            this.toppings = new ArrayList<>(Builder.toppings);
            this.hasGuacamole = builder.hasGuacamole;
        }
        // Getters only - no setters to maintain immutability
        public String getTortilla() { return tortilla; }
        public List<String> getProteins() { return new ArrayList<>(proteins); }
        public List<String> getToppings() { return new ArrayList<>(toppings); }
        public boolean hasGuacamole() { return hasGuacamole; }

        // Creating Builder class - a static inner class that acces the private fields of Burrito
        public static class Builder {
            private String tortilla;

            // Optional parameters - initialized with default values
            private List<String> proteins = new ArrayList<>();
            private List<String> toppings = new ArrayList<>();
            private boolean hasGuacamole = false;

            // Constructor with required parameters
            public Builder(String tortilla) {
                if (tortilla == ""){
                    throw IllegalStateException("Need to provide a tortilla shape");
                }
                this.tortilla = tortilla;
            }
            // Step 4: Implement Construction Steps
            public Builder addProtein(String protein) {
                this.proteins.add(protein);
                return this;
            }
            
            public Builder addTopping(String topping) {
                this.toppings.add(topping);
                return this;
            }
            
            public Builder addGuacamole() {
                this.hasGuacamole = true;
                return this;
            }

            // final build step - returning immutable object
            public Burrito build(){
                return new Burrito(this);
            }

        } 

        // Step 6: Client Code Uses the Builder
        public class BurritoShop {
            public static void main(String[] args) {
                // Using the builder directly
                Burrito customBurrito = new Burrito.Builder("Corn")
                        .addProtein("Carnitas")
                        .addProtein("Steak")
                        .addTopping("Onions")
                        .addTopping("Cilantro")
                        .addGuacamole()
                        .build();
                        
            }
        }
    }
    ```
4. **Singleton Pattern**: It is a creational design pattern that lets you ensure that a class has a single instance, while providing a global access point to this instance.

    Example: Suppose I have created a game board called for Tic-Tac-Toe game. I have to let client access the created instance instead of overwriting it. The client can perform some actions on the game board like view the current state of the board and play a move. If no further action can be done the game is considered as over or when a player completes a row or a column with same characters. Then only this object can be dropped and a new instance can be created.

    ```Java
    /**
     * Anti-pattern: Multiple instances of Tic-Tac-Toe game board
     * This creates inconsistent game states across the application
    */
    public class TicTacToeBoardAntiPattern {
        // Game state variables
        private Character[][] board;
        private char currentPlayer;
        private boolean gameOver;
        
        // Public constructor allows multiple instances
        public TicTacToeBoardAntiPattern() {
            board = new Character[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = null;
                }
            }
            currentPlayer = 'X';
            gameOver = false;
        }
        
        // Make a move on the board
        public String makeMove(int row, int col) {
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                return "Invalid position! Row and column must be between 0 and 2.";
            }
            
            if (board[row][col] == null && !gameOver) {
                board[row][col] = currentPlayer;
                
                // Check for winner
                if (checkWinner()) {
                    gameOver = true;
                    return "Player " + currentPlayer + " wins!";
                }
                
                // Check for draw
                if (isBoardFull()) {
                    gameOver = true;
                    return "Game ended in a draw!";
                }
                
                // Switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                return "Move made. Next player: " + currentPlayer;
            }
            return "Invalid move! Position already occupied or game is over.";
        }
        
        // Check if there's a winner
        private boolean checkWinner() {
            // Check rows, columns, diagonals (same as in singleton version)
            // Implementation omitted for brevity
            return false;
        }
        
        // Check if the board is full (draw)
        private boolean isBoardFull() {
            // Implementation omitted for brevity
            return false;
        }
        
        // Print the current board state
        public void printBoard() {
            System.out.println("Current Board State:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == null) {
                        System.out.print(" - ");
                    } else {
                        System.out.print(" " + board[i][j] + " ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
        
        // Example demonstrating the problem with multiple instances
        public static void main(String[] args) {
            // Create separate instances for different parts of the application
            TicTacToeBoardAntiPattern playerOneView = new TicTacToeBoardAntiPattern();
            TicTacToeBoardAntiPattern playerTwoView = new TicTacToeBoardAntiPattern();
            TicTacToeBoardAntiPattern refereeView = new TicTacToeBoardAntiPattern();
            
            System.out.println("Player 1 makes a move:");
            playerOneView.makeMove(0, 0); // X at (0,0)
            playerOneView.printBoard();
            
            System.out.println("Player 2 makes a move (on a different board instance):");
            playerTwoView.makeMove(1, 1); // X at (1,1) - note this is X, not O!
            playerTwoView.printBoard();
            
            System.out.println("Referee checks the game (on yet another instance):");
            refereeView.printBoard(); // Empty board!
            
            System.out.println("PROBLEM: Each player and the referee see different game states!");
            System.out.println("- Player 1 sees X at (0,0) only");
            System.out.println("- Player 2 sees X at (1,1) only");
            System.out.println("- Referee sees an empty board");
            
            System.out.println("\nThis is why we need a Singleton pattern!");
        }
    }
    ```
    Solution: Singleton solves the above problem with following steps
    
    1. Make constructor private, preventing direct object instantiation.
    2. Create a static access method to create object else return the private static object stored.
    3. Ensure thread safety by using `synchronized` in java.

    ```Java
    /**
     * Singleton implementation of a Tic-Tac-Toe game board
    */
    public class TicTacToeBoard {
        // Private static instance variable
        private static TicTacToeBoard instance;
        
        // Game state variables
        private Character[][] board;
        private char currentPlayer;
        private boolean gameOver;
        
        // Private constructor to prevent direct instantiation
        private TicTacToeBoard() {
            board = new Character[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = null;
                }
            }
            currentPlayer = 'X';
            gameOver = false;
        }
        
        // Static method to get the singleton instance (lazy initialization)
        public static synchronized TicTacToeBoard getInstance() {
            if (instance == null) {
                instance = new TicTacToeBoard();
            }
            return instance;
        }
        
        // Reset the game (only way to create a "new" board)
        public void resetGame() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = null;
                }
            }
            currentPlayer = 'X';
            gameOver = false;
        }
        
        // Make a move on the board
        public String makeMove(int row, int col) {
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                return "Invalid position! Row and column must be between 0 and 2.";
            }
            
            if (board[row][col] == null && !gameOver) {
                board[row][col] = currentPlayer;
                
                // Check for winner
                if (checkWinner()) {
                    gameOver = true;
                    return "Player " + currentPlayer + " wins!";
                }
                
                // Check for draw
                if (isBoardFull()) {
                    gameOver = true;
                    return "Game ended in a draw!";
                }
                
                // Switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                return "Move made. Next player: " + currentPlayer;
            }
            return "Invalid move! Position already occupied or game is over.";
        }
        
        // Check if there's a winner
        private boolean checkWinner() {
            // Check rows
            for (int i = 0; i < 3; i++) {
                if (board[i][0] != null && 
                    board[i][0].equals(board[i][1]) && 
                    board[i][1].equals(board[i][2])) {
                    return true;
                }
            }
            
            // Check columns
            for (int i = 0; i < 3; i++) {
                if (board[0][i] != null && 
                    board[0][i].equals(board[1][i]) && 
                    board[1][i].equals(board[2][i])) {
                    return true;
                }
            }
            
            // Check diagonals
            if (board[0][0] != null && 
                board[0][0].equals(board[1][1]) && 
                board[1][1].equals(board[2][2])) {
                return true;
            }
            
            if (board[0][2] != null && 
                board[0][2].equals(board[1][1]) && 
                board[1][1].equals(board[2][0])) {
                return true;
            }
            
            return false;
        }
        
        // Check if the board is full (draw)
        private boolean isBoardFull() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == null) {
                        return false;
                    }
                }
            }
            return true;
        }
        
        // Get the current board state (defensive copy)
        public Character[][] getBoardState() {
            Character[][] copy = new Character[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    copy[i][j] = board[i][j];
                }
            }
            return copy;
        }
        
        // Print the current board state
        public void printBoard() {
            System.out.println("Current Board State:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == null) {
                        System.out.print(" - ");
                    } else {
                        System.out.print(" " + board[i][j] + " ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
        
        // Example usage
        public static void main(String[] args) {
            // Get the singleton instance
            TicTacToeBoard gameBoard = TicTacToeBoard.getInstance();
            
            // Make some moves
            System.out.println(gameBoard.makeMove(0, 0)); // X at (0,0)
            gameBoard.printBoard();
            
            System.out.println(gameBoard.makeMove(1, 1)); // O at (1,1)
            gameBoard.printBoard();
            
            // Another part of the application uses the same instance
            TicTacToeBoard sameBoard = TicTacToeBoard.getInstance();
            System.out.println("Is it the same instance? " + (gameBoard == sameBoard));
            
            // Continue with the game
            System.out.println(sameBoard.makeMove(0, 1)); // X at (0,1)
            sameBoard.printBoard();
            
            // Finish the game to demonstrate reset
            System.out.println(gameBoard.makeMove(2, 2)); // O at (2,2)
            System.out.println(gameBoard.makeMove(0, 2)); // X at (0,2) - X wins
            gameBoard.printBoard();
            
            // Reset the game
            System.out.println("Resetting game...");
            gameBoard.resetGame();
            gameBoard.printBoard();
        }
    }
    ```

5. **Adapter Pattern**: It's a structural design pattern that allows objects with incompatible interfaces to collaborate.

    Example: I have an application that only accepts images of ".arff" format. Now a new requirement arised, I should support a new image format "JPEG" and let users do the image processing funcationalities just like before. However, a new file format means I need to change the existing code. 

    ``` Java
    // Anti-pattern example

    public class ImageEditing{
        private String inputfile;
        public ImageEditing(String file){
            this.inputFile = file;
        }
        private Image extractImage(){
            if(this.inputfile == "arff") return readARff();
            else if(this.inputfile == "jpeg") return readJPEG();
            // keepm adding lines here to support new formats
        }
    }

    ```

    Solution: Adapter can help objects with different interfaces collaborate. Here's how it works - 
    1. The adapter gets an interface compatible with one of the existing objects
    2. Using the interface, existing object can safely call the adapter's methods
    3. Upon receiving a call, the adapter passes the request to second object, but in a format and order that the second object expects.

    ``` Java
        public class Image {
            // Image properties and methods
            private int width;
            private int height;
            
            public Image() {
                this.width = 100;
                this.height = 100;
            }
            
            // Additional image methods
        }

        // Target interface that the client code expects
        public interface ImageProcessor {
            public Image processImage();
        }

        // Existing implementation for ARFF files
        public class ArffImageProcessor implements ImageProcessor {
            private String arffFilePath;
            
            public ArffImageProcessor(String filePath) {
                this.arffFilePath = filePath;
            }
            
            @Override
            public Image processImage() {
                // Original code for processing ARFF images
                System.out.println("Processing ARFF image: " + arffFilePath);
                return readArffImage(arffFilePath);
            }
            
            private Image readArffImage(String path) {
                // Implementation specific to ARFF format
                System.out.println("Reading ARFF image from: " + path);
                // Return the image object
                return new Image(); // Placeholder
            }
        }

        // The incompatible class for JPEG processing
        public class JpegProcessor {
            private String jpegFilePath;
            
            public JpegProcessor(String filePath) {
                this.jpegFilePath = filePath;
            }
            
            // Note: Different method name and possibly different return type
            public Base64Image readJpegImage() {
                System.out.println("Reading JPEG image from: " + jpegFilePath);
                // Implementation specific to JPEG format
                return new Base64Image(); // Placeholder
            }
            
            // Additional JPEG-specific methods that don't exist in the target interface
            public void adjustContrast(int level) {
                System.out.println("Adjusting JPEG contrast to level: " + level);
            }
        }

        // Adapter to make JpegProcessor work with ImageProcessor interface
        public class JpegToArffAdapter implements ImageProcessor {
            private JpegProcessor jpegProcessor;
            
            public JpegToArffAdapter(String jpegFilePath) {
                this.jpegProcessor = new JpegProcessor(jpegFilePath);
            }
            
            @Override
            public Image processImage() {
                // Call the adaptee's method and perform any necessary conversions
                System.out.println("Adapter: Converting JPEG processing to match ARFF interface");
                return convertBase64Image(jpegProcessor.readJpegImage());
            }
            
            public Image convertBase64Image(Base64Image img) {
                // code here
                return new Image()
            }
        }

        public class Client {
            public static void main(String[] args) {
                // Process an ARFF image (original format)
                String arffFile = "sample.arff";
                ImageProcessor arffProcessor = new ArffImageProcessor(arffFile);
                ImageEditing arffEditor = new ImageEditing(arffProcessor);
                Image arffImage = arffEditor.editImage();
                
                System.out.println("\n--- Now using JPEG with Adapter ---\n");
                
                // Process a JPEG image using the adapter
                String jpegFile = "sample.jpeg";
                ImageProcessor jpegAdapter = new JpegToArffAdapter(jpegFile);
                ImageEditing jpegEditor = new ImageEditing(jpegAdapter);
                Image jpegImage = jpegEditor.editImage();
                
                // Using extended functionality of the JPEG processor
                if (jpegAdapter instanceof JpegToArffAdapter) {
                    ((JpegToArffAdapter) jpegAdapter).adjustJpegContrast(50);
                }
            }
        }
    ```

6. **Decorator Pattern**: A structural design pattern that lets you attach new behaviors to objects by placing these objects inside a special wrapper objects that contain the behaviors.

    Example: I initially created a simple website for my artisan pizza shop that handled just two types of pizzas - Cheese and Veggie. The code was clean and worked perfectly. However, as customer demand grew, I needed to add the ability for customers to customize their pizzas with various toppings. I quickly realized I had a problem - my current approach would require creating a separate subclass for every possible pizza-topping combination. With just 5 toppings, I would need to create dozens of different classes (CheeseWithMushroomPizza, VeggieWithExtraCheeseAndOlivesPizza, etc.). Adding even more toppings in the future would make this completely unmanageable. What I need is a flexible way to dynamically add toppings to pizza objects at runtime, without creating an explosion of subclasses and while maintaining the ability to correctly calculate the price and description of each customized pizza.

    ``` Java 
    // My existing implementation

    // Anti-pattern: Creating a separate class for each pizza variation
    public class Pizza {
        protected String description;
        protected double cost;
        
        public String getDescription() {
            return description;
        }
        
        public double getCost() {
            return cost;
        }
    }

    // Base pizza types
    class MargheritaPizza extends Pizza {
        public MargheritaPizza() {
            description = "Margherita Pizza";
            cost = 6.99;
        }
    }

    class VegetarianPizza extends Pizza {
        public VegetarianPizza() {
            description = "Vegetarian Pizza";
            cost = 7.99;
        }
    }

    // Subclasses for ALL variations - this is the anti-pattern!
    class MargheritaWithExtraCheeseAndMushrooms extends Pizza {
        public MargheritaWithExtraCheeseAndMushrooms() {
            description = "Margherita Pizza with extra cheese and mushrooms";
            cost = 9.99;
        }
    }

    class VegetarianWithExtraCheeseAndOlives extends Pizza {
        public VegetarianWithExtraCheeseAndOlives() {
            description = "Vegetarian Pizza with extra cheese and olives";
            cost = 10.99;
        }
    }

    // Need to create a new class for EVERY combination of toppings!
    class MargheritaWithExtraCheeseMushroomsAndPepperoni extends Pizza {
        public MargheritaWithExtraCheeseMushroomsAndPepperoni() {
            description = "Margherita Pizza with extra cheese, mushrooms, and pepperoni";
            cost = 11.99;
        }
    }

    // Client code
    public class PizzaShop {
        public static void main(String[] args) {
            Pizza pizza = new MargheritaWithExtraCheeseAndMushrooms();
            System.out.println(pizza.getDescription() + " $" + pizza.getCost());
            
            // What if a customer wants a new combination? Need a new class!
        }
    }

    ``` 
    Solution: Decorator pattern can be used to add new features to objects during runtime (Composition). Here's how we could do it - 

    1. Create a common interface (Pizza) that both the concrete component and decorator will implement
    2. Implement concrete components that define basic behavior and can be altered by decorators
    3. Define an abstract decorator class (ToppingDecorator) that implements the component interface
    4. Create specific decorator classes for each topping 
    (ExtraCheeseDecorator, MushroomDecorator, etc.)

    ```Java
    // Step 1: Create the Component interface (Pizza)
    public interface Pizza {
        String getDescription();
        double getCost();
    }

    // Step 2: Create Concrete Component classes (Base pizzas)
    class MargheritaPizza implements Pizza {
        @Override
        public String getDescription() {
            return "Margherita Pizza";
        }
        
        @Override
        public double getCost() {
            return 6.99;
        }
    }

    class VegetarianPizza implements Pizza {
        @Override
        public String getDescription() {
            return "Vegetarian Pizza";
        }
        
        @Override
        public double getCost() {
            return 7.99;
        }
    }

    // Step 3: Create the Decorator abstract class
    abstract class ToppingDecorator implements Pizza {
        protected Pizza pizza;
        
        public ToppingDecorator(Pizza pizza) {
            this.pizza = pizza;
        }
        
        @Override
        public String getDescription() {
            return pizza.getDescription();
        }
        
        @Override
        public double getCost() {
            return pizza.getCost();
        }
    }

    // Step 4: Create Concrete Decorator classes (Toppings)
    class ExtraCheeseDecorator extends ToppingDecorator {
        public ExtraCheeseDecorator(Pizza pizza) {
            super(pizza);
        }
        
        @Override
        public String getDescription() {
            return pizza.getDescription() + ", extra cheese";
        }
        
        @Override
        public double getCost() {
            return pizza.getCost() + 1.50;
        }
    }

    class MushroomDecorator extends ToppingDecorator {
        public MushroomDecorator(Pizza pizza) {
            super(pizza);
        }
        
        @Override
        public String getDescription() {
            return pizza.getDescription() + ", mushrooms";
        }
        
        @Override
        public double getCost() {
            return pizza.getCost() + 1.00;
        }
    }

    class PepperoniDecorator extends ToppingDecorator {
        public PepperoniDecorator(Pizza pizza) {
            super(pizza);
        }
        
        @Override
        public String getDescription() {
            return pizza.getDescription() + ", pepperoni";
        }
        
        @Override
        public double getCost() {
            return pizza.getCost() + 1.75;
        }
    }

    class OliveDecorator extends ToppingDecorator {
        public OliveDecorator(Pizza pizza) {
            super(pizza);
        }
        
        @Override
        public String getDescription() {
            return pizza.getDescription() + ", olives";
        }
        
        @Override
        public double getCost() {
            return pizza.getCost() + 0.75;
        }
    }

    // Step 5: Client code using decorators
    public class PizzaShop {
        public static void main(String[] args) {
            // Create a Margherita with extra cheese and mushrooms
            Pizza pizza1 = new MushroomDecorator(new ExtraCheeseDecorator(new MargheritaPizza()));
            System.out.println(pizza1.getDescription() + " $" + pizza1.getCost());
            
            // Create a Vegetarian with olives and extra cheese
            Pizza pizza2 = new OliveDecorator(new ExtraCheeseDecorator(new VegetarianPizza()));
            System.out.println(pizza2.getDescription() + " $" + pizza2.getCost());
            
            // Create a Margherita with all toppings
            Pizza pizza3 = new PepperoniDecorator(
                            new OliveDecorator(
                            new MushroomDecorator(
                                new ExtraCheeseDecorator(
                                new MargheritaPizza()))));
            System.out.println(pizza3.getDescription() + " $" + pizza3.getCost());
        }
    }
    ```
7. **Facade Pattern**: A structural design pattern that provides a simplified interface to a library, a framework, or any other complex set of classes.

    Example: Let's you go to a hotel in paris for the olympics. Now, the hotel you're staying at offers multiple services like - Room service, spa, laundry and restraunt table reservation. Inorder to use these services you need to go through the hotel service extension and dial for the appropriate services or you can just call the help desk and they would redirect your request to the appropriate service. Here the hotel help desk acts like a Facade abstracting all the complex subsytems of the hotel.

    ```Java
    // Anti-pattern: Direct interaction with each hotel service
    class RoomService {
        public void orderFood(String room, String meal) {
            System.out.println("Room " + room + ": Ordering " + meal + " from Room Service");
            System.out.println("Room Service: Checking availability...");
            System.out.println("Room Service: Processing payment...");
            System.out.println("Room Service: Food will be delivered in 30 minutes");
        }
    }

    class SpaService {
        public void bookTreatment(String room, String treatment, String time) {
            System.out.println("Room " + room + ": Booking " + treatment + " at " + time);
            System.out.println("Spa: Checking available therapists...");
            System.out.println("Spa: Checking available time slots...");
            System.out.println("Spa: Treatment confirmed");
        }
    }

    class LaundryService {
        public void pickupClothes(String room, boolean expressService) {
            System.out.println("Room " + room + ": Requesting laundry pickup");
            System.out.println("Laundry: Scheduling pickup...");
            System.out.println("Laundry: " + (expressService ? "Express" : "Standard") + " service selected");
            System.out.println("Laundry: Pickup scheduled for next 30 minutes");
        }
    }

    class RestaurantService {
        public void reserveTable(String room, int guests, String time) {
            System.out.println("Room " + room + ": Reserving table for " + guests + " at " + time);
            System.out.println("Restaurant: Checking availability...");
            System.out.println("Restaurant: Processing reservation...");
            System.out.println("Restaurant: Table reserved");
        }
    }

    // Client code (hotel guest) - needs to know about each service
    public class HotelGuest {
        public static void main(String[] args) {
            String roomNumber = "512";
            
            // Guest needs to know about and directly interact with each service
            RoomService roomService = new RoomService();
            roomService.orderFood(roomNumber, "Croque Monsieur");
            
            SpaService spaService = new SpaService();
            spaService.bookTreatment(roomNumber, "Deep Tissue Massage", "2:00 PM");
            
            LaundryService laundryService = new LaundryService();
            laundryService.pickupClothes(roomNumber, true);
            
            RestaurantService restaurantService = new RestaurantService();
            restaurantService.reserveTable(roomNumber, 2, "8:00 PM");
        }
    }
    ```

    Solution: Facade design pattern helps client navigate this complicated setup by - 
    1. Guests interact with a single point of contact (concierge) rather than multiple departments
    2. Enhanced Guest Experience: The concierge knows how to coordinate between services
    
    ```Java
    // Step 1: Keep the service subsystem classes (unchanged from above)
    class RoomService {
        public void orderFood(String room, String meal) {
            System.out.println("Room " + room + ": Ordering " + meal + " from Room Service");
            System.out.println("Room Service: Checking availability...");
            System.out.println("Room Service: Processing payment...");
            System.out.println("Room Service: Food will be delivered in 30 minutes");
        }
    }

    class SpaService {
        public void bookTreatment(String room, String treatment, String time) {
            System.out.println("Room " + room + ": Booking " + treatment + " at " + time);
            System.out.println("Spa: Checking available therapists...");
            System.out.println("Spa: Checking available time slots...");
            System.out.println("Spa: Treatment confirmed");
        }
    }

    class LaundryService {
        public void pickupClothes(String room, boolean expressService) {
            System.out.println("Room " + room + ": Requesting laundry pickup");
            System.out.println("Laundry: Scheduling pickup...");
            System.out.println("Laundry: " + (expressService ? "Express" : "Standard") + " service selected");
            System.out.println("Laundry: Pickup scheduled for next 30 minutes");
        }
    }

    class RestaurantService {
        public void reserveTable(String room, int guests, String time) {
            System.out.println("Room " + room + ": Reserving table for " + guests + " at " + time);
            System.out.println("Restaurant: Checking availability...");
            System.out.println("Restaurant: Processing reservation...");
            System.out.println("Restaurant: Table reserved");
        }
    }

    // Step 2: Create the Facade class (Hotel Concierge)
    class ConciergeService {
        private RoomService roomService;
        private SpaService spaService;
        private LaundryService laundryService;
        private RestaurantService restaurantService;
        
        public ConciergeService() {
            this.roomService = new RoomService();
            this.spaService = new SpaService();
            this.laundryService = new LaundryService();
            this.restaurantService = new RestaurantService();
        }
        
        // Simple methods that delegate to the appropriate service
        public void orderFood(String room, String meal) {
            roomService.orderFood(room, meal);
        }
        
        public void bookSpa(String room, String treatment, String time) {
            spaService.bookTreatment(room, treatment, time);
        }
        
        public void requestLaundry(String room, boolean express) {
            laundryService.pickupClothes(room, express);
        }
        
        public void bookRestaurant(String room, int guests, String time) {
            restaurantService.reserveTable(room, guests, time);
        }
        
        // Complex combined services
        public void arrangeRomanticEvening(String room) {
            System.out.println("\n=== Arranging Romantic Evening Package ===");
            roomService.orderFood(room, "Champagne and Chocolates");
            restaurantService.reserveTable(room, 2, "8:00 PM");
            // Additional services like room decoration, etc.
            System.out.println("=== Romantic Evening Package Arranged ===\n");
        }
        
        public void arrangeOlympicDayPlan(String room) {
            System.out.println("\n=== Arranging Olympic Day Package ===");
            roomService.orderFood(room, "High-Protein Breakfast");
            requestLaundry(room, true); // Express service for sports clothes
            restaurantService.reserveTable(room, 2, "7:00 PM");
            bookSpa(room, "Sports Massage", "5:00 PM");
            // Additional Olympic-specific services
            System.out.println("=== Olympic Day Package Arranged ===\n");
        }
    }

    // Step 3: Client code using the facade
    public class HotelGuest {
        public static void main(String[] args) {
            String roomNumber = "512";
            
            // Guest only interacts with the concierge
            ConciergeService concierge = new ConciergeService();
            
            // Individual services through the facade
            concierge.orderFood(roomNumber, "Croque Monsieur");
            concierge.bookSpa(roomNumber, "Deep Tissue Massage", "2:00 PM");
            
            // Specialized package through the facade
            concierge.arrangeOlympicDayPlan(roomNumber);
        }
    }
    ```

8. **Observer pattern**: A Behavioral design pattern, where one object's state changes, all objects that depend on it are notified and updated automatically. Symbolizes 1-to-many relationship.

    Example: I have subscribed to Mr.Beast's Youtube channel that has millions of subscribers alongside me. Whenever a new content is added to the channel, all the subscribers get notified about the new content so that they can go and check out the new video for themselves. How can you update the state of all the subscribers at the same time?

    ```Java
    // Anti-pattern: God Class (does everything)
    // This violates Single Responsibility Principle and has tight coupling
    public class YouTubeSystem {
        private static YouTubeSystem instance = new YouTubeSystem(); // Singleton instance
        private String[] subscriberEmails = new String[1000]; // Fixed array size
        private int subscriberCount = 0;
        private String channelName = "MrBeast";
        
        // Private constructor (Singleton pattern misused)
        private YouTubeSystem() {}
        
        // Global access point
        public static YouTubeSystem getInstance() {
            return instance;
        }
        
        // Add subscriber with no validation
        public void addSubscriber(String email) {
            // No validation for email format
            // No check for existing subscribers
            if (subscriberCount < subscriberEmails.length) {
                subscriberEmails[subscriberCount] = email;
                subscriberCount++;
                System.out.println("Added subscriber: " + email);
            } else {
                System.out.println("Cannot add more subscribers - array is full!");
                // No proper error handling
            }
        }
        
        // Tight coupling with email service
        public void uploadVideo(String videoTitle) {
            System.out.println("New video uploaded: " + videoTitle);
            
            // Directly sending emails - tight coupling
            for (int i = 0; i < subscriberCount; i++) {
                sendEmail(subscriberEmails[i], videoTitle);
            }
            
            // Also posting to database
            updateDatabase(videoTitle);
            
            // Also updating UI
            refreshUI(videoTitle);
        }
        
        private void sendEmail(String email, String videoTitle) {
            // Hard-coded email logic
            System.out.println("EMAIL TO " + email + ": New video '" + videoTitle + 
                            "' published on " + channelName + " channel!");
            // No error handling for failed emails
        }
        
        private void updateDatabase(String videoTitle) {
            System.out.println("Database updated with new video: " + videoTitle);
            // Direct database access with no abstraction
        }
        
        private void refreshUI(String videoTitle) {
            System.out.println("UI refreshed to show new video: " + videoTitle);
            // Direct UI manipulation with no abstraction
        }
        
        // Client code
        public static void main(String[] args) {
            YouTubeSystem system = YouTubeSystem.getInstance();
            
            // Adding subscribers
            system.addSubscriber("user1@example.com");
            system.addSubscriber("user2@example.com");
            
            // Uploading video
            system.uploadVideo("$1,000,000 Challenge");
        }
    }
    ```
    Solution: Observer design pattern can be used to notify all the users. Here's a stepwise breakdown of the process.

    1. The Subscriber interface defines the update() method that all concrete subscribers must implement.
    2. The User class is a concrete implementation of the Subscriber interface, representing YouTube users.
    3. The Channel interface defines methods for managing subscribers and sending notifications.
    4. The YouTubeChannel class is a concrete implementation of the Channel interface, representing a YouTube channel like MrBeast.
    5. When a new video is uploaded via the uploadVideo() method, the channel automatically notifies all subscribers by calling the notifySubscribers() method.

    ```Java
    import java.util.ArrayList;
    import java.util.List;

    // Observer interface
    interface Subscriber {
        void update(String channelName, String videoTitle);
    }

    // Concrete Observer
    class User implements Subscriber {
        private String username;
        
        public User(String username) {
            this.username = username;
        }
        
        @Override
        public void update(String channelName, String videoTitle) {
            System.out.println(username + " received notification: New video '" + 
                            videoTitle + "' uploaded on " + channelName);
        }
    }

    // Subject interface
    interface Channel {
        void subscribe(Subscriber subscriber);
        void unsubscribe(Subscriber subscriber);
        void notifySubscribers();
    }

    // Concrete Subject (Observable)
    class YouTubeChannel implements Channel {
        private String channelName;
        private List<Subscriber> subscribers = new ArrayList<>();
        private String latestVideo;
        
        public YouTubeChannel(String channelName) {
            this.channelName = channelName;
        }
        
        @Override
        public void subscribe(Subscriber subscriber) {
            subscribers.add(subscriber);
            System.out.println("Added a new subscriber. Total subscribers: " + subscribers.size());
        }
        
        @Override
        public void unsubscribe(Subscriber subscriber) {
            subscribers.remove(subscriber);
            System.out.println("Removed a subscriber. Total subscribers: " + subscribers.size());
        }
        
        @Override
        public void notifySubscribers() {
            for (Subscriber subscriber : subscribers) {
                subscriber.update(channelName, latestVideo);
            }
        }
        
        // Business logic
        public void uploadVideo(String videoTitle) {
            this.latestVideo = videoTitle;
            System.out.println("\n" + channelName + " uploaded a new video: " + videoTitle);
            notifySubscribers();
        }
    }

    // Client code to test the pattern
    public class YouTubeNotificationSystem {
        public static void main(String[] args) {
            // Create a YouTube channel
            YouTubeChannel mrBeast = new YouTubeChannel("MrBeast");
            
            // Create subscribers
            Subscriber user1 = new User("Alex");
            Subscriber user2 = new User("Emma");
            Subscriber user3 = new User("Sophia");
            
            // Subscribe to the channel
            mrBeast.subscribe(user1);
            mrBeast.subscribe(user2);
            mrBeast.subscribe(user3);
            
            // Upload a new video - all subscribers are notified
            mrBeast.uploadVideo("Giving Away $1,000,000 to Random Subscribers");
            
            // Unsubscribe one user
            mrBeast.unsubscribe(user2);
            
            // Upload another video
            mrBeast.uploadVideo("Last To Leave Circle Wins $500,000");
        }
    }

    ```

9. **Stategy Pattern**: Allows you to define a set of algorithms and encapsulate them in independent classes so that they can be used interchangeably.

    Example: People are looking to find a gaming console for this black friday sale. They logged into amazon and found an item meeting their requirements. However, when they navigate to payments there's only one payment method i.e, using BitCoins. Now as a developer in Amazon how do modify the payment gateway inorder accept other payment methods without changing the existing implementation.

    ```Java
    // Anti-pattern: Hard-coded payment methods with conditional logic
    class AmazonPaymentGateway {
        private static final int PAYMENT_BITCOIN = 1;
        private static final int PAYMENT_CREDIT_CARD = 2;
        private static final int PAYMENT_PAYPAL = 3;
        
        // Payment details
        private String bitcoinAddress;
        private String creditCardNumber;
        private String creditCardCvv;
        private String creditCardExpiry;
        private String creditCardName;
        private String paypalEmail;
        
        // Selected payment method
        private int selectedPaymentMethod = PAYMENT_BITCOIN; // Default
        
        // Set Bitcoin payment details
        public void setBitcoinDetails(String bitcoinAddress) {
            this.bitcoinAddress = bitcoinAddress;
        }
        
        // Set Credit Card details
        public void setCreditCardDetails(String number, String cvv, String expiry, String name) {
            this.creditCardNumber = number;
            this.creditCardCvv = cvv;
            this.creditCardExpiry = expiry;
            this.creditCardName = name;
        }
        
        // Set PayPal details
        public void setPayPalDetails(String email) {
            this.paypalEmail = email;
        }
        
        // Select payment method
        public void selectPaymentMethod(int method) {
            // Validate method
            if (method != PAYMENT_BITCOIN && 
                method != PAYMENT_CREDIT_CARD && 
                method != PAYMENT_PAYPAL) {
                throw new IllegalArgumentException("Invalid payment method");
            }
            this.selectedPaymentMethod = method;
        }
        
        // Process payment with selected method
        public boolean processPayment(double amount) {
            // Using conditional logic for different payment methods
            switch (selectedPaymentMethod) {
                case PAYMENT_BITCOIN:
                    if (bitcoinAddress == null || bitcoinAddress.isEmpty()) {
                        throw new IllegalStateException("Bitcoin address not provided");
                    }
                    System.out.println("Processing Bitcoin payment of $" + amount + 
                                    " to address " + bitcoinAddress);
                    // Bitcoin payment logic
                    return true;
                    
                case PAYMENT_CREDIT_CARD:
                    if (creditCardNumber == null || creditCardNumber.isEmpty()) {
                        throw new IllegalStateException("Credit card details not provided");
                    }
                    String maskedCard = "xxxx-xxxx-xxxx-" + 
                                    creditCardNumber.substring(creditCardNumber.length() - 4);
                    System.out.println("Processing Credit Card payment of $" + amount + 
                                    " with card " + maskedCard);
                    // Credit card payment logic
                    return true;
                    
                case PAYMENT_PAYPAL:
                    if (paypalEmail == null || paypalEmail.isEmpty()) {
                        throw new IllegalStateException("PayPal email not provided");
                    }
                    System.out.println("Processing PayPal payment of $" + amount + 
                                    " with account " + paypalEmail);
                    // PayPal payment logic
                    return true;
                    
                default:
                    throw new IllegalStateException("Payment method not selected");
            }
        }
    }

    // Client code
    public class AmazonCheckoutAntiPattern {
        public static void main(String[] args) {
            double orderTotal = 569.98; // PS5 + game
            
            AmazonPaymentGateway paymentGateway = new AmazonPaymentGateway();
            
            // User selects Credit Card and enters details
            paymentGateway.selectPaymentMethod(2); // hardcoded constant
            paymentGateway.setCreditCardDetails(
                "1234567890123456", "123", "12/25", "John Doe");
            
            // Process payment
            boolean success = paymentGateway.processPayment(orderTotal);
            
            if (success) {
                System.out.println("Payment successful!");
            } else {
                System.out.println("Payment failed!");
            }
        }
    }
    ```

    Solution: Strategy design pattern helps solve this problem via composition. 

    1. Define a family of payment algorithms
    2. Make them interchangeable at runtime
    3. Add new payment methods without modifying existing code

    ```Java

    // Payment Strategy Interface
    interface PaymentStrategy {
        boolean pay(double amount);
        String getPaymentMethodName();
    }

    // Existing Bitcoin Payment implementation
    class BitcoinPaymentStrategy implements PaymentStrategy {
        private String bitcoinAddress;
        
        public BitcoinPaymentStrategy(String bitcoinAddress) {
            this.bitcoinAddress = bitcoinAddress;
        }
        
        @Override
        public boolean pay(double amount) {
            System.out.println("Paying " + amount + " using Bitcoin to address " + bitcoinAddress);
            // Bitcoin payment processing logic
            return true;
        }
        
        @Override
        public String getPaymentMethodName() {
            return "Bitcoin";
        }
    }

    // New Credit Card Payment implementation
    class CreditCardPaymentStrategy implements PaymentStrategy {
        private String cardNumber;
        private String cvv;
        private String expiryDate;
        private String nameOnCard;
        
        public CreditCardPaymentStrategy(String cardNumber, String cvv, String expiryDate, String nameOnCard) {
            this.cardNumber = cardNumber;
            this.cvv = cvv;
            this.expiryDate = expiryDate;
            this.nameOnCard = nameOnCard;
        }
        
        @Override
        public boolean pay(double amount) {
            System.out.println("Paying " + amount + " using Credit Card: " + maskCreditCard(cardNumber));
            // Credit card payment processing logic
            return true;
        }
        
        private String maskCreditCard(String cardNumber) {
            return "xxxx-xxxx-xxxx-" + cardNumber.substring(cardNumber.length() - 4);
        }
        
        @Override
        public String getPaymentMethodName() {
            return "Credit Card";
        }
    }

    // New PayPal Payment implementation
    class PayPalPaymentStrategy implements PaymentStrategy {
        private String email;
        
        public PayPalPaymentStrategy(String email) {
            this.email = email;
        }
        
        @Override
        public boolean pay(double amount) {
            System.out.println("Paying " + amount + " using PayPal account: " + email);
            // PayPal payment processing logic
            return true;
        }
        
        @Override
        public String getPaymentMethodName() {
            return "PayPal";
        }
    }

    // Context - Payment Processor
    class PaymentProcessor {
        private PaymentStrategy paymentStrategy;
        
        // Default constructor can set default payment method or leave it null
        public PaymentProcessor() {}
        
        // Set payment strategy at runtime
        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }
        
        // Process payment using the selected strategy
        public boolean processPayment(double amount) {
            if (paymentStrategy == null) {
                throw new IllegalStateException("Payment method not selected");
            }
            return paymentStrategy.pay(amount);
        }
        
        // Get available payment methods
        public List<String> getAvailablePaymentMethods() {
            List<String> methods = new ArrayList<>();
            methods.add("Bitcoin");
            methods.add("Credit Card");
            methods.add("PayPal");
            // New payment methods can be added here without changing existing code
            return methods;
        }
    }

    // Shopping Cart that uses the payment processor
    class ShoppingCart {
        private List<Item> items;
        private PaymentProcessor paymentProcessor;
        
        public ShoppingCart() {
            this.items = new ArrayList<>();
            this.paymentProcessor = new PaymentProcessor();
        }
        
        public void addItem(Item item) {
            items.add(item);
        }
        
        public double calculateTotal() {
            return items.stream().mapToDouble(Item::getPrice).sum();
        }
        
        public boolean checkout(PaymentStrategy paymentStrategy) {
            double total = calculateTotal();
            paymentProcessor.setPaymentStrategy(paymentStrategy);
            return paymentProcessor.processPayment(total);
        }
        
        public List<String> getAvailablePaymentMethods() {
            return paymentProcessor.getAvailablePaymentMethods();
        }
    }

    // Item class
    class Item {
        private String id;
        private String name;
        private double price;
        
        public Item(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
        
        public String getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public double getPrice() {
            return price;
        }
    }

    // Client code
    public class AmazonCheckout {
        public static void main(String[] args) {
            // Create shopping cart
            ShoppingCart cart = new ShoppingCart();
            
            // Add gaming console to cart
            cart.addItem(new Item("PS5-001", "PlayStation 5 Console", 499.99));
            cart.addItem(new Item("PS5-GAME-001", "Spider-Man 2", 69.99));
            
            System.out.println("Items in cart:");
            System.out.println("Total: $" + cart.calculateTotal());
            
            System.out.println("\nAvailable payment methods:");
            for (String method : cart.getAvailablePaymentMethods()) {
                System.out.println("- " + method);
            }
            
            // User selects payment method
            System.out.println("\nUser selects Credit Card payment method");
            PaymentStrategy creditCardPayment = new CreditCardPaymentStrategy(
                "1234567890123456", "123", "12/25", "John Doe");
            
            // Process checkout with selected payment method
            boolean paymentSuccess = cart.checkout(creditCardPayment);
            
            if (paymentSuccess) {
                System.out.println("Payment successful! Your order has been placed.");
            } else {
                System.out.println("Payment failed. Please try again.");
            }
        }
    }
    ```