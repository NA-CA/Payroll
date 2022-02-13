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

package com.neel.payroll;

import com.neel.payroll.Util.Util;
import com.neel.payroll.Util.toast;
import com.neel.payroll.VM.OnePayrollVM;
import com.neel.payroll.VM.EmployeeTabVM;
import com.neel.payroll.VM.CompanyTabVM;
import com.neel.payroll.VM.EmployeeW4TabVM;
import com.neel.payroll.VM.GenerateReportVM;
import com.neel.payroll.VM.TimeSheetTabVM;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.IDN;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Neel
 */
//public class PayrollUI extends javax.swing.JFrame implements ActionListener  {
public class PayrollUI extends javax.swing.JFrame {

    /**
     * Creates new form ContactEditorUI
     */
    public PayrollUI() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Center dialog in screen
        getjTableEmployeeList().getSelectionModel().addListSelectionListener(m_employeeTableListener);
        CompanyTabVM.FillUI(this);
        EmployeeTabVM.FillUI(this);

        AddTimeSheetDateChangedListener();

    }

    public javax.swing.JComboBox<String> getOldW4FilingStatus() {
        return cboOldW4FilingStatus;
    }

    public javax.swing.JTextField getw4OldAdditionalAmount() {
        return tfw4OldAdditionalAmount;
    }

    public javax.swing.JSpinner getW4EmployeeOldW4Allowance() {
        return w4EmployeeOldW4Allowance;
    }

    public javax.swing.JTable getW4New2020W4FormTable() {
        return w4New2020W4FormTable;
    }

    public javax.swing.JCheckBox getW4Submitted2020PlusW4() {
        return w4Submitted2020PlusW4;
    }

    public javax.swing.JCheckBox getW4Exempt() {
        return w4_Exempt;
    }

    private void AddTimeSheetDateChangedListener() {
        this.timesheetStartDate.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println(evt.getPropertyName());
                if (timesheetStartDate.getDate() != null) {
                    TimeSheetTabVM.FillUI(PayrollUI.this);
                }
            }
        });
        this.timesheetStartDate.getCalendarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JDateChooser getTimesheetStartDate() {
        return timesheetStartDate;
    }

    public JTable getTimesheetTable() {
        return timesheetTable;
    }

    public JTable getjTableEmployeeList() {
        return jTableEmployeeList;
    }

    public JPanel getPanelEmployeeInfo() {
        return panelEmployeeInfo;
    }

    public JTable getCompanyRateTable() {
        return companyRateTable;
    }

    public JTextField getTfCompanyName() {
        return tfCompanyName;
    }

    public JComboBox<String> getCompanyPayrollFrequency() {
        return cbCompanyPayrollFrequency;
    }

    public JTextField getTfEmployeeFirstName() {
        return tfEmployeeFirstName;
    }

    public JTextField getTfEmployeeLastName() {
        return tfEmployeeLastName;
    }

    public JTextField getTfSalary() {
        return tfSalary;
    }

    public JCheckBox getCkemployee50PlusAge() {
        return ckemployee50PlusAge;
    }

    public JSpinner getspinemployee401KRate() {
        return spinemployee401KRate;
    }

    public int GetEmployeeId() {
        int row = jTableEmployeeList.getSelectedRow();
        int employeeId = (int) PayrollUI.this.getjTableEmployeeList().getValueAt(row, 0);
        return employeeId;
    }
    ListSelectionListener m_employeeTableListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {

            int row = jTableEmployeeList.getSelectedRow();
            int employeeId = (int) PayrollUI.this.getjTableEmployeeList().getValueAt(row, 0);

            EmployeeTabVM.EmployeeSelChanged(PayrollUI.this, row);
            //Enable Or Disable W4 tabbed control
            boolean existingEmployeeEnable = (employeeId > 0);
            PayrollUI.this.jtabEmployeeDetails.setEnabledAt(1, existingEmployeeEnable);
            if (existingEmployeeEnable == false) {
                //Switch to employee detail tab
                PayrollUI.this.jtabEmployeeDetails.setSelectedIndex(0);
            }
            PayrollUI.this.showHideW4TabContrls(w4Submitted2020PlusW4.isSelected());
            int J = 0;
        }
    };

    public void ClearEmployeeTable() {
        var table = getjTableEmployeeList();

        table.getSelectionModel().removeListSelectionListener(m_employeeTableListener);
        EmployeeTabVM.FillUI(this);
        table.getSelectionModel().addListSelectionListener(m_employeeTableListener);

        m_employeeTableListener.valueChanged(null);

    }

    public JLabel getLblTimesheetEndDate() {
        return lblTimesheetEndDate;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        mainTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        reportYearChooser = new com.toedter.calendar.JYearChooser();
        tfReportFilename = new javax.swing.JTextField();
        btnGenerateReport = new javax.swing.JButton();
        runPayrollPane = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        payTimesheetStartDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        labelPayTimesheetEndDate = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtableOnePayPeriod = new javax.swing.JTable();
        btnRunOnePayPeriodPayroll = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        payDate = new com.toedter.calendar.JDateChooser();
        timesheetPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        timesheetTable = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        timesheetStartDate = new com.toedter.calendar.JDateChooser();
        lblTimesheetEndDate = new javax.swing.JLabel();
        timesheetEndDate = new javax.swing.JLabel();
        timesheetSaveTable = new javax.swing.JButton();
        employeePane = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEmployeeList = new javax.swing.JTable();
        jtabEmployeeDetails = new javax.swing.JTabbedPane();
        panelEmployeeInfo = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tfEmployeeFirstName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfEmployeeLastName = new javax.swing.JTextField();
        btnEmployeeAddUpdate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tfSalary = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ckemployee50PlusAge = new javax.swing.JCheckBox();
        spinemployee401KRate = new javax.swing.JSpinner();
        panelEmployeeW4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        w4New2020W4FormTable = new javax.swing.JTable();
        w4Submitted2020PlusW4 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        w4EmployeeOldW4Allowance = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfw4OldAdditionalAmount = new javax.swing.JTextField();
        cboOldW4FilingStatus = new javax.swing.JComboBox<>();
        employeeW4SaveW4Changes = new javax.swing.JButton();
        w4_Exempt = new javax.swing.JCheckBox();
        w4_StateDe4Btn = new javax.swing.JButton();
        companyPane = new javax.swing.JPanel();
        companyNameEtcPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfCompanyName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbCompanyPayrollFrequency = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        companyRateTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        companySaveCompanyInfo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Small business Payroll Application");
        setLocation(new java.awt.Point(300, 300));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        mainTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mainTabbedPaneStateChanged(evt);
            }
        });

        tfReportFilename.setText("myPayrollReportForYear.csv");

        btnGenerateReport.setText("Generate Report");
        btnGenerateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(reportYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(tfReportFilename, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerateReport)
                .addContainerGap(215, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfReportFilename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGenerateReport))
                    .addComponent(reportYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(609, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Reports", jPanel1);

        jLabel3.setText("Timesheet Start Date");

        jLabel4.setText("End Date:");

        jtableOnePayPeriod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Hours", "Gross Wages", "Fedral WithHolding", "State WithHolding", "401(K)", "SDI", "SS", "Medicare", "Net Pay"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtableOnePayPeriod.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(jtableOnePayPeriod);
        jtableOnePayPeriod.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnRunOnePayPeriodPayroll.setText("Run Payroll");
        btnRunOnePayPeriodPayroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunOnePayPeriodPayrollActionPerformed(evt);
            }
        });

        jLabel18.setText("Pay Date:");

        javax.swing.GroupLayout runPayrollPaneLayout = new javax.swing.GroupLayout(runPayrollPane);
        runPayrollPane.setLayout(runPayrollPaneLayout);
        runPayrollPaneLayout.setHorizontalGroup(
            runPayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            .addGroup(runPayrollPaneLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(runPayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(runPayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(payTimesheetStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(payDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(runPayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(runPayrollPaneLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(labelPayTimesheetEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(runPayrollPaneLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnRunOnePayPeriodPayroll)))
                .addContainerGap(329, Short.MAX_VALUE))
        );
        runPayrollPaneLayout.setVerticalGroup(
            runPayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(runPayrollPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(runPayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(runPayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(labelPayTimesheetEndDate))
                    .addComponent(payTimesheetStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(runPayrollPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(payDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRunOnePayPeriodPayroll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Run Payroll", runPayrollPane);

        timesheetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fist Name", "Last Name", "Hours"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(timesheetTable);
        if (timesheetTable.getColumnModel().getColumnCount() > 0) {
            timesheetTable.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel14.setText("Start Date");

        lblTimesheetEndDate.setText("End Date:");

        timesheetSaveTable.setText("Save Timesheet");
        timesheetSaveTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timesheetSaveTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout timesheetPaneLayout = new javax.swing.GroupLayout(timesheetPane);
        timesheetPane.setLayout(timesheetPaneLayout);
        timesheetPaneLayout.setHorizontalGroup(
            timesheetPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timesheetPaneLayout.createSequentialGroup()
                .addGroup(timesheetPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timesheetPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(timesheetPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(timesheetPaneLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(timesheetStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTimesheetEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timesheetEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(timesheetPaneLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(timesheetSaveTable, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        timesheetPaneLayout.setVerticalGroup(
            timesheetPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timesheetPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timesheetPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(timesheetPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTimesheetEndDate)
                        .addComponent(timesheetEndDate))
                    .addComponent(timesheetStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(timesheetSaveTable)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Timesheet", timesheetPane);

        jTableEmployeeList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEmployeeList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableEmployeeList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableEmployeeList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEmployeeListPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(jTableEmployeeList);
        if (jTableEmployeeList.getColumnModel().getColumnCount() > 0) {
            jTableEmployeeList.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel10.setText("First Name:");

        tfEmployeeFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmployeeFirstNameActionPerformed(evt);
            }
        });

        jLabel11.setText("Last Name:");

        btnEmployeeAddUpdate.setText("Update Employee");
        btnEmployeeAddUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmployeeAddUpdateActionPerformed(evt);
            }
        });

        jLabel1.setText("Salary:");

        jLabel19.setText("401 (K)");

        jLabel20.setText("%");

        jLabel21.setText("per hour");

        ckemployee50PlusAge.setText("Age 50+");

        spinemployee401KRate.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(90.0f), Float.valueOf(1.0f)));

        javax.swing.GroupLayout panelEmployeeInfoLayout = new javax.swing.GroupLayout(panelEmployeeInfo);
        panelEmployeeInfo.setLayout(panelEmployeeInfoLayout);
        panelEmployeeInfoLayout.setHorizontalGroup(
            panelEmployeeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeeInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmployeeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelEmployeeInfoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(tfEmployeeFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEmployeeInfoLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(tfEmployeeLastName)))
                .addGap(67, 67, 67)
                .addGroup(panelEmployeeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmployeeInfoLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addGroup(panelEmployeeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelEmployeeInfoLayout.createSequentialGroup()
                                .addComponent(ckemployee50PlusAge, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addGroup(panelEmployeeInfoLayout.createSequentialGroup()
                                .addComponent(spinemployee401KRate)
                                .addGap(18, 18, 18))))
                    .addGroup(panelEmployeeInfoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(tfSalary)
                        .addGap(18, 18, 18)))
                .addGroup(panelEmployeeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addContainerGap(215, Short.MAX_VALUE))
            .addGroup(panelEmployeeInfoLayout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(btnEmployeeAddUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEmployeeInfoLayout.setVerticalGroup(
            panelEmployeeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeeInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmployeeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfEmployeeFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tfSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmployeeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfEmployeeLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(spinemployee401KRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ckemployee50PlusAge)
                .addGap(33, 33, 33)
                .addComponent(btnEmployeeAddUpdate)
                .addContainerGap(270, Short.MAX_VALUE))
        );

        jtabEmployeeDetails.addTab("Employee Info", panelEmployeeInfo);

        w4New2020W4FormTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Step 1 (c) FilingStatus (S/M/H)", "S"},
                {"Step 2 (c)  Multiple Jobs Checked (true/false)", "false"},
                {"Step 3 Line 3 Dependents", "0"},
                {"Step 4 (a) Other Income", "0"},
                {"Step 4 (b) Deductions", "0"},
                {"Step 4 (c) Extra With Holding", "0"}
            },
            new String [] {
                "2020 or newer W-4 Form Field", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(w4New2020W4FormTable);

        w4Submitted2020PlusW4.setSelected(true);
        w4Submitted2020PlusW4.setText("submitted 2020 or newer W-4 Form");
        w4Submitted2020PlusW4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                w4Submitted2020PlusW4ActionPerformed(evt);
            }
        });

        jLabel6.setText("2019 and older W-4 number of allowance");

        w4EmployeeOldW4Allowance.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));
        w4EmployeeOldW4Allowance.setEnabled(false);

        jLabel2.setText("2019 and older W4- Filing Status:");

        jLabel13.setText("2019 and older Additional Amount");

        tfw4OldAdditionalAmount.setText("0");
        tfw4OldAdditionalAmount.setEnabled(false);

        cboOldW4FilingStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", "Married (Hold at higher single rate)" }));
        cboOldW4FilingStatus.setEnabled(false);

        employeeW4SaveW4Changes.setText("Save W-4");
        employeeW4SaveW4Changes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeW4SaveW4ChangesActionPerformed(evt);
            }
        });

        w4_Exempt.setSelected(true);
        w4_Exempt.setText("EXEMPT");

        w4_StateDe4Btn.setText("View/Edit California DE4");
        w4_StateDe4Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                w4_StateDe4BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEmployeeW4Layout = new javax.swing.GroupLayout(panelEmployeeW4);
        panelEmployeeW4.setLayout(panelEmployeeW4Layout);
        panelEmployeeW4Layout.setHorizontalGroup(
            panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeeW4Layout.createSequentialGroup()
                .addGroup(panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmployeeW4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEmployeeW4Layout.createSequentialGroup()
                                .addGroup(panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfw4OldAdditionalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(w4EmployeeOldW4Allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboOldW4FilingStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(w4_Exempt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelEmployeeW4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEmployeeW4Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(w4Submitted2020PlusW4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelEmployeeW4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(w4_StateDe4Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEmployeeW4Layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(employeeW4SaveW4Changes, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        panelEmployeeW4Layout.setVerticalGroup(
            panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmployeeW4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(w4Submitted2020PlusW4)
                .addGap(9, 9, 9)
                .addGroup(panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboOldW4FilingStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(w4EmployeeOldW4Allowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfw4OldAdditionalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelEmployeeW4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEmployeeW4Layout.createSequentialGroup()
                        .addComponent(w4_Exempt)
                        .addGap(43, 43, 43))
                    .addComponent(employeeW4SaveW4Changes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(w4_StateDe4Btn)
                .addContainerGap())
        );

        jtabEmployeeDetails.addTab("W4", panelEmployeeW4);

        javax.swing.GroupLayout employeePaneLayout = new javax.swing.GroupLayout(employeePane);
        employeePane.setLayout(employeePaneLayout);
        employeePaneLayout.setHorizontalGroup(
            employeePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(employeePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtabEmployeeDetails))
        );
        employeePaneLayout.setVerticalGroup(
            employeePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeePaneLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtabEmployeeDetails))
        );

        mainTabbedPane.addTab("Employees", employeePane);

        jLabel5.setText("Company Name");

        jLabel9.setText("Payroll Frequency");

        cbCompanyPayrollFrequency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Weekly", "BiWeekly", "SemiMonthly", "Monthly", "Quarterly", "Annually" }));
        cbCompanyPayrollFrequency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCompanyPayrollFrequencyActionPerformed(evt);
            }
        });

        companyRateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Year", "Company FUTA (Unemployment Rate) %", "Employee SDI/VDI Rate %"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Short.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(companyRateTable);
        if (companyRateTable.getColumnModel().getColumnCount() > 0) {
            companyRateTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        javax.swing.GroupLayout companyNameEtcPanelLayout = new javax.swing.GroupLayout(companyNameEtcPanel);
        companyNameEtcPanel.setLayout(companyNameEtcPanelLayout);
        companyNameEtcPanelLayout.setHorizontalGroup(
            companyNameEtcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyNameEtcPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companyNameEtcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(companyNameEtcPanelLayout.createSequentialGroup()
                        .addGroup(companyNameEtcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addGap(23, 23, 23)
                        .addGroup(companyNameEtcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(companyNameEtcPanelLayout.createSequentialGroup()
                                .addComponent(tfCompanyName, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                .addContainerGap(263, Short.MAX_VALUE))
                            .addGroup(companyNameEtcPanelLayout.createSequentialGroup()
                                .addComponent(cbCompanyPayrollFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(companyNameEtcPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        companyNameEtcPanelLayout.setVerticalGroup(
            companyNameEtcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyNameEtcPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companyNameEtcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(companyNameEtcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCompanyPayrollFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        companySaveCompanyInfo.setText("Save Company Information");
        companySaveCompanyInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companySaveCompanyInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout companyPaneLayout = new javax.swing.GroupLayout(companyPane);
        companyPane.setLayout(companyPaneLayout);
        companyPaneLayout.setHorizontalGroup(
            companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(companyPaneLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(companyNameEtcPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(companyPaneLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(companySaveCompanyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        companyPaneLayout.setVerticalGroup(
            companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(companyNameEtcPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(companySaveCompanyInfo)
                .addGap(104, 104, 104)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Company", companyPane);

        jLabel7.setText("Freeware Software");

        jLabel8.setText("Free for commercial or personal use.  Author assumes no liablility, use it at your own risk.");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("The software is written in Java programming language using OpenJDK.\nIt uses following external components: -\nSQLiteDB\nSQLite-JDBC\nJCalendar\nCommons-CSV\n\nCredits:\nNetBeans\nIntelliJ IDEA\nStackoverflow.com\nBing.com\nGoogle.com\n\n\n\nMIT License\n\nCopyright (c) 2022 Neel Apte\n\nPermission is hereby granted, free of charge, to any person obtaining a copy\nof this software and associated documentation files (the \"Software\"), to deal\nin the Software without restriction, including without limitation the rights\nto use, copy, modify, merge, publish, distribute, sublicense, and/or sell\ncopies of the Software, and to permit persons to whom the Software is\nfurnished to do so, subject to the following conditions:\n\nThe above copyright notice and this permission notice shall be included in all\ncopies or substantial portions of the Software.\n\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\nIMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\nFITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\nAUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\nLIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\nOUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\nSOFTWARE.\n");
        jScrollPane5.setViewportView(jTextArea1);

        jLabel12.setText("Author Email Address:   ");

        jTextField1.setEditable(false);
        jTextField1.setText("neelcompanypayrollapp");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("About", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mainTabbedPaneStateChanged
        // TODO add your handling code here:

        JTabbedPane tabSource = (JTabbedPane) evt.getSource();
        String tab = tabSource.getTitleAt(tabSource.getSelectedIndex());

    }//GEN-LAST:event_mainTabbedPaneStateChanged

    private void btnGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateReportActionPerformed
        GenerateReportVM.GeneratePayrollReport(this);
    }//GEN-LAST:event_btnGenerateReportActionPerformed

    private void companySaveCompanyInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companySaveCompanyInfoActionPerformed
        // TODO add your handling code here:
        CompanyTabVM.SaveCompanyInfo(this);

    }//GEN-LAST:event_companySaveCompanyInfoActionPerformed

    private void cbCompanyPayrollFrequencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCompanyPayrollFrequencyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCompanyPayrollFrequencyActionPerformed

    private void w4_StateDe4BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_w4_StateDe4BtnActionPerformed
        // TODO add your handling code here:
        CA_De4_Dlg dialog = new CA_De4_Dlg(new javax.swing.JFrame(), true, GetEmployeeId());
        dialog.setVisible(true);
        dialog.dispose();
    }//GEN-LAST:event_w4_StateDe4BtnActionPerformed

    private void employeeW4SaveW4ChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeW4SaveW4ChangesActionPerformed
        // TODO add your handling code here:
        EmployeeW4TabVM.SaveEmployeeW4(this);
    }//GEN-LAST:event_employeeW4SaveW4ChangesActionPerformed

    private void w4Submitted2020PlusW4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_w4Submitted2020PlusW4ActionPerformed
        // TODO add your handling code here:
        showHideW4TabContrls(w4Submitted2020PlusW4.isSelected());
    }//GEN-LAST:event_w4Submitted2020PlusW4ActionPerformed

    private void btnEmployeeAddUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeeAddUpdateActionPerformed
        // TODO add your handling code here:
        EmployeeTabVM.EmployeeAddOrUpdate(this);
    }//GEN-LAST:event_btnEmployeeAddUpdateActionPerformed

    private void tfEmployeeFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmployeeFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmployeeFirstNameActionPerformed

    private void jTableEmployeeListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEmployeeListPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableEmployeeListPropertyChange

    private void btnRunOnePayPeriodPayrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunOnePayPeriodPayrollActionPerformed
        // TODO add your handling code here:
        OnePayrollVM.PayEmployeesOnePayPeriod(this);
    }//GEN-LAST:event_btnRunOnePayPeriodPayrollActionPerformed

    private void timesheetSaveTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timesheetSaveTableActionPerformed
        // TODO add your handling code here:
        TimeSheetTabVM.TimesheetAddOrUpdate(this);
    }//GEN-LAST:event_timesheetSaveTableActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public JYearChooser getReportYearChooser() {
        return reportYearChooser;
    }

    public JTextField getReportFilename() {
        return tfReportFilename;
    }

    private void showHideW4TabContrls(boolean bShowNewW4) {
        w4EmployeeOldW4Allowance.setEnabled(!bShowNewW4);
        cboOldW4FilingStatus.setEnabled(!bShowNewW4);
        tfw4OldAdditionalAmount.setEnabled(!bShowNewW4);

        w4New2020W4FormTable.setEnabled(bShowNewW4);
        w4New2020W4FormTable.setVisible(bShowNewW4);
    }

    public JTable getJtableOnePayPeriod() {
        return jtableOnePayPeriod;
    }

    public JLabel getLabelPayTimesheetEndDate() {
        return labelPayTimesheetEndDate;
    }

    public JDateChooser getPayDate() {
        return payDate;
    }

    public JDateChooser getPayTimesheetStartDate() {
        return payTimesheetStartDate;
    }

    public JButton getEmployeeAddUpdateBtn() {
        return btnEmployeeAddUpdate;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PayrollUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PayrollUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PayrollUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayrollUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        try {
            Util.ConsoleLogControl();
        } catch (Exception e) {
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                var mainDialog = new MainLoginOrCreateCompanyDlg(new javax.swing.JFrame(), true);
                mainDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }

                });
                mainDialog.setVisible(true);
                if (mainDialog.isloginOK() == true) {
                    mainDialog.dispose();
                    new PayrollUI().setVisible(true);
                }
                mainDialog.dispose();

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEmployeeAddUpdate;
    private javax.swing.JButton btnGenerateReport;
    private javax.swing.JButton btnRunOnePayPeriodPayroll;
    private javax.swing.JComboBox<String> cbCompanyPayrollFrequency;
    private javax.swing.JComboBox<String> cboOldW4FilingStatus;
    private javax.swing.JCheckBox ckemployee50PlusAge;
    private javax.swing.JPanel companyNameEtcPanel;
    private javax.swing.JPanel companyPane;
    private javax.swing.JTable companyRateTable;
    private javax.swing.JButton companySaveCompanyInfo;
    private javax.swing.JPanel employeePane;
    private javax.swing.JButton employeeW4SaveW4Changes;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTableEmployeeList;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTabbedPane jtabEmployeeDetails;
    private javax.swing.JTable jtableOnePayPeriod;
    private javax.swing.JLabel labelPayTimesheetEndDate;
    private javax.swing.JLabel lblTimesheetEndDate;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JPanel panelEmployeeInfo;
    private javax.swing.JPanel panelEmployeeW4;
    private com.toedter.calendar.JDateChooser payDate;
    private com.toedter.calendar.JDateChooser payTimesheetStartDate;
    private com.toedter.calendar.JYearChooser reportYearChooser;
    private javax.swing.JPanel runPayrollPane;
    private javax.swing.JSpinner spinemployee401KRate;
    private javax.swing.JTextField tfCompanyName;
    private javax.swing.JTextField tfEmployeeFirstName;
    private javax.swing.JTextField tfEmployeeLastName;
    private javax.swing.JTextField tfReportFilename;
    private javax.swing.JTextField tfSalary;
    private javax.swing.JTextField tfw4OldAdditionalAmount;
    private javax.swing.JLabel timesheetEndDate;
    private javax.swing.JPanel timesheetPane;
    private javax.swing.JButton timesheetSaveTable;
    private com.toedter.calendar.JDateChooser timesheetStartDate;
    private javax.swing.JTable timesheetTable;
    private javax.swing.JSpinner w4EmployeeOldW4Allowance;
    private javax.swing.JTable w4New2020W4FormTable;
    private javax.swing.JCheckBox w4Submitted2020PlusW4;
    private javax.swing.JCheckBox w4_Exempt;
    private javax.swing.JButton w4_StateDe4Btn;
    // End of variables declaration//GEN-END:variables

}
