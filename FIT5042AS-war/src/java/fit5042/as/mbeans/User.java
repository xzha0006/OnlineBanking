/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.as.mbeans;

import fit5042.as.repository.entities.BankUser;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author xuanzhang
 */
@ManagedBean(name = "user")
@RequestScoped
public class User extends BankUser implements Serializable{
    
}
