package be.example.wordcombiner;

import be.example.WordCombination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdvancedSixLetterWordCombiner implements WordCombiner {
    private final int wordCombinationCharSize;

    public AdvancedSixLetterWordCombiner() {
        this.wordCombinationCharSize = 6;
    }

    public Set<WordCombination> findCombinationsIn(final List<String> words) {
        final Set<String> foundWordCombinationCandidate = words.stream()
                .filter(word -> word.length() == wordCombinationCharSize)
                .collect(Collectors.toSet());

        if (foundWordCombinationCandidate.isEmpty()) {
            return Set.of();
        }

        return findWordCombinationsFor(words, foundWordCombinationCandidate);
    }

    private Set<WordCombination> findWordCombinationsFor(final List<String> words, final Set<String> wordCombinationCandidate) {
        final List<String> wordParts = words.stream()
                .filter(word -> word.length() < wordCombinationCharSize)
                .toList();

        return wordCombinationCandidate.stream()
                .flatMap(candidate -> {
                    final List<String> wordSet = wordParts.stream().filter(candidate::contains).toList();
                    final List<WordCombination> wordCombinations = new ArrayList<>();

                    backtrackWordCombination(wordSet, new ArrayList<>(), candidate, wordCombinations);
                    return wordCombinations.stream();
                })
                .collect(Collectors.toSet());
    }

    //Based on backtrack algorithm which brute force calculates all possible six-letter-word combinations for a given word
    //source: https://examples.javacodegeeks.com/backtracking-algorithm/
    private void backtrackWordCombination(List<String> wordParts, List<String> currentWordCombination, String target, List<WordCombination> sixLetterWordCombination) {
        final String candidate = String.join("", currentWordCombination);

        if (target.equals(candidate)) {
            final WordCombination wordCombination = new WordCombination(target, currentWordCombination.toArray(new String[]{}));
            sixLetterWordCombination.add(wordCombination);
            return;
        }

        final Set<String> uniqueWordParts = new HashSet<>(wordParts);
        for (String word : uniqueWordParts) {
            final String formedWord = candidate + word;
            if (target.startsWith(formedWord)) {
                final List<String> newCombo = new ArrayList<>(currentWordCombination);
                newCombo.add(word);

                final List<String> newSubSet = new ArrayList<>(wordParts);
                newSubSet.remove(word);

                backtrackWordCombination(newSubSet, newCombo, target, sixLetterWordCombination);
            }
        }
    }

}
