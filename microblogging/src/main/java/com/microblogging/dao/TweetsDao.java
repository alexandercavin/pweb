package com.microblogging.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("tweetsDao")
public class TweetsDao {
private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Tweets> getTweets() {
		
		final String getTweetsSql = "SELECT a.username, a.full_name, b.content, b.timestamp from users a right join tweets b ON a.user_id = b.user_id";

		return jdbc.query(getTweetsSql, new RowMapper<Tweets>() {

			public Tweets mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tweets tweets = new Tweets();

				tweets.setUsername(rs.getString("username"));
				tweets.setFull_name(rs.getString("full_name"));
				tweets.setContent(rs.getString("content"));
				tweets.setTimestamp(rs.getString("timestamp"));
				System.out.println(rs.getString("full_name"));
				return tweets;
			}

		});
	}
	
	public List<Tweets> getTweetsSelected(String inputDate) {
		
		final String getTweetsSql = "SELECT a.username, a.full_name, b.content, b.timestamp from users a right join tweets b ON a.user_id = b.user_id "
				+ "where b.timestamp >='" + inputDate + " 00:00:00'" 
				+ " AND b.timestamp <= '" + inputDate + " 23:59:59'";
		System.out.println(getTweetsSql);
		return jdbc.query(getTweetsSql, new RowMapper<Tweets>() {

			public Tweets mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tweets tweets = new Tweets();

				tweets.setUsername(rs.getString("username"));
				tweets.setFull_name(rs.getString("full_name"));
				tweets.setContent(rs.getString("content"));
				tweets.setTimestamp(rs.getString("timestamp"));
				System.out.println(rs.getString("full_name"));
				return tweets;
			}

		});
	}
	
	@Transactional
	public boolean createTweets(CreateTweets createTweets) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("username", createTweets.getUsername());
		params.addValue("content", createTweets.getContent());
		
		return jdbc.update("INSERT into tweets SELECT 0, user_id, :content, NOW() from users where username = :username", params) == 1;
	}
}
