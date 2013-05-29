import java.io.*;
import java.util.*;

public class Main
{
    static ArrayList<String> lines;
    static ArrayList<Precondition> pres;
    static ArrayList<Action> actions;

    public static void main(String[] args)
    {
        String filename = "list.txt";
        
        getFull(filename);
        
        for(int i = 0; i < lines.size(); i++)
        {
            System.out.println(lines.get(i));
        }
        
        getPre(lines);
        getAction(lines);
        
        for(int i = 0; i < pres.size(); i++)
        {
            System.out.println(pres.get(i));
        }
        
        for(int i = 0; i < actions.size(); i++)
        {
            System.out.println(actions.get(i));
        }

        //Action action = new Action(x);
        //Precondition pre = new Precondition(x);
    }

    public static ArrayList<String> getFull(String filename)
    {
        int i = 0;
        lines = new ArrayList<String>();
        //need to initialize lines 

        try
        {
            Scanner scanner = new Scanner(new File(filename)); //in data folder

            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                lines.add(line);
                i++;
            }

        }
        catch(Exception e)
        {
            System.out.println("error in scanner");
        }
        
        
        return lines;
    }

    public static ArrayList<Precondition> getPre(ArrayList<String> array)
    {
        pres = new ArrayList<Precondition>();
        
        for(int i = 0; i < array.size(); i++)
        {
            String pre = array.get(i);
            
            pre = pre.substring(0, 35);

            Precondition p = new Precondition(pre);
            
            pres.add(p);
        }
        
        return pres;
    }

    public static ArrayList<Action> getAction(ArrayList<String> array)
    {
        actions = new ArrayList<Action>();
        
        for(int i = 0; i < array.size(); i++)
        {
            String action = array.get(i);
            
            action = action.substring(38);
            
            Action a = new Action(action);
            
            actions.add(a);
        }
        
        return actions;
    }
}
