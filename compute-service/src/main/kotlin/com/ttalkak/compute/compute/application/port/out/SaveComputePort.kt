package com.ttalkak.compute.compute.application.port.out

import com.ttalkak.compute.compute.domain.ComputerType

interface SaveComputePort {
    /**
     * 컴퓨터 정보 저장
     *
     * @param userId 사용자 ID
     * @param computeLimit 컴퓨터 제한
     * @param availablePortStart 사용 가능한 포트 시작
     * @param availablePortEnd 사용 가능한 포트 끝
     * @param computeType 컴퓨터 타입
     * @param maxMemory 최대 메모리
     * @return Unit
     */
    fun saveCompute(
        userId: Long,
        computeLimit: Int,
        availablePortStart: Int,
        availablePortEnd: Int,
        computeType: ComputerType,
        maxMemory: Int
    )

    /**
     * 컴퓨터 정보 삭제
     *
     * @param userId 사용자 ID
     * @return Unit
     */
    fun deleteCompute(userId: Long)

    /**
     * 컴퓨터 정보 수정
     *
     * @param userId 사용자 ID
     * @param computeLimit 컴퓨터 제한
     * @param availablePortStart 사용 가능한 포트 시작
     * @param availablePortEnd 사용 가능한 포트 끝
     * @param computeType 컴퓨터 타입
     * @param maxMemory 최대 메모리
     * @return Unit
     */
    fun updateCompute(
        userId: Long,
        computeLimit: Int,
        availablePortStart: Int,
        availablePortEnd: Int,
        computeType: ComputerType,
        maxMemory: Int
    )
}