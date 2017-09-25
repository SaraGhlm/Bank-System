/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javabankproject.Data;

/**
 *
 * @author Novin Pendar
 */
public class Branch 
{
    
    private int code ;
    private String name ;
    private City city ;
    
    /**
     * 
     * @param code
     * @param name
     * @param city 
     */
    public Branch (int code, String name, City city)
    {
        this.code = code ;
        this.name = name ;
        this.city = city ;
    }
    
    public int getCode ()
    {
        return code ;
    }
    
    public String getName ()
    {
        return name ;
    }
    
    public City getCity ()
    {
        return city ;
    }
    
}
