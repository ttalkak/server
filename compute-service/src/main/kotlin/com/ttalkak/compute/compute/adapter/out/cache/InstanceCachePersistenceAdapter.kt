package com.ttalkak.compute.compute.adapter.out.cache

import com.ttalkak.compute.common.PersistenceAdapter
import com.ttalkak.compute.compute.adapter.out.cache.repository.InstanceCacheRepository

@PersistenceAdapter
class InstanceCachePersistenceAdapter (
    private val instanceCacheRepository: InstanceCacheRepository
) {

}