package tobyspring.helloboot;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GreetingRepositoryJdbc implements GreetingRepository{

  private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
  private final JdbcTemplate jdbcTemplate;

  public GreetingRepositoryJdbc(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Greeting findGreeting(String name) {
    try{
      return jdbcTemplate.queryForObject("select * from greeting where name = '" + name + "'",
          new RowMapper<Greeting>() {
            @Override
            public Greeting mapRow(ResultSet rs, int rowNum) throws SQLException {
              return new Greeting(rs.getString("name"), rs.getInt("count"));
            }
          });
    }catch(DataAccessException exception){
      logger.debug("DataAccessException");
      return null;
    }
  }

  @Override
  public void increaseCount(String name) {
    Greeting greeting = findGreeting(name);
    if(greeting == null) {
      jdbcTemplate.update("insert into greeting values (?, ?)", name, 1);
    }else{
      jdbcTemplate.update("update greeting set count = ? where name = ? ", greeting.getCount()+1, greeting.getName());
    }
  }
}
