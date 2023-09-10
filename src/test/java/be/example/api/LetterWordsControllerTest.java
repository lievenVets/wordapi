package be.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LetterWordsController.class)
class LetterWordsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void letterWordHappyFlow() throws Exception {
        mockMvc.perform(post("/api/letter-words/combinations")
                        .content("""
                                [
                                \"barber\",
                                \"ba\",
                                \"rber\"
                                ]
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [\"ba+rber=barber\"]
                         """));
    }
}