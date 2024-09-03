package com.ttalkak.compute.compute.application.port.out

import com.ttalkak.compute.compute.adapter.out.cache.entity.ComputeUserCache

interface LoadComputePort {
    /**
     * 컴퓨터 정보 불러오기
     *
     * @param userId 사용자 ID
     * @return Unit
     */
    fun loadCompute(userId: Long)

    /**
     * 특정한 사용자가 현재 연결되어있는지 확인
     *
     * @param userId 사용자 ID
     * @return Boolean
     */
    fun isConnected(userId: Long): Boolean
}