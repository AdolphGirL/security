package com.reyes.securityr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.reyes.securityr.model.Member;

@Mapper
public interface MemberMapper {

	@Insert("""
			    INSERT INTO member (username, password, enabled, created_at)
			    VALUES (#{username}, #{password}, #{enabled}, GETDATE())
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Member member);

	@Update("""
			    UPDATE member
			    SET username = #{username},
			        password = #{password},
			        enabled = #{enabled}
			    WHERE id = #{id}
			""")
	int update(Member member);

	@Delete("DELETE FROM member WHERE id = #{id}")
	int delete(Long id);

	@Select("SELECT * FROM member WHERE id = #{id}")
	Member findById(Long id);

	@Select("SELECT * FROM member WHERE username = #{username}")
	Member findByUsername(String username);

	@Select("SELECT * FROM member")
	List<Member> findAll();
}