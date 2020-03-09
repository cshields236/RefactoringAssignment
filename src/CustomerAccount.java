

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CustomerAccount  {

    String number;
    double balance;
    ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

    //Blank Constructor
    public CustomerAccount()
    {
        this.number = "";
        this.balance = 0;
        this.transactionList = null;
    }

    //Constructor with Details
    public CustomerAccount(String number, double balance, ArrayList<AccountTransaction> transactionList)
    {
        this.number = number;
        this.balance = balance;
        this.transactionList = transactionList;
    }

    //Accessor methods

    public String getNumber()
    {
        return this.number;
    }




    public double getBalance()
    {
        return this.balance;
    }

    public ArrayList getTransactionList()
    {
        return this.transactionList;
    }

    //Mutator methods
    public void setNumber(String number)
    {
        this.number = number;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void setTransactionList(ArrayList transactionList)
    {
        this.transactionList = transactionList;
    }


    public void statement(CustomerAccount acc,JFrame f, Container container){
        f.dispose();
        f = new JFrame("Customer Menu");
        f.setSize(400, 600);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        f.setVisible(true);

        JLabel label1 = new JLabel("Summary of account transactions: ");

        JPanel returnPanel = new JPanel();
        JButton returnButton = new JButton("Return");
        returnPanel.add(returnButton);

        JPanel textPanel = new JPanel();

        textPanel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea(40, 20);
        textArea.setEditable(false);
        textPanel.add(label1, BorderLayout.NORTH);
        textPanel.add(textArea, BorderLayout.CENTER);
        textPanel.add(returnButton, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(textArea);
        textPanel.add(scrollPane);

        for (int i = 0; i < acc.getTransactionList().size(); i++) {
            textArea.append(acc.getTransactionList().get(i).toString());

        }
        textPanel.add(textArea);
        container.removeAll();



        container.setLayout(new GridLayout(1, 1));

        container.add(textPanel);


    }

    public void summary(JFrame f, ArrayList<Customer> customerList, CustomerAccount acc, Container container){
        f.dispose();

        Menu m = new Menu();
        f = new JFrame("Summary of Transactions");
        f.setSize(400, 700);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        f.setVisible(true);

        JLabel label1 = new JLabel("Summary of all transactions: ");

        JPanel returnPanel = new JPanel();
        JButton returnButton = new JButton("Return");
        returnPanel.add(returnButton);

        JPanel textPanel = new JPanel();

        textPanel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea(40, 20);
        textArea.setEditable(false);
        textPanel.add(label1, BorderLayout.NORTH);
        textPanel.add(textArea, BorderLayout.CENTER);
        textPanel.add(returnButton, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(textArea);
        textPanel.add(scrollPane);

        for (int a = 0; a < customerList.size(); a++)//For each customer, for each account, it displays each transaction.
        {
            for (int b = 0; b < customerList.get(a).getAccounts().size(); b++) {
                acc = customerList.get(a).getAccounts().get(b);
                for (int c = 0; c < customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++) {

                    textArea.append(acc.getTransactionList().get(c).toString());
                    //Int total = acc.getTransactionList().get(c).getAmount(); //I was going to use this to keep a running total but I couldnt get it  working.

                }
            }
        }


        textPanel.add(textArea);
        container.removeAll();


        Container content = f.getContentPane();
        content.setLayout(new GridLayout(1, 1));
        //	content.add(label1);
        content.add(textPanel);
        //content.add(returnPanel);

        JFrame finalF = f;
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                finalF.dispose();
                m.admin();
            }
        });}

}
