package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2017_3_2Test {

    private final Solver2017_3_2 sut = new Solver2017_3_2();
    
    @Test
    public void testRealInput() {
        assertEquals(
            349975,
            Integer.parseInt(
                sut.solve(new InputUtil().getStringFromResourceFile("input-2017-3.txt").get())));
    }
}
