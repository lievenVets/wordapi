package be.example.wordcombiner;

import be.example.WordCombination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleSixLetterWordsCombiner implements WordCombiner {

    private final int wordCombinationCharSize;

    public SimpleSixLetterWordsCombiner() {
        this.wordCombinationCharSize = 6;
    }

    public Set<WordCombination> findCombinationsIn(final List<String> words) {
        final Set<String> candidates = words.stream()
                .filter(word -> word.length() == wordCombinationCharSize)
                .collect(Collectors.toSet());

        if (candidates.isEmpty()) {
            return Set.of();
        }

        final List<String> combinationWords = words.stream()
                .filter(word -> word.length() < wordCombinationCharSize)
                .collect(Collectors.toList());

        return findWordCombinationsFor(combinationWords, candidates);
    }

    private Set<WordCombination> findWordCombinationsFor(final List<String> combinationWords, final Set<String> candidates) {
        return candidates.stream()
                .flatMap(candidate -> findWordCombinationsFor(combinationWords, candidate).stream())
                .collect(Collectors.toSet());
    }

    private Set<WordCombination> findWordCombinationsFor(final List<String> combinationWords, final String candidate) {
        final Set<WordCombination> combinations = new HashSet<>();

        final Set<String> prefixes = combinationWords.stream().filter(candidate::startsWith).collect(Collectors.toSet());

        prefixes.forEach(prefix -> {
            final List<String> possibleCombinations = new ArrayList<>(combinationWords);
            possibleCombinations.remove(prefix);

            final Set<WordCombination> foundCombinations = possibleCombinations.stream()
                    .filter(postfix -> String.join("", prefix, postfix).equals(candidate))
                    .map(postfix -> new WordCombination(candidate, prefix, postfix))
                    .collect(Collectors.toSet());

            combinations.addAll(foundCombinations);
        });

        return combinations;
    }

}
