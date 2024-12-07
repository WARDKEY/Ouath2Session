package spring.oauth2session.OAuth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class CustomCLientRegistrationRepository {

    private final SocialClientRegistration socialClientRegistration;

    public CustomCLientRegistrationRepository(SocialClientRegistration socialClientRegistration) {
        this.socialClientRegistration = socialClientRegistration;
    }


    // 저장할 때 인메모리에 값을 저장하는 방식과 JDBC로 DB에 연결하여 값을 저장하는 방식이 있는데 등록할 서비스가
    // 보통 10개 이하라서 인메모리에 저장해도 문제 없다.
    public ClientRegistrationRepository clientRegistrationRepository(){
        return new InMemoryClientRegistrationRepository(socialClientRegistration.naverClientRegistration());
//        return new InMemoryClientRegistrationRepository(socialClientRegistration.naverClientRegistration(), socialClientRegistration.googleClientRegostration());
    }

}
