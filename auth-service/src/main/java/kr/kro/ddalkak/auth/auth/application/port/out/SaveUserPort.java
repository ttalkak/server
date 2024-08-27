package kr.kro.ddalkak.auth.auth.application.port.out;

public interface SaveUserPort {
    /**
     * 사용자 정보를 저장한다.
     *
     * @param username 사용자 ID
     * @param password 비밀번호
     * @param email 이메일
     * @param userRole 사용자 권한
     */
    void save(String username, String password, String email, String userRole);
}
