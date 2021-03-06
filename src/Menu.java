import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Menu extends JFrame {

    private ArrayList<Customer> customerList = new ArrayList<Customer>();
    private int position = 0;
    private String password;
    private Customer customer;
    CustomerAccount acc = new CustomerAccount();
    JFrame f, f1;
    JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
    JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
    JLabel customerIDLabel, passwordLabel;
    JTextField customerIDTextField, passwordTextField;
    Container content;
    Customer cust;


    public static void main(String[] args) {
        Menu driver = new Menu();
        driver.menuStart();
    }

    public void menuStart() {
		   /*The menuStart method asks the user if they are a new customer, an existing customer or an admin. It will then start the create customer process
		  if they are a new customer, or will ask them to log in if they are an existing customer or admin.*/
        f = new JFrame("User Type");
        f.setSize(400, 300);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        JPanel userTypePanel = new JPanel();
        final ButtonGroup userType = new ButtonGroup();
        JRadioButton radioButton;
        userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
        radioButton.setActionCommand("Customer");
        userType.add(radioButton);

        userTypePanel.add(radioButton = new JRadioButton("Administrator"));
        radioButton.setActionCommand("Administrator");
        userType.add(radioButton);

        userTypePanel.add(radioButton = new JRadioButton("New Customer"));
        radioButton.setActionCommand("New Customer");
        userType.add(radioButton);

        JPanel continuePanel = new JPanel();
        JButton continueButton = new JButton("Continue");
        continuePanel.add(continueButton);

        Container content = f.getContentPane();
        content.setLayout(new GridLayout(2, 1));
        content.add(userTypePanel);
        content.add(continuePanel);


        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                MenuMethods methods = new MenuMethods();
                String user = userType.getSelection().getActionCommand();
                // Switch case to check customer selection and launch chosen method
                switch (user) {
                    case "New Customer":
                        methods.createNewCustomer(f, customerList);
                        break;
                    case "Administrator":
                        methods.selectAdmin(f, f1);
                        break;
                    case "Customer":
                        methods.selectCustomer(f, customerList);
                        break;
                }


            }
        });
        f.setVisible(true);
    }


    public void admin() {
        dispose();

        f = new JFrame("Administrator Menu");
        f.setSize(400, 400);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        f.setVisible(true);

        JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton deleteCustomer = new JButton("Delete Customer");
        deleteCustomer.setPreferredSize(new Dimension(250, 20));
        deleteCustomerPanel.add(deleteCustomer);

        JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton deleteAccount = new JButton("Delete Account");
        deleteAccount.setPreferredSize(new Dimension(250, 20));
        deleteAccountPanel.add(deleteAccount);

        JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton bankChargesButton = new JButton("Apply Bank Charges");
        bankChargesButton.setPreferredSize(new Dimension(250, 20));
        bankChargesPanel.add(bankChargesButton);

        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton interestButton = new JButton("Apply Interest");
        interestPanel.add(interestButton);
        interestButton.setPreferredSize(new Dimension(250, 20));

        JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton editCustomerButton = new JButton("Edit existing Customer");
        editCustomerPanel.add(editCustomerButton);
        editCustomerButton.setPreferredSize(new Dimension(250, 20));

        JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton navigateButton = new JButton("Navigate Customer Collection");
        navigatePanel.add(navigateButton);
        navigateButton.setPreferredSize(new Dimension(250, 20));

        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton summaryButton = new JButton("Display Summary Of All Accounts");
        summaryPanel.add(summaryButton);
        summaryButton.setPreferredSize(new Dimension(250, 20));

        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton accountButton = new JButton("Add an Account to a Customer");
        accountPanel.add(accountButton);
        accountButton.setPreferredSize(new Dimension(250, 20));

        JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton returnButton = new JButton("Exit Admin Menu");
        returnPanel.add(returnButton);

        JLabel label1 = new JLabel("Please select an option");

        content = f.getContentPane();
        content.setLayout(new GridLayout(9, 1));
        content.add(label1);
        content.add(accountPanel);
        content.add(bankChargesPanel);
        content.add(interestPanel);
        content.add(editCustomerPanel);
        content.add(navigatePanel);
        content.add(summaryPanel);
        content.add(deleteCustomerPanel);
        //	content.add(deleteAccountPanel);
        content.add(returnPanel);

        BankAdministration administration = new BankAdministration();
        bankChargesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                administration.bankCharges(acc, customerList, f, cust);

            }
        });

        interestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                administration.bankInterest(acc, customerList, f, cust);

            }
        });

        editCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
                } else {
                    cust.editCustomer(f, customerList);
                }
            }
        });

        summaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                acc.summary(f, customerList, acc, content);
            }
        });

        navigateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                f.dispose();

                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
                    admin();
                } else {

                    JButton first, previous, next, last, cancel;
                    JPanel gridPanel, buttonPanel, cancelPanel;

                    Container content = getContentPane();

                    content.setLayout(new BorderLayout());

                    buttonPanel = new JPanel();
                    gridPanel = new JPanel(new GridLayout(8, 2));
                    cancelPanel = new JPanel();

                    firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
                    surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
                    pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
                    dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
                    customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
                    passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
                    firstNameTextField = new JTextField(20);
                    surnameTextField = new JTextField(20);
                    pPSTextField = new JTextField(20);
                    dOBTextField = new JTextField(20);
                    customerIDTextField = new JTextField(20);
                    passwordTextField = new JTextField(20);

                    first = new JButton("First");
                    previous = new JButton("Previous");
                    next = new JButton("Next");
                    last = new JButton("Last");
                    cancel = new JButton("Cancel");

                    firstNameTextField.setText(customerList.get(0).getFirstName());
                    surnameTextField.setText(customerList.get(0).getSurname());
                    pPSTextField.setText(customerList.get(0).getPPS());
                    dOBTextField.setText(customerList.get(0).getDOB());
                    customerIDTextField.setText(customerList.get(0).getCustomerID());
                    passwordTextField.setText(customerList.get(0).getPassword());

                    firstNameTextField.setEditable(false);
                    surnameTextField.setEditable(false);
                    pPSTextField.setEditable(false);
                    dOBTextField.setEditable(false);
                    customerIDTextField.setEditable(false);
                    passwordTextField.setEditable(false);

                    gridPanel.add(firstNameLabel);
                    gridPanel.add(firstNameTextField);
                    gridPanel.add(surnameLabel);
                    gridPanel.add(surnameTextField);
                    gridPanel.add(pPPSLabel);
                    gridPanel.add(pPSTextField);
                    gridPanel.add(dOBLabel);
                    gridPanel.add(dOBTextField);
                    gridPanel.add(customerIDLabel);
                    gridPanel.add(customerIDTextField);
                    gridPanel.add(passwordLabel);
                    gridPanel.add(passwordTextField);

                    buttonPanel.add(first);
                    buttonPanel.add(previous);
                    buttonPanel.add(next);
                    buttonPanel.add(last);

                    cancelPanel.add(cancel);

                    content.add(gridPanel, BorderLayout.NORTH);
                    content.add(buttonPanel, BorderLayout.CENTER);
                    content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
                    first.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            position = 0;
                            firstNameTextField.setText(customerList.get(0).getFirstName());
                            surnameTextField.setText(customerList.get(0).getSurname());
                            pPSTextField.setText(customerList.get(0).getPPS());
                            dOBTextField.setText(customerList.get(0).getDOB());
                            customerIDTextField.setText(customerList.get(0).getCustomerID());
                            passwordTextField.setText(customerList.get(0).getPassword());
                        }
                    });

                    previous.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {

                            if (position < 1) {
                                //don't do anything
                            } else {
                                position = position - 1;

                                firstNameTextField.setText(customerList.get(position).getFirstName());
                                surnameTextField.setText(customerList.get(position).getSurname());
                                pPSTextField.setText(customerList.get(position).getPPS());
                                dOBTextField.setText(customerList.get(position).getDOB());
                                customerIDTextField.setText(customerList.get(position).getCustomerID());
                                passwordTextField.setText(customerList.get(position).getPassword());
                            }
                        }
                    });

                    next.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {

                            if (position == customerList.size() - 1) {
                                //don't do anything
                            } else {
                                position = position + 1;

                                firstNameTextField.setText(customerList.get(position).getFirstName());
                                surnameTextField.setText(customerList.get(position).getSurname());
                                pPSTextField.setText(customerList.get(position).getPPS());
                                dOBTextField.setText(customerList.get(position).getDOB());
                                customerIDTextField.setText(customerList.get(position).getCustomerID());
                                passwordTextField.setText(customerList.get(position).getPassword());
                            }


                        }
                    });

                    last.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {

                            position = customerList.size() - 1;

                            firstNameTextField.setText(customerList.get(position).getFirstName());
                            surnameTextField.setText(customerList.get(position).getSurname());
                            pPSTextField.setText(customerList.get(position).getPPS());
                            dOBTextField.setText(customerList.get(position).getDOB());
                            customerIDTextField.setText(customerList.get(position).getCustomerID());
                            passwordTextField.setText(customerList.get(position).getPassword());
                        }
                    });

                    cancel.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            dispose();
                            admin();
                        }
                    });
                    setContentPane(content);
                    setSize(400, 300);
                    setVisible(true);
                }
            }
        });

        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
                } else {
                    cust.addCustomer(f1, f, customerList, "1235");
                }
            }
        });

        deleteCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!customerList.isEmpty()){
                cust.deleteCustomer(f, customerList);}
                else{
                    JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
                }
            }
        });

        deleteAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean found = true;


                {
                    Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer from which you wish to delete an account");

                    for (Customer aCustomer : customerList) {

                        if (aCustomer.getCustomerID().equals(customerID)) {
                            found = true;
                            customer = aCustomer;

                        }
                    }

                    if (found == false) {
                        JOptionPane.showConfirmDialog(null, null, "User not found", JOptionPane.ERROR_MESSAGE);

                    } else {
                        //Here I would make the user select a an account to delete from a combo box. If the account had a balance of 0 then it would be deleted. (I do not have time to do this)
                    }


                }
            }

        });
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                f.dispose();
                menuStart();
            }
        });
    }

    public void customer(Customer e1) {
        f = new JFrame("Customer Menu");
        e1 = cust;
        f.setSize(400, 300);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        f.setVisible(true);

        if (cust.getAccounts().size() == 0) {
            JOptionPane.showMessageDialog(f, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. ", "Oops!", JOptionPane.INFORMATION_MESSAGE);
            f.dispose();
            menuStart();
        } else {
            JPanel buttonPanel = new JPanel();
            JPanel boxPanel = new JPanel();
            JPanel labelPanel = new JPanel();

            JLabel label = new JLabel("Select Account:");
            labelPanel.add(label);

            JButton returnButton = new JButton("Return");
            buttonPanel.add(returnButton);
            JButton continueButton = new JButton("Continue");
            buttonPanel.add(continueButton);

            JComboBox<String> box = new JComboBox<String>();
            for (int i = 0; i < cust.getAccounts().size(); i++) {
                box.addItem(cust.getAccounts().get(i).getNumber());
            }


            for (int i = 0; i < cust.getAccounts().size(); i++) {
                if (cust.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
                    acc = cust.getAccounts().get(i);
                }
            }


            boxPanel.add(box);
            content = f.getContentPane();
            content.setLayout(new GridLayout(3, 1));
            content.add(labelPanel);
            content.add(boxPanel);
            content.add(buttonPanel);

            returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    f.dispose();
                    menuStart();
                }
            });

            Customer finalE = e1;
            continueButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {

                    f.dispose();

                    f = new JFrame("Customer Menu");
                    f.setSize(400, 300);
                    f.setLocation(200, 200);
                    f.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we) {
                            System.exit(0);
                        }
                    });
                    f.setVisible(true);

                    JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JButton statementButton = new JButton("Display Bank Statement");
                    statementButton.setPreferredSize(new Dimension(250, 20));

                    statementPanel.add(statementButton);

                    JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JButton lodgementButton = new JButton("Lodge money into account");
                    lodgementPanel.add(lodgementButton);
                    lodgementButton.setPreferredSize(new Dimension(250, 20));

                    JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JButton withdrawButton = new JButton("Withdraw money from account");
                    withdrawalPanel.add(withdrawButton);
                    withdrawButton.setPreferredSize(new Dimension(250, 20));

                    JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    JButton returnButton = new JButton("Exit Customer Menu");
                    returnPanel.add(returnButton);

                    JLabel label1 = new JLabel("Please select an option");

                    content = f.getContentPane();
                    content.setLayout(new GridLayout(5, 1));
                    content.add(label1);
                    content.add(statementPanel);
                    content.add(lodgementPanel);
                    content.add(withdrawalPanel);
                    content.add(returnPanel);

                    statementButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {


                            returnButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent ae) {

                                    acc.statement(acc, f, content);
                                    f.dispose();
                                    customer(cust);
                                }
                            });
                        }
                    });

                    lodgementButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            CustomerDepositAccount depositAccount = new CustomerDepositAccount();
                            depositAccount.lodge(acc, finalE, f);


                        }
                    });

                    withdrawButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            CustomerCurrentAccount currentAccount = new CustomerCurrentAccount();
                            currentAccount.withdraw(acc, finalE, f);
                        }
                    });

                    returnButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            f.dispose();
                            menuStart();
                        }
                    });
                }
            });
        }
    }

    public static boolean isNumeric(String str) { // a method that tests if a string is numeric{
        try {
            double d = Double.parseDouble(str);
        } catch (
                NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}