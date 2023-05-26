package com.example.kakaologin.service;

import com.example.kakaologin.client.KakaoClient;
import com.example.kakaologin.dto.KakaoInfo;
import com.example.kakaologin.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoClient kakaoClient;

    @Value("${kakao.auth-url}")
    private String kakaoAuthUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirecctUrl;

    public KakaoInfo getInfo(final String code){
        //userAPI에서, 사용자가 동의한 정보를 가져옴
        final KakaoToken token = getToken(code);
        try {
            return kakaoClient.getInfo(new URI(kakaoUserApiUrl), token.getTokenType() +" "+ token.getAccessToken());
        } catch (Exception e){
            log.error("info error ", e);
            return KakaoInfo.fail();
        }

    }

    private KakaoToken getToken(final String code){
        //인증API에서, 토큰정보를 받아옴 (이게 먼저 실행)
        try{
            return kakaoClient.getToken(new URI(kakaoAuthUrl), restapiKey, redirecctUrl, code, "authorization_code");
        }catch (Exception e){
            log.error("token error ", e);
            return KakaoToken.fail();
        }
    }

}
