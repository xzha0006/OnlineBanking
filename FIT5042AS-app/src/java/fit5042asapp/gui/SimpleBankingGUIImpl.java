/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fit5042asapp.gui;

import fit5042.as.repository.entities.BankAccount;
import fit5042.as.repository.entities.BankUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

public class SimpleBankingGUIImpl extends JFrame implements SimpleBankingGUI {
    
    private static final String[] TABLE_COLUMNS = {"ID", "FirstName", "Last Name", "Password", "Email", "Type"};
    private static final String TAG_SEPARATOR = ",";
    
    private final JButton closeButton;
    private final JButton addButton;
    private final JButton viewButton;
    private final JButton searchButton;
    private final JButton updateButton;
    private final JButton deleteButton;
    
    private final JPanel inputPanel;
    private final JPanel buttonPanel;

    private final JLabel propertyIdLabel;
//    private final JLabel numberOfBedroomLabel;
//    private final JLabel sizeLabel;
//    private final JLabel priceLabel;
    private final JLabel streetNumberLabel;
    private final JLabel streetAddressLabel;
    private final JLabel suburbLabel;
    private final JLabel postcodeLabel;
    private final JLabel stateLabel;
//    private final JLabel contactPersonLabel;
//    private final JLabel tagLabel;

    private final JTextField propertyIdTextField;
//    private final JTextField numberOfBedroomTextField;
//    private final JTextField sizeTextField;
//    private final JTextField priceTextField;
    private final JTextField streetNumberTextField;
    private final JTextField streetAddressTextField;
    private final JTextField suburbTextField;
    private final JTextField postcodeTextField;
    private final JTextField stateTextField;
//    private final JTextField tagTextField;
    
    private final JTable propertyTable;
    
    private final JComboBox contactPersonComboBox;

    BankUser user;

