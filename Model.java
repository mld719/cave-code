import java.util.*;
import java.io.*;

/**
 * Write a description of class Model here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Model
{
    String filename;
    String number;

    boolean hasFrame;
    boolean hasTmp;
    boolean hasWMS;

    Chunk currentTmp;
    Frame currentFrame; 
    int WMS = 0; 
    String inputType; 

    State currentState = new State(currentFrame, currentTmp, WMS);

    public Model(String f, String n)
    {
        filename = f;
        number = n;
    }

    public String go()
    {
        //this is used by the tester class
        ArrayList<String> lines = getFull(filename);
        ArrayList<Precondition> pres = getPre(lines);
        ArrayList<Action> actions = getAction(lines);
        ArrayList<Production> prods = makeProductions(pres, actions);

        transcode(number, prods);

        return currentFrame.toString();
    }

    //takes lines out of the file and creates an array where each line is a seperate string (production)
    public ArrayList<String> getFull(String filename)
    {
        int i = 0;
        ArrayList<String> lines = new ArrayList<String>();
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

    //gets all of the preconditions from the lines
    public ArrayList<Precondition> getPre(ArrayList<String> array)
    {
        ArrayList<Precondition> pres = new ArrayList<Precondition>();

        for(int i = 0; i < array.size(); i++)
        {
            String pre = array.get(i);

            pre = pre.substring(0, 38);
            //System.out.println(pre);

            Precondition p = new Precondition(pre);

            pres.add(p);
        }

        return pres;
    }

    //gets all of the actions from the lines
    public ArrayList<Action> getAction(ArrayList<String> array)
    {
        ArrayList<Action> actions = new ArrayList<Action>();

        for(int i = 0; i < array.size(); i++)
        {
            String action = array.get(i);

            action = action.substring(40);
            //System.out.println(action);

            Action a = new Action(action);

            actions.add(a);
        }

        return actions;
    }

    //makes productions out of all the pres and actions

    public ArrayList<Production> makeProductions(ArrayList<Precondition> p, ArrayList<Action> a)
    {
        ArrayList<Production> prods = new ArrayList<Production>();

        for(int i = 0; i < p.size(); i++)
        {
            Precondition pr = p.get(i);
            Action ac = a.get(i);

            Production prod = new Production(pr, ac);
            //System.out.println(prod);

            prods.add(prod);
        }

        return prods;
    }

    public void transcode(String number, ArrayList<Production> prods)
    {
        //String[] array = number.split(" ");

        Scanner scanner = new Scanner(number);

        while(scanner.hasNext() || currentTmp != null || WMS != 0 || currentFrame.toString().contains("__"))
        {
            String word = "";

            if(scanner.hasNext())
            {
                word = scanner.next();
                System.out.println(word);
            }

            if(word.equals(""))
            {
                System.out.println("end");
            }
            
            Precondition p = setPrecondition(word);
            System.out.println("Precondion Made: " + p.toString());
            //now need to match this precondition to the right production

            double[] matches = matchProds(p, prods); //now has 0 - 1 matching score (increment by 0.2);
            //need to now pick which one # is the best, pick that production, and fire that action

            int index = findBestProd(matches);
            
            Production bestMatch = prods.get(index);

            System.out.println("Production Index Chosen: " + index);
            System.out.println("Production Chosen: " + bestMatch.toString());

            fire(bestMatch.getAction(), word);

        }

        System.out.println(currentFrame.toString());
    }

    //creates the precondition based on the input and current state of the model

    public Precondition setPrecondition(String word)
    {
        //decides input type
        if(word.equals("billion"))
        {
            inputType = "b";
        }
        else if(word.equals("million"))
        {
            inputType = "m";
        }
        else if(word.equals("thousand"))
        {
            inputType = "k";
        }
        else if(word.equals("hundred"))
        {
            inputType = "h";
        }
        else if(word.equals("ten") || word.endsWith("ty"))
        {
            inputType = "d";
        }
        else if(word.equals("eleven") || word.equals("twelve") || word.endsWith("teen"))
        {
            inputType = "t";
        }
        else if(word.equals(""))
        {
            inputType = "e"; //for end?
        }
        else
        {
            inputType = "i"; //for digits
        }

        if(currentFrame == null)
        {
            hasFrame = false;
        }
        else
        {
            hasFrame = true;
        }

        if(currentTmp == null)
        {
            hasTmp = false;
        }
        else
        {
            hasTmp = true;
        }

        if(WMS == 0)
        {
            hasWMS = false;
        }
        else
        {
            hasWMS = true;
        }

        Precondition current = new Precondition(inputType, hasFrame, hasWMS, hasTmp); //creates a precondition

        //frame and WMS are update in the action side of things
        return current;
    }

    public double[] matchProds(Precondition currentPre, ArrayList<Production> prods)
    {
        double[] matches = new double[prods.size()];

        //need to compare the precondition to each precondition part of the production
        //get precondition part
        //then get elements of precondition

        boolean currentFrame  = currentPre.hasFrame();
        boolean currentWMS    = currentPre.hasWMS();
        boolean currentTMP    = currentPre.hasTMP();
        String  currentInput  = currentPre.getInputType();
        //String  currentFrameSize  = currentPre.getFrameSize();
        //int     currentWMSnum = currentPre.getWMS();

        for(int i = 0; i < prods.size(); i++)
        {
            Production prod = prods.get(i);
            Precondition fromProd = prod.getPre();

            boolean prodFrame  = fromProd.hasFrame();
            boolean prodWMS    = fromProd.hasWMS();
            boolean prodTMP    = fromProd.hasTMP();
            String  prodInput  = fromProd.getInputType();
            //String  prodFrameSize  = fromProd.getFrameSize();
            //int     prodWMSnum = fromProd.getWMS();

            double match = 0.0;

            //right now there are five variables that determine if matching, so each is weighted equally.
            //a perfect match will produce a 1.0, something that matches 2 varables will be a 0.4 etc.

            if(currentFrame == prodFrame)
            {
                match = match + 0.25;
            }

            if(currentWMS == prodWMS)
            {
                match = match + 0.25;
            }

            if(currentTMP == prodTMP)
            {
                match = match + 0.25;
            }

            if(currentInput.equals(prodInput))
            {
                match = match + 0.25;
            }

            /*
            if(currentFrameSize.equals(prodFrameSize))
            {
            match = match + 0.2;
            }

            if(currentWMSnum == prodWMSnum)
            {
            match = match + 0.2;
            }
             */

            match = Math.pow(match, 18);

            //System.out.println(match);

            matches[i] = match;
        }

        return matches;
    }

    public int findBestProd(double[] array)
    {
        //find highest value in array and then get corresponding production from list of prods

        //now want to set up the dart thing. so sum all the scores, find a random number between the two, and then subtract/

        int index = 0;
        double sum = 0.0;

        for(int i = 0; i < array.length; i++)
        {
            sum = sum + array[i];
        }

        System.out.println("Sum of Matching Values: " + sum);

        Random rng = new Random();

        double d = rng.nextDouble();
        d = d*sum;

        double place = 0.0;

        int i = 0;
        while(place <= d)
        {
            place = place + array[i];
            i++;
        }

        System.out.println("Random Chosen: " + d);
        
        index = i - 1;
        //System.out.println(index);
        return index;
    }

    public void fire(Action a, String word)
    {

        //if current frame is null, create a frame because you need one
        // if current frame is smaller than the new one would be, create the bigger one and insert
        // the smaller one into the first chunk of the big one

        //if the current frame is the biggest, then just create a new chunk to insert

        currentState = a.update(currentState, word);

        currentFrame = currentState.getFrame();
        currentTmp   = currentState.getTMP();
        WMS          = currentState.getWMS();

        System.out.println(currentState.toString());
        
        //figure out what to return here.
        //what if i return a precondition

    }
}
