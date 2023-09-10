package be.example.wordcombiner;

import be.example.WordCombination;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class SimpleSixLetterWordsCombinerTest {

    @Test
    void returnsEmptyListGivenEmptyWordsList() {
        final SimpleSixLetterWordsCombiner simpleSixLetterWordsCombiner = new SimpleSixLetterWordsCombiner();
        final Set<WordCombination> result = simpleSixLetterWordsCombiner.findCombinationsIn(List.of());

        assertThat(result).isEmpty();
    }

    @Test
    void returnEmptyListWhenNoSixLetterWordsAreGiven() {
        final SimpleSixLetterWordsCombiner simpleSixLetterWordsCombiner = new SimpleSixLetterWordsCombiner();
        final List<String> words = List.of("foo", "bar");
        final Set<WordCombination> result = simpleSixLetterWordsCombiner.findCombinationsIn(words);

        assertThat(result).isEmpty();
    }

    @Test
    void returnResultForSingleCombination() {
        final SimpleSixLetterWordsCombiner simpleSixLetterWordsCombiner = new SimpleSixLetterWordsCombiner();
        final List<String> words = List.of("foobar", "foo", "bar");
        final Set<WordCombination> result = simpleSixLetterWordsCombiner.findCombinationsIn(words);

        assertThat(result).containsExactlyInAnyOrder(new WordCombination("foobar", "foo", "bar"));
    }

    @Test
    void returnsResultForMultipleCombinations() {
        final SimpleSixLetterWordsCombiner simpleSixLetterWordsCombiner = new SimpleSixLetterWordsCombiner();
        final List<String> words = List.of("foobar", "foo", "bar", "pl", "anet", "planet");
        final Set<WordCombination> result = simpleSixLetterWordsCombiner.findCombinationsIn(words);

        assertThat(result).containsExactlyInAnyOrder(new WordCombination("foobar", "foo", "bar"), new WordCombination("planet", "pl", "anet"));
    }

    @Test
    void returnsEmptyWhenNoPossibleCombinations() {
        final SimpleSixLetterWordsCombiner simpleSixLetterWordsCombiner = new SimpleSixLetterWordsCombiner();
        final List<String> words = List.of("foobar", "fo", "bar", "h", "word");
        final Set<WordCombination> result = simpleSixLetterWordsCombiner.findCombinationsIn(words);

        assertThat(result).isEmpty();
    }

    @Test
    void returnResultShouldSingleUseGivenWords() {
        final SimpleSixLetterWordsCombiner simpleSixLetterWordsCombiner = new SimpleSixLetterWordsCombiner();
        final List<String> words = List.of("barbar", "bar");
        final Set<WordCombination> result = simpleSixLetterWordsCombiner.findCombinationsIn(words);

        assertThat(result).isEmpty();
    }

    @Test
    void returnResultShouldUseMultipleTimesGivenWordParts() {
        final SimpleSixLetterWordsCombiner simpleSixLetterWordsCombiner = new SimpleSixLetterWordsCombiner();
        final List<String> words = List.of("barbar", "bar", "bar");
        final Set<WordCombination> result = simpleSixLetterWordsCombiner.findCombinationsIn(words);

        assertThat(result).containsExactlyInAnyOrder(new WordCombination("barbar", "bar", "bar"));
    }


}