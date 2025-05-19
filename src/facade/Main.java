package facade;

public class Main {
  public static void main(String[] args) {
    // Create instances of the subsystem components
    Amplifier amp = new Amplifier("Top-O-Line Amplifier");
    DvdPlayer dvd = new DvdPlayer("Top-O-Line DVD Player", amp);
    Projector projector = new Projector("Top-O-Line Projector", dvd);
    TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
    // Create other components...

    // Create the Facade, providing it with the subsystem components
    HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, dvd, projector, lights);

    // Use the Facade to perform complex tasks with simple method calls
    System.out.println("--- Watching a movie ---");
    homeTheater.watchMovie("Raiders of the Lost Ark");

    System.out.println("\n--- Ending the movie ---");
    homeTheater.endMovie();
  }
}
