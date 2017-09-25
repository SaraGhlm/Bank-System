/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javabankproject.Data;

import java.util.Random;

/**
 *
 * @author Novin Pendar
 */
public class City 
{
    
    private int code ;
    private String name ;
    
    /**
     * 
     * @param code
     * @param name 
     */
    public City (int code, String name)
    {
        this.code = code ;
        this.name = name ;
    }
    
    public int getCode ()
    {
        return code ;
    }
    
    public String getName ()
    {
        return name ;
    }
    
    
}
