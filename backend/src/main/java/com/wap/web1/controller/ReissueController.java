package com.wap.web1.controller;

import com.wap.web1.service.ReissueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReissueController {

    private final ReissueService reissueService;

    public ReissueController(ReissueService reissueService) {
        this.reissueService = reissueService;
    }

    @PostMapping("/auth/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        String refresh = request.getHeader("Refresh");

        if(refresh == null) {
            return new ResponseEntity<>("Refresh token is null",HttpStatus.BAD_REQUEST);
        }

        try{
            String tokens = reissueService.reissueTokens(refresh);

            String[] newToken = tokens.split(" ");
            response.setHeader("Authorization","Bearer " + newToken[0]);
            response.setHeader("Refresh",newToken[1]);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch(RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
