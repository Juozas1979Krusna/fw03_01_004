
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class fw03_01_004 {
    static JButton getAccountButton_dv_sn;
    static JButton getAccountButton_sp_pr_nm;
    static JButton getAccountButton_sp_pr_sn;
    static JButton getAccountButton_event;
    static JButton getAccountButton_sp_rt_in;
    static JList accountNumberList_dv_sn;
    static JList accountNumberList_sp_pr_nm;
    static JList accountNumberList_sp_pr_sn;
    static JList accountNumberList_event;
    static JList accountNumberList_sp_rt_in;
    static Connection connection;
    static JTextField accountIDText_dv_sn,
            usernameText_dv_sn,
            passwordText_dv_sn,
            tsText_dv_sn;
    static JTextField accountIDText_sp_pr_nm,
            usernameText_sp_pr_nm,
            passwordText_sp_pr_nm,
            tsText_sp_pr_nm;
    static JTextField accountIDText_sp_pr_sn,
            usernameText_sp_pr_sn,
            passwordText_sp_pr_sn,
            tsText_sp_pr_sn;

    static JTextField t01;
    static JTextField t02;
    static JButton b01;
    static String A_text;
    static String A_text_dv_sn;
    static String A_text_sp_pr_sn;
    static String A_text_event;
    static String A_text_sp_rt_in;
    static String B_text;

    // JTextField
    static JTextField tLogInLogIn;
    static JTextField tLogInPassw;

    static JTextField tEmpRgLogIn;
    static JTextField tEmpRgPssw1;
    static JTextField tEmpRgPssw2;

    static JTextField tDevRgDevNm;
    static JTextField tDevRgDescr;
    static JTextField tDevRgSrNum;

    static JTextField tSpPrtSPtNm;
    static JTextField tSpPrtDescr;
    static JTextField tSpPrtSrNum;

    // JButton
    static JButton buttonSaveListName_xxx;
    static JButton bEmpRgSubmit;

    static JButton bDevRgSubmit;

    static JButton bSpPrtSubmit;

    // JComboBox
    static JComboBox xSpPrtPtType;

    static void connectToDB() {
        try {
            String JdbcURL = "jdbc:mysql://localhost:3306/u866625815_yxub?" + "serverTimezone=UTC";
            String Username = "jk";
            String password = "1979";
            Connection con = null;
            connection = DriverManager.getConnection(JdbcURL, Username, password);
        } catch(SQLException e) {
            System.out.println("Unable to connect to database");
            System.exit(1);
        }
    }
    private void displaySQLErrors(SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
    private void init() {
        connectToDB();
    }
    public fw03_01_004()
    {
        this.butt_05_scope();
    }
    private static void JPanel1() {
        JTextField tcw06_01_001_f02 = new JTextField(20);
        tcw06_01_001_f02.setText("Insert the activity NAME here");
        int arrayDim = 40;
        int arrhw06_01_001_doneIn01[] = new int[40];
        for (int z = 0; z < arrayDim; z++) {
            arrhw06_01_001_doneIn01[z] = 0;
        }
        JPanel panel;
        panel = new JPanel(new GridLayout(0, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        /* V Log In */
        JPanel logInPanel = new JPanel();

        tLogInLogIn = new JTextField(20);
        tLogInPassw = new JTextField(20);

        logInPanel.add(new JLabel("Log In"));
        logInPanel.add(tLogInLogIn);
        logInPanel.add(new JLabel("Password"));
        logInPanel.add(tLogInPassw);

        buttonSaveListName_xxx = new JButton(new AbstractAction("Save the activities list NAME"){
            public void actionPerformed(ActionEvent arg0) {
                final ButtonGroup btnGroup = new ButtonGroup();
                JPanel panel;
                panel = new JPanel(new GridLayout(0, 1));
                JTabbedPane tabbedPane = new JTabbedPane();
                /* V Device Register */
                JPanel deviceRegisterPanel = new JPanel();

                tDevRgDevNm = new JTextField(20);
                tDevRgDescr = new JTextField(20);
                tDevRgSrNum = new JTextField(20);

                deviceRegisterPanel.add(new JLabel("Device Name"));
                deviceRegisterPanel.add(tDevRgDevNm);
                deviceRegisterPanel.add(new JLabel("Device description"));
                deviceRegisterPanel.add(tDevRgDescr);
                deviceRegisterPanel.add(new JLabel("S/N"));
                deviceRegisterPanel.add(tDevRgSrNum);

                bDevRgSubmit = new JButton("Submit");
                deviceRegisterPanel.add(bDevRgSubmit);
                tabbedPane.addTab("Device Register", deviceRegisterPanel);
                /* A Device Register*/
                /* V Spare Part*/
                JPanel spPrtRegisterPanel = new JPanel();
                JPanel sparePartPanel = new JPanel();
                tSpPrtSPtNm = new JTextField(20);
                tSpPrtDescr = new JTextField(20);
                tSpPrtSrNum = new JTextField(20);

                sparePartPanel.add(new JLabel("Spare Part name"));
                sparePartPanel.add(tSpPrtSPtNm);
                sparePartPanel.add(new JLabel("Spare Part description"));
                sparePartPanel.add(tSpPrtDescr);
                sparePartPanel.add(new JLabel("S/N"));
                sparePartPanel.add(tSpPrtSrNum);

                sparePartPanel.add(new JLabel("Part Type"));
                String sparePartItLst[]={"Display","CD-rom","Keyboard","Power Supply","Motherboard"};
                xSpPrtPtType = new JComboBox(sparePartItLst);
                sparePartPanel.add(xSpPrtPtType);

                bSpPrtSubmit = new JButton("Submit");
                sparePartPanel.add(bSpPrtSubmit);
                //Do Account List
                Vector v_sp_rt_in = new Vector();
                try {
                    Statement statement_sp_rt_in = connection.createStatement();
                    ResultSet rs_sp_rt_in = statement_sp_rt_in.executeQuery("SELECT spare_part_name FROM spare_parts_00");
                    while(rs_sp_rt_in.next()) {
                        v_sp_rt_in.addElement(rs_sp_rt_in.getString("spare_part_name"));
                    }
                    rs_sp_rt_in.close();
                } catch(SQLException e_sp_rt_in) { }
                accountNumberList_sp_rt_in = new JList(v_sp_rt_in);
                accountNumberList_sp_rt_in.setVisibleRowCount(2);
                JScrollPane accountNumberListScrollPane_sp_rt_in = new JScrollPane(accountNumberList_sp_rt_in);
                getAccountButton_sp_rt_in = new JButton("Select S/P Type and Save");
                getAccountButton_sp_rt_in.addActionListener (
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Statement statement_sp_rt_in = connection.createStatement();
                                    ResultSet rs_sp_rt_in = statement_sp_rt_in.executeQuery("SELECT * FROM spare_parts_00 WHERE spare_part_name = '" + accountNumberList_sp_rt_in.getSelectedValue()+"'");
                                    if (rs_sp_rt_in.next()) {
                                        A_text_sp_rt_in=(rs_sp_rt_in.getString("spare_parts_id"));
                                    }
                                } catch(SQLException ee) {}
                                try {
                                    Statement statement_sp_rt_in = connection.createStatement();
                                    String Name= tSpPrtSPtNm.getText();
                                    String  Description = tSpPrtDescr.getText();
                                    String  Sn = tSpPrtSrNum.getText();
                                    String spare_part_list_id ="2";
                                    String employee_list_id = "20";
                                    int i = statement_sp_rt_in.executeUpdate("INSERT INTO spare_parts_items_00 VALUES('"+Name+"', '"+Description+"', "+Sn+", "+"now()"+", '"+0+"', "+Sn+", " + A_text_sp_rt_in+", "+20+")");                                     // | name     | description    | sn   | reg_date      | state |           id |         spare_part_list_id | employee_list_id |
                                } catch(SQLException insertException) {
                                }
                            }
                        }
                );
                JPanel first_event = new JPanel();
                first_event.add(accountNumberListScrollPane_sp_rt_in);
                first_event.add(getAccountButton_sp_rt_in);

                spPrtRegisterPanel.add(sparePartPanel);
                spPrtRegisterPanel.add(first_event);
                tabbedPane.addTab("Spare Part", spPrtRegisterPanel);
                /* A Spare Part*/
                /* V Event Register */
                JPanel eventRegisterPanel = new JPanel();
                eventRegisterPanel.add(new JLabel("Event Register"));
                Vector v_dv_sn = new Vector();
                try {
                    Statement statement_dv_sn = connection.createStatement();
                    ResultSet rs_dv_sn = statement_dv_sn.executeQuery("SELECT sn FROM Device_items_00");
                    while(rs_dv_sn.next()) {
                        v_dv_sn.addElement(rs_dv_sn.getString("sn"));
                    }
                    rs_dv_sn.close();
                } catch(SQLException e) { }
                accountNumberList_dv_sn = new JList(v_dv_sn);
                accountNumberList_dv_sn.setVisibleRowCount(2);
                JScrollPane accountNumberListScrollPane_dv_sn = new JScrollPane(accountNumberList_dv_sn);
                getAccountButton_dv_sn = new JButton("Select Device S/N");
                getAccountButton_dv_sn.addActionListener (
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Statement statement_dv_sn = connection.createStatement();
                                    ResultSet rs = statement_dv_sn.executeQuery("SELECT * FROM Device_items_00 WHERE sn = '" + accountNumberList_dv_sn.getSelectedValue()+"'");
                                    if (rs.next()) {
                                        A_text_dv_sn=(rs.getString("sn"));
                                    }
                                    //Do Account List
                                    Vector v_sp_pr_nm = new Vector();
                                    try {
                                        Statement statement_sp_pr_nm = connection.createStatement();
                                        ResultSet rs_sp_pr_nm = statement_sp_pr_nm.executeQuery("SELECT spare_part_name FROM Spare_parts_00");
                                        while(rs_sp_pr_nm.next()) {
                                            v_sp_pr_nm.addElement(rs_sp_pr_nm.getString("spare_part_name"));
                                        }
                                        rs_sp_pr_nm.close();
                                    } catch(SQLException e_sp_pr_nm) { }
                                    accountNumberList_sp_pr_nm = new JList(v_sp_pr_nm);
                                    accountNumberList_sp_pr_nm.setVisibleRowCount(2);
                                    JScrollPane accountNumberListScrollPane_sp_pr_nm = new JScrollPane(accountNumberList_sp_pr_nm);
                                    getAccountButton_sp_pr_nm = new JButton("Select Spare Part");
                                    getAccountButton_sp_pr_nm.addActionListener (
                                            new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        Statement statement_sp_pr_nm = connection.createStatement();
                                                        ResultSet rs_sp_pr_nm = statement_sp_pr_nm.executeQuery("SELECT * FROM Spare_parts_00 WHERE spare_part_name = '" + accountNumberList_sp_pr_nm.getSelectedValue()+"'");
                                                        if (rs_sp_pr_nm.next()) {
                                                            accountIDText_sp_pr_nm.setText(rs_sp_pr_nm.getString("spare_parts_id"));
                                                            usernameText_sp_pr_nm.setText(rs_sp_pr_nm.getString("spare_part_name"));
                                                            passwordText_sp_pr_nm.setText(rs_sp_pr_nm.getString("spare_part_description"));
                                                            tsText_sp_pr_nm.setText(rs_sp_pr_nm.getString("spare_part_reg_date"));
                                                            A_text=(rs_sp_pr_nm.getString("spare_parts_id"));
                                                            B_text=(rs_sp_pr_nm.getString("spare_part_name"));
                                                        }
                                                        Vector v_sp_pr_sn = new Vector();
                                                        try {
                                                            Statement statement_sp_pr_sn = connection.createStatement();
                                                            ResultSet rs_sp_pr_sn = statement_sp_pr_sn.executeQuery("SELECT sn FROM spare_parts_items_00 WHERE spare_part_list_id = '" +A_text+"'");
                                                            while(rs_sp_pr_sn.next()) {
                                                                v_sp_pr_sn.addElement(rs_sp_pr_sn.getString("sn"));
                                                            }
                                                            rs_sp_pr_sn.close();
                                                        } catch(SQLException e_sp_pr_sn) { }
                                                        accountNumberList_sp_pr_sn = new JList(v_sp_pr_sn);
                                                        accountNumberList_sp_pr_sn.setVisibleRowCount(2);
                                                        JScrollPane accountNumberListScrollPane_sp_pr_sn = new JScrollPane(accountNumberList_sp_pr_sn);
                                                        getAccountButton_sp_pr_sn = new JButton("Select Spare Part S/N");
                                                        getAccountButton_sp_pr_sn.addActionListener (
                                                                new ActionListener() {
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        try {
                                                                            Statement statement_sp_pr_sn = connection.createStatement();
                                                                            ResultSet rs_sp_pr_sn = statement_sp_pr_sn.executeQuery("SELECT * FROM spare_parts_items_00 WHERE sn = '" + accountNumberList_sp_pr_sn.getSelectedValue()+"'");
                                                                            if (rs_sp_pr_sn.next()) {
                                                                                A_text_sp_pr_sn=(rs_sp_pr_sn.getString("sn"));
                                                                            }
                                                                            Vector v_event = new Vector();
                                                                            try {
                                                                                Statement statement_event = connection.createStatement();
                                                                                ResultSet rs_event = statement_event.executeQuery("SELECT work_name FROM works_00 ");
                                                                                while(rs_event.next()) {
                                                                                    v_event.addElement(rs_event.getString("work_name"));
                                                                                }
                                                                                rs_event.close();
                                                                            } catch(SQLException e_event) { }
                                                                            accountNumberList_event = new JList(v_event);
                                                                            accountNumberList_event.setVisibleRowCount(2);
                                                                            JScrollPane accountNumberListScrollPane_event = new JScrollPane(accountNumberList_event);
                                                                            getAccountButton_event = new JButton("Select Event and Save");
                                                                            getAccountButton_event.addActionListener (
                                                                                    new ActionListener() {
                                                                                        public void actionPerformed(ActionEvent e) {
                                                                                            try {
                                                                                                Statement statement_event = connection.createStatement();
                                                                                                ResultSet rs_event = statement_event.executeQuery("SELECT * FROM works_00 WHERE work_name = '" + accountNumberList_event.getSelectedValue()+"'");
                                                                                                if (rs_event.next()) {
                                                                                                    A_text_event=(rs_event.getString("work_name"));
                                                                                                }
                                                                                            } catch(SQLException ee) {}
                                                                                            try {
                                                                                                Statement statement_insert = connection.createStatement();
                                                                                                ResultSet rs_sp_pr_sn = statement_sp_pr_sn.executeQuery("SELECT * FROM spare_parts_items_00 WHERE sn = '" + accountNumberList_sp_pr_sn.getSelectedValue()+"'");
                                                                                                String Mouse = "Mouse"; String order = "order";
                                                                                                int i = statement_insert.executeUpdate("INSERT INTO event_02 VALUES("+2+", "+A_text_dv_sn+", '"+B_text+"', "+A_text_sp_pr_sn+", '"+A_text_event+"', " + "now())");
                                                                                            } catch(SQLException insertException) {
                                                                                            }
                                                                                        }
                                                                                    }
                                                                            );
                                                                            JPanel first_event = new JPanel();
                                                                            first_event.add(accountNumberListScrollPane_event);
                                                                            first_event.add(getAccountButton_event);
                                                                            eventRegisterPanel.add(first_event);
                                                                        } catch(SQLException ee) {}
                                                                    }
                                                                }
                                                        );
                                                        JPanel first_sp_pr_sn = new JPanel();
                                                        first_sp_pr_sn.add(accountNumberListScrollPane_sp_pr_sn);
                                                        first_sp_pr_sn.add(getAccountButton_sp_pr_sn);
                                                        accountIDText_sp_pr_sn = new JTextField(15);
                                                        usernameText_sp_pr_sn = new JTextField(15);
                                                        passwordText_sp_pr_sn = new JTextField(15);
                                                        tsText_sp_pr_sn = new JTextField(15);
                                                        t02 = new JTextField(16);
                                                        t01 = new JTextField(16);
                                                        b01 = new JButton("Calculate");
                                                        JPanel third_sp_pr_sn = new JPanel();
                                                        third_sp_pr_sn.add(b01);
                                                        JPanel fourth_sp_pr_sn = new JPanel();
                                                        fourth_sp_pr_sn.add(t01);
                                                        fourth_sp_pr_sn.add(t02);
                                                        eventRegisterPanel.add(first_sp_pr_sn);
                                                    } catch(SQLException ee) {}
                                                }
                                            }
                                    );
                                    JPanel first_sp_pr_nm = new JPanel();
                                    first_sp_pr_nm.add(accountNumberListScrollPane_sp_pr_nm);
                                    first_sp_pr_nm.add(getAccountButton_sp_pr_nm);
                                    accountIDText_sp_pr_nm = new JTextField(15);
                                    usernameText_sp_pr_nm = new JTextField(15);
                                    passwordText_sp_pr_nm = new JTextField(15);
                                    tsText_sp_pr_nm = new JTextField(15);
                                    t02 = new JTextField(16);
                                    t01 = new JTextField(16);
                                    b01 = new JButton("Calculate");
                                    JPanel third_sp_pr_nm = new JPanel();
                                    third_sp_pr_nm.add(b01);
                                    JPanel fourth_sp_pr_nm = new JPanel();
                                    fourth_sp_pr_nm.add(t01);
                                    fourth_sp_pr_nm.add(t02);
                                    eventRegisterPanel.add(first_sp_pr_nm);
                                } catch(SQLException ee) {}
                            }
                        }
                );
                JPanel first_dv_sn = new JPanel();
                first_dv_sn.add(accountNumberListScrollPane_dv_sn);
                first_dv_sn.add(getAccountButton_dv_sn);
                accountIDText_dv_sn = new JTextField(15);
                usernameText_dv_sn = new JTextField(15);
                passwordText_dv_sn = new JTextField(15);
                tsText_dv_sn = new JTextField(15);
                t02 = new JTextField(16);
                t01 = new JTextField(16);

                b01 = new JButton("Calculate");
                JPanel third_dv_sn = new JPanel();
                third_dv_sn.add(b01);
                JPanel fourth_dv_sn = new JPanel();
                fourth_dv_sn.add(t01);
                fourth_dv_sn.add(t02);
                eventRegisterPanel.add(first_dv_sn);
                tabbedPane.addTab("Event Register", eventRegisterPanel);
                /* A Event Register*/
                /* V Common Table*/
                JPanel commonTablePanel = new JPanel();
                commonTablePanel.add(new JLabel("Common Table"));

                // Add Account Tab
                tabbedPane.addTab("Common Table", commonTablePanel);
                panel.setLayout(new BorderLayout());
                panel.setPreferredSize(new Dimension(500, 200));
                panel.add(tabbedPane, BorderLayout.CENTER);
                JFrame frame = new JFrame("Insert activities into activities list of ");
                frame.getContentPane().add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        logInPanel.add(buttonSaveListName_xxx);
        // Add Log In Tab
        tabbedPane.addTab("Log In", logInPanel);
        /* A Log In */
        /* V Employ Register  */
        JPanel employRegisterPanel = new JPanel();

        tEmpRgLogIn = new JTextField(20);
        tEmpRgPssw1 = new JTextField(20);
        tEmpRgPssw2 = new JTextField(20);

        employRegisterPanel.add(new JLabel("Sign Up"));
        employRegisterPanel.add(tEmpRgLogIn);
        employRegisterPanel.add(new JLabel("Password"));
        employRegisterPanel.add(tEmpRgPssw1);
        employRegisterPanel.add(new JLabel("re Password"));
        employRegisterPanel.add(tEmpRgPssw2);

        bEmpRgSubmit = new JButton("Submit");
        employRegisterPanel.add(bEmpRgSubmit);

        // Add Employ Register Tab
        tabbedPane.addTab("Employ Register", employRegisterPanel);
        /* A Employ Register */
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(500, 200));
        panel.add(tabbedPane, BorderLayout.CENTER);
        JFrame frame = new JFrame("Insert the activities list NAME");
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void  butt_05_scope() {
        this.JPanel1();
    }
    public static void main(String[] args) {
        fw03_01_004 bf = new fw03_01_004();
        bf.init();
        bf.JPanel1();
    }
}


