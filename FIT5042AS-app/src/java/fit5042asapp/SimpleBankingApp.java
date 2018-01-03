package fit5042asapp;

import fit5042.as.repository.UserRepository;
import fit5042.as.repository.entities.BankAccount;
import fit5042.as.repository.entities.BankUser;
import fit5042asapp.gui.SimpleBankingGUI;
import fit5042asapp.gui.SimpleBankingGUIImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ejb.EJB;

/**
 *
 * @author Eddie
 * @author Jian
 */
public class SimpleBankingApp implements ActionListener, ListSelectionListener {
    
    @EJB
    private static UserRepository userRepository;

    private String name;
    private SimpleBankingGUI gui;

    public SimpleBankingApp(String name) throws Exception {
        this.name = name;
    }

    public void initView() {
        this.gui = new SimpleBankingGUIImpl(this, this);
        this.displayAllUsers();
//        this.displayAllContactPeople();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == gui.getViewButton()) {
            this.displayAllUsers();
        } else if (event.getSource() == gui.getAddButton()) {
            this.addUser();
            this.displayAllUsers();
        } else if (event.getSource() == gui.getSearchButton()) {
            this.searchUser();
        } else if (event.getSource() == gui.getUpdateButton()) {
            this.updateUser();
        } else if (event.getSource() == gui.getDeleteButton()) {
            this.deleteUser();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if ((event.getSource() == this.gui.getUserTable().getSelectionModel())
            && (! event.getValueIsAdjusting()))
        {
            try
            {
                if (this.gui.isUserSelected()) {
                    int propertyId = this.gui.getSelectedUserId();
                
                    BankUser bankUser = this.userRepository.searchUserById(propertyId);
                    this.gui.displaySelectedUserDetails(bankUser);
                }               
            }
            catch (Exception e)
            {
                gui.displayMessageInDialog(e.getMessage());
            }
        }
    }
    
    public void updateUser() {
        try {
            BankUser bankUser = this.gui.getUserDetails();
            userRepository.editBankUser(bankUser);
            this.displayAllUsers();
            this.gui.clearInput();
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to update user: " + ex.getMessage());
        }
    }

    public void deleteUser() {
        try {
            int userId = this.gui.getUserId();
            userRepository.removeBankUser(userId);
            this.displayAllUsers();
            //this.displayAllContactPeople();
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to update user: " + ex.getMessage());
        }  finally {
            this.gui.clearInput();
        }
    }
    
    public void searchUser() {
        
        int id = this.gui.getUserId();
        
        if (id > 0) {
            this.searchUserById(id);
        } else {
//            double budget = this.gui.getBudget();
            
//            System.out.println("budget" + budget);
//            
//            if (budget > 0) {
//                this.searchPropertyByBudget(budget);
//            } else {               
//                ContactPerson contactPerson = this.gui.getSelectedContactPerson();
//                this.searchPropertyByContactPerson(contactPerson);
//            }
        }
    }
    
//    public void searchPropertyByContactPerson(ContactPerson contactPerson) {
//        
//        try {
//            if (contactPerson != null) {
//                Set<Property> properties = propertyRepository.searchPropertyByContactPerson(contactPerson);
//            
//                if (properties != null && !properties.isEmpty()) {
//                    this.gui.displayPropertyDetails(properties);
//                } else {
//                    this.gui.displayMessageInDialog("No matched properties found");
//                    this.gui.clearPropertyTable();
//                }
//            } else {
//                this.gui.displayMessageInDialog("Details of the contact person not found");
//                this.gui.clearPropertyTable();
//            }      
//        } catch (Exception ex) {
//            this.gui.displayMessageInDialog("Failed to search property by contact person: " + ex.getMessage());
//            this.gui.clearPropertyTable();
//        } finally {
//            this.gui.clearInput();
//        }
//    }
    
//    public void searchPropertyByBudget(double budget) {
//        
//        try {
//            
//            System.out.println(budget);
//            
//            List<Property> properties = propertyRepository.searchPropertyByBudget(budget);
//            
//            if (properties != null && !properties.isEmpty()) {
//                this.gui.displayPropertyDetails(properties);
//            } else {
//                this.gui.displayMessageInDialog("No matched properties found");
//                this.gui.clearPropertyTable();
//            }  
//        } catch (Exception ex) {
//            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
//            this.gui.clearPropertyTable();
//        } finally {
//            this.gui.clearInput();
//        }
//    }

    public void searchUserById(int id) {
        
        try {
            
            BankUser bankUser = userRepository.searchUserById(id);
            
            if (bankUser != null) {
                this.gui.displayUserDetails(bankUser);
            } else {
                this.gui.displayMessageInDialog("No matched users found");
                this.gui.clearPropertyTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search user by ID: " + ex.getMessage());
            this.gui.clearPropertyTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
//    private void displayAllContactPeople() {
//        try {
//            List<ContactPerson> contactPeople = propertyRepository.getAllContactPeople();
//            
//            if (contactPeople != null) {
//                this.gui.displayContactPeople(contactPeople);
//            }
//            
//        } catch (Exception ex) {
//            this.gui.displayMessageInDialog("Failed to retrieve contact people: " + ex.getMessage());
//        }
//    }

    private void displayAllUsers() {
        try {
            List<BankUser> bankUsers = userRepository.getAllUsers();
            
            if (bankUsers != null) {
                this.gui.displayUserDetails(bankUsers);
            }
            
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to retrieve users: " + ex.getMessage());
        }
    }

    private void addUser() {
        //Because id is auto generated, so we must not enter the id while creating a new user.
        BankUser bankUser = gui.getUserDetails();
        BankUser newUser = BankUser.copyWithoutId(bankUser);
        BankAccount account = new BankAccount(1000);
        newUser.setAccount(account);
        
        try {
            userRepository.addBankUser(newUser);
            this.displayAllUsers();
            this.gui.clearInput();
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to add user: " + ex.getMessage());
        } finally {
            this.gui.clearInput();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            final SimpleBankingApp agency = new SimpleBankingApp("Monash Simple Banking App");
            //JDK 1.7
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    agency.initView();
//                }
//            });
            agency.initView();
            
//            //JDK 1.8
//            SwingUtilities.invokeLater(()-> {
//                agency.initView();
//            });
        } catch (Exception ex) {
            System.out.println("Failed to run application: " + ex.getMessage());
        }
    }

}
