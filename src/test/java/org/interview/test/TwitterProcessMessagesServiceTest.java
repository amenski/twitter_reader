package org.interview.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.interview.domain.Author;
import org.interview.domain.Message;
import org.interview.service.ITwitterService;
import org.interview.service.TwitterProcessMessagesServiceImpl;
import org.junit.Test;

//@RunWith(MockitoJUnitRunner.class)
public class TwitterProcessMessagesServiceTest {

    ITwitterService processMessageService = new TwitterProcessMessagesServiceImpl();
    
    @Test
    public void processMessageGroupByAuthorSuccess() throws Exception {
        //GIVEN
        List<Message> messages = getMockMessages1();
        messages.addAll(getMockMessages2());
        Map<String, List<Message>>  response = processMessageService.processMessages(messages);
        
        //VALIDATE
        assertEquals(3, response.get("bizzjustbieber").size());
        assertEquals(1, response.get("rauhlsbreaker").size());
    }
    
    @Test
    public void processMessageOderMessagesAscSuccess() throws Exception {
        //GIVEN
        List<Message> messages = getMockMessages2();
        
        
        Map<String, List<Message>>  response = processMessageService.processMessages(messages);
        List<String> sortedMessageIds = response.get("bizzjustbieber").stream().map(Message::getId).collect(Collectors.toList());
        
        // VALIDATE
        assertThat(sortedMessageIds).containsExactly(messages.get(1).getId(), messages.get(2).getId(), messages.get(0).getId());
    }
    
    private List<Message> getMockMessages1() {
        List<Message> messages = new ArrayList<>();
        Author author1 = new Author();
        author1.setId("2597339000");
        author1.setName("Ovy");
        author1.setScreenName("rauhlsbreaker");
        author1.setCreatedAt(1638187200000L);
        

        Message msg4 = new Message();
        msg4.setId("1464986685490356227");
        msg4.setText("my biggest flex is loving Justin Bieber so much that every time people hear or see him they’re forced to think of me.");
        msg4.setCreatedAt(1638187200000L);
        msg4.setAuthor(author1);
        

        messages.add(msg4);
        return messages;
    }
    private List<Message> getMockMessages2() {
        List<Message> messages = new ArrayList<>();
        Author author = new Author();
        author.setId("1438118910071615488");
        author.setName("Beliber");
        author.setScreenName("bizzjustbieber");
        author.setCreatedAt(1638187200000L);

        
        Message msg1 = new Message(); //3rd with asc
        msg1.setId("1464986643459366916");
        msg1.setText("RT @haileybieberbrs: Em seu Instagram Stories, Hailey Bieber publicou vídeos apoiando pequenas empresas e empresas lideradas por mulheres.");
        msg1.setCreatedAt(1638187200000L);
        msg1.setAuthor(author);
        
        Message msg2 = new Message(); //1 with asc
        msg2.setId("1464986600878784519");
        msg2.setText("RT @sprinkle_hoe: Justin bieber smiling is one of the most purest beautiful thing ever existed in this world .🖤 https://t.co/AqOjJAnQNw");
        msg2.setCreatedAt(1638187200000L);
        msg2.setAuthor(author);
        
        Message msg3 = new Message(); //2 with asc
        msg3.setId("1464986621690925059");
        msg3.setText("Justin The King Bieber");
        msg3.setCreatedAt(1638187200000L);
        msg3.setAuthor(author);
        
        
        messages.add(msg1);
        messages.add(msg2);
        messages.add(msg3);
        return messages;
    }
}
