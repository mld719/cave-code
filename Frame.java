

import java.util.*;

/**
 * ADAPT Model Code - Frame Class
 * 
 * @author Megan DeLaunay
 * @version 1/28/13
 */
 
public class Frame
{
    //instance variables
    String size;
    int length;
    ArrayList<Chunk> array; 
    
    public Frame()
    {
        array = null;
    }
    
    public Frame(Frame f)
    {
        size = f.getSize();
        length = f.getLength();
        array = f.getFull();
        
    }
    
    public Frame(String s)
    {
        size = s;
        Chunk c = new Chunk();
        
        if(size.equals("b"))
        {
            array = new ArrayList<Chunk>(4);           
            
            for(int i = 0; i < 4; i++)
            {
                array.add(c);
            }
            
            length = 4;
        }
        else if(size.equals("m"))
        {
            array = new ArrayList<Chunk>(3);
            
            for(int i = 0; i < 3; i++)
            {
                array.add(c);
            }
            length = 3;
        }
        else if(size.equals("k"))
        {
            array = new ArrayList<Chunk>(2);
            
            for(int i = 0; i < 2; i++)
            {
                array.add(c);
            }
            
            length = 2;
        }
        else
        {
            array = new ArrayList<Chunk>(1);
            
            for(int i = 0; i < 1; i++)
            {
                array.add(c);
            }
            
            length = 1;
        }
    }
    
    public Frame(String s, int num)
    {
        size = s;
        Chunk ch = new Chunk();
        
        if(size.equals("b"))
        {
            array = new ArrayList<Chunk>(4);
            length = 4;
            
            for(int i = 0; i < 4; i++)
            {
                array.add(ch);
            }
            
            //WHY IS IT ADDING IT TO EVERY CHUNK
            Chunk t = new Chunk();
            t.insert(num, 2);
            
            array.set(0, t);
            
            //array.get(0).insert(num, 2);
            
            //Chunk c = array.get(0);
            //c.insert(num, 2);
        }
        else if(size.equals("m"))
        {
            array = new ArrayList<Chunk>(3);
            length = 3;
            
            for(int i = 0; i < 3; i++)
            {
                array.add(ch);
            }
            
            Chunk t = new Chunk();
            t.insert(num, 2);
            
            array.set(0, t);
        }
        else if(size.equals("k"))
        {
            array = new ArrayList<Chunk>(2);
            length = 2;
            
            for(int i = 0; i < 2; i++)
            {
                array.add(ch);
            }
            
            Chunk t = new Chunk();
            t.insert(num, 2);
            
            array.set(0, t);
        }
        else if(size.equals("h"))
        {
            array = new ArrayList<Chunk>(1);
            length = 1;
            
            for(int i = 0; i < 1; i++)
            {
                array.add(ch);
            }
            
            Chunk t = new Chunk();
            t.insert(num, 0);
            
            array.set(0, t);
        }  
        else if(size.equals("d"))
        {
            array = new ArrayList<Chunk>(1);
            length = 1;
            
            for(int i = 0; i < 1; i++)
            {
                array.add(ch);
            }
            
            Chunk t = new Chunk();
            t.insert(num, 1);
            
            array.set(0, t);
        }
        else 
        {
            array = new ArrayList<Chunk>(1);
            length = 1;
            
            for(int i = 0; i < 1; i++)
            {
                array.add(ch);
            }
            
            Chunk t = new Chunk();
            t.insert(num, 2);
            
            array.set(0, t);
        }   
    }
    
    public Frame(String s, Chunk c)
    {
        size = s;
        Chunk ch = new Chunk();
        
        if(size.equals("b"))
        {
            array = new ArrayList<Chunk>(4);
            length = 4;
            
            for(int i = 0; i < 4; i++)
            {
                array.add(ch);
            }
            
            
            array.set(0, c);
            
            //array.get(0).insert(num, 2);
            
            //Chunk c = array.get(0);
            //c.insert(num, 2);
        }
        else if(size.equals("m"))
        {
            array = new ArrayList<Chunk>(3);
            length = 3;
            
            for(int i = 0; i < 3; i++)
            {
                array.add(ch);
            }
            
            
            array.set(0, c);
        }
        else if(size.equals("k"))
        {
            array = new ArrayList<Chunk>(2);
            length = 2;
            
            for(int i = 0; i < 2; i++)
            {
                array.add(ch);
            }
            
            
            array.set(0, c);
        }
        else if(size.equals("h"))
        {
            array = new ArrayList<Chunk>(1);
            length = 1;
            
            for(int i = 0; i < 1; i++)
            {
                array.add(ch);
            }

            
            array.set(0, c);
        }  
        else if(size.equals("d"))
        {
            array = new ArrayList<Chunk>(1);
            length = 1;
            
            for(int i = 0; i < 1; i++)
            {
                array.add(ch);
            }
            
            
            array.set(0, c);
        }
        else 
        {
            array = new ArrayList<Chunk>(1);
            length = 1;
            
            for(int i = 0; i < 1; i++)
            {
                array.add(ch);
            }
            
            
            array.set(0, c);
        }   
    }
    
    public Chunk getChunk(int pos)
    {
        return array.get(pos);
    }
    
    public void setChunk(int pos, Chunk c)
    {
        array.set(pos, c);
    }
    
    public String getSize()
    {
        return size;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public ArrayList<Chunk> getFull()
    {
        return array;
    }
    
    public Frame overlap(Frame smaller)
    {
        //this method takes two frames and inserts the smaller into the bigger
        //ex: five billion six million creates a billion and a million frame; this inserts
        //the million frame into the correct spot in the billion frame
        
        Frame bigger = this; //ease of words
        
        if( (bigger.getSize().equals("b") && smaller.getSize().equals("m")) ||
                (bigger.getSize().equals("m") && smaller.getSize().equals("k")) ||
                (bigger.getSize().equals("k") && smaller.getSize().equals("h"))
                )
        {
            Chunk c = smaller.getChunk(0);
            bigger.setChunk(1, c);
        }
        else if( (bigger.getSize().equals("b") && smaller.getSize().equals("k")) ||
                    (bigger.getSize().equals("m") && smaller.getSize().equals("h"))
                    )
        {
            Chunk c = smaller.getChunk(0);
            bigger.setChunk(2, c);
        }
        else if( bigger.getSize().equals("b") && smaller.getSize().equals("h"))
        {
            Chunk c = smaller.getChunk(0);
            bigger.setChunk(3, c);
        }
        
        return bigger;
    }
    
    public void setNumber(int position, int num)
    {
        
        if(num > 9)
        {
            System.out.println("Cannot add a number more than one digit to each frame place");
        }
        
        //array[position] = num;
    }
    
    public String toString()
    {
        String s = new String();
        
        for(int i = 0; i < array.size(); i++)
        {
            s = s.concat(array.get(i).toString() + "  ");
        }
        
        return s.trim();
    }
    
}


