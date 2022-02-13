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

package com.neel.payroll.Model;

import com.neel.payroll.DB.CurrCompanyAndUser;
import com.neel.payroll.Util.Util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Neel
 */
public class CreateCompanyDlgModel {

    public static boolean CreateNewCompanyDBFile(String companyName, String companyDBName,
            String userName, String userPwd) {
        boolean bRet = false;
        if (CreateCompanyDlgModel.copyDbFile(companyDBName, userName, userPwd) == true) {
            bRet = UpdateDbWithNewCompanyBasicData(companyName, companyDBName, userName, userPwd);
        }

        return bRet;
    }

    private static boolean copyDbFile(String companyDBName, String userName, String userPwd) {
        boolean bRet = false;
        //String compFilePath = "";
        String strSrcMasterDb = "";
        String strSrcDestDb = "";
        try {
            String strNewDbFilename;
            if (companyDBName.contains(".db") == true) {
                strNewDbFilename = companyDBName;
            } else {
                strNewDbFilename = companyDBName + ".db";
            }

            final String strMasterDbRelPath = "/masterpayroll.db";

            strSrcMasterDb = Util.getAppDataDir() + strMasterDbRelPath;
            strSrcDestDb = Util.getAppDir() + strNewDbFilename;

            File source = new File(strSrcMasterDb);
            File dest = new File(strSrcDestDb);

            if (dest.exists() && !dest.isDirectory()) {
                JOptionPane.showMessageDialog(null, "File " + strSrcDestDb + " already exists please select new name");

                return bRet;
            }

            var V = Files.copy(source.toPath(), dest.toPath());

            bRet = true;
        } catch (IOException e) {
            String strErrorMsg = "Failed to copy file from " + strSrcMasterDb + " to " + strSrcDestDb
                    + ". Error " + e.getMessage() + " cause " + e.getCause();
            JOptionPane.showMessageDialog(null, strErrorMsg);

        }

        CurrCompanyAndUser.getInstance().initialize(userName, userPwd, strSrcDestDb);
        return bRet;
    }

    static private boolean UpdateDbWithNewCompanyBasicData(String companyName, String companyDBName,
            String userName, String userPwd) {
        boolean bRet = false;
        try {
            Connection connection = CurrCompanyAndUser.getInstance().getExistingConnection();

            String sql = "INSERT INTO companyinfo(companyname,address,phonenumber,federalein,stateid,pay_frequency,adminusername,password) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, companyName);
            statement.setString(2, ""); //companyAddress
            statement.setString(3, ""); //phoneNumber);
            statement.setString(4, ""); //federalEIN);
            statement.setString(5, ""); //stateID);
            statement.setString(6, "BiWeekly"); //pay_frquency
            statement.setString(7, userName);
            statement.setString(8, userPwd);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                //println("A row has been inserted");
                bRet = true;
            }

            statement.close();

        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, throwables.getMessage());
            throwables.printStackTrace();
        }

        return bRet;
    }

}
