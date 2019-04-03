package brackets;
/**
 * The class Bracket.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class Bracket {
    private char bracket;
    private int index;

    Bracket(char bracket, int index) {
        this.bracket = bracket;
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public char getBracket() {
        return this.bracket;
    }
}
