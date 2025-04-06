package com.wap.web1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyInfoDto {
    private Long id;
    private String email;
    private String nickname;
    private String profileImage;
    //그룹별 프사 다르다면 확인 후 코드수정
}
