public class Precondition
{
    boolean hasFrame;
    boolean hasWMS;
    String  inputType;
    Frame actual;
    int WMS;

    public Precondition(String p)
    {
        setUp(p);
    }
   
    public void setUp(String p)
    {
        //input types: digit(i), teen, decade, hundred, thousand(k), million, billion
        
        //string looks like these:
        // input = i, frame = n 'n', wms = n 0
        // input = i, frame = y 'k', wms = y 8 
       
        String input = p.substring(8,9);
        
        //System.out.println(input);
        
        String f = p.substring(19);
        
        if(f.equals("n"))
        {
            hasFrame = false;
        }
        else
        {
            hasFrame = true;
            
            String size = p.substring(22, 23) ;        
            actual = new Frame(size);
            
            //System.out.println(size);
        }
        
        //System.out.println(hasFrame);
        
        String w = p.substring(32,33);
        
        //System.out.println(w);
        
        if(w.equals("n"))
        {
            hasWMS = false;
        }
        else
        {
            hasWMS = true;
            
            String wms = p.substring(34);
            
            WMS = Integer.parseInt(wms);
            
            //System.out.println(WMS);
        }
        
        //System.out.println(hasWMS);
    }
}
