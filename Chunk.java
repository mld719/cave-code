
public class Chunk
{
    String[] spaces;

    public Chunk()
    {
        spaces = new String[3];

        for(int i = 0; i < spaces.length; i++)
        {
            spaces[i] = "__";
        }
    }
    
    public Chunk(String n1, String n2, String n3)
    {
        spaces = new String[3];
        
        //String num1 = Integer.toString(n1);
        //String num2 = Integer.toString(n2);
        //String num3 = Integer.toString(n3);
        
        spaces[0] = n1;
        spaces[1] = n2;
        spaces[2] = n3;
    }

    public String get(int pos)
    {
        return spaces[pos];
    }
    
    public void insert(int num, int pos)
    {
        String number = Integer.toString(num);

        spaces[pos] = number;
    }

    public void print()
    {

        for(int i = 0; i < spaces.length; i++)
        {
            System.out.println(spaces[i] + " ");
        }
    }
    
    public String toString()
    {
        String s = new String();
        
        for(int i = 0; i < spaces.length; i++)
        {
            s = s.concat(" " + spaces[i]);
        }
        
        return s;
    }
}
