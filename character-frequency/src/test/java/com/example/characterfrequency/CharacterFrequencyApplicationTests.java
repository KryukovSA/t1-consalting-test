package com.example.characterfrequency;

import com.example.characterfrequency.controller.CharacterFrequencyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CharacterFrequencyController.class)
class CharacterFrequencyApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterFrequencyController characterFrequencyController;

    @Test
    public void testGetCharacterFrequency() throws Exception {
        String inputString = "aaaaabcccc";
        Map<Character, Integer> expectedResponse = Map.of('a', 5, 'c', 4, 'b', 1);

        when(characterFrequencyController.getCharacterFrequency(anyString()))
                .thenReturn(expectedResponse);

        mockMvc.perform(get("/character-frequency")
                        .param("inputString", inputString))
                .andExpect(jsonPath("$.a").value(expectedResponse.get('a')))
                .andExpect(jsonPath("$.b").value(expectedResponse.get('b')))
                .andExpect(jsonPath("$.c").value(expectedResponse.get('c')));
    }

    @Test
    public void testGetCharacterFrequencyEmptyString() throws Exception {
        String inputString = "";
        Map<Character, Integer> expectedResponse = Map.of();

        when(characterFrequencyController.getCharacterFrequency(anyString()))
                .thenReturn(expectedResponse);

        mockMvc.perform(get("/character-frequency")
                        .param("inputString", inputString))
                .andExpect(jsonPath("$").isEmpty());
    }

}
