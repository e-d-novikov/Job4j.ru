package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class ParserVacancy implements AutoCloseable, Job {

    private static final Logger LOG = LogManager.getLogger(ParserVacancy.class.getName());

    private static LocalDateTime lastRecordDate;
    private static Connection connection;

    private static String url;
    private static String username;
    private static String password;
    private static String driver;
    private static String quartz;

    public ParserVacancy() {
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, URISyntaxException {
        File properties = new File(args[0]);
        lastRecordDate = LocalDateTime.now().minusYears(1);
        getProperties(properties);
        connection();
        createTable();
        if (checkTable()) {
            getLastRecordDate();
        }
        checkNewRecords();
    }

    private static void getProperties(File properties) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fin = new FileInputStream(properties)) {
            props.load(fin);
        }
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
        driver = props.getProperty("driver");
        quartz = props.getProperty("time");
    }

    private static void connection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }

    private static void checkNewRecords() throws IOException {
        Document document  = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Integer countPages = Integer.valueOf(document.select("table.sort_options > tbody > tr > td").get(2).getElementsByTag("a").get(9).text());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uu, HH:mm");
        DateTimeFormatter yest = DateTimeFormatter.ofPattern("вчера, HH:mm");
        DateTimeFormatter today = DateTimeFormatter.ofPattern("сегодня, HH:mm");
        LocalDate nowDate = LocalDate.now();
        LocalDate yesterday = nowDate.minusDays(1);
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
                if (date.isAfter(lastRecordDate)) {
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
                            createNewRecord(name, text, link, Timestamp.valueOf(date));
                        }
                    }
                }
            }
        }
    }

    private static void createNewRecord(String name, String text, String link, Timestamp date) {
        String sql = "INSERT INTO vacancy (name, text, link, date) VALUES(?, ?, ?, ?);";
        try (PreparedStatement ps  = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, text);
            ps.setString(3, link);
            ps.setTimestamp(4, date);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS vacancy("
                + "id serial primary key, "
                + "name text, "
                + "text text, "
                + "link text, "
                + "date timestamp);";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkTable() {
        boolean result = false;
        int count = 0;
        String sql = "SELECT count(*) FROM vacancy as count";
        try (PreparedStatement ps  = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            if (count > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void getLastRecordDate() {
        String sql = "SELECT max(date) FROM vacancy";
        try (PreparedStatement ps  = connection.prepareStatement(sql)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                lastRecordDate = result.getTimestamp("id").toLocalDateTime();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printVacancy() {
        String sql = "SELECT * FROM vacancy";
        try (PreparedStatement ps  = connection.prepareStatement(sql)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler sched = null;

        JobDetail job = newJob(ParserVacancy.class)
                .withIdentity("myJob", "group1")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .withSchedule(cronSchedule(quartz))
                .forJob("myJob", "group1")
                .build();

        try {
            sched = schedFact.getScheduler();
            sched.start();
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
