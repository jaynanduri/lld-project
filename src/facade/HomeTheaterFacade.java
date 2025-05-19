package facade;

// 2. Facade: Provides a simplified interface to the home theater subsystem
public class HomeTheaterFacade {
  // The facade holds references to all subsystem components
  Amplifier amp;
  DvdPlayer dvd;
  Projector projector;
  TheaterLights lights;
  // Other components like Screen, PopcornPopper can be added

  public HomeTheaterFacade(Amplifier amp, DvdPlayer dvd, Projector projector, TheaterLights lights) {
    this.amp = amp;
    this.dvd = dvd;
    this.projector = projector;
    this.lights = lights;
  }

  // Simplified method to watch a movie
  public void watchMovie(String movie) {
    System.out.println("Get ready to watch a movie...");
    lights.dim(10);           // Dim lights
    projector.on();           // Turn on projector
    projector.wideScreenMode(); // Set projector mode
    amp.on();                 // Turn on amplifier
    amp.setDvd(dvd);          // Set amplifier input to DVD
    amp.setSurroundSound();   // Set surround sound
    amp.setVolume(5);         // Set volume
    dvd.on();                 // Turn on DVD player
    dvd.play(movie);          // Play the movie
  }

  // Simplified method to end the movie
  public void endMovie() {
    System.out.println("Shutting down the home theater...");
    dvd.stop();     // Stop DVD player
    dvd.off();      // Turn off DVD player
    amp.off();      // Turn off amplifier
    projector.off(); // Turn off projector
    lights.on();    // Turn on lights
  }

  // Other simplified methods could be added, e.g., listenToCd(), watchTv()
}