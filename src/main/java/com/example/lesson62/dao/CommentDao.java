package com.example.lesson62.dao;

import com.example.lesson62.dto.CommentDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CommentDao extends BaseDao {

    public CommentDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<CommentDto> getCommentsFromPublication() {
        String sql = "select * from comments";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CommentDto.class));
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS comments(" +
                "id SERIAL PRIMARY KEY," +
                "commentText TEXT NOT NULL DEFAULT 'text'," +
                "commentTime TIMESTAMP NOT NULL DEFAULT '2000-01-01 10:10:10'," +
                "publication_id INTEGER," +
                "user_id INTEGER," +
                "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE," +
                "FOREIGN KEY (publication_id) REFERENCES publications(id) ON DELETE CASCADE);" +
                "INSERT INTO comments (commentText, commentTime, publication_id, user_id) \n" +
                "VALUES \n" +
                "('some text', '2022-03-20 10:30:00', '1', '1'),\n" +
                "('some text', '2022-04-20 10:40:00', '2', '2'),\n" +
                "('some text', '2022-05-20 10:50:00', '2', '3');\n");
    }

    public void addComment(Long userId, Long postId, String text) {
        String sql = "INSERT INTO comments (commentText, commentTime, publication_id, user_id) VALUES (?, ?, ?, ?)";
        Object[] params = new Object[]{text, Timestamp.valueOf(LocalDateTime.now()), postId, userId};
        jdbcTemplate.update(sql, params);
    }
}
