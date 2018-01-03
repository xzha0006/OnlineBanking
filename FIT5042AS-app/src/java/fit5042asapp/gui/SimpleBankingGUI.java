package fit5042asapp.gui;

import fit5042.as.repository.entities.BankUser;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 * It defines the structure that all the GUI of the RealEstateAgency 
 * 
 * @author Eddie
 */
public interface SimpleBankingGUI {
    
    /**
     * Clear all the inputs in the GUI
     */
    void clearInput();

    /**
     * Clear all the text fields in the GUI
     */
    void clearTextFields();
    
    /**
     * Clear all the combo boxes in the GUI
     */
    void clearComboBoxes();
    
    /**
     * Clear all records in the table
     */
    void clearPropertyTable();

    /**
     * Display a message in a dialog box
     *
     * @param message - the message to display
     */
    void displayMessageInDialog(String message);

    /**
     * Display the details of the properties
     *
     * @param user - the details of the property to display
     */
    void displayUserDetails(BankUser user);
    
    /**
     * Display the details of the selected properties
     *
     * @param property - the details of the selected property to display
     */
    void displaySelectedUserDetails(BankUser user);
    
    /**
     * Display the details of the properties
     *
     * @param properties - the details of the properties to display
     */
    void displayUserDetails(List<BankUser> users);
    
    /**
     * Display the details of the properties
     *
     * @param properties - the details of the properties to display
     */
    void displayUserDetails(Set<BankUser> users);
    
    /**
     * Display the details of the contact people
     *
     * @param contact people - the details of the contact people to display
     */
//    void displayContactPeople(List<ContactPerson> contactPeople);
    
    /**
     * Return the ID of the property selected in table
     *
     * @return the ID of the selected property
     */
    int getSelectedUserId() throws Exception;
    
    /**
     * Return the Add Property button
     *
     * @return the attribute addButton
     */
    JButton getAddButton();

    /**
     * Return the Close Window button
     *
     * @return the attribute closeButton
     */
    JButton getCloseButton();
    
    /**
     * Return the Search Property by ID button
     *
     * @return the attribute searchButton
     */
    JButton getSearchButton();

    /**
     * Return the Display All Properties button
     *
     * @return the attribute viewButton
     */
    JButton getViewButton(); 
    
    /**
     * Return the Update Property Details button
     *
     * @return the attribute updateButton
     */
    public JButton getUpdateButton();

    /**
     * Return the Delete Property Details button
     *
     * @return the attribute deleteButton
     */
    public JButton getDeleteButton();

    /**
     * Return the Property Details table
     *
     * @return the attribute propertyTable
     */
    public JTable getUserTable();

    /** 
     * Return the details of the properties to add to the repository
     *
     * @return a Property object that contains all the details
     */
    BankUser getUserDetails();

    /**
     * Return the id of the property to search the repository for
     *
     * @return the id of the property to search the repository for
     */
    int getUserId();
    
    /**
     * Return the maximum price of the property that the user wants to search the repository for
     *
     * @return the maximum price of the property to search the repository for
     */
//    double getBudget();
    
    /**
     * Return the contact person to search the repository for
     *
     * @return the contact person of a property
     */
//    ContactPerson getSelectedContactPerson();
    
    /**
     * Indicate whether any property is selected
     *
     * @return true if a property is selected, or false otherwise
     */
    boolean isUserSelected();
}
