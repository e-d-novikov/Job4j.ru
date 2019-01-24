package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static void checkNewRecords() throws IOException {
        Document document  = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Integer countPages = Integer.valueOf(document.select("table.sort_options > tbody > tr > td").get(2).getElementsByTag("a").get(9).text());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uu, HH:mm");
        DateTimeFormatter yest = DateTimeFormatter.ofPattern("вчера, HH:mm");
        DateTimeFormatter today = DateTimeFormatter.ofPattern("сегодня, HH:mm");
        LocalDate nowDate = LocalDate.now();
        LocalDate yesterday = nowDate.minusDays(1);
        LocalDateTime lastRecord = VacancyStorage.getInstance().getLastRecordDate();
        for (int i = 1; i <= countPages; i++) {
            Document page  = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
            Elements tr = page.select("table.forumTable > tbody > tr");
            Element td;
            for (int j = 1; j < tr.size(); j++) {
                td = tr.get(j);
                String dateTime = td.getElementsByTag("td").get(5).text();
                LocalDateTime date;
                if (dateTime.contains("вчера")) {
                    LocalTime time = LocalTime.parse(dateTime, yest);
                    date = LocalDateTime.of(yesterday, time);
                } else if (dateTime.contains("сегодня")) {
                    LocalTime time = LocalTime.parse(dateTime, today);
                    date = LocalDateTime.of(nowDate, time);
                } else if (dateTime.contains("май")) {
                    String newDateTime = dateTime.replace("май", "мая");
                    date = LocalDateTime.parse(newDateTime, formatter);
                } else {
                    date = LocalDateTime.parse(td.getElementsByTag("td").get(5).text(), formatter);
                }
                if (date.isAfter(lastRecord)) {
                    Element element = td.getElementsByTag("a").get(0);
                    String name = element.text();
                    String link = element.attr("href");
                    String text;
                    if (name.contains("Java") || name.contains("java") || name.contains("JAVA")) {
                        if (name.contains("script") || name.contains("Script") || name.contains("SCRIPT") || name.contains("nodejs")) {
                            continue;
                        } else {
                            Document vac = Jsoup.connect(link).get();
                            Elements vactr = vac.select("table.msgTable > tbody > tr");
                            text = vactr.get(1).getElementsByTag("td").get(1).text();
                            VacancyStorage.getInstance().createNewRecord(new Vacancy(name, text, link, Timestamp.valueOf(date)));
                        }
                    }
                }
            }
        }
    }
}
