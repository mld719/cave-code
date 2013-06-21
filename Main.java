import java.util.*;

/**
 * Takes in a txt filename and a String of the number that you wants transcoded
 * 
 * ex:     {"productions.txt", "three hundred forty two"}
 */
public class Main
{
    public static void main(String[] args)
    {
        String filename = args[0];
        String number = args[1]; 

        Model model = new Model(filename, number);

        ArrayList<String> lines = model.getFull(filename);
        ArrayList<Precondition> pres = model.getPre(lines);
        ArrayList<Action> actions = model.getAction(lines);
        ArrayList<Production> prods = model.makeProductions(pres, actions);

        //System.out.println("Megan, you're the best computer scientist in the world!");

        model.transcode(number, prods);

       
        /*
        for(int i = 0; i < lines.size(); i++)
        {
            System.out.println(lines.get(i));
        }
        */
       

        //TranscoderTest test6 = new TranscoderTest(number);
        //System.out.println(test6.transcode());
    }
}
