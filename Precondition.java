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
        //string looks like these:
        // input = digit, frame = n, wms = n 
        // input = digit, frame = y 'hundred', wms = y 8 
       
        int i = p.indexOf("=");
        int j = p.indexOf(",");
        
        String in = p.substring(i+2,j);
        in.trim();
        
        System.out.println(in);
        
        p = p.substring(j+1);
        
        i = p.indexOf("=");
        
        String f = p.substring(i+2, i+3);
        f.trim();
        
        System.out.println(f);
        
        if(f.equals("n"))
        {
            hasFrame = false;
        }
        else
        {
            hasFrame = true;
            
            i = p.indexOf("'");
            j = p.lastIndexOf("'");
            
            String s = p.substring(i+1,j).trim();
            actual = new Frame(s);
            
            System.out.println(s);
            //System.out.println(output.toString());
        }
        
        System.out.println(hasFrame);
        
        p = p.substring(j+1);
        
        i = p.indexOf("=");
        
        String w = p.substring(i+2, i+3);
        w.trim();
        
        System.out.println(w);
        
        if(w.equals("n"))
        {
            hasWMS = false;
        }
        else
        {
            hasWMS = true;
            
            String wms = p.substring(i+4).trim();
            
            WMS = Integer.parseInt(wms);
            
            System.out.println(WMS);
        }
        
        System.out.println(hasWMS);
    }
}
