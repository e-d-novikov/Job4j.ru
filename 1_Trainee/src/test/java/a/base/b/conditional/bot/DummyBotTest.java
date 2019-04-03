package a.base.b.conditional.bot;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * In the class DummyBotTest tests methods from the class DummyBot.
 *
 * @author Egor Novikov
 * E-mail: enovikovdev@gmail.com
 * @version 1$
 * @since 0.1
 */
public class DummyBotTest {

  @Test
  public void whenGreetBot() {
    DummyBot bot = new DummyBot();
    assertThat(
            bot.answer("Привет, Бот."),
            is("Привет, умник.")
    );
  }

  @Test
  public void whenByuBot() {
    DummyBot bot = new DummyBot();
    assertThat(
            bot.answer("Пока."),
            is("До скорой встречи.")
    );
  }

  @Test
  public void whenUnknownBot() {
    DummyBot bot = new DummyBot();
    assertThat(
            bot.answer("Сколько будет 2 + 2?"),
            is("Это ставит меня в тупик. Спросите другой вопрос.")
    );
  }
}
