package kr.kro.ttalkak.deployment.deployment.application.outputport;

import kr.kro.ttalkak.deployment.deployment.domain.DatabaseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseOutputPort {

    public DatabaseEntity save(DatabaseEntity databaseEntity);
}
