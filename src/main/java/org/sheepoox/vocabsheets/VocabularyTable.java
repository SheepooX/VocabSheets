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
            String currentName = null;
            DatedVocabulary.Vocabulary currentVocabulary = null;
            List<List<Object>> rows = (List<List<Object>>) this.values.get("values");
            for (List<Object> row : rows) {
                if (row.size() != 3) continue;
                if (!row.get(0).toString().isEmpty()) {
                    if (currentVocabulary != null) {
                        datedVocabulary.vocabularies.put(currentName, currentVocabulary);
                    }
                    currentName = row.get(0).toString().replaceAll("[/,]", "-");
                    String[] languageSplit = name.split("-");
                    currentVocabulary = new DatedVocabulary.Vocabulary(languageSplit[0], languageSplit[1]);
                }
                currentVocabulary.vocabulary.put(row.get(1).toString(), row.get(2).toString());
            }
            datedVocabulary.vocabularies.put(currentName, currentVocabulary);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VocabularyTable)) return false;
        VocabularyTable that = (VocabularyTable) o;
        return Objects.equals(this.hashCode(), that.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), datedVocabulary);
    }

    public DatedVocabulary getDatedVocabulary() {
        return datedVocabulary;
    }

    public void setDatedVocabulary(DatedVocabulary datedVocabulary) {
        this.datedVocabulary = datedVocabulary;
    }

}
