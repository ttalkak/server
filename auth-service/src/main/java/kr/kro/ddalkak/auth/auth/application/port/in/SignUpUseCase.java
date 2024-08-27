package kr.kro.ddalkak.auth.auth.application.port.in;

public interface SignUpUseCase {
    /**
     * 사용자 등록 처리를 한다.
     *
     * @param command {@link RegisterCommand} 사용자 등록 정보
     */
    void signUp(RegisterCommand command);
}
