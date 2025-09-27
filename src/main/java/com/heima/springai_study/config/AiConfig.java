package com.heima.springai_study.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
    @Bean
    public ChatClient deepseelChatClient(DeepSeekChatModel deepSeekChatModel){
        return ChatClient.create(deepSeekChatModel);
    }
}
