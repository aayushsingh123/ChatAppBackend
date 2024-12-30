package com.substring.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	//IN dono method ko samjhne ke liye OneNote(Springboot) par likhe hai details..
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) { //messagebroker ko configure karega
		// TODO Auto-generated method stub
		//WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
		config.enableSimpleBroker("/topic");//server eisper message bhej sakta hai
		config.setApplicationDestinationPrefixes("/app");
		
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		//WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
		registry.addEndpoint("/chat")// /chat ko user subscribe karlega and connection establised(set kiya gaya) hoga
		.setAllowedOrigins(AppConstants.FRONT_END_BASE_URL)
		.withSockJS();//withSockJS() Spring WebSocket mein fallback(Fallback ka matlab hai ek backup option ya alternative method ka use karna jab primary method fail ho jaye) mechanism enable karta hai, jo WebSocket ke fail hone par alternative communication methods (jaise XHR polling) ka use karta hai.
	}
      // /chat endpoint par connection aapka establish hoga

}
