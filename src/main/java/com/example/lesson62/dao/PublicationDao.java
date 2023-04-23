package com.example.lesson62.dao;

import com.example.lesson62.entity.Publication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PublicationDao extends BaseDao {

    public PublicationDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<Publication> getPublicationOfOtherUsers(String email) {
        String sql = String.format("select * from publications where user_id <> '%s'", email);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    public List<Publication> selectAllPublications() {
        String sql = "select * from publications";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS publications(" +
                "id SERIAL PRIMARY KEY," +
                "imageLink TEXT," +
                "description TEXT," +
                "publicationTime TIMESTAMP NOT NULL DEFAULT '2000-01-01 10:10:10'," +
                "likes INTEGER NOT NULL DEFAULT '0'," +
                "user_id INTEGER," +
                "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE);" +
                "INSERT INTO publications (imageLink, description, publicationTime, likes, user_id) \n" +
                "VALUES \n" +
                "('index_bg.jpg', 'some text', '2022-03-20 10:30:00', '0', '1'),\n" +
                "('index_bg.jpg', 'some text', '2022-04-20 10:40:00', '0', '2'),\n" +
                "('index_bg.jpg', 'some text', '2022-05-20 10:50:00', '0', '3');\n");
    }

    public void save(String fileName, String description, Integer userId) {
        String sql = "insert into publications(imageLink, description, publicationTime, likes, user_id) " +
                "values(?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fileName);
            ps.setString(2, description);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, 0);
            ps.setInt(5, userId);
            return ps;
        });
    }

    public void deleteById(Long id) {
        String sql = "delete from publications " +
                "where id = ?";
        jdbcTemplate.update(sql);
    }

    public List<Publication> getPublicationById(Long userId) {
        String sql = String.format("SELECT * FROM publications where id = '%s'", userId);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Publication.class));
    }

    public void deleteCommentById(Long commentId) {
        String sql = "delete from comments " +
                "where id = ?";
        jdbcTemplate.update(sql);
    }

    public Long getPostLastId() {
        String sql = "SELECT id FROM publications ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
