
public class Action
{
    String[] steps;
    String original;

    State state;

    Frame frame;
    Chunk tmp;
    int WMS;
    Chunk c;
    Frame f;

    public Action(String a) //line from file
    {
        original = a;
        setUp(a);
    }

    public void setUp(String a)
    {
        steps = a.split(", ");

        /*
        System.out.println(" Start new action:");
        for(int i = 0; i < steps.length; i++)
        {
        System.out.println(steps[i]);
        }
         */
    }

    public String toString()
    {
        return original;
    }

    public State update(State s, String num)
    {
        //going to update the state depending on the current state. i'm still not quite sure how this is going to go down.
        //really going to depend on how i set up the new text files, but i'm still shaky on how to make those work. UGHGHGHGHGHGHGH. 

        state = s; 

        if(state.getFrame() != null)
        {
            frame = state.getFrame();
        }

        if(s.getTMP() != null)
        {
            tmp = state.getTMP();
        }

        WMS = state.getWMS();

        for(int i = 0; i < steps.length; i++)
        {
            //for each step in the action, do something, update something, and then remake a state

            //System.out.println(steps[i]);

            String step = steps[i];

            if(step.startsWith("frame") && !step.contains("overlap") && !step.contains("length"))
            {
                setFrame(step);
            }
            else if(step.equals("set WMS"))
            {
                setWMS(step, num);
            }
            else if(step.equals("fill zeros"))
            {
                fillZeros();
            }
            else if(step.startsWith("tmp") && !step.contains("tmp = tmp") && !step.contains("null"))
            {
                setTmp(step);
            }
            else if(step.startsWith("tmp = tmp"))
            {
                replaceTmp(step);
            }
            else if(step.equals("WMS = 0"))
            {
                WMS = 0;
            }
            else if(step.equals("tmp = null"))
            {
                tmp = null;
            }
            else if(step.contains("overlap"))
            {
                frame.overlap(f);
            }
            else if(step.startsWith("f = "))
            {
                setF(step);
            }
            else if(step.startsWith("c = frame"))
            {
                setC(step);   
            }
            else if(step.startsWith("c = c"))
            {
                replaceC(step);
            }
            else if(step.startsWith("frame = frame"))
            {
                replaceFrame(step);
            }
            else if(step.equals("STOP"))
            {
                break;
            }
            
            
            
        }

        
        state.setFrame(frame);
        state.setTMP(tmp);
        state.setWMS(WMS);

        return state;
    }

    public void setFrame(String step)
    {
        String size = step.substring(8,9);

        if(step.length() > 10)
        {
            String piece = step.substring(10);

            if(piece.equals("WMS"))
            {
                frame = new Frame(size, WMS);    
            }
            else if(piece.equals("tmp"))
            {
                frame = new Frame(size, tmp);
            }
            else if(piece.equals("c"))
            {
                frame = new Frame(size, c);
            }
        }
        else
        {
            frame = new Frame(size);
        }
    }

    public void setWMS(String step, String word)
    {
        //set wms based on the word

        if(word.equals("ten") || word.equals("one") || word.equals("eleven"))
        {
            WMS = 1;
        }
        else if(word.equals("twenty") || word.equals("two") || word.equals("twelve"))
        {
            WMS = 2;
        }
        else if(word.equals("thirty") || word.equals("three") || word.equals("thirteen"))
        {
            WMS = 3;
        }
        else if(word.equals("forty") || word.equals("four") || word.equals("fourteen"))
        {
            WMS = 4;
        }
        else if(word.equals("fifty") || word.equals("five") || word.equals("fifteen"))
        {
            WMS = 5;
        }
        else if(word.equals("sixty") || word.equals("six") || word.equals("sixteen"))
        {
            WMS = 6;
        }
        else if(word.equals("seventy") || word.equals("seven") || word.equals("seventeen"))
        {
            WMS = 7;
        }
        else if(word.equals("eighty") || word.equals("eight") || word.equals("eighteen"))
        {
            WMS = 8;
        }
        else if(word.equals("ninety") || word.equals("nine") || word.equals("nineteen"))
        {
            WMS = 9;
        }
    }
    
    public void fillZeros()
    {
        for(int i = 0; i < frame.getLength(); i++)
        {
            Chunk c = frame.getChunk(i);

            for(int j = 0; j < 3; j++)
            {
                if(c.get(j).equals("__"))
                {
                    c.insert(0, j);
                }
            }
        }
    }
    
    public void setTmp(String step)
    {
        String sub = step.substring(6);
        String[] pieces = sub.split(" ");
        
        for(int i = 0; i < pieces.length; i++)
        {
            //System.out.println(pieces[i]);
            
            if(pieces[i].equals("WMS"))
            {
                pieces[i] = Integer.toString(WMS);
            }
        }
        
        tmp = new Chunk(pieces[0], pieces[1], pieces[2]);
    }
    
    public void replaceTmp(String step)
    {
        if(step.contains("WMS"))
        {
            int position = Integer.parseInt(step.substring(14));
            tmp.insert(WMS, position);
        }
        else
        {
            int position = Integer.parseInt(step.substring(12));
            tmp.insert(Integer.parseInt(step.substring(10, 11)), position);
        }
    }
    
    public void setF(String step)
    {
        String size = step.substring(4,5);

        if(step.length() > 5)
        {
            String piece = step.substring(6);

            if(piece.equals("WMS"))
            {
                f = new Frame(size, WMS);    
            }
            else if(piece.equals("tmp"))
            {
                f = new Frame(size, tmp);
            }
            else if(piece.equals("c"))
            {
                f = new Frame(size, c);
            }
        }
        else
        {
            f = new Frame(size);
        }
    }
    
    public void setC(String step)
    {
        String position = step.substring(10);
        
        int pos;
        
        if(position.equals("length-1"))
        {
            pos = frame.getLength() - 1;
        }
        else
        {
            pos = Integer.parseInt(position);
        }
        
        c = frame.getChunk(pos);
    }
    
    public void replaceC(String step)
    {
        int position = Integer.parseInt(step.substring(10));
        c.insert(WMS, position);    
    }
    
    public void replaceFrame(String step)
    {
        int position = frame.getLength() - 1;
        
        String piece = step.substring(23);
        
        if(piece.equals("tmp"))
        {
            frame.setChunk(position, tmp);
        }
        else if(piece.equals("c"))  
        {
            frame.setChunk(position, c);
        }
        
    }
    
}
