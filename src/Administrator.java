/*
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Administrator
{
    private int avgAge;
    private int numParticipants = 0;
    private ArrayList<String[]> records = new ArrayList();
    private final String path;
    
    /**
     * Opens the participant results file found in path and populates some 
     * of the values needed for getResults.
     * @param path
     * @return 
     */
    
    public Administrator(String path) throws IOException
    {
        this.path = path;
        String line = null;
        
        try(BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            while((line = br.readLine()) != null)
            {
                String[] temp = line.split(", ");
                records.add(temp);
                numParticipants++;
            }
            br.close();
        }
    }
    
    /**
     * Calculates the averages and totals from the participant data file.
     * @return 
     */        
            
    public String getResults()
    {          
        return null;  //string builder
    }
    
    /**
     * 
     * @return 
     */
    
    private int[] getTotals() //all houses
    {
        int[] totals = new int[4];
        
        int max = 0, 
            curr,
            index = 0, 
            griffindor_count = 0, 
            slytherin_count = 0, 
            ravenclaw_count = 0, 
            hufflepuff_count = 0;
        
        //find winning house
        for(int i = 0; i < records.size(); i++)
        {
            String[] temp = records.get(i); 
            for(int j = 3; j < 7; j++)
            {
                curr = Integer.parseInt(temp[j]);
                if(curr > max)
                {
                    max = curr;
                    index = j;
                }
            }
            //inc winning house
            switch (index)
            {
                case 3: 
                    totals[0] = ++griffindor_count;
                    break;
                case 4:
                    totals[1] = ++slytherin_count;
                    break;
                case 5:
                    totals[2] = ++ravenclaw_count;
                    break;
                case 6: 
                    totals[3] = ++hufflepuff_count;
                    break;
            }
        }
        return totals;
    }
    
    /**
     * Returns the total number of participants for specified house.
     * @param house
     * @return 
     */
    
    private int getTotal(String house)  //specific house
    {
        int[] totals = getTotals();
        
        
        
        
        if(house.equalsIgnoreCase("griffindor"))
            return totals[0];
        else if(house.equalsIgnoreCase("slytherin"))
            return totals[1];
        else if(house.equalsIgnoreCase("ravenclaw")) 
            return totals[2]; 
        else if(house.equalsIgnoreCase("hufflepuff")) 
            return totals[3];
        else
            return -1; //invalid argument    
    }
    
    /**
     * Returns the average number of participants for the specified house.
     * @param house
     * @return 
     */
    
    private int getAvg(String house)
    {
        return (int)getTotal(house)/numParticipants;   
    }
    
    /**
     * Returns a string of the countries found in the participant data and 
     * their appearance counts.
     * @return 
     */
    
    private String getCountryList()
    {
        return null;
    }
    
    //Results file structure
    //name, age, country, griffindor_count, slytherin_count, ravenclaw_count, 
    //hufflepuff_count

    
}