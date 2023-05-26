package com.example.kakaologin.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //in kakao api naming method is snakecase strategy
public class KakaoInfo {
    private KakaoAccount kakaoAccount;

    public static KakaoInfo fail(){
        return null; //이부분 향후 필요한지 확인
    }
}
