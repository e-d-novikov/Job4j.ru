package d.servlet.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Location {

    private static final Logger LOG = LoggerFactory.getLogger(Location.class);
    private static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> location = new LinkedHashMap<>();
    private String url = "jdbc:postgresql://localhost:5432/location";
    private String username = "postgres";
    private String password = "password";
    private Connection connection = null;
    private static Location instance = new Location();

    private Location() {
        createList();
    }

    public static Location getInstance() {
        return instance;
    }

    private void createList() {
        String sql = "SELECT country.name AS country, region.name AS region, city.name AS city FROM country "
        + "LEFT JOIN region ON country.id = region.country_id "
        + "LEFT JOIN city ON region.id = city.region_id";
        ResultSet result = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                addCity(result.getString("country"), result.getString("region"), result.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    private void addCity(String country, String region, String city) {
        if (location.containsKey(country)) {
            if (location.get(country).containsKey(region)) {
                location.get(country).get(region).add(city);
            } else {
                ArrayList<String> cities = new ArrayList<>();
                cities.add(city);
                location.get(country).put(region, cities);
            }
        } else {
            ArrayList<String> cities = new ArrayList<>();
            cities.add(city);
            LinkedHashMap<String, ArrayList<String>> regions = new LinkedHashMap<>();
            regions.put(region, cities);
            location.put(country, regions);
        }
    }

    public ArrayList<String> getCountries() {
        return new ArrayList<>(location.keySet());
    }

    public ArrayList<String> getRegions(String country) {
        return new ArrayList<>(location.get(country).keySet());
    }

    public ArrayList<String> getCities(String country, String region) {
        return location.get(country).get(region);
    }
}
