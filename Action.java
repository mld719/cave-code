public class Action
{
    boolean hasFrame;
    boolean hasWMS;
    Frame output;
    int WMS;
    
    //n(one) d (teen?) h k m b

    
    
    public Action(String a) //line from file
    {
        
        setUp(a);
    }
    
    
    public void setUp(String a)
    {
        //string looks like these:
        // frame = n 'n', wms = n 0 
        // frame = y 'k', wms = y 8

        String f = a.substring(8, 9);
        
        //System.out.println(f);
        
        
        if(f.equals("n"))
        {
            hasFrame = false;
        }
        else
        {
            hasFrame = true;
            
            String size = a.substring(11,12);
            output = new Frame(size);
            
            //System.out.println(size);
        }
        
        //System.out.println(hasFrame);
        
        
        String w = a.substring(21,22);
        
        //System.out.println(w);
        
        if(w.equals("n"))
        {
            hasWMS = false;
        }
        else
        {
            hasWMS = true;
            String wms = a.substring(23);
            
            WMS = Integer.parseInt(wms);
            
            //System.out.println(WMS);
        }
        
        //System.out.println(hasWMS);
        
    }
}
