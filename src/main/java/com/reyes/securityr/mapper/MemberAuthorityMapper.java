package com.reyes.securityr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.reyes.securityr.model.MemberAuthority;

@Mapper
public interface MemberAuthorityMapper {

	@Insert("""
			    INSERT INTO member_authority (member_id, authority, created_at)
			    VALUES (#{memberId}, #{authority}, GETDATE())
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(MemberAuthority authority);

	@Delete("DELETE FROM member_authority WHERE member_id = #{memberId}")
	int deleteByMemberId(Long memberId);

	@Select("SELECT * FROM member_authority WHERE member_id = #{memberId}")
	List<MemberAuthority> findByMemberId(Long memberId);

}
