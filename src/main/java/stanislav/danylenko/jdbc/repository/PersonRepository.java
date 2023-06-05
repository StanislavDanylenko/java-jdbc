package stanislav.danylenko.jdbc.repository;

import stanislav.danylenko.jdbc.config.HikariCPDataSource;
import stanislav.danylenko.jdbc.exceptions.DbException;
import stanislav.danylenko.jdbc.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private static final String GET_ALL = "SELECT * FROM person;";
    private static final String FIND_BY_NAME = "SELECT * FROM person WHERE name ILIKE ?;";
    private static final String SAVE = "INSERT INTO person(name, age, is_married) VALUES (?, ?, ?);";
    private static final String UPDATE =
            """
            UPDATE person
            SET name = ?, age = ?, is_married = ?
            WHERE id = ?
            """;
    private static final String DELETE_BY_ID = "DELETE FROM person WHERE id = ?;";

    public List<Person> findAll() {
        List<Person> people = new ArrayList<>();
        try (Connection con = HikariCPDataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL)) {
            while (rs.next()) {
                people.add(new Person(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getBoolean(4),
                        rs.getTimestamp(5).toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new DbException("Failed to get all people", e);
        }
        return people;
    }

    public List<Person> findByName(String name) {
        List<Person> people = new ArrayList<>();
        try (Connection con = HikariCPDataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_NAME)) {
            ps.setString(1, "%"+ name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    people.add(new Person(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getBoolean(4),
                            rs.getTimestamp(5).toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DbException("Failed to get people by name", e);
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

    public void update(Person person) {
        try (Connection con = HikariCPDataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE)) {
            int k = 0;
            ps.setString(++k, person.getName());
            ps.setInt(++k, person.getAge());
            ps.setBoolean(++k, person.getMarried());
            ps.setLong(++k, person.getId());
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Failed to update person");
            }
        } catch (SQLException e) {
            throw new DbException("Failed to update person", e);
        }
    }

    public boolean deleteById(Long id) {
        try (Connection con = HikariCPDataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_BY_ID)) {
            ps.setLong(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DbException("Failed to delete person", e);
        }
    }

}
