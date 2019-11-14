package com.vshershnov.CurrencyEXService.model;

import com.vshershnov.CurrencyEXService.utils.TimestampUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author vshershnov
 *
 * Mapper used by JdbcTemplate method to generate model object
 */
public class CurrencyPairMapper implements RowMapper<CurrencyPair> {

	@Override
	public CurrencyPair mapRow(ResultSet rs, int rowNum) throws SQLException {
		TimestampUtils timestampUtils = new TimestampUtils();
		CurrencyPair curr = new CurrencyPair();		
		curr.setId(rs.getInt("id"));
		curr.setFromCurr(rs.getString("fromCurr"));
		curr.setToCurr(rs.getString("toCurr"));
		curr.setRate(rs.getBigDecimal("rate"));
		curr.setRateTime(timestampUtils.getISO8601StringForDate(rs.getTimestamp("rateTime")));
		curr.setSourceID(rs.getString("sourceID"));
		return curr;
	}
}
