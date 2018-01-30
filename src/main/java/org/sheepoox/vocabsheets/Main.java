package org.sheepoox.vocabsheets;

import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Tomáš Černý on 28 Dec 2017
 * in project vocabsheets
 */
public class Main {


    private static final String VOCABULARY_RANGE = "B3:D";
    private static final String APPLICATION_NAME = "org.sheepoox.vocabsheets";

    private static final SheetsAuthorizer SHEETS_AUTHORIZER = SheetsAuthorizer.getInstance();
    private final static Logger LOGGER = Logger.getLogger(VocabularyHelper.class.getName());

    public static void main(String... args) throws IOException {
        Sheets service = SHEETS_AUTHORIZER.getSheetsService(APPLICATION_NAME);
        TableExtractor tableExtractor = new TableExtractor(service);

        Table tableOfTables = tableExtractor.extractTable("Tables", "1QpkuRYtARP9YveXqJXTisLEfsUxWTJOaYXhzlqXuTlE", "A1:C");

        List<List<Object>> tables = (List<List<Object>>) tableOfTables.getValues().get("values");
        tables.removeIf((table) -> {
            return !table.get(1).equals("VOC");
        });

        List<VocabularyTable> vocabularyTables = new ArrayList<>();
        tables.forEach((row) -> {
            VocabularyTable vocabularyTable = null;
            try {
                vocabularyTable = new VocabularyTable(tableExtractor.extractTable(row.get(0).toString(), row.get(2).toString(), VOCABULARY_RANGE));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert vocabularyTable != null;
            vocabularyTable.parseVocabulary();
            vocabularyTables.add(vocabularyTable);
            VocabularyHelper.writeVocab(vocabularyTable.datedVocabulary, row.get(0).toString().substring(0, 2).toUpperCase());
        });
    }

}
