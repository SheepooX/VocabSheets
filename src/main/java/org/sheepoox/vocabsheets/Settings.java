package org.sheepoox.vocabsheets;

/**
 * Created by Tomáš Černý on 30 Jan 2018
 * in project vocabsheets
 */
public class Settings {

    // The directory where the extracted files will be saved
    // Default %USERHOME%/vocabulary
    public static final String DIR = System.getProperty("user.home") + System.getProperty("file.separator") + "vocabulary";
    // Here you specify the ID of the master table
    // Example one can be found at: https://docs.google.com/spreadsheets/d/1Btg-_JYc3hP4e_xvKs6IAEcda9ilG6IW8iNPpwekfUk/
    public static final String MASTER_TABLE_ID = "1Btg-_JYc3hP4e_xvKs6IAEcda9ilG6IW8iNPpwekfUk";

    // Do not change these unless you know what it does
    public static final String NL = "\r\n";
    public static final String MASTER_TABLE_RANGE = "A2:C";
    public static final String VOCABULARY_RANGE = "B3:D";
    public static final String APPLICATION_NAME = "org.sheepoox.vocabsheets";

}
