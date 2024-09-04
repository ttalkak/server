package com.ttalkak.compute.compute.adapter.`in`.socket

import com.ttalkak.compute.common.SocketAdapter
import com.ttalkak.compute.compute.application.service.ConnectService
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import org.springframework.web.socket.messaging.SessionSubscribeEvent
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent

@SocketAdapter
class SocketSubscribeEventListener(
    private val connectService: ConnectService
) {
    @EventListener
    fun handleConnectEvent(event: SessionConnectEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val connectUserId = headerAccessor.getNativeHeader("X-USER-ID")?.first()?.toLong()
        val sessionId = headerAccessor.sessionId
        println("connectUserId: $connectUserId, sessionId: $sessionId")

        if (connectUserId != null && sessionId != null) {
            connectService.connect(connectUserId, sessionId)
        }
    }

    @EventListener
    fun handleDisConnectEvent(event: SessionDisconnectEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val sessionId = headerAccessor.sessionId

        if (sessionId != null) {
            connectService.disconnect(sessionId)
        }
    }
}