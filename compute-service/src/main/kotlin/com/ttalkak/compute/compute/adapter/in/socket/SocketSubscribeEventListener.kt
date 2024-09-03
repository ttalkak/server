package com.ttalkak.compute.compute.adapter.`in`.socket

import com.ttalkak.compute.common.SocketAdapter
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionSubscribeEvent
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent

@SocketAdapter
class SocketSubscribeEventListener {
    @EventListener
    fun handleSubscribeEvent(event: SessionSubscribeEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val topic = headerAccessor.destination
        println("SocketSubscribeEventListener: $event")
    }

    @EventListener
    fun handleUnSubscribeEvent(event: SessionUnsubscribeEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val topic = headerAccessor.destination
        println("SocketUnSubscribeEventListener: $event")
    }
}