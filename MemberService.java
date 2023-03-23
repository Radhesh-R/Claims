package com.claims.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.claims.models.Insurances;
import com.claims.models.Member;

@Service
public class MemberService {
	@Autowired NamedParameterJdbcTemplate temp;
	
	public List<Member> getAllMembers(){		
		return temp.query("SELECT * FROM members order by created_on desc", new MemberRowMapper());
	}
	//add category
	public String saveMember(Member m) {
		String memberid=generateMemberId();
		m.setMemberid(memberid);
		final String sql="insert into members(memberid,fname,lname,phone,email,dob,gender,address,nominee_count,insurance_type,insurance_amount,max_claim_amount) "
				+ "values(:memberid,:fname,:lname,:phone,:email,:dob,:gender,:address,:nominee_count,:insurance_type,:insurance_amount,:max_claim_amount)";
		temp.update(sql, getSqlParameterByModel(m));
		return memberid;
	}
	
	//get Insurance List
	public List<Insurances> getInsurances(){
		final String sql="SELECT * FROM insurances";		
		return temp.query(sql, new BeanPropertyRowMapper(Insurances.class));
	}
	
	//get Insurance List	
	public Insurances getInsurancesById(int id){
		final String sql="SELECT * FROM insurances where id=:id";
		Map<String,Object> map=new HashMap<>();
		map.put("id",id);
		Insurances ins= temp.queryForObject(sql,map, new BeanPropertyRowMapper<Insurances>(Insurances.class));
		ins.setMax_claim_amount(ins.getInsurance_amount()*ins.getPercentage()/100);
		return ins;
	}
	
	//update category
	public void updateMember(Member m) {
		System.out.println(m);
		final String sql="update members set fname=:fname,lname=:lname,phone=:phone,email=:email,"
				+ "dob=:dob,gender=:gender,address=:address,nominee_count=:nominee_count where memberid=:memberid";
		temp.update(sql, getSqlParameterByModel(m));
	}
	
	public Member findByMemberId(String memberid) {
		Member p=new Member();
		p.setMemberid(memberid);
		try {
		return temp.queryForObject("SELECT * from members WHERE memberid=:memberid", 
				getSqlParameterByModel(p), new MemberRowMapper());
		}catch(Exception ex) {
			return null;
		}
	}
	
	public String generateMemberId() {
		return String.format("MBC-%05d", totalMembers()+1);
	}
	
	public long totalMembers() {
		return temp.queryForObject("SELECT count(*) from members",getSqlParameterByModel(null),Long.class);
	}
	
	private SqlParameterSource getSqlParameterByModel(Member a) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (a != null) {
            ps.addValue("memberid", a.getMemberid());
            ps.addValue("fname", a.getFname());
            ps.addValue("lname", a.getLname());
            ps.addValue("dob", a.getDob());
            ps.addValue("nominee_count", a.getNominee_count());
            ps.addValue("phone", a.getPhone());
            ps.addValue("address", a.getAddress());
            ps.addValue("citizen_type", a.getCitizen_type());
            ps.addValue("gender", a.getGender());
            ps.addValue("insurance_amount", a.getInsurance_amount());
            ps.addValue("email", a.getEmail());
            ps.addValue("insurance_type", a.getInsurance_type());
            ps.addValue("max_claim_amount", a.getMax_claim_amount());            
        }
        return ps;
    }
	
	private class MemberRowMapper implements RowMapper<Member>{

		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Member member=new Member();
			member.setMemberid(rs.getString("memberid"));
			member.setAddress(rs.getString("address"));
			member.setCitizen_type(rs.getString("citizen_type"));
			member.setDob(rs.getString("dob"));
			member.setFname(rs.getString("fname"));
			member.setLname(rs.getString("lname"));
			member.setPhone(rs.getString("phone"));
			member.setEmail(rs.getString("email"));
			member.setGender(rs.getString("gender"));
			member.setInsurance_amount(rs.getInt("insurance_amount"));
			member.setNominee_count(rs.getInt("nominee_count"));
			member.setMax_claim_amount(rs.getInt("max_claim_amount"));
			member.setInsurance_type(rs.getString("insurance_type"));
			return member;
		}
		
	}
}
