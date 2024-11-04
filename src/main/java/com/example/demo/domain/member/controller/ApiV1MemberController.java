package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.request.MemberRequest;
import com.example.demo.domain.member.dto.response.MemberResponse;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.global.RsData.RsData;
import com.example.demo.global.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "ApiV1MemberController", description = "회원 인증인가 API")
public class ApiV1MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @PostMapping("/join")
    public RsData<MemberResponse> join (@Valid @RequestBody MemberRequest memberRequest) {
        Member member = this.memberService.join(memberRequest.getUsername(), memberRequest.getPassword());
        return RsData.of("200", "회원가입이 완료되었습니다.", new MemberResponse(member));
    }

    @PostMapping("/login")
    public RsData<MemberResponse> login (@Valid @RequestBody MemberRequest memberRequest, HttpServletResponse res) {

        Member member = this.memberService.getMember(memberRequest.getUsername());

        String accessToken = jwtProvider.genAccessToken(member);
        res.addCookie(new Cookie("accessToken", accessToken));
        
        return RsData.of("200", "토큰 발급 성공: " + accessToken , new MemberResponse(member));
    }

    @GetMapping("/me")
    public RsData<MemberResponse> me (HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String accessToken = "";
        for (Cookie cookie : cookies) {
            if ("accessToken".equals(cookie.getName())) {
                accessToken = cookie.getValue();
            }
        }

        Map<String, Object> claims =  jwtProvider.getClaims(accessToken);
        String username = (String) claims.get("username");
        Member member = this.memberService.getMember(username);

        return RsData.of("200", "내 회원정보", new MemberResponse(member));
    }
}
