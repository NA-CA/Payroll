/*
MIT License

Copyright (c) 2022 Neel Apte

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.neel.payroll.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Neel
 */
public class Util {

    private static String m_strAppDir = "";

    public static String getAppDir() {
        if (m_strAppDir.length() > 0) {
            return m_strAppDir;
        }

        m_strAppDir = getAppicationDirFromUserDir() + "/";
        //m_strAppDir = getAppicationDirTEMPIDEDebug() + "/";

        return m_strAppDir;
    }

    public static String getAppDataDir() {

        return getAppDir() + "data";
    }

    public static String getAppReportsDir() {

        return getAppDir() + "reports";
    }
    private static String getAppicationDirFromUserDir() {
        String strAppDir = System.getProperty("user.dir");;
        
        return strAppDir;
    }
    private static String getAppicationDirFromClass() {
        String strAppDir = System.getProperty("user.dir");;
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);

        if (classpathEntries.length > 0) {
             JOptionPane.showMessageDialog(null, "strAppDir user.dir " + strAppDir);
             JOptionPane.showMessageDialog(null, "strAppDir class path .dir " + classpathEntries[0]);
            Path p = Paths.get(classpathEntries[0]);
            strAppDir = p.getParent().toString();
        }

        return strAppDir;
    }

    private static String getAppicationDirTEMPIDEDebug() {
        String strAppDir = System.getProperty("user.dir");;
        JOptionPane.showMessageDialog(null, "From Debug AppDir " + strAppDir);
        return strAppDir;
    }

    public static long getStartDateSinceEpoch(Date date) {
        LocalDate localDate = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.toEpochDay();
    }

    public static int getDayOfMonthFromEpoch(long daySinceEpoch) {
        LocalDate localDate = LocalDate.ofEpochDay(daySinceEpoch);
        return localDate.getDayOfMonth();
    }
    
    public static int getYearFromEpoch(long daySinceEpoch) {
        LocalDate localDate = LocalDate.ofEpochDay(daySinceEpoch);
        return localDate.getYear();
    }
   
    public static int getMonthFromEpoch(long daySinceEpoch) {
        LocalDate localDate = LocalDate.ofEpochDay(daySinceEpoch);
        return localDate.getMonthValue();
    }

    
    public static String getYearMonthDayOfMonthFromEpoch(long daySinceEpoch)
    {
        LocalDate localDate = LocalDate.ofEpochDay(daySinceEpoch);
        return localDate.toString();
    }
    
    public static LocalDate getLocalDateFromDate(Date date) {
        LocalDate localDate = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate;
    }

    public static Date getDateFromLocalDate(LocalDate localDate) {

        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static void ConsoleLogControl() throws FileNotFoundException
    {
        // Keep a copy of the original out stream so that if needed only selective messages
        // can be printed using original.println("test to wirte to standard console as before");
        // PrintStream original = new PrintStream(System.out);
        
        //the following line will remove all logging messages sent to console.log()
        System.setOut(new PrintStream(new FileOutputStream("NUL:")));
        
        //the following line will send all logging messages from console.log to log.txt (do not append)
        //System.setOut(new PrintStream(new FileOutputStream("log.txt",false))); 
    }
}
