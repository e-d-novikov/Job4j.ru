package b.oop.a.tracker;

import b.oop.a.tracker.inputs.StubInput;
import b.oop.a.tracker.inputs.ValidateInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
/**
 * Class ValidateInputTest check for errors when entering incorrectly.
 * @author Egor Novikov (enovikovdev@gmail.com)
 * @version 1
 * @since 18/06/2018
 */
public class ValidateInputTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;
    public String l = System.lineSeparator();

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInputOne() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"invalid", "1"}));
        input.ask("Enter", new int[] {1});
        assertThat(this.mem.toString(), is(String.format("To select an action, use the numbers 1 to 7" + l)));
    }
}