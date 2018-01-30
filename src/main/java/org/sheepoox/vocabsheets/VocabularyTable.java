package org.sheepoox.vocabsheets;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Tomáš Černý on 27 Dec 2017
 * in project vocabsheets
 */
public class VocabularyTable extends Table {

    protected DatedVocabulary datedVocabulary;

    public VocabularyTable(Table table) {
        this(table.name, table.spreadsheetId);
        this.values = table.values;
    }

    public VocabularyTable(String name, String spreadsheetsId) {
        super(name, spreadsheetsId);
    }

    public void parseVocabulary() {
        datedVocabulary = new DatedVocabulary();
        if (values.get("values") == null || values.getValues().size() == 0) {
            System.out.println("No data found.");
        } else {
            Date currentDate = null;
            DatedVocabulary.Vocabulary currentVocabulary = null;
            List<List<Object>> rows = (List<List<Object>>) this.values.get("values");
            for (List<Object> row : rows) {
                if (row.size() != 3) continue;
                if (!row.get(0).toString().isEmpty()) {
                    if (currentVocabulary != null) {
                        datedVocabulary.vocabularies.put(currentDate, currentVocabulary);
                    }
                    String[] dateSplit = row.get(0).toString().split("/");
                    currentDate = new Date(Integer.valueOf(dateSplit[2]) - 1900, Integer.valueOf(dateSplit[1]) - 1, Integer.valueOf(dateSplit[0]));
                    String[] nameSplit = name.split("-");
                    currentVocabulary = new DatedVocabulary.Vocabulary(nameSplit[0], nameSplit[1]);
                }
                currentVocabulary.vocabulary.put(row.get(1).toString(), row.get(2).toString());
            }
            datedVocabulary.vocabularies.put(currentDate, currentVocabulary);
        }
    }

    @Override
    public String toString() {
        return "org.sheepoox.vocabsheets.VocabularyTable{" +
                "datedVocabulary=" + datedVocabulary +
                ", name='" + name + '\'' +
                ", spreadsheetId='" + spreadsheetId + '\'' +
                ", values=" + values +
                "} " + super.toString();
    }

    public DatedVocabulary getDatedVocabulary() {
        return datedVocabulary;
    }

    public void setDatedVocabulary(DatedVocabulary datedVocabulary) {
        this.datedVocabulary = datedVocabulary;
    }

}
