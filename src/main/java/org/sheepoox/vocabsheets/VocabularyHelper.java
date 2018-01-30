package org.sheepoox.vocabsheets;

//import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tomáš Černý on 28 Dec 2017
 * in project vocabsheets
 */
public class VocabularyHelper {

    private final static Logger LOGGER = Logger.getLogger(VocabularyHelper.class.getName());

    public static void writeVocab(DatedVocabulary vocabulary, String lang) {
        writeVocabToFile(vocabulary, lang);
    }

    public static void writeVocabToFile(DatedVocabulary vocabulary, String lang) {
        LOGGER.log(Level.INFO, "Writing vocabulary: " + vocabulary + "\nto '" + Settings.DIR + "'");
        for (Map.Entry<String, DatedVocabulary.Vocabulary> vocab : vocabulary.vocabularies.entrySet()) {
            final String name = vocab.getValue().FROM + "-" + vocab.getValue().TO + " " + vocab.getKey();
            File file = new File(Settings.DIR + "\\" + name + ".txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "Cp1250"));
                writer.write("# " + name + Settings.NL);
                for (Map.Entry<String, String> pair : vocab.getValue().vocabulary.entrySet()) {
                    writer.write(pair.getKey() + ":" + pair.getValue() + Settings.NL);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
