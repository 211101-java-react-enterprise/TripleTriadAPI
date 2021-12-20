package com.revature.ttapi.websocket.config;//package com.revature.ttapi.game.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // connect to this endpoint with SockJS
        registry.addEndpoint("/triad-game-active").setAllowedOrigins("*").withSockJS();
   }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // the prefix to use for all stomp client sends
        registry.setApplicationDestinationPrefixes("/app");

        // subscribe to this endpoint after connecting
        registry.enableSimpleBroker("/game"); //Associated with requests to start multiplayer
    }



}
