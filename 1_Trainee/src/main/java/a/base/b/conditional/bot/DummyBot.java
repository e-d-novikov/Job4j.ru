package a.base.b.conditional.bot;

/**
 * Class implements elementary talking bot.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
public class DummyBot {
  /**
   * Elementary talking bot
   * @param question - question of the client
   * @return - answer.
   */
  public String answer(String question) {
    String rsl = "Это ставит меня в тупик. Спросите другой вопрос.";
    if ("Привет, Бот.".equals(question)) {
      rsl = "Привет, умник.";
    } else if ("Пока.".equals(question)) {
      rsl = "До скорой встречи.";
    }
    return rsl;
  }
}
