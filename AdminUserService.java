package com.claims.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.claims.models.Admin;

@Service
public class AdminUserService {

	@Autowired NamedParameterJdbcTemplate temp;
	
	public Admin validate(String userid,String pwd) {
		Admin a=new Admin(userid, pwd);
		try {
		return temp.queryForObject("SELECT * from users WHERE userid=:userid and pwd=:pwd", 
				getSqlParameterByModel(a),new AdminMapper());
		}
		catch(Exception ex) {
			//System.out.println("hii");
			return null;
		}
	}
	
	private SqlParameterSource getSqlParameterByModel(Admin a) {
		//System.out.println(a);
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (a != null) {
            ps.addValue("userid", a.getUserid());
            ps.addValue("pwd", a.getPwd());
        }
        //System.out.println(ps);
        return ps;
    }
	
	private class AdminMapper implements RowMapper<Admin>{

		@Override
		public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Admin admin=new Admin(rs.getString("userid"), rs.getString("pwd"));
			admin.setUname(rs.getString("uname"));
			//System.out.println(admin);
			return admin;
		}
		
	}
}
