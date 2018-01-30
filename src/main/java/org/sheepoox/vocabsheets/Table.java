package org.sheepoox.vocabsheets;

import com.google.api.services.sheets.v4.model.ValueRange;

import java.util.Objects;

/**
 * Created by Tomáš Černý on 28 Dec 2017
 * in project vocabsheets
 */
public class Table {

    protected String name;
    protected String spreadsheetId;
    protected ValueRange values;

    public Table(String name, String spreadsheetsId, ValueRange values) {
        this.name = name;
        this.spreadsheetId = spreadsheetsId;
        this.values = values;
    }

    public Table(String name, String spreadsheetsId) {
        this.name = name;
        this.spreadsheetId = spreadsheetsId;
    }

    @Override
    public String toString() {
        return "org.sheepoox.vocabsheets.Table{" +
                "name='" + name + '\'' +
                ", spreadsheetId='" + spreadsheetId + '\'' +
                ", values=" + values +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpreadsheetId() {
        return spreadsheetId;
    }

    public void setSpreadsheetId(String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
    }

    public ValueRange getValues() {
        return values;
    }

    public void setValues(ValueRange values) {
        this.values = values;
    }

}
