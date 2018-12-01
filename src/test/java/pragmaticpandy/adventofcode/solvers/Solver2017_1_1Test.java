package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2017_1_1Test {

    private final Solver2017_1 sut = new Solver2017_1(1);
    
    @Test
    public void testRealInput() {
        assertEquals(
            1177,
            Integer.parseInt(
                sut.solve(new InputUtil().getStringFromResourceFile("input-2017-1.txt").get())));
    }
}
