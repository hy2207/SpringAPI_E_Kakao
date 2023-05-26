package com.example.kakaologin.controller;

import com.example.kakaologin.dto.KakaoAccount;
import com.example.kakaologin.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class KakaoController {
    private final KakaoService kakaoService;

    /**
     * https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}
     *
     *
     */
    @GetMapping("/callback")
    public KakaoAccount getKakaoAcount(@RequestParam("code") String code){
        log.debug("code={ }", code);
        return kakaoService.getInfo(code).getKakaoAccount();

    }
}
