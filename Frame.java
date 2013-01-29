
/**
 * Write a description of class Frame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Frame
{
    //instance variables
    int[] frame;
    String size;
    int length;

    public Frame()
    {
        frame = null;
    }
    
    public Frame(String s)
    {
        size = s;
        
        if(s.equals("hundred"))
        {
            frame = new int[2];
            size = s;
            length = 2;
        }
        else if(s.equals("thousand"))
        {
            frame = new int[3];
            size = s;
            length = 3;
        }
        else if(s.equals("million"))
        {
            frame = new int[6];
            size = s;
            length = 6;
        }
        else if(s.equals("billion"))
        {
            frame = new int[9];
            size = s;
            length = 9;
        }
    }
    
    public Frame(String s, int n)
    {
        if(s.equals("hundred"))
        {
            frame = new int[3];
            frame[0] = n;
            size = s;
            length = 3;
        }
        else if(s.equals("thousand"))
        {
            frame = new int[4];
            frame[0] = n;
            size = s;
            length = 4;
        }
        else if(s.equals("million"))
        {
            frame = new int[7];
            frame[0] = n;
            size = s;
            length = 7;
        }
        else if(s.equals("billion"))
        {
            frame = new int[10];
            frame[0] = n;
            size = s;
            length = 10;
        }
        else if(s.equals("decade"))
        {
            frame = new int[2];
            frame[0] = n;
            size = s;
            length = 2;
        }
    }
    
    public void setNumber(int position, int number)
    {
        //should position go from left to right or right to left like a number?
        
        if(number > 9)
        {
            System.out.println("Cannot add a number more than one digit to each frame place");
        }
        
        frame[position] = number;
    }
    
    public int getNumber(int position)
    {
        return frame[position];
    }
    
    //will work as long as the algorithm works to fill in all zeros at one time, not just place by place
    //not needed! array is initialized to all zeros.
    public void setZero()
    {
        for(int i = 0; i < frame.length; i++)
        {
            if(frame[i] == 0)//check
            {
                frame[i] = 0;
            }
        }
    }
    
    //will stick a frame to the right of the exisiting frame
    public void merge(Frame newFrame)
    {
        //depends on what the current frame is
    }
    
    //will put a frame on top of the exisitng frame (for example, hundred on million frame)
    public void overlap(Frame littleFrame)
    {
        if((littleFrame.size).equals("hundred"))
        {
            int num = littleFrame.getNumber(0);
            setNumber(1, num);
        }
        else //if ((littleFrame.size).equals("thousand"))
        {
            int num = littleFrame.getNumber(0);
            setNumber((frame.length - 4), num);
        }
        
    }
    
    public void print()
    {
        String s = new String();
        
        for(int i = 0; i < frame.length; i++)
        {
            String num = Integer.toString(frame[i]);
            s = s + " " + num;
        }
        
        System.out.println(s);
    }
}
