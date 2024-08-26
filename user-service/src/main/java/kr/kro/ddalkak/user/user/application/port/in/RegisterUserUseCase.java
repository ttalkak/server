package kr.kro.ddalkak.user.user.application.port.in;

public interface RegisterUserUseCase {
    /**
     * 사용자 등록 처리를 한다.
     *
     * @param command {@link RegisterCommand} 사용자 등록 정보
     */
    void register(RegisterCommand command);
}
