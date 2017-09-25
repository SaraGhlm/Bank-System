/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javabankproject.Data.Manager;

import java.util.Arrays;
import java.util.Random;
import javabankproject.Data.User;
import javabankproject.Data.Account;
import javabankproject.Data.AccountType;
import javabankproject.Data.Branch;
import javabankproject.Data.TransactionType;
import javabankproject.Data.Card;
import javabankproject.Data.Roles;
import javax.swing.JOptionPane;

/**
 *
 * @author Novin Pendar
 */
public class UserManager 
{
    public static final UserManager manager = new UserManager () ;
    
    private int userLength = 1 ;
    private User user [] = new User [userLength] ;
    public User userTemp ;
    
    private int accountLength = 1 ;
    private Account account [] = new Account [accountLength] ;
    
    private int cardLength = 1 ;
    private Card card [] = new Card[cardLength] ;
    
    private UserManager () {
    }
    
    public void setAdminData(User usr)
    {
        user[0] = usr ;
        
        User userTemp [] = new User [userLength+1] ;

        for ( int i = 0 ; i < userLength ; i ++ )
            userTemp [i] = user [i] ;
        user = userTemp ;
        userLength ++ ;
    }
    
    public boolean addUser (User usr)
    {
        
        boolean exist = true ;
        
        if (usr.getRole() == Roles.Admin)
        {
            if (usr.getNationalCode() == user[0].getNationalCode())
                exist = false ;
        }
        
        else
        {
            for (int i = 0; i < userLength-1; i++)
            {
                if (usr.getNationalCode() == user[i].getNationalCode())
                    exist = false ;
            }
        }

        if (exist)
        {
            user[userLength-1] = usr ;

            User userTemp [] = Arrays.copyOf(user, userLength+1) ;

            user = userTemp ;
            userLength++ ;
        }
        
        return exist ;
    }
    
    public User findUser (long nationalCode, char [] password)
    {
        User usr = null ;
        
        for ( int i = 0 ; i < userLength-1 ; i ++ )
        {
            if ( nationalCode == user[i].getNationalCode())
            {
                if (Arrays.equals(user[i].getPassword(), password))
                    usr = user[i] ;
            }
        }
        
        return usr ;
    }
    
    public User findUser (long nationalCode)
    {
        User usr = null ;
        
        for ( int i = 0 ; i < userLength-1 ; i ++ )
        {
            if ( nationalCode == user[i].getNationalCode())
            {
                usr = user[i] ;
            }
        }
        
        return usr ;
    }

    public int addAccount (AccountType type, Branch branch, User owner)
    {
        Random rand = new Random () ;
        int id = 1000 + rand.nextInt (9000) ;
        
        for ( int i = 0 ; i < accountLength-1 ; i ++ )
        {
            if ( account[i].getCode() == id )
            {
                id = 1000 + rand.nextInt (9000) ;
                i = 0 ;
            }
        }
        
        Account act = new Account(id, type, branch, owner) ;
        account [accountLength-1] = act ;
        owner.addAccount(act);
        
        Account account_temp [] = Arrays.copyOf(account, accountLength+1) ;
        account = account_temp ;
        
        accountLength ++ ;
        
        return id ;
    }
    
    public int withdrawDeposit (long accountNumber,long nationalCode, float money, TransactionType type)
    {
        Account ac = null ;
        int status = 1 ;
        
        for (int i = 0; i < accountLength-1; i++)
        {
            if (account[i].getOwner().getNationalCode() == nationalCode)
            {
                if (account[i].getCode() == accountNumber)
                {
                    ac = account[i] ;
                }
            }
        }
        
        if (ac != null)
        {
            if (type == TransactionType.withdraw)
            {
                if ( ac.getBalance() >= money )
                {
                    ac.setBalance ( ac.getBalance() - money ) ;
                    ac.addTransaction(type, money);
                }
                
                else 
                {
                    status = 2 ;
                }
            }
            
            else
            {
                ac.setBalance(money + ac.getBalance());
                ac.addTransaction(type, money);
            }
        }
        
        else
        {
            status = 3 ;
        }
        return status ;
    }
    
    public Account findAccount (long accountNumber)
    {
        Account act = null ;
        
        for (int i = 0; i < accountLength-1; i++)
        {
            if (account[i].getCode() == accountNumber)
                act = account[i] ;
        }
        
        return act ;
    }
    
    public Card addCard (long nationalCode,long accountNumber )
    {
        Account act = null ;
        Card crd = null ;
        for (int i = 0; i < accountLength-1; i++)
        {
            if (account[i].getCode() == accountNumber)
                if (account[i].getOwner().getNationalCode() == nationalCode)
                    act = account[i] ;
        }
        
        if ( act != null )
        {
            Random rand = new Random () ;
            long card_number = 100000000000L + (long)(rand.nextDouble() * (899999999999L));
            card_number = 6001000000000000L + card_number ;

            for ( int i = 0 ; i < cardLength-1 ; i ++ )
            {
                if ( card_number == card[i].getNumber() )
                {
                    card_number = 100000000000L + (long)(rand.nextDouble() * (899999999999L));
                    card_number = 6001000000000000L + card_number ;
                    i = 0 ;
                }
            }
            
            int password = 1000 + rand.nextInt (9000) ;
            
            crd = new Card( card_number , act , password ) ;
            
            card[cardLength-1] = crd ;
            Card cardTemp [] = Arrays.copyOf(card, cardLength+1) ;
            card = cardTemp ;

            cardLength ++ ;
        }
        
        return crd ;
    }
    
