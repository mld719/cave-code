
/**
 * A state is a combination of the three working memory parts - tmp, frame, and WMS. However, I only made it at 
 * the last minute so I'm probably not using it everywhere that I could be, I'm only using it when I absolutely
 * need it. 
 */
public class State
{
    Frame frame;
    Chunk tmp;
    int WMS;
    
    public State()
    {
        frame = null;
        tmp = null;
        WMS = 0;
    }
    
    public State(Frame f, Chunk t, int w)
    {
        frame = f;
        tmp = t;
        WMS = w;
    }
    
    public Frame getFrame()
    {
        if(frame == null)
        {
            return null;
        }
        
        return frame;
    }
    
    public Chunk getTMP()
    {
        if(tmp == null)
        {
            return null;
        }
        
        return tmp;
    }
    
    public int getWMS()
    {
        return WMS;
    }
    
    public void setFrame(Frame f)
    {
        frame = f;
    }
    
    public void setTMP(Chunk t)
    {
        tmp = t;
    }
    
    public void setWMS(int w)
    {
        WMS = w;
    }
}
