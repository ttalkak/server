package kr.kro.ttalkak.deployment.deployment.framework.jpaadapter;

import kr.kro.ttalkak.deployment.deployment.application.outputport.DatabaseOutputPort;
import kr.kro.ttalkak.deployment.deployment.domain.DatabaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DatabaseAdapter implements DatabaseOutputPort {

    private final DatabaseRepository databaseRepository;
    @Override
    public DatabaseEntity save(DatabaseEntity databaseEntity) {
        DatabaseEntity savedDatabaseEntity = databaseRepository.save(databaseEntity);
        return savedDatabaseEntity;
    }
}
