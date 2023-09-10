package be.example.api;

import be.example.WordCombination;
import be.example.wordcombiner.SimpleSixLetterWordsCombiner;
import be.example.wordcombiner.WordCombiner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/letter-words")
public class LetterWordsController {

    private final WordCombiner simpleWordCombiner;

    public LetterWordsController() {
        this.simpleWordCombiner = new SimpleSixLetterWordsCombiner();
    }

    @PostMapping(value = "/combinations")
    public List<String> findLetterWords(@RequestBody final List<String> words) {
        return simpleWordCombiner.findCombinationsIn(words).stream().sorted(Comparator.comparing(WordCombination::result)).map(this::mapToString).collect(Collectors.toList());
    }

    private String mapToString(final WordCombination wordCombination) {
        final String wordCombo = String.join("+", wordCombination.wordParts());
        return String.format("%s=%s", wordCombo, wordCombination.result());
    }
}
