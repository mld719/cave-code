public class Action
{
    boolean hasFrame;
    boolean hasWMS;
    Frame output;
    int WMS;


    public Action(String a) //line from file
    {
        //string looks like these:
        // frame = n, wms = n
        // frame = y 'thousand', wms = y 8
        
        setUp(a);
    }
    
    
    public void setUp(String a)
    {
        
        int i = a.indexOf("=");
        
        String f = a.substring(i+2, i+3);
        f.trim();
        
        System.out.println(f);
        
        if(f.equals("n"))
        {
            hasFrame = false;
        }
        else
        {
            hasFrame = true;
            
            i = a.indexOf("'");
            int j = a.lastIndexOf("'");
            
            String s = a.substring(i+1,j).trim();
            output = new Frame(s);
            
            System.out.println(s);
            //System.out.println(output.toString());
        }
        
        System.out.println(hasFrame);
        
        
        int k = a.lastIndexOf("=");
        String w = a.substring(k+2, k+3);
        
        System.out.println(w);
        w.trim();
        
        if(w.equals("n"))
        {
            hasWMS = false;
        }
        else
        {
            hasWMS = true;
            String wms = a.substring(k+4).trim();
            
            WMS = Integer.parseInt(wms);
            
            System.out.println(WMS);
        }
        
        System.out.println(hasWMS);
        
    }
}
