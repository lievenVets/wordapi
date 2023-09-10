package be.example.wordcombiner;

import be.example.WordCombination;

import java.util.List;
import java.util.Set;

public interface WordCombiner {

    Set<WordCombination> findCombinationsIn(final List<String> words);
}
