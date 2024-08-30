package kr.kro.ttalkak.deployment.deployment.adapter.out.persistence.entity;

import lombok.Getter;

@Getter
public enum DatabaseType {
    MYSQL, REDIS, POSTGRESQL, MONGODB, MARIADB
}
