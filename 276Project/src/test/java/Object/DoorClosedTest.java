package Object;

import org.junit.Test;

public class DoorClosedTest {

    @Test
    public void createDoorClosed(){
        DoorClosed d = new DoorClosed();
        assert(d instanceof DoorClosed);

    }
}
