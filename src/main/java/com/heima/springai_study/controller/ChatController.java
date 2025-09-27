package com.heima.springai_study.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heima.springai_study.entity.History;
import com.heima.springai_study.service.IHistoryService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ChatController {

    private final DeepSeekChatModel chatModel;
    @Autowired
    private IHistoryService historyService;
    @Resource
    private EmbeddingModel embeddingModel;
    @Resource
    private VectorStore vectorStore;
    @Resource
    private ChatClient chatClient;

    @Autowired
    public ChatController(DeepSeekChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Resource
    public ChatClient client;
    @GetMapping("/ai/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return client.prompt("你是白胡子船队下的第三船队队长砖石乔兹，你作为ai助手要对用户发起的请求配合剧情回答").call().content();
    }

    @GetMapping(value = "/ai/generateStream",produces = "text/html;charset=UTF-8")
	public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message,
                                       @RequestParam(value="sessionId",defaultValue = "1")Long sessionId)      {

        List<Document> documents = vectorStore.similaritySearch(message);
        StringBuilder contextBuilder=new StringBuilder();
        documents.forEach(document -> {
            contextBuilder.append(document).append("\n");
        });
        History userHistory=new History();
        userHistory.setContent(message);
        userHistory.setRole("user");
        userHistory.setDatetime(LocalDateTime.now());
        userHistory.setSessionId(sessionId);
        String promote="你是编程助手，世界上最强海贼白胡子!，以下是本次会话传递的信息:"+contextBuilder.toString()+
                "\n请根据以上内容进行展开，回答下面的问题";

        historyService.save(userHistory);
        List<History> historyList = historyService.list(new LambdaQueryWrapper<History>().eq(History::getSessionId,sessionId));
        List<Message> userMessageList = historyList.stream().map(history->
            history.getRole().equals("user")?new UserMessage(history.getContent()):new AssistantMessage(history.getContent())).collect(Collectors.toList());
        StringBuilder[] builder={new StringBuilder()};

        Flux<String> content = client.prompt(promote).user(message).messages(userMessageList).stream().content();
        return content.doOnNext(s->builder[0].append(s))
                .doOnComplete(()->{
                    History assistentHistory=new History();
                    assistentHistory.setSessionId(sessionId);
                    assistentHistory.setRole("assistant");
                    assistentHistory.setContent(builder[0].toString());
                    assistentHistory.setDatetime(LocalDateTime.now());
                    historyService.save(assistentHistory);
                });

    }
}