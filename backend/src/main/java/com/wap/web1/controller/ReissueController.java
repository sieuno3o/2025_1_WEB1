package com.wap.web1.controller;

import com.wap.web1.domain.RefreshToken;
import com.wap.web1.jwt.JWTUtil;
import com.wap.web1.repository.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ReissueController {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public ReissueController(JWTUtil jwtUtil,RefreshRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @PostMapping("/auth/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        String refresh = request.getHeader("Refresh");

        if(refresh == null) {
            return new ResponseEntity<> ("refresh token null", HttpStatus.BAD_REQUEST);
        }

        try{
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("refresh token expired",HttpStatus.BAD_REQUEST);
        }
        // 토큰이 refresh인지 확인
        String category = jwtUtil.getCategory(refresh);

        if(!category.equals("refresh")) {
            return new ResponseEntity<> ("invaild refresh token", HttpStatus.BAD_REQUEST);
        }

        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if(!isExist) {
            return new ResponseEntity<>("invalid refresh token",HttpStatus.BAD_REQUEST);
        }

        String email = jwtUtil.getEmail(refresh);
        String role = jwtUtil.getRole(refresh);

        String newAccess =jwtUtil.createJwt("access",email,role,600000L);
        String newRefresh = jwtUtil.createJwt("refresh",email,role,86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshRepository.deleteByRefresh(refresh);
        addRefreshToken(email,newRefresh,86400000L);

        //response
        response.setHeader("Authorization","Bearer "+newAccess);
        response.setHeader("Refresh",newRefresh);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void addRefreshToken(String email,String refresh,Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshToken refreshToken = RefreshToken.builder()
                .email(email)
                .refresh(refresh)
                .expiration(date.toString())
                .build();

        refreshRepository.save(refreshToken);
    }
}
