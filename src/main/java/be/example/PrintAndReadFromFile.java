package be.example;

import be.example.wordcombiner.AdvancedSixLetterWordCombiner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class PrintAndReadFromFile {
    public static void main(String[] args) {
        final AdvancedSixLetterWordCombiner AdvancedSixLetterWordCombiner = new AdvancedSixLetterWordCombiner();

        try (Stream<String> lines = Files.lines(Paths.get("input.txt"))) {
            final List<String> words = lines.toList();
            final Set<WordCombination> wordCombinations = AdvancedSixLetterWordCombiner.findCombinationsIn(words);

            wordCombinations
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}