import java.util.*;
import java.io.*;

/**
 * Takes in a file that is set up as follows WITH NO HEADER:
 * 
 * pID stimID gender major correctAnswer response
 * 
 * Output is a step by step progression of the errors made in the response.
 * Indexing may be off in places but I don't THINK it is anymore. 

 */
public class ErrorClassifier
{
    static ArrayList<String> answers = new ArrayList<String>();
    static ArrayList<String> responses = new ArrayList<String>();
    static ArrayList<String> pIDs = new ArrayList<String>();
    static ArrayList<String> stimNums = new ArrayList<String>();
    static ArrayList<String> genders = new ArrayList<String>();
    static ArrayList<String> majors = new ArrayList<String>();

    static String pID;
    static String stimNum;
    
    static String answer;
    static String response;
    static String changedToMatch;
    static boolean ambiguity; //this currently isn't being used

    static ArrayList<ArrayList<String>> per = new ArrayList<ArrayList<String>>();

    public static void main(String[] args)
    {
        String filename = args[0];

        readFile(filename); //reads in a file with the following format:
                            //pID stimID gender major correctAnswer response

        for(int i = 0; i < answers.size(); i++) //for each row given in the input file
        {

            answer = answers.get(i);
            response = responses.get(i);
            changedToMatch = response;

            //System.out.println("Correct answer: " + answer);
            //System.out.println("Given response: " + response);

            if(answer.equals(response) || response.contains("c"))
            {
                //System.out.println("CORRECT" + "\n");
                ArrayList<String> b = new ArrayList<String>();

                b.add("correct" + " " + "null" + " null " + "null" + " null " + answer);
                per.add(b);
            }
            else if(response.contains("n"))
            {
                //System.out.println("no response" + "\n");
                ArrayList<String> b = new ArrayList<String>();

                b.add("none" + " " + "null" + " null " + "null" + " null " + answer);
                per.add(b);
            }
            else
            {
                ArrayList<String> tmp = compare(); //main method

                /*
                for(int j = 0; j < tmp.size(); j++)
                {
                    System.out.println(tmp.get(j));
                }
                */

                per.add(i, tmp);
            }

        }

        writeToFile(per);
    }

    public static void writeToFile(ArrayList<ArrayList<String>> array)
    {
        try
        {
            File file = new File("/users/mld719/Documents/College/Research/Landy Lab/PracticeCode/outputFINAL.txt");

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("pID" + "\t" + "sNum" + "\t" + "gender" + "\t" + "major" + "\t" + "correctAnswer" + "\t" + "input" + "\t" + "error" + "\t" + "numAffected1" + "\t" + "numAffected2" + "\t" + "position1" + "\t" + "position2" + "\t" + "output" + "\n");

            for(int i = 0; i < array.size(); i++) //for each arraylist of steps
            {
                ArrayList<String> s = array.get(i);

                String output = responses.get(i);

                for(int j = 0; j < s.size(); j++) //for each step in the arraylist
                {
                    String p = pIDs.get(i);
                    String st = stimNums.get(i);
                    String g = genders.get(i);
                    String m = majors.get(i);

                    String str = p + "\t" + st + "\t" + g + "\t" + m + "\t" + answers.get(i);

                    String step = s.get(j);
                    String[] pieces = step.split(" ");
                    String input = output;
                    String type = pieces[0];
                    String numAffected1 = pieces[1];
                    String numAffected2 = pieces[2];
                    String position1 = pieces[3];
                    String position2 = pieces[4];
                    output = pieces[5];

                    str = str + "\t" + input + "\t" + type + "\t" + numAffected1 + "\t" + numAffected2 + "\t" + position1 + "\t" + position2 + "\t" + output + "\n";

                    //System.out.println(str);
                    
                    bw.write(str);
                    
                    str = "";
                }

            }

            bw.close();
        }
        catch(Exception e)
        {
            System.out.println("Writing File Error");
        }
    }

