package com.example.lesson62.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionDao extends BaseDao {
    public SubscriptionDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS subscriptions(" +
                "id SERIAL PRIMARY KEY," +
                "whoIsSigning INTEGER NOT NULL DEFAULT '0'," +
                "whoIsSubscribedTo INTEGER NOT NULL DEFAULT '0'," +
                "FOREIGN KEY (whoIsSubscribedTo) REFERENCES users(id) ON DELETE CASCADE," +
                "FOREIGN KEY (whoIsSigning) REFERENCES users(id) ON DELETE CASCADE );" +
                "INSERT INTO subscriptions (whoIsSigning, whoIsSubscribedTo) \n" +
                "VALUES \n" +
                "('1', '3'),\n" +
                "('2', '2'),\n" +
                "('3', '1');\n");
    }
}
