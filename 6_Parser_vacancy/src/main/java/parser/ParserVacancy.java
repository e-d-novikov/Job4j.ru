package parser;

import org.quartz.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class ParserVacancy implements Job {

    private String quartz;

    private ParserVacancy() {
        try {
            getQuartz();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Parser.checkNewRecords();
    }

    private void getQuartz() throws IOException {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            props.load(stream);
        }
        quartz = props.getProperty("time");
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
}
