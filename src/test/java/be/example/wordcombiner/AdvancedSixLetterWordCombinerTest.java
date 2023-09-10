package be.example.wordcombiner;

import be.example.WordCombination;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AdvancedSixLetterWordCombinerTest {

    @Test
    void returnsEmptyListGivenEmptyWordsList() {
        final AdvancedSixLetterWordCombiner advancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();
        final Set<WordCombination> result = advancedSixLetterWordCombiner.findCombinationsIn(List.of());

        assertThat(result).isEmpty();
    }

    @Test
    void returnEmptyListWhenNoSixLetterWordsAreGiven() {
        final AdvancedSixLetterWordCombiner advancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();
        final List<String> words = List.of("foo", "bar");
        final Set<WordCombination> result = advancedSixLetterWordCombiner.findCombinationsIn(words);

        assertThat(result).isEmpty();
    }

    @Test
    void returnResultForSingleCombination() {
        final AdvancedSixLetterWordCombiner advancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();
        final List<String> words = List.of("foobar", "foo", "bar");
        final Set<WordCombination> result = advancedSixLetterWordCombiner.findCombinationsIn(words);

        assertThat(result).containsExactlyInAnyOrder(new WordCombination("foobar", "foo", "bar"));
    }

    @Test
    void returnsResultForMultipleCombinations() {
        final AdvancedSixLetterWordCombiner advancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();
        final List<String> words = List.of("foobar", "foo", "bar", "pl", "anet", "planet");
        final Set<WordCombination> result = advancedSixLetterWordCombiner.findCombinationsIn(words);

        assertThat(result).containsExactlyInAnyOrder(new WordCombination("foobar", "foo", "bar"), new WordCombination("planet", "pl", "anet"));
    }

    @Test
    void returnsEmptyWhenNoPossibleCombinations() {
        final AdvancedSixLetterWordCombiner advancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();
        final List<String> words = List.of("foobar", "fo", "bar", "h", "word");
        final Set<WordCombination> result = advancedSixLetterWordCombiner.findCombinationsIn(words);

        assertThat(result).isEmpty();
    }

    @Test
    void returnResultShouldSingleUseGivenWords() {
        final AdvancedSixLetterWordCombiner advancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();
        final List<String> words = List.of("barbar", "bar");
        final Set<WordCombination> result = advancedSixLetterWordCombiner.findCombinationsIn(words);

        assertThat(result).isEmpty();
    }

    @Test
    void returnResultShouldUseDuplicateGivenWordParts() {
        final AdvancedSixLetterWordCombiner advancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();
        final List<String> words = List.of("barber", "b", "a", "r", "b", "er");
        final Set<WordCombination> result = advancedSixLetterWordCombiner.findCombinationsIn(words);

        assertThat(result).containsExactlyInAnyOrder(new WordCombination("barber", "b", "a", "r", "b", "er"));
    }

    @Test
    void returnResultShouldUseUniqueWordParts() {
        final AdvancedSixLetterWordCombiner advancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();
        final List<String> words = List.of("foobar", "fo", "o", "bar");
        final Set<WordCombination> result = advancedSixLetterWordCombiner.findCombinationsIn(words);

        assertThat(result).containsExactlyInAnyOrder(new WordCombination("foobar", "fo", "o", "bar"));
    }

    @Test
    void returnResultShouldUseReuseWordPartForMultipleWordCombinationsParts() {
        final AdvancedSixLetterWordCombiner advancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();
        final List<String> words = List.of("barber", "b", "arber", "barney", "arney");
        final Set<WordCombination> result = advancedSixLetterWordCombiner.findCombinationsIn(words);

        assertThat(result).containsExactlyInAnyOrder(new WordCombination("barber", "b", "arber"), new WordCombination("barney", "b", "arney"));
    }
}