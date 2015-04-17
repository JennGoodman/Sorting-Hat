/*
 * Administrator Class
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;

public class Administrator
{
    private int avgAge;
    private int numParticipants = 0;
    private ArrayList<String[]> records = new ArrayList();
    private final String[] houseList = {"Griffindor", "Slytherin", "Ravenclaw", 
            "Hufflepuff"};
    private final String path;
    
    /**
     * Opens the participant results file found in path and populates some 
     * of the values needed for getResults.
     * @param path The path to the file.
     * @throws java.io.IOException Signals that an I/O exception has occurred.
     */
    
    public Administrator(String path) throws IOException  
    {
        this.path = path;
        String line;
        
        try(BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            while((line = br.readLine()) != null)
            {
                String[] temp = line.split(",");
                records.add(temp);
                numParticipants++;
            }
            br.close();
            setAvgAge();
        }
    }
    
    /**
     * Accesses all private methods and formats the output to the participant 
     * data file.
     * @return A string containing all the compiled information.
     */        
            
    public String getResults()
    {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);

        sb.append("Total Number of Participants: \t");
        sb.append(numParticipants);
        sb.append("\n------------------------------------------------\n");
        sb.append("Number of Participants Per House: \n\n");
        for(int i = 0; i < houseList.length; i++)
        {
            fm.format("\t %-20s %4d \n", houseList[i], 
                    getTotalOneHouse(houseList[i]));
        }
        sb.append("------------------------------------------------\n");
        sb.append("Percent of Average Per House: \n\n");
        for(int i = 0; i < houseList.length; i++)
        {
            fm.format("\t %-20s %4d%% \n", houseList[i], 
                    getAvgOneHouse(houseList[i]));
        }
        sb.append("------------------------------------------------\n");
        sb.append("Average Age of Participants: \t");
        sb.append(getAvgAge());
        sb.append("\n------------------------------------------------\n");
        sb.append("Country of Origin: \n\n");
        sb.append(getCountryList());
        sb.append("------------------------------------------------\n");        
        
        fm.close();
        return sb.toString();
    }
    
    /**
     * Returns the total number of participants for each house.
     * @return An array containing the number of participants for each house.
     */
    
    private int[] getTotalAllHouses()
    {
        int[] totalParticipants = new int[4];
        int index = 0;
        
        //compute winning house
        for(int i = 0; i < records.size(); i++)
        {
            int winningScore = 0;
            String[] line = records.get(i);
            
            for(int j = 3; j < 7; j++)
            {
                int currScore = Integer.parseInt(line[j]);
                
                if(currScore > winningScore)
                {
                    winningScore = currScore;
                    index = j;
                }
            }
            
            //increment winning house
            switch (index)
            {
                case 3:
                    //griffindor count
                    totalParticipants[0] = ++totalParticipants[0]; 
                    break;
                case 4:
                    //slytherin count
                    totalParticipants[1] = ++totalParticipants[1]; 
                    break;
                case 5:
                    //ravenclaw count
                    totalParticipants[2] = ++totalParticipants[2];  
                    break;
                case 6: 
                    //hufflepuff count
                    totalParticipants[3] = ++totalParticipants[3]; 
                    break;
            }
        }
        return totalParticipants;
    }
    
    /**
     * Returns the total number of participants for a specified house.
     * @param house The specified house.
     * @return The number of participants.
     */
    
    private int getTotalOneHouse(String house)
    {
        int[] totalParticipants = getTotalAllHouses();
        
        for(int i = 0; i < houseList.length; i++)
        {
            if(house.equalsIgnoreCase(houseList[i]))
                return totalParticipants[i];
        }
        return -1; //invalid house
    }
    
    /**
     * Returns the percentage of participants for a specified house.
     * @param house The specified house.
     * @return The percentage of participants.
     */
    
    private int getAvgOneHouse(String house)
    {
        return (int)((100 *getTotalOneHouse(house)/numParticipants) + 0.5);
    }
    
   /**
     * Returns the average age of the participants.
     * @return The average age of the participants.
     */
    
    private int getAvgAge()
    {
        return avgAge;
    }
    /**
     * Calculates the average age of the participants. 
     */
    
    private void setAvgAge()
    {
        ArrayList<Object[]> ageList = new ArrayList();
        int sumAge = 0;

        for(int i = 0; i < records.size(); i++)
        {
            String[] line = records.get(i); 
            int currAge = Integer.parseInt(line[1]);
            sumAge += currAge;        
        }
        this.avgAge = (int)sumAge/numParticipants;
    }
    
    /**
     * Returns a string of the countries found in the participant data and 
     * their appearance counts.
     * @return A string containing all the compiled information.
     */
    
    private String getCountryList()
    {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        ArrayList<Object[]> countryList = new ArrayList();

        //build country list
        for(int i = 0; i < records.size(); i++)
        {
            String[] line = records.get(i); 
            boolean flag = false;
            
            //increment country already in list
            for(int j = 0; j < countryList.size(); j++)
            {
                Object[] country = countryList.get(j); 
                if(country[0].equals(line[2]))    
                {                    
                    int country_count = (int)country[1];
                    country[1] = ++country_count;
                    flag = true;
                }
            }
            
            //add new country to list
            if(!flag)    
            {
                Object[] country = {line[2], (Integer)1};
                countryList.add(country);
            }         
        }
        
        //sort list of countries
        Collections.sort(countryList, new Comparator<Object[]>()
        {
            @Override
            public int compare(Object[] o1, Object[] o2)
            {                
                //alphabetize country names
            //return String.valueOf(o1[0]).compareTo(String.valueOf(o2[0]));
                
                //rank by number of participants
                if((int)o1[1] < (int)o2[1])
                    return 1;
                return -1;
            }
        });
        
        //create columned table
        for(int k = 0; k < countryList.size(); k++)
        {
            Object[] country = countryList.get(k);
            fm.format("\t %-20s %4d \n", country[0], country[1]);
        }
        fm.close();
        return sb.toString();
    }
}