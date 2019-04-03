package brackets;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * The class checks the correctness of the location of the parentheses
 * in the string and outputs either a list of open parentheses or a list
 * of all pairs of parentheses.
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1
 * @since 0.1
 */
public class Brackets {
    /**
     * Base brackets.
     */
    private final char[] bracket = new char[]{'[', ']', '{', '}', '(', ')'};
    /**
     * Array with pair brackets.
     */
    private ArrayList<PairBrackets> pairBrackets = new ArrayList<PairBrackets>();
    /**
     * Array with brackets.
     */
    private ArrayList<Bracket> brackets = new ArrayList<Bracket>();
    /**
     * Scanner.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Start.
     * @param args - args.
     */
    public static void main(String[] args) {
        String string = ask("Введите строку: ");
        new Brackets().parsing(string);
    }
    /**
     * The method carries all the parentheses from the string to the array.
     * @param string - expression.
     */
    private void toMassive(String string) {
        char[] simbols = string.toCharArray();
        for (int i = 0; i < simbols.length; i++) {
            for (int j = 0; j < 6; j++) {
                if (simbols[i] == bracket[j]) {
                    brackets.add(new Bracket(bracket[j], i));
                }
            }
        }
    }
    /**
     * The method moves the parentheses to an array.
     */
    private void  pairBrackets() {
        for (int i = 0; i < this.brackets.size() - 1; i++) {
            if (brackets.get(i).getBracket() == '[' && brackets.get(i + 1).getBracket() == ']'
                    || brackets.get(i).getBracket() == '{' && brackets.get(i + 1).getBracket() == '}'
                    || brackets.get(i).getBracket() == '(' && brackets.get(i + 1).getBracket() == ')') {
                pairBrackets.add(new PairBrackets(brackets.get(i), this.brackets.get(i + 1), 1));
                brackets.remove(i);
                brackets.remove(i);
                i = -1;
            }
        }
    }
    /**
     * The method sorts pairs of parentheses.
     */
    private void sort() {
        for (int i = pairBrackets.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (pairBrackets.get(j).getFirst().getIndex() > pairBrackets.get(j + 1).getFirst().getIndex()) {
                    PairBrackets g = pairBrackets.get(j);
                    pairBrackets.set(j, pairBrackets.get(j + 1));
                    pairBrackets.set(j + 1, g);
                }
            }
        }
    }
    /**
     * The method performs the nesting group definition.
     */
    private void group() {
        for (int i = 0; i < pairBrackets.size(); i++) {
            for (int j = 0; j < pairBrackets.size(); j++) {
                if (pairBrackets.get(i).getFirst().getIndex() > pairBrackets.get(j).getFirst().getIndex()
                        && pairBrackets.get(i).getSecond().getIndex() < pairBrackets.get(j).getSecond().getIndex()) {
                    pairBrackets.get(i).setGroup(pairBrackets.get(i).getGroup() + 1);
                }
            }
        }
    }
    /**
     * The method checks the string for errors. Displays a list of errors, or a list of matching brackets.
     * @param string - expression.
     */
    private void parsing(String string) {
        toMassive(string);
        pairBrackets();
        if (brackets.size() > 0) {
            System.out.println("Строка имеет не закрытые скобки: \n № п/п | Скобка | Индекс");
            for (int i = 0; i < brackets.size(); i++) {
                System.out.printf("%5d %7s %8d%n", i + 1, brackets.get(i).getBracket(), brackets.get(i).getIndex());
            }
        } else {
            sort();
            group();
            System.out.println("№ п/п | Группа | 1 скобка | 2 скобка");
            for (int i = 0; i < pairBrackets.size(); i++) {
                System.out.printf("%4d %7d %6s %3d %6s %3d%n", i + 1, pairBrackets.get(i).getGroup(), pairBrackets.get(i).getFirst().getBracket(), pairBrackets.get(i).getFirst().getIndex(), pairBrackets.get(i).getSecond().getBracket(), pairBrackets.get(i).getSecond().getIndex());
            }
        }
    }
    /**
     * Method for interacting with the user.
     * @param question - expression.
     * @return - list of errors, or a list of matching brackets.
     */
    public static String ask(String question) {
        System.out.print(question);
        return scanner.next();
    }
}
