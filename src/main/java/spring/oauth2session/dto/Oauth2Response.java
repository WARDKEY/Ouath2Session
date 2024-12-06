package spring.oauth2session.dto;

// 네이버나 구글에서 보내주는 응답값의 기본적인 틀
public interface Oauth2Response {

    // 제공자 이름 구글, 네이버 등
    String getProvider();

    // 제공자에서 발급해주는 번호
    String getProviderId();

    // 이메일
    String getEmail();

    // 사용자 실명
    String getName();
}
