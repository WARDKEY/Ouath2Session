package spring.oauth2session.OAuth2;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Component;

@Component
public class SocialClientRegistration {

    public ClientRegistration naverClientRegistration() {

        return ClientRegistration.withRegistrationId("naver")   // 어떤 소셜인지
                .clientId("${CLIENT_ID}")   // 클라이언트 아이디
                .clientSecret("${CLIENT_SECRET}")   // 클라이언트 비밀번호
                .redirectUri("http://localhost:8080/login/oauth2/code/naver")   // 리다이렉트 url
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)  // 어떤 방식으로 받을건지
                .scope("name", "email") // 무엇을 받을지
                .authorizationUri("https://nid.naver.com/oauth2.0/authorize")   // 인증 url
                .tokenUri("https://nid.naver.com/oauth2.0/token")   // token을 받을 url
                .userInfoUri("https://openapi.naver.com/v1/nid/me") // 사용자 정보를 받을 url
                .userNameAttributeName("response")  // 응답 데이터의 키
                .build();
    }

//    public ClientRegistration googleClientRegistration() {
//
//        return ClientRegistration.withRegistrationId("google")
//                .clientId("아이디")
//                .clientSecret("비밀번호")
//                .redirectUri("http://localhost:8080/login/oauth2/code/google")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .scope("profile", "email")
//                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//                .issuerUri("https://accounts.google.com")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .build();
//    }

}
