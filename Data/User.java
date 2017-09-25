/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javabankproject.Data;

import java.util.Arrays;

/**
 *
 * @author Novin Pendar
 */
public class User {
    
    private long nationalCode ;
    private char [] password ;
    private String firstName ;
    private String lastName ;
    private Roles role ;
    private Branch branch ;
    private int accountLength = 1 ;
    private Account [] account = new Account[accountLength] ;
    
    /**
     * 
     * @param nationalCode
     * @param password
     * @param firstName
     * @param lastName
     * @param role
     * @param branch 
     * this constructor is for the staff.
     */
    public User ( long nationalCode , char [] password , String firstName 
            , String lastName , Roles role , Branch branch )
    {
        this.nationalCode = nationalCode ;
        this.password = password ;
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.role = role ;
        this.branch = branch ;
    }
    
    /**
     * 
     * @param nationalCode
     * @param password
     * @param firstName
     * @param lastName
     * @param role 
     * This constructor is for the manager and clients.
     */
    public User ( long nationalCode , char [] password , String firstName 
            , String lastName , Roles role )
    {
        this.nationalCode = nationalCode ;
        this.password = password ;
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.role = role ;
    }
    
    /**
     * 
     * @return national code
     */
    public long getNationalCode ()
    {
        return nationalCode ;
    }
    
    /**
     * 
     * @return password
     */
    public char [] getPassword ()
    {
        return password ;
    }
    
    /**
     * 
     * @return first name 
     */
    public String getFirstName ()
    {
        return firstName ;
    }
    
    /**
     * 
     * @return last name
     */
    public String getLastName ()
    {
        return lastName ;
    }
    
    /**
     * 
     * @return role of the user
     */
    public Roles getRole ()
    {
        return role ;
    } 
    
    /**
     * This method is for the staffs
     * @return branch
     */
    public Branch getBranch ()
    {
        return branch ;
    }
    
    public Account [] getAccount ()
    {
        return account ;
    }
    
    public void addAccount (Account act)
    {
        account [accountLength-1] = act ;
        
        Account account_temp [] = Arrays.copyOf(account, accountLength+1) ;
        account = account_temp ;
        
        accountLength ++ ;
    }
}