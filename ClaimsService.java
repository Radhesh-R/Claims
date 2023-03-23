package com.claims.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.claims.models.Claims;
import com.claims.models.ClaimsDTO;

@Service
public class ClaimsService {

	@Autowired NamedParameterJdbcTemplate temp;
	
	public List<Claims> getAllClaims(){		
		return temp.query("SELECT * FROM claims order by id desc", new ClaimsRowMapper());
	}
	
	public List<ClaimsDTO> searchClaims(){		
		return temp.query("SELECT c.*,fname,lname FROM claims c join members m on c.memberid=m.memberid order by id desc", new ClaimsDTORowMapper());
	}
	
	public long totalClaims() {
		return temp.queryForObject("SELECT count(*) from claims",getSqlParameterByModel(null),Long.class);
	}
	
	public long totalPending() {
		return temp.queryForObject("SELECT count(*) from claims where status='Pending'",getSqlParameterByModel(null),Long.class);
	}
	
	public long totalApproved() {
		return temp.queryForObject("SELECT count(*) from claims where status='Approved'",getSqlParameterByModel(null),Long.class);
	}
	
	public Claims findClaimByMemberId(String memberid){
		Map<String,Object> map=new HashMap<>();
		map.put("memberid",memberid);
		try {
		return temp.queryForObject("SELECT * FROM claims where memberid=:memberid", map,
				new ClaimsRowMapper());
		}catch(Exception ex) {
			return null;
		}
	}
	
	public void updateDocs(int claimid,String doc1,String doc2,String doc3) {
		final String sql="insert into documents(claim_id,doc1,doc2,doc3) "
				+ "values(:claim_id,:doc1,:doc2,:doc3)";
		Map<String,Object> map=new HashMap<>();
		map.put("claim_id",claimid);
		map.put("doc1",doc1);
		map.put("doc2",doc2);
		map.put("doc3",doc3);
		temp.update(sql, map);
	}
	
	//add category
	public void saveClaims(Claims c) {
		c.setStatus("Pending");
		final String sql="insert into claims(memberid,req_date,final_amount,reason,process_date,status) "
				+ "values(:memberid,:req_date,:final_amount,:reason,:process_date,:status)";
		temp.update(sql, getSqlParameterByModel(c));
	}
	
	//UPDATE CLAIM
	public void updateClaims(Claims c) {
		final String sql="update claims set status=:status,rej_reason=:rej_reason where memberid=:memberid";
		temp.update(sql, getSqlParameterByModel(c));
	}
	
	private SqlParameterSource getSqlParameterByModel(Claims a) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (a != null) {
            ps.addValue("memberid", a.getMemberid());
            ps.addValue("req_date", a.getReq_date());
            ps.addValue("process_date", a.getProcess_date());
            ps.addValue("final_amount", a.getFinal_amount());
            ps.addValue("reason", a.getReason());
            ps.addValue("status", a.getStatus());
            ps.addValue("rej_reason", a.getRej_reason());
            ps.addValue("id", a.getId());
        }
        return ps;
    }
	
	private class ClaimsRowMapper implements RowMapper<Claims>{

		@Override
		public Claims mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Claims claim=new Claims();
			claim.setMemberid(rs.getString("memberid"));
			claim.setId(rs.getInt("id"));
			claim.setReq_date(rs.getString("req_date"));
			claim.setProcess_date(rs.getDate("process_date").toLocalDate());
			claim.setFinal_amount(rs.getInt("final_amount"));
			claim.setStatus(rs.getString("status"));
			claim.setReason(rs.getString("reason"));
			claim.setRej_reason(rs.getString("rej_reason"));
			return claim;
		}
		
	}
	
	private class ClaimsDTORowMapper implements RowMapper<ClaimsDTO>{

		@Override
		public ClaimsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			ClaimsDTO claim=new ClaimsDTO();
			claim.setMemberid(rs.getString("memberid"));
			claim.setId(rs.getInt("id"));
			claim.setReq_date(rs.getString("req_date"));
			claim.setProcess_date(rs.getDate("process_date").toLocalDate());
			claim.setFinal_amount(rs.getInt("final_amount"));
			claim.setStatus(rs.getString("status"));
			claim.setReason(rs.getString("reason"));
			claim.setFname(rs.getString("fname"));
			claim.setLname(rs.getString("lname"));
			claim.setRej_reason(rs.getString("rej_reason"));
			return claim;
		}
		
	}
}
