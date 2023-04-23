package com.example.lesson62.dao;

import com.example.lesson62.entity.ILikeIt;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class LikeItDao extends BaseDao {
    public LikeItDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS likes(" +
                "id SERIAL PRIMARY KEY," +
                "user_id INTEGER NOT NULL DEFAULT '0'," +
                "publication_id INTEGER NOT NULL DEFAULT '0'," +
                "likeTime TIMESTAMP NOT NULL DEFAULT '2000-01-01 10:10:10'," +
                "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE," +
                "FOREIGN KEY (publication_id) REFERENCES publications(id) ON DELETE CASCADE );" +
                "INSERT INTO likes (user_id, publication_id, likeTime) \n" +
                "VALUES \n" +
                "('1', '3', '2022-03-20 10:30:00'),\n" +
                "('2', '2', '2022-04-20 10:40:00'),\n" +
                "('3', '1', '2022-05-20 10:50:00');\n");
    }

    public boolean checkFromLike(Long postId, Long id) {
        String sql = String.format("select * from likes where publication_id = '%s' and user_id = '%s'", postId, id);
        var list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ILikeIt.class));
        return list.size() == 0;
    }

    public boolean likePublication(Long id, Long postId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        if (checkFromLike(postId, id)) {
            jdbcTemplate.update("INSERT INTO likes (user_id, publication_id, likeTime) \n" +
                    "VALUES \n" +
                    "('" + id + "' '" + postId + "' '" + timestamp + "')");
            return true;
        } else {
            return false;
        }
    }
}
