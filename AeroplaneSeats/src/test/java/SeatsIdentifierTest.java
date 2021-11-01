import org.junit.Test;
import static org.junit.Assert.*;

public class SeatsIdentifierTest {

    @Test
    public void test_identifySeats() {
        int seats[][] = {{2,3}, {3,4}, {3,2}, {4,3}};
        SeatsIdentifier.identifySeats(seats);
        assertTrue(SeatsIdentifier.allSeats.size()>0);
    }

    @Test
    public void test_createSeats() {
        int size = SeatsIdentifier.allSeats.size();

        SeatsIdentifier.createSeat(4,1,2, "Window");
        assertTrue(SeatsIdentifier.allSeats.size() == size+1);
    }
}
