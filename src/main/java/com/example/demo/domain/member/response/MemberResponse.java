package com.example.demo.domain.member.response;

import com.example.demo.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private final Long id;
    private final String username;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public MemberResponse(Member article) {
        this.id = article.getId();
        this.username = article.getUsername();
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();
    }
}
