package spring.oauth2session.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import spring.oauth2session.dto.CustomOAuth2User;
import spring.oauth2session.dto.NaverResponse;
import spring.oauth2session.dto.Oauth2Response;
import spring.oauth2session.entity.UserEntity;
import spring.oauth2session.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // request를 통해 구글이나, 카카오, 네이버의 데이터가 넘어옴

        Oauth2Response oauth2Response = null;

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        // registerationId를 통해 어떤 인증인지 받아옴(구글, 카카오, 네이버 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        if (registrationId.equals("naver")) {
            oauth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
//        else if (registrationId.equals("google")) {
//          oauth2Response = new GoogleResponse(oAuth2User.getAttributes());
//        }
        else {
            return null;
        }

        String userName = oauth2Response.getProvider()+" "+oauth2Response.getProviderId();

        UserEntity existData = userRepository.findByUserName(userName);

        String role = "ROLE_USER";

        // DB에 유저가 없는 경우 (처음 로그인)
        if (existData == null){
            UserEntity user = new UserEntity();
            user.setUserName(userName);
            user.setEmail(oauth2Response.getEmail());
            user.setRole(role);

            userRepository.save(user);

        }else {
            // 새로 업데이트된 이메일을 업데이트
            existData.setEmail(oauth2Response.getEmail());
            existData.setUserName(userName);
            existData.setRole(role);

            userRepository.save(existData);
        }


        return new CustomOAuth2User(oauth2Response, role);
    }
}