    public static void readFile(String filename)
    {
        try
        {
            Scanner scanner = new Scanner(new File(filename));

            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                String[] words = line.split("\t");

                //System.out.println(words[0]);

                pIDs.add(words[0]);
                stimNums.add(words[1]);
                genders.add(words[2]);
                majors.add(words[3]);
                answers.add(words[4]);
                responses.add(words[5]); 
            }

        }
        catch(Exception e)
        {
            System.out.println("Reading file error");
        }
    }

    public static ArrayList<String> compare()
    {
        /*
         * Steps:
         *  1. Count zeros. If not the same, add or delete to make CTM same as A
         *  2. Make sure all correct digits are there. If not, there is a swap somewhere. Solve accordingly.
         *  3. Once zeros and digits the same, perform flips. Try to use least number of flips. 
         */

        ArrayList<String> stepsTaken = new ArrayList<String>();

        if(answer.length() > changedToMatch.length())
        {
            ArrayList<String> ctm = new ArrayList<String>(Arrays.asList(changedToMatch.split("")));
            ArrayList<String> a = new ArrayList<String>(Arrays.asList(answer.split("")));

            ctm.remove(0);
            a.remove(0);

            for(int i = 0; i < changedToMatch.length(); i++)
            {
                String aplace = a.get(i);
                String rplace = ctm.get(i);

                if(!aplace.equals("0") && rplace.equals("0"))
                {
                    if(answer.length() == changedToMatch.length())
                    {
                        stepsTaken = flip(stepsTaken);

                    }
                    else
                    {
                        stepsTaken = findIns2(a, ctm, i, stepsTaken);

                    }
                }

                if(aplace.equals("0") && !rplace.equals("0"))
                {
                    if(answer.length() == changedToMatch.length())
                    {
                        stepsTaken = flip(stepsTaken);

                    }
                    else
                    {
                        stepsTaken = findDel2(a, ctm, i, stepsTaken);

                    }
                }
            }

            if(ctm.size() < a.size())
            {
                stepsTaken = findDel2(a, ctm, answer.length(), stepsTaken);    

            }
        }

        if(answer.length() < changedToMatch.length())
        {
            ArrayList<String> ctm = new ArrayList<String>(Arrays.asList(changedToMatch.split("")));
            ArrayList<String> a = new ArrayList<String>(Arrays.asList(answer.split("")));

            ctm.remove(0);
            a.remove(0);

            for(int i = 0; i < answer.length(); i++)
            {
                String aplace = a.get(i);
                String rplace = ctm.get(i);

                if(!aplace.equals("0") && rplace.equals("0"))
                {
                    if(answer.length() == changedToMatch.length())
                    {
                        stepsTaken = flip(stepsTaken);

                    }
                    else
                    {
                        stepsTaken = findIns2(a, ctm, i, stepsTaken);

                    }
                }

                if(aplace.equals("0") && !rplace.equals("0"))
                {
                    if(answer.length() == changedToMatch.length())
                    {
                        stepsTaken = flip(stepsTaken);

                    }
                    else
                    {
                        stepsTaken = findDel2(a, ctm, i, stepsTaken);

                    }
                }
            }

            if(ctm.size() > a.size())
            {
                stepsTaken = findIns2(a, ctm, changedToMatch.length(), stepsTaken);    

            }
        }

        stepsTaken = checkDigits(stepsTaken);
        
        int acount = countZeros(answer);
        int rcount = countZeros(changedToMatch);

        ArrayList<String> ctm = new ArrayList<String>(Arrays.asList(changedToMatch.split("")));
        ArrayList<String> a = new ArrayList<String>(Arrays.asList(answer.split("")));

        ctm.remove(0);
        a.remove(0);

        if(acount == rcount)
        {
            stepsTaken = flip(stepsTaken);

        }
        else if(acount > rcount)
        {
            stepsTaken = findDel2(a, ctm, changedToMatch.length() - 1, stepsTaken);

        }
        else if(rcount > acount)
        {
            stepsTaken = findIns2(a, ctm, answer.length(), stepsTaken);

        }

        //System.out.println("After changes:  " + changedToMatch);
        return stepsTaken;
    }

    public static ArrayList<String> flip(ArrayList<String> stepsTaken)
    {
        ArrayList<Integer> fix = new ArrayList<Integer>();

        ArrayList<String> ctm = new ArrayList<String>(Arrays.asList(changedToMatch.split("")));
        ArrayList<String> a = new ArrayList<String>(Arrays.asList(answer.split("")));

        ctm.remove(0);
        a.remove(0);

        for(int i = 0; i < answer.length(); i++)
        {
            if(!(a.get(i).equals(ctm.get(i)))) 
            {
                fix.add(i);
            }
        }

        for(int i = 0; i < fix.size(); i++)
        {

            for(int k = 0; k < (fix.size()-1); k++)
            {
                int index1 = fix.get(k);
                int index2 = fix.get(k+1);

                if(ctm.get(index1).equals("0") || ctm.get(index2).equals("0") || answer.equals(changedToMatch))
                {
                    continue;
                }

                String groupR = ctm.get(index1) + ctm.get(index2);

                if(answer.contains(groupR))
                {
                    ctm.set(index1, groupR);
                    ctm.remove(index2);

                    int indexInA = answer.indexOf(groupR);

                    String one = a.get(indexInA);
                    String two = a.get(indexInA + 1);
                    String groupA = one + two;

                    String tmp = ctm.get(indexInA);

                    ctm.set(indexInA, groupR);
                    ctm.set(index1, tmp);

                    String n = new String();

                    for(int j = 0; j < ctm.size(); j++)
                    {
                        n = n + ctm.get(j);
                    }

                    changedToMatch = n;
                    
                    int i1 = answer.length() - a.indexOf(groupR) - 1;
                    int i2 = answer.length() - a.indexOf(tmp) - 1;

                  
                    //System.out.println("flipped: " + groupR + " and " + tmp);

                    stepsTaken.add("flip " + groupR + " " + tmp + " " + index1 + " " + index2 + " " + changedToMatch);
                }
            }

            if(changedToMatch.equals(answer) == false)
            {
                int j = fix.get(i);
                String r1 = ctm.get(j);
                String a1 = a.get(j);

                int index = 0;

                while(!(answer.equals(changedToMatch)) && index < ctm.size())
                {
                    String p = ctm.get(index);

                    if(p.equals(a1))
                    {
                        String a2 = a.get(index);
                        String r2 = ctm.get(index);

                        int i1 = changedToMatch.length() - ctm.indexOf(a2) - 1;
                        int i2 = answer.length() - a.indexOf(a2) - 1;
                        
                        if(!(a2.equals(r2)))
                        {
                            String tmp = r2;
                            ctm.set(j, r2);
                            ctm.set(index, r1);

                            String tmps = new String();

                            for(int k = 0; k < ctm.size(); k++)
                            {
                                tmps = tmps + ctm.get(k);
                            }

                            changedToMatch = tmps;

                            //System.out.println("flipped: " + r2 + " and " + a2);

                            stepsTaken.add("flip " + r2 + " " + a2 + " " + i1 + " " + i2 + " " + changedToMatch);

                            break;
                        }

                    }
                    
                    index++;
                }
            }
        }

        return stepsTaken;
    }

    public static ArrayList<String> checkDigits(ArrayList<String> stepsTaken)
    {
        String sc = new String(changedToMatch);
        String sa   = new String(answer);

        String m = sc.replaceAll("0", "");
        String n = sa.replaceAll("0", "");

        if(!m.equals(n))
        {
            ArrayList<String> ctm = new ArrayList<String>(Arrays.asList(m.split("")));
            ArrayList<String> a = new ArrayList<String>(Arrays.asList(n.split("")));

            ctm.remove(0);
            a.remove(0);

            if(ctm.size() < a.size())
            {
                for(int i = 0; i < ctm.size(); i++)
                {
                    if(!(ctm.get(i).equals(a.get(i))))
                    {
                        int index = answer.indexOf(a.get(i), i);

                        ctm.add(i, "0");
                        changedToMatch = changedToMatch.substring(0, index) + a.get(i) + changedToMatch.substring(index+1);

                        //System.out.println(("swapped: " + a.get(i) + " and 0 at index: " + (changedToMatch.length() - index - 1)));

                        stepsTaken.add("swap " + a.get(i) + " 0 " + (answer.length() - index - 1) + " null " + changedToMatch);
                    }
                }

                for(int j = ctm.size(); j < a.size(); j++)
                {
                    if(changedToMatch.endsWith("0"))
                    {
                        changedToMatch = changedToMatch.substring(0, answer.length() - 1) + a.get(j);
                    }
                    else
                    {
                        changedToMatch = changedToMatch.substring(0, changedToMatch.length() ) + a.get(j);
                    }

                    //System.out.println("swapped: " + a.get(j) + " and 0 at index: " + (changedToMatch.length() - j - 2));

                    stepsTaken.add("swap " + a.get(j) + " 0 " + "0" + " null " + changedToMatch);
                }
            }
            else if(ctm.size() > a.size())
            {
                for(int i = 0; i < a.size(); i++)
                {
                    if(!(ctm.get(i).equals(a.get(i))) && !(changedToMatch.equals(answer)))
                    {
                        int index = changedToMatch.indexOf(ctm.get(i));

                        String tmp = ctm.get(i);

                        ctm.set(i, "0");
                        changedToMatch = changedToMatch.substring(0, index) + "0" + changedToMatch.substring(index+1);

                        //System.out.println("swapped: " + ctm.get(i) + " and 0 at index: " + (changedToMatch.length() - index - 1));

                        stepsTaken.add("swap " + tmp + " 0 " + (answer.length() - index - 1) + " null " + changedToMatch);
                    }
                }

                if(answer.equals(changedToMatch) == false)
                {
                    for(int j = a.size(); j < ctm.size(); j++)
                    {

                        changedToMatch = changedToMatch.substring(0, answer.length() - 1) + "0";
                       
                        //System.out.println("swapped: " + ctm.get(j) + " and 0 at index: " + (changedToMatch.length() - j - 2));

                        stepsTaken.add("swap " + ctm.get(j) + " 0 " + (answer.length() - j - 2) + " null " + changedToMatch);
                    }
                }
            }
            else
            {
                for(int i = 0; i < ctm.size(); i++)
                {
                    if(!(ctm.get(i).equals(a.get(i))))
                    {
                        int index = answer.indexOf(a.get(i));

                        String tmp = ctm.get(i);

                        ctm.set(i, a.get(i));
                        changedToMatch = changedToMatch.substring(0, index) + a.get(i) + changedToMatch.substring(index+1);

                        //System.out.println("swapped: " + a.get(i) + " and " + tmp +" at index: " + (changedToMatch.length() - index - 1));

                        stepsTaken.add("swap " + a.get(i) + " " + tmp + " " + (answer.length() - index - 1) + " null " + changedToMatch);
                    }
                }
            }
        }

        return stepsTaken;
    }

    public static ArrayList<String> findDel2(ArrayList<String> a, ArrayList<String> ctm, int i, ArrayList<String> stepsTaken)
    {

        if(i >= a.size())
        {
            return stepsTaken;
        }

        String place = a.get(i);

        String rplace = "";

        if(i >= ctm.size())
        {
            rplace = ctm.get(i - 1);

            int numIns = 0;

            for(int j = i; j < a.size(); j++) 
            {
                if(j >= a.size())
                {
                    break;
                }

                if(a.get(j).equals("0"))
                {
                    ctm.add(j, "0");
                    numIns++;
                }
            }

            String tmp = changedToMatch.substring(0, i);
            String tmp2 = "";

            for(int j = 0; j < numIns; j++)
            {
                tmp = tmp + "0";
                tmp2 = tmp2.concat("0");
            }

            tmp = tmp + changedToMatch.substring(i);

            changedToMatch = tmp;
            stepsTaken.add("deletion " + tmp2 + " " + "null" + " " + (answer.length() - i - 1) + " null " + changedToMatch);

            return stepsTaken;
        }
        else
        {
            rplace = ctm.get(i);
        }

        int numIns = 0;

        if(place.equals("0") && !(rplace.equals("0")))
        {
            if(answer.contains(rplace))
            {
                int index = answer.indexOf(rplace, i);

                if(answer.equals(changedToMatch) == false)
                {
                    for(int j = i; j < index; j++) //check indexing
                    {
                        if(j >= a.size())
                        {
                            break;
                        }

                        if(a.get(j).equals("0"))
                        {
                            ctm.add(j, "0");
                            j--;
                            numIns++;
                            index--;
                        }
                    }

                    String tmp = changedToMatch.substring(0, i);
                    String tmp2 = "";

                    for(int j = 0; j < numIns; j++)
                    {
                        tmp = tmp + "0";
                        tmp2 = tmp2.concat("0");
                    }

                    tmp = tmp + changedToMatch.substring(i);
                    changedToMatch = tmp;

                    stepsTaken.add("deletion " + tmp2 + " null " + (answer.length() - i - 1) + " null " + changedToMatch);
                }
            }
        }

        return stepsTaken;
    }

    public static ArrayList<String> findIns2(ArrayList<String> a, ArrayList<String> ctm, int i, ArrayList<String> stepsTaken)
    {

        if(i >= ctm.size())
        {
            return stepsTaken;
        }

        String place = "";

        if(i >= a.size())
        {
            place = a.get(i - 1);

            int numDel = 0;

            for(int j = i; j < ctm.size(); j++)
            {
                if(j >= ctm.size())
                {
                    break;
                }

                if(ctm.get(j).equals("0"))
                {
                    ctm.remove(j);
                    j--;
                    numDel++;
                }
            }

            String tmp = changedToMatch.substring(0, i) + changedToMatch.substring(i + numDel);
            changedToMatch = tmp;

            String tmp2 = "";

            for(int k = 0; k < numDel; k++)
            {
                tmp2 = tmp2.concat("0");
            }

            stepsTaken.add("insertion " + tmp2 + " null " + (answer.length() - i) + " null " + changedToMatch);
            
            return stepsTaken;
        }
        else
        {
            place = a.get(i);
        }

        String rplace = ctm.get(i);

        int numDel = 0;

        if(!place.equals("0") && rplace.equals("0"))
        {

            if(changedToMatch.contains(place))
            {
                int index = changedToMatch.indexOf(place, i);

                for(int j = i; j < index; j++)
                {
                    if(j >= ctm.size())
                    {
                        break;
                    }

                    if(ctm.get(j).equals("0"))
                    {
                        ctm.remove(j);
                        j--;
                        numDel++;
                        index--;
                    }
                }

                String tmp = changedToMatch.substring(0, i) + changedToMatch.substring(i + numDel);
                changedToMatch = tmp;

                String tmp2 = "";

                for(int k = 0; k < numDel; k++)
                {
                    tmp2 = tmp2.concat("0");
                }

                stepsTaken.add("insertion " + tmp2 + " null " + (answer.length() - i - 1) + " null " + changedToMatch);
            }
        }

        return stepsTaken;
    }

    public static int countZeros(String s)
    {
        int count = 0;
        char[] ss = s.toCharArray();

        for(int i = 0; i < ss.length; i++)
        {
            if(ss[i] == '0')
            {
                count++;
            }
        }

        return count;
    }
}
