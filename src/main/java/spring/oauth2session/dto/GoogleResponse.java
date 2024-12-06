package spring.oauth2session.dto;

import java.util.Map;

public class GoogleResponse implements Oauth2Response {

    private final Map<String, Object> attribute;

    public GoogleResponse(Map<String, Object> attribute) {
        // 구글의 경우 그대로 응답 정보가 넘어오기 때문에 캐스팅 불필요
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
//        return "google";
        return null;
    }

    @Override
    public String getProviderId() {
//        return attribute.get("sub").toString();
        return null;
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
