package com.example.lesson62.dao;

import com.example.lesson62.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao extends BaseDao {

    public UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<User> getUsersFromName(String name) {
        String sql = "select * from users where name like '%" + name + "%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersFromAccountName(String name) {
        String sql = "select * from users where accountName like '%" + name + "%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersFromEmail(String email) {
        String sql = "select * from users where email like '%" + email + "%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public String checkUserFromEmail(String email) {
        String sql = String.format("select * from users where email like '%s'", email);
        var user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User value : user) {
            if (value.getEmail().contains(email)) {
                return value.getEmail();
            }
        }
        User userEmail = new User();
        userEmail.setAccountName("d2ax21j424sdi21iih3o4c2348c24dn24d");
        return userEmail.getEmail();
    }

    public User checkUserFromAccountName(String accountName) {
        String sql = String.format("select * from users where accountName like '%s'", accountName);
        var user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User value : user) {
            if (value.getAccountName().contains(accountName)) {
                return value;
            }
        }
        User userName = new User();
        userName.setAccountName("d2ax21j424sdi21iih3o4c2348c24dn24d");
        return userName;
    }

    public boolean login(String usernameOrEmail, String password) {
        List<User> users;
        if (usernameOrEmail.contains("@")) {
            users = getUsersFromEmail(usernameOrEmail);
        } else {
            users = getUsersFromAccountName(usernameOrEmail);
        }
        if (!users.isEmpty()) {
            User user = users.get(0);
            return user.getEmail().contains(usernameOrEmail) || user.getPassword().contains(password);
        }
        return false;
    }


    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY," +
                "accountName VARCHAR(100) NOT NULL DEFAULT 'account name'," +
                "email VARCHAR(100) NOT NULL DEFAULT 'admin@gmail.com'," +
                "password VARCHAR(100) NOT NULL DEFAULT 'password'," +
                "postsCount INTEGER NOT NULL DEFAULT '0'," +
                "subscriptionsCount INTEGER NOT NULL DEFAULT '0'," +
                "followersCount INTEGER NOT NULL DEFAULT '0'," +
                "name VARCHAR(40) NOT NULL DEFAULT 'name'," +
                "surname VARCHAR(40) NOT NULL DEFAULT 'surname' );" +
                "INSERT INTO users (accountName, email, password, postsCount, subscriptionsCount," +
                "followersCount, name, surname) \n" +
                "VALUES \n" +
                "('qwer', 'john@gmail.com'," + "'" + new BCryptPasswordEncoder().encode("123") + "'" +
                ", '0', '0', '0', 'oroz', 'altyn'),\n" +
                "('qwer', 'emeli@gmail.com', " + "'" + new BCryptPasswordEncoder().encode("123") + "'" +
                ", '0', '0', '0', 'aman', 'zoevich'),\n" +
                "('qwer', 'one@gmail.com', " + "'" + new BCryptPasswordEncoder().encode("123") + "'" +
                ", '0', '0', '0', 'rus', 'rusovich');\n");
    }

    public void saveToBase(User user) {
        jdbcTemplate.update("INSERT INTO users (accountName, email, password) \n" +
                "VALUES \n" +
                "('" + user.getAccountName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "')");
    }

    public List<User> getByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email LIKE '" + email + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
