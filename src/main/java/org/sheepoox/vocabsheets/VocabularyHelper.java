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

    private static final String DIR = "C:\\Users\\SheepooX\\Desktop\\voc";
    private static final String NL = "\r\n";

    private final static Logger LOGGER = Logger.getLogger(VocabularyHelper.class.getName());

    public static void writeVocab(DatedVocabulary vocabulary, String lang) {
        writeVocabToFile(vocabulary, lang);
    }

    public static void writeVocabToFile(DatedVocabulary vocabulary, String lang) {
        LOGGER.log(Level.INFO, "Writing vocabulary: " + vocabulary + "\nto '" + DIR + "'");
        for (Map.Entry<Date, DatedVocabulary.Vocabulary> vocab : vocabulary.vocabularies.entrySet()) {
            final String NAME = lang + " " + vocab.getKey().getDate() + "-" + (vocab.getKey().getMonth() + 1) + "-" + (vocab.getKey().getYear() + 1900);
            File file = new File(DIR + "\\" + NAME + ".txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "Cp1250"));
                writer.write("# " + NAME + NL);
                for (Map.Entry<String, String> pair : vocab.getValue().vocabulary.entrySet()) {
                    writer.write(pair.getKey() + ":" + pair.getValue() + NL);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
