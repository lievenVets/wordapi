package be.example;

import java.util.Arrays;
import java.util.Objects;

public record WordCombination(String result, String... wordParts) {

    public WordCombination {
        final String wordCombo = String.join("", wordParts);

        if (!wordCombo.equals(result)) {
            throw new IllegalArgumentException("Invalid word combination. Combined result " + wordCombo + " does not match " + result);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordCombination that = (WordCombination) o;
        return Objects.equals(result, that.result) && Arrays.equals(wordParts, that.wordParts);
    }

    @Override
    public int hashCode() {
        int result1 = Objects.hash(result);
        result1 = 31 * result1 + Arrays.hashCode(wordParts);
        return result1;
    }

    @Override
    public String toString() {
        return "WordCombination{" +
                "result='" + result + '\'' +
                ", wordParts=" + Arrays.toString(wordParts) +
                '}';
    }
}
