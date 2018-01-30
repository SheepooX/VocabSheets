package org.sheepoox.vocabsheets;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Tomáš Černý on 25 Dec 2017
 * in project vocabsheets
 */
public class DatedVocabulary {

    public final Map<String, Vocabulary> vocabularies = new HashMap<>();

    @Override
    public String toString() {
        return "org.sheepoox.vocabsheets.DatedVocabulary{" +
                "vocabularies=" + vocabularies +
                '}';
    }

    static class Vocabulary {

        public final Map<String, String> vocabulary = new LinkedHashMap<>();

        public final String FROM;
        public final String TO;

        public Vocabulary(String FROM, String TO) {
            this.FROM = FROM;
            this.TO = TO;
        }

        @Override
        public String toString() {
            return "Vocabulary{" +
                    "vocabulary=" + vocabulary +
                    '}';
        }

    }

}
