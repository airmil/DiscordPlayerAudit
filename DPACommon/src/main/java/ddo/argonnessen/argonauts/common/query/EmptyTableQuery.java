/**
 * 
 */
package ddo.argonnessen.argonauts.common.query;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 */
@Repository
public class EmptyTableQuery {

	/**
	 * 
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	/**
	 * 
	 */
	public void execute() {
		jdbcTemplate.execute("delete from playerclass"); //$NON-NLS-1$
		jdbcTemplate.execute("delete from player"); //$NON-NLS-1$
		jdbcTemplate.execute("delete from location"); //$NON-NLS-1$
	}
}
