package com.vshershnov.CurrencyEXService.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vshershnov.CurrencyEXService.utils.TimestampUtils;

public class CurrencyPairMapper implements RowMapper<CurrencyPair> {

	@Override
	public CurrencyPair mapRow(ResultSet rs, int rowNum) throws SQLException {
		TimestampUtils timestampUtils = new TimestampUtils();
		CurrencyPair curr = new CurrencyPair();		
		curr.setId(rs.getInt("id"));
		curr.setFromCurr(rs.getString("fromCurr"));
		curr.setToCurr(rs.getString("toCurr"));
		curr.setRate(rs.getBigDecimal("rate"));
		curr.setRateTime(timestampUtils.getISO8601StringForDate(rs.getDate("rateTime")));
		curr.setSourceID(rs.getString("sourceID"));
		return curr;
	}
}
