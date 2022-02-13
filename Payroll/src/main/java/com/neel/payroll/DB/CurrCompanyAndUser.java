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

package com.neel.payroll.DB;

import java.sql.*;
import javax.swing.JOptionPane;


//package my.contacteditor;


public class CurrCompanyAndUser
{
    private static CurrCompanyAndUser instance = new CurrCompanyAndUser();
    private boolean m_initiialized = false;

    private String m_username = "";
    private String m_password = "";
    private String m_companydbFile = "";
    private Connection m_connection;

    public Connection getExistingConnection()
    {

        return m_connection;
    }

    private CurrCompanyAndUser()
    {

    }


    public static CurrCompanyAndUser getInstance()
    {
        if (instance == null)
        {
            instance = new CurrCompanyAndUser();
        }


        return instance;
    }


    public boolean initialize(String username, String password, String companydbFile)
    {
        boolean bRet = false;
        this.m_username = username;
        this.m_password = password;
        this.m_companydbFile = companydbFile;

        if(connectdb() == true)
        {
            m_initiialized = true;
             bRet = true;
        }
        else{
            m_initiialized = true;
        }
        return  bRet;
    }



    private boolean connectdb()
    {
        boolean bRet = false;
        try
        {
            if (m_connection != null)
            {
                m_connection.close();
                m_connection = null;
            }
              
            m_connection = DriverManager.getConnection("jdbc:sqlite:"+m_companydbFile);
            bRet = true;
        }
        catch (SQLException q)
        {
            JOptionPane.showMessageDialog(null,q.getMessage());
        }
        return bRet;
    }
}