    public void transferMoneyByAccontNumber (long nationalCode, long originNumber ,long destinationNumber ,float money)
    {
        Account originAccount = null ;
        
        for (int i = 0; i < accountLength-1; i++)
        {
            if (originNumber == account[i].getCode())
                if (nationalCode == account[i].getOwner().getNationalCode())
                    originAccount = account[i] ;
        }
        
        if (originAccount != null)
        {
            Account destinationAccount = null ;
            
            for (int i = 0; i < accountLength-1; i++)
                if (destinationNumber == account[i].getCode())
                    destinationAccount = account[i] ;
            
            if (destinationAccount != null)
            {
                if (originAccount.getBalance() >= money)
                {
                    int answer = JOptionPane.showConfirmDialog(null, "The destination account belongs to "
                            + destinationAccount.getOwner().getFirstName() + " " 
                            + destinationAccount.getOwner().getLastName() + ".\n" 
                            + "Account opened in " + destinationAccount.getBranch()
                                    .getCity().getName() + " " + destinationAccount
                                            .getBranch().getName() + ".\n" + 
                            "Are you sure to transfer " + money + " Real.", "Confirmation", JOptionPane.YES_NO_OPTION) ;
                    
                    if (answer == JOptionPane.YES_OPTION)
                    {
                        originAccount.setBalance(originAccount.getBalance() - money);
                        destinationAccount.setBalance(money + destinationAccount.getBalance());
                        originAccount.addTransaction(TransactionType.withdraw, money);
                        destinationAccount.addTransaction(TransactionType.deposit, money);
                        
                        JOptionPane.showMessageDialog(null, "Transaction was successful.\n" 
                                + "Your money is " + originAccount.getBalance() + " Real.");
                    }
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "You do not have enough money.");
                }
            }
            
            else
            {
                JOptionPane.showMessageDialog(null, "The destination account number is invalid.");
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog(null, "The origin account number is invalid.");
        }
    }
    
    public void displayCardBalance (long cardNumber ,int password)
    {
        Card cr = null ;
        for ( int i = 0 ; i < cardLength-1 ; i  ++ )
        {
            if ( cardNumber == card[i].getNumber() )
                if ( password == card[i].getPassword() )
                    cr = card[i] ;
        }
        
        if ( cr != null )
        {
            JOptionPane.showMessageDialog(null, "Your balance is " + 
                    cr.getAccount().getBalance() + " Real.");
        }
        
        else
            JOptionPane.showMessageDialog(null, "Card number or password is invalid.");
    }
    
    public void transferMoneyByCard (long originNumber, int password, long destinationNumber, float money)
    {
        Card originCr = null ;
        
        for ( int i = 0 ; i < cardLength-1 ; i ++ )
        {
            if ( originNumber == card[i].getNumber() )
                if ( password == card[i].getPassword() )
                    originCr = card[i] ;
        }
        
        if (originCr != null)
        {
            if (originCr.getAccount().getBalance() >= money)
            {
                Card destinationCr = null ;
                
                for ( int i = 0 ; i < cardLength-1 ; i ++ )
                {
                    if ( destinationNumber == card[i].getNumber() )
                        destinationCr = card[i] ;
                }
                
                if ( destinationCr != null )
                {
                    int answer = JOptionPane.showConfirmDialog(null, "The destination card belongs to "
                            + destinationCr.getAccount().getOwner().getFirstName() + " " 
                            + destinationCr.getAccount().getOwner().getLastName() + ".\n" 
                            + "Account opened in " + destinationCr.getAccount().getBranch()
                                    .getCity().getName() + " " + destinationCr.getAccount()
                                            .getBranch().getName() + ".\n" + 
                            "Are you sure to transfer " + money + " Real.", "Confirmation", JOptionPane.YES_NO_OPTION) ;
                    
                    if (answer == JOptionPane.YES_OPTION)
                    {
                        originCr.getAccount().setBalance(originCr.getAccount().getBalance() - money);
                        originCr.getAccount().addTransaction(TransactionType.withdraw, money);
                        
                        destinationCr.getAccount().setBalance(money + destinationCr.getAccount().getBalance());
                        destinationCr.getAccount().addTransaction(TransactionType.deposit, money);
                    }
                }
            }
            
            else
                JOptionPane.showMessageDialog(null, "You do not have enough money.");
        }
        
        else 
            JOptionPane.showMessageDialog(null, "Your origin card number or password is invalid.");
    }
}