    public SimpleBankingGUIImpl(ActionListener actionListener, ListSelectionListener listSelectionListener) {
        super("FIT5042 Simple Banking App");

        // create buttons
        this.closeButton = new JButton("Close");
        this.viewButton = new JButton("View");
        this.searchButton = new JButton("Search");
        this.addButton = new JButton("Add");
        this.updateButton = new JButton("Update");
        this.deleteButton = new JButton("Delete");

        // create container
        Container container = this.getContentPane();

        // create labels
        this.propertyIdLabel = new JLabel("ID:");
        this.streetNumberLabel = new JLabel("First Name:");
        this.streetAddressLabel = new JLabel("Last Name:");
        this.suburbLabel = new JLabel("Password:");
        this.postcodeLabel = new JLabel("Email:");
        this.stateLabel = new JLabel("Type:");       
//        this.numberOfBedroomLabel = new JLabel("Account Balance:");

        // create text fields
        this.propertyIdTextField = new JTextField();
//        this.numberOfBedroomTextField = new JTextField();
//        this.sizeTextField = new JTextField();
//        this.priceTextField = new JTextField();
        this.streetNumberTextField = new JTextField();
        this.streetAddressTextField = new JTextField();
        this.suburbTextField = new JTextField();
        this.postcodeTextField = new JTextField();
        this.stateTextField = new JTextField();
//        this.tagTextField = new JTextField();
        
        // create table
        this.propertyTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.propertyTable.getSelectionModel().addListSelectionListener(listSelectionListener);       
        this.propertyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        TableColumnModel propertyTableColumnModel = this.propertyTable.getColumnModel();
        propertyTableColumnModel.getColumn(0).setPreferredWidth(50);
        propertyTableColumnModel.getColumn(1).setPreferredWidth(300);
        propertyTableColumnModel.getColumn(2).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(3).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(4).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(5).setPreferredWidth(100);
        
        //create combobox
        this.contactPersonComboBox = new JComboBox();
        
        // create panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();

        // set layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(11,2));
        this.buttonPanel.setLayout(new GridLayout(1,4));

        // add action listeners
        this.closeButton.addActionListener(actionListener);
        this.addButton.addActionListener(actionListener);
        this.viewButton.addActionListener(actionListener);
        this.searchButton.addActionListener(actionListener);
        this.updateButton.addActionListener(actionListener);
        this.deleteButton.addActionListener(actionListener);

        // add components
        this.inputPanel.add(propertyIdLabel);
        this.inputPanel.add(propertyIdTextField);

        this.inputPanel.add(this.streetNumberLabel);
        this.inputPanel.add(this.streetNumberTextField);
        
        this.inputPanel.add(this.streetAddressLabel);
        this.inputPanel.add(this.streetAddressTextField);
        
        this.inputPanel.add(this.suburbLabel);
        this.inputPanel.add(this.suburbTextField);
        
        this.inputPanel.add(this.postcodeLabel);
        this.inputPanel.add(this.postcodeTextField);
        
        this.inputPanel.add(this.stateLabel);
        this.inputPanel.add(this.stateTextField);
        
//        this.inputPanel.add(this.streetNumberLabel);
//        this.inputPanel.add(this.streetNumberTextField);
        
//        this.inputPanel.add(this.streetAddressLabel);
//        this.inputPanel.add(this.streetAddressTextField);
        
//        this.inputPanel.add(this.suburbLabel);
//        this.inputPanel.add(this.suburbTextField);
        
//        this.inputPanel.add(this.postcodeLabel);
//        this.inputPanel.add(this.postcodeTextField);
        
//        this.inputPanel.add(this.stateLabel);
//        this.inputPanel.add(this.stateTextField);       

//        this.inputPanel.add(numberOfBedroomLabel);
//        this.inputPanel.add(numberOfBedroomTextField);
        
//        this.inputPanel.add(sizeLabel);
//        this.inputPanel.add(sizeTextField);
        
//        this.inputPanel.add(priceLabel);
//        this.inputPanel.add(priceTextField);
        
//        this.inputPanel.add(this.contactPersonLabel);
//        this.inputPanel.add(this.contactPersonComboBox);
        
//        this.inputPanel.add(this.tagLabel);
//        this.inputPanel.add(this.tagTextField);

        // add buttons to panel
        this.buttonPanel.add(this.addButton);
        this.buttonPanel.add(this.updateButton);
        this.buttonPanel.add(this.deleteButton);
        this.buttonPanel.add(this.searchButton);
        this.buttonPanel.add(this.viewButton);
        this.buttonPanel.add(this.closeButton);
        
        // add panels to content pane
        container.add(inputPanel,BorderLayout.NORTH);
        container.add(new JScrollPane(this.propertyTable), BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);
       
        // change the default behaviour of the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(750, 570);       
        this.setVisible(true);
    }
    
//    @Override
//    public void displayContactPeople(List<ContactPerson> contactPeople) {
//        this.contactPersonComboBox.removeAllItems();
//        
//        this.contactPersonComboBox.addItem("");
//        
//        for (ContactPerson contactPerson : contactPeople) {
//            this.contactPersonComboBox.addItem(contactPerson);
//        }
//    }
    
//    @Override
//    public ContactPerson getSelectedContactPerson() {
//        if (this.contactPersonComboBox.getItemCount() > 0 && this.contactPersonComboBox.getSelectedIndex() > 0) {
//            return (ContactPerson)this.contactPersonComboBox.getSelectedItem();
//        } else {
//            return null;
//        }
//    }
    
    @Override
    public int getUserId() {
        String id = this.propertyIdTextField.getText();
        return id == null || id.isEmpty() ? 0 : Integer.parseInt(id);
    }
    
//    @Override
//    public double getBudget() {
//        String price = this.priceTextField.getText();
//        return price == null || price.isEmpty() ? 0 : Double.parseDouble(price);
//    }
    @Override
    public boolean isUserSelected() {
        return (this.propertyTable.getSelectedRow() >= 0);
    }
    
    @Override
    public int getSelectedUserId() {
        int selectedRowIndex = this.propertyTable.getSelectedRow();
        
        String propertyId = this.propertyTable.getValueAt(selectedRowIndex, 0).toString();
        return Integer.parseInt(propertyId); 
    }

    @Override
    public BankUser getUserDetails()
    {
        BankUser userLocal = new BankUser();	
        userLocal.setUserId(Integer.parseInt(propertyIdTextField.getText()));
        userLocal.setFirstName(this.streetNumberTextField.getText());
        userLocal.setLastName(this.streetAddressTextField.getText());
        userLocal.setPassword(this.suburbTextField.getText());
        userLocal.setEmail(this.postcodeTextField.getText());
        userLocal.setType(this.stateTextField.getText());
//        BankAccount account = new BankAccount(Double.parseDouble(numberOfBedroomTextField.getText()));
        userLocal.setIsValid("true");
//        userLocal.setAccount(account);
        return userLocal;
    }
    
