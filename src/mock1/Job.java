package mock1;
import java.util.*;

public class Job {
    private int id;
    private JobStates state;
    private List<Event> events;
    protected int compute;

    public Job(int id, List<Event> events, int res) {
        this.id = id;
        this.events = events;
        this.state = JobStates.READY;
        this.compute = res;
    }

    
}
