package com.heima.springai_study;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringAiStudyApplicationTests {
    @Resource
    private VectorStore vectorStore;
    @Test
    void contextLoads() {
//        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader("classpath:/海贼王连载28年.pdf",
//                PdfDocumentReaderConfig.builder()
//                        .withPageTopMargin(0)
//                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
//                                .withNumberOfTopTextLinesToDelete(0)
//                                .build())
//                        .withPagesPerDocument(1)
//                        .build());
//        TokenTextSplitter splitter = new TokenTextSplitter(1000, 400, 10, 100000, true);
//        splitter.apply(pdfReader.read()).forEach(document->{
//            vectorStore.accept(Arrays.asList(document));
//        });
        List<Document> taobao = vectorStore.similaritySearch("海军");
        taobao.forEach(System.out::println);
    }

}
