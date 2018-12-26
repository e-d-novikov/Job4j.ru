package brackets;
/**
 * The class PairBrackets.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class PairBrackets {
    private Bracket first;
    private Bracket second;
    private int group;

    PairBrackets(Bracket first, Bracket second, int group) {
        this.first = first;
        this.second = second;
        this.group = group;
    }

    public Bracket getFirst() {
        return first;
    }

    public Bracket getSecond() {
        return second;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
