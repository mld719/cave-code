
public class Precondition
{
    boolean hasFrame;
    boolean hasTmp;
    boolean hasWMS;
    
    String original;
    String inputType;
    
    public Precondition(String p)
    {
        original = p;
        setUp(p);
    }
    
    public Precondition(String i, boolean frame, boolean WMS, boolean tmp)
    {
        hasFrame = frame;
        hasTmp = tmp;
        hasWMS = WMS;
        
        inputType = i;
    }
    
    public boolean hasFrame()
    {
        return hasFrame;
    }
    
    public boolean hasWMS()
    {
        return hasWMS;
    }
    
    public boolean hasTMP()
    {
        return hasTmp;
    }
    
    public String getInputType()
    {
        return inputType;
    }
    
    public void setUp(String p)
    {
        //input = b, frame = n, tmp = n, wms = n
        
        inputType = p.substring(8,9);
        
        String f = p.substring(19, 20);
        String t = p.substring(28, 29);
        String w = p.substring(37, 38);
        
        if(f.equals("n"))
        {
            hasFrame = false;
        }
        else
        {
            hasFrame = true;
        }
        
        if(t.equals("n"))
        {
            hasTmp = false;
        }
        else
        {
            hasTmp = true;
        }
        
        if(w.equals("n"))
        {
            hasWMS = false;
        }
        else
        {
            hasWMS = true;
        }
        
        /*
        System.out.println("input: " + inputType);
        System.out.println("frame: " + hasFrame);
        System.out.println("tmp: " + hasTmp);
        System.out.println("WMS: " + hasWMS);
        */
    }
    
    public String toString()
    {
        return original;
    }
}
