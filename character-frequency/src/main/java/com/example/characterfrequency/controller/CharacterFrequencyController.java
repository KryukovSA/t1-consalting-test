package com.example.characterfrequency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CharacterFrequencyController {

    @GetMapping("/character-frequency")
    public Map<Character, Integer> getCharacterFrequency(@RequestParam String inputString) {
        Map<Character, Integer> characterFrequency = new HashMap<>();

        for (char c : inputString.toCharArray()) {
            characterFrequency.put(c, characterFrequency.getOrDefault(c, 0) + 1);
        }

        characterFrequency = sortByValue(characterFrequency);

        return characterFrequency;
    }

    private Map<Character, Integer> sortByValue(Map<Character, Integer> unsortedMap) {
        return unsortedMap.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
