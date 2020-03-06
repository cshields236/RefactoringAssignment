

import javax.swing.*;
import java.awt.*;
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


        Container content = f.getContentPane();
        container.setLayout(new GridLayout(1, 1));

        container.add(textPanel);


    }

}
