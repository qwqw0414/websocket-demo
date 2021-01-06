package com.example.webchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/***
	 * @Configuration - 클래스 선언 앞에 작성합니다. 이 어노테이션은 해당 클래스가 Bean의 설정을 할 것이라는 것을 나타냅니다.
	 * @EnableWebSocketMessageBroker - WebSocket 서버를 활성화하는 데 사용됩니다. implements
	 *                               WebSocketMessageBrokerConfigurer - 웹 소켓 연결을
	 *                               구성하기 위한 메서드를 구현하고 제공합니다. registerStompEndpoints
	 *                               메서드 - 클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드 포인트를
	 *                               등록합니다. 엔드 포인트 구성에 withSockJS ()를 사용합니다. SockJS는
	 *                               웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화하는 데 사용됩니다.
	 */

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic");
	}

}
