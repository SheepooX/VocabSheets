package org.sheepoox.vocabsheets;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tomáš Černý on 28 Dec 2017
 * in project vocabsheets
 */
public class TableExtractor {

    private final static Logger LOGGER = Logger.getLogger(TableExtractor.class.getName());

    public final Sheets service;

    public TableExtractor(Sheets service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "org.sheepoox.vocabsheets.TableExtractor{" +
                "service=" + service +
                '}';
    }

    public Table extractTable(String name, String spreadsheetId, String range) throws IOException {
        LOGGER.log(Level.INFO, "Extracting table with id '" + spreadsheetId + "' in range '" + range + "'");
        Table table = new Table(name, spreadsheetId);
        ValueRange response = service.spreadsheets().values()
                .get(table.getSpreadsheetId(), range)
                .execute();
        table.setValues(response);
        return table;
    }

}