    @Override
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void displayUserDetails(BankUser bankUser) {  
        this.clearPropertyTable();
        this.clearInput();
        
        ((DefaultTableModel)this.propertyTable.getModel()).addRow(new Object[]{bankUser.getUserId(), 
                                                                               bankUser.getFirstName(), 
                                                                               bankUser.getLastName(),
                                                                               bankUser.getPassword(), 
                                                                               bankUser.getEmail(), 
                                                                               bankUser.getType()});
        
    }
    
    @Override
    public void displayUserDetails(List<BankUser> users) {    
        this.clearPropertyTable();
        this.clearInput();
        
        for (BankUser bankUser : users) {
            ((DefaultTableModel)this.propertyTable.getModel()).addRow(new Object[]{bankUser.getUserId(), 
                                                                               bankUser.getFirstName(), 
                                                                               bankUser.getLastName(),
                                                                               bankUser.getPassword(), 
                                                                               bankUser.getEmail(), 
                                                                               bankUser.getType()});
        }
    }
    
    @Override
    public void displayUserDetails(Set<BankUser> users) {    
        this.clearPropertyTable();
        this.clearInput();
        
        for (BankUser bankUser : users) {
            ((DefaultTableModel)this.propertyTable.getModel()).addRow(new Object[]{bankUser.getUserId(), 
                                                                               bankUser.getFirstName(), 
                                                                               bankUser.getLastName(),
                                                                               bankUser.getPassword(), 
                                                                               bankUser.getEmail(), 
                                                                               bankUser.getType()});
        }
    }

    @Override
    public void displaySelectedUserDetails(BankUser bankUser) {
        this.propertyIdTextField.setText(String.valueOf(bankUser.getUserId()));           
        this.streetNumberTextField.setText(bankUser.getFirstName());
        this.streetAddressTextField.setText(bankUser.getLastName());
        this.suburbTextField.setText(bankUser.getPassword());
        this.postcodeTextField.setText(bankUser.getEmail());
        this.stateTextField.setText(bankUser.getType());               
    }
    
//    private Set<String> getPropertyTags() {
//        StringTokenizer tokenizer = new StringTokenizer(this.tagTextField.getText(), TAG_SEPARATOR);
//        Set<String> tags = new HashSet<>();
//        String tag = null;
//                
//        while (tokenizer.hasMoreTokens()) {
//            tag = tokenizer.nextToken();
//            tags.add(tag.trim());
//        }
//        
//        return tags;
//    }
    
//    private String getTagsString(Set<String> tags) {
//        if (tags != null && !tags.isEmpty()) {
//           StringBuilder tagsBuilder = new StringBuilder();
//           int index = 0;
//           for (String tag : tags) {
//               if (index != 0) {
//                   tagsBuilder.append(TAG_SEPARATOR).append(" ");
//               }
//               
//               tagsBuilder.append(tag.trim());
//               index ++;
//           }
//           
//           return tagsBuilder.toString();
//        }
//        
//        return "";
//    }
    
    @Override
    public void clearPropertyTable() {     
        int numberOfRow = this.propertyTable.getModel().getRowCount();
        
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.propertyTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }            
        }
    }
    
    @Override
    public void clearInput() {
        this.clearTextFields();
        this.clearComboBoxes();
    }

    @Override
    public void clearTextFields() {
        this.propertyIdTextField.setText("");
        this.streetNumberTextField.setText("");
        this.streetAddressTextField.setText("");
        this.suburbTextField.setText("");
        this.postcodeTextField.setText("");
        this.stateTextField.setText(""); 
//        this.numberOfBedroomTextField.setText("");
//        this.sizeTextField.setText("");
//        this.priceTextField.setText("");
//        this.tagTextField.setText("");
        
          
    }
    
    @Override
    public void clearComboBoxes() {
        if (this.contactPersonComboBox.getItemCount() > 0) {
            this.contactPersonComboBox.setSelectedIndex(0);
        } 
    }

    @Override
    public JButton getUpdateButton() {
        return updateButton;
    }

    @Override
    public JButton getDeleteButton() {
        return deleteButton;
    }

    @Override
    public JTable getUserTable() {
        return propertyTable;
    }

    @Override
    public JButton getViewButton() {
        return viewButton;
    }

    @Override
    public JButton getAddButton() {
        return addButton;
    }
    
    @Override
    public JButton getSearchButton() {
        return searchButton;
    }

    @Override
    public JButton getCloseButton() {
        return closeButton;
    }
}

