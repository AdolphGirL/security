package com.reyes.securityr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.reyes.securityr.model.Member;

@Mapper
public interface MemberMapper {

	@Select("SELECT id, username, password, authorities FROM member")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "username", column = "username"),
			@Result(property = "password", column = "password"),
			@Result(property = "authorities", column = "authorities", javaType = List.class, typeHandler = org.apache.ibatis.type.ArrayTypeHandler.class) })
	List<Member> findAll();

	@Select("SELECT id, username, password, authorities FROM member WHERE id = #{id}")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "username", column = "username"),
			@Result(property = "password", column = "password"),
			@Result(property = "authorities", column = "authorities", javaType = List.class, typeHandler = org.apache.ibatis.type.ArrayTypeHandler.class) })
	Member findById(String id);

	@Insert("INSERT INTO member (id, username, password, authorities) VALUES (#{id}, #{username}, #{password}, #{authorities, typeHandler=org.apache.ibatis.type.ArrayTypeHandler})")
	void insert(Member member);

	@Delete("DELETE FROM member WHERE id = #{id}")
	void delete(String id);

	@Select("SELECT id, username, password, authorities FROM member WHERE username = #{username}")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "username", column = "username"),
			@Result(property = "password", column = "password"),
			@Result(property = "authorities", column = "authorities", javaType = List.class, typeHandler = org.apache.ibatis.type.ArrayTypeHandler.class) })
	Member findByUsername(@Param("username") String username);

}