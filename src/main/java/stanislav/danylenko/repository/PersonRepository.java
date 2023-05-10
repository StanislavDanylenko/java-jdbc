package stanislav.danylenko.repository;

import stanislav.danylenko.config.HikariCPDataSource;
import stanislav.danylenko.exceptions.DbException;
import stanislav.danylenko.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private static final String GET_ALL = "SELECT * FROM person;";
    private static final String SAVE = "INSERT INTO person(name, age, is_married) VALUES (?, ?, ?);";

    public List<Person> findAll() {
        List<Person> people = new ArrayList<>();
        try (Connection con = HikariCPDataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL)) {
            while (rs.next()) {
                people.add(new Person(
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getBoolean(4),
                        rs.getTimestamp(5).toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new DbException("Failed to get all novels", e);
        }
        return people;
    }

    public void save(Person person) {
        try (Connection con = HikariCPDataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(SAVE)) {
            int k = 0;
            ps.setString(++k, person.getName());
            ps.setInt(++k, person.getAge());
            ps.setBoolean(++k, person.getMarried());
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Failed to save person");
            }
        } catch (SQLException e) {
            throw new DbException("Failed to save person", e);
        }
    }

}
