package com.threedsoft.util.service;

import java.time.LocalDateTime;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.threedsoft.util.dto.events.WMSEvent;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventPublisher {
	private final MessageChannel messageChannel;

	public EventPublisher(MessageChannel messageChannel) {
	        this.messageChannel = messageChannel;
	    }

	public void publish(WMSEvent event) {
		event.setCreatedDttm(LocalDateTime.now());
		log.info("Sending event {}", event);
		MessageHeaderAccessor msgHdrAccessor = new MessageHeaderAccessor();
		msgHdrAccessor.copyHeadersIfAbsent(event.getHeaderMap());
		messageChannel.send(MessageBuilder.withPayload(event)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.setHeaders(msgHdrAccessor)
				.build());
	}
}
