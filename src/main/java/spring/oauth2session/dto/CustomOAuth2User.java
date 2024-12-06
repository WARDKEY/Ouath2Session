package spring.oauth2session.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

    private final Oauth2Response oauth2Response;

    private final String role;

    public CustomOAuth2User(Oauth2Response oauth2Response, String role) {
        this.oauth2Response = oauth2Response;
        this.role = role;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

//        collection.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return role;
//            }
//        });

        // 람다로 변경 가능
        collection.add((GrantedAuthority) () -> role);

        return collection;
    }

    @Override
    public String getName() {
        return oauth2Response.getName();
    }

    // 외부 인증 서버에서 가져온 데이터에는 아이디(userName)으로 사용할만한 것이 없어서 새로 생성
    public String getUserName(){
        return oauth2Response.getProvider() + " "+ oauth2Response.getProviderId();
    }

}
