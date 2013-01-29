
/**
 * Write a description of class Model here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Model
{
    private static int WMS; 
    private static int position = 0;
    private static String number;


    public static void main(String[] args)
    {
        number = args[0];
        transcode();
    }
    
    public Model(String n)
    {
        number = n;
    }
    
    public static void transcode()
    {
        String[] array = number.split(" ");
        Frame frame = new Frame();
        //int j = 0;
        
        for(int i = 0; i < array.length; i++)
        {
            String word = array[i];
            //System.out.println(word);
            
            if(word.equals("hundred") || word.equals("thousand") || word.equals("million") || word.equals("billion"))
            {
                
                if(frame.size != null)
                {
                    //need to deal with overlap here.
                    
                    Frame frame2 = new Frame(word, WMS);
                    frame.overlap(frame2);
                    
                    if(word.equals("hundred"))
                    {
                        //j = j-2; //might need to be 3
                    }
                    else
                    {
                        //j = j-3; //might need to be 4
                    }
                    
                    //j--;
                }
                
                else if(WMS != 0)
                {
                    frame = new Frame(word, WMS);
                    WMS = 0;
                }
                else
                {
                    frame = new Frame(word);
                }
                
                //frame.print();
                position++;
            }
            else if(word.endsWith("ty") || word.equals("ten") || word.equals("eleven") || word.equals("twelve") || word.endsWith("teen")) //will be a number decade
            {
                
                if(word.equals("ten") || word.equals("eleven") || word.equals("twelve") || word.endsWith("teen"))
                {
                    WMS = 1;
                }
                else if(word.equals("twenty"))
                {
                    WMS = 2;
                }
                else if(word.equals("thirty"))
                {
                    WMS = 3;
                }
                else if(word.equals("forty"))
                {
                    WMS = 4;
                }
                else if(word.equals("fifty"))
                {
                    WMS = 5;
                }
                else if(word.equals("sixty"))
                {
                    WMS = 6;
                }
                else if(word.equals("seventy"))
                {
                    WMS = 7;
                }
                else if(word.equals("eighty"))
                {
                    WMS = 8;
                }
                else if(word.equals("ninety"))
                {
                    WMS = 9;
                }
                else
                {
                    System.out.println("wrong");
                }
                
                
                if(frame.size == null)
                {
                    frame = new Frame("decade", WMS);
                }
                else
                {
                    frame.setNumber(position, WMS);
                    position++;
                }
                
                WMS = 0;
                //frame.print();
                
            }
            else //will be a digit
            {
                
                if(word.equals("one"))
                {
                    WMS = 1;
                }
                else if(word.equals("two"))
                {
                    WMS = 2;
                }
                else if(word.equals("three"))
                {
                    WMS = 3;
                }
                else if(word.equals("four"))
                {
                    WMS = 4;
                }
                else if(word.equals("five"))
                {
                    WMS = 5;
                }
                else if(word.equals("six"))
                {
                    WMS = 6;
                }
                else if(word.equals("seven"))
                {
                    WMS = 7;
                }
                else if(word.equals("eight"))
                {
                    WMS = 8;
                }
                else if(word.equals("nine"))
                {
                    WMS = 9;
                }
                else
                {
                    System.out.println("wrong");
                }
                
            }
            
            //j++;
        }
        
        
        if(WMS != 0)
        {
            //System.out.println(j);
            frame.setNumber(frame.length - 1, WMS);
            //frame.print();
        }
        
        
        frame.print();
    }
}
