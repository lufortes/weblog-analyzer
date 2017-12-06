/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Daily count
    private int[] dayCounts;
    // Monthly count
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the array object to hold the daily
        // access counts.
        dayCounts = new int[31];
        // Create the array object to hold the monthly
        // access counts.
        monthCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    /**
     * Exercise 7.12 Constructor that accepts log file name
     */
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[24];
        dayCounts = new int[31];
        monthCounts = new int[12];
        reader = new LogfileReader(filename);
        
    }
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
            int day = entry.getDay();
            dayCounts[day]++;
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the daily counts.
     */
    public void printDailyCounts()
    {
        System.out.println("Day: Count");
        for(int day = 0; day < dayCounts.length; day++){
            System.out.println(day + ": " + dayCounts[day]);
        }
    }
    
    /**
     * Print the monthly counts.
     */
    public void totalAccessesPerMonth()
    {
        System.out.println("Month: Count");
        for(int month = 0; month < monthCounts.length; month++){
            System.out.println(month + ": " + monthCounts[month]);
        }
    }
    
    /**
     * Print the average monthly accesses.
     */
    public int averageAccessesPerMonth()
    {
        int avg = 0;
        int total = 0;
        for(int month = 0; month < monthCounts.length; month++){
            total += monthCounts[month];
        }
        return total/12;
    }
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    /** 
     * Exercise 7.14 Return the number of accesses recorded in the log file
     */
    public int numberOfAccesses()
    {
        int total = 0;
        // Add the value in each element of hourCounts to total
        for(int i = 0; i < hourCounts.length; i++)
        {
            total += hourCounts[i];
        }
        return total;
    }
    /** 
     * Exercise 7.15 Get the busiest hour
     */
    public int busiestHour()
    {
        int maxCount = 0;
        int busHour = 0;
        for (int i = 0; i < hourCounts.length; i++)
        {
            if (hourCounts[i] > maxCount) 
            {
                maxCount = hourCounts[i];
                busHour = i;
            }
        }
        return busHour;
    }
    /** 
     * Exercise 7.16 Return the quietest hour
     */
    public int quietestHour()
    {
        int minCount = maxHitsHour();
        int quietHour = 0;
        for (int i = 0; i < hourCounts.length; i++)
        {
            if ((hourCounts[i] < minCount) && hourCounts[i] > 0)  
            {
                minCount = hourCounts[i];
                quietHour = i;
            }
        }
        return quietHour;
    }
    /**
     * Return hour with max hits
     */
    private int maxHitsHour()
    {
        int maxCount = 0;
        for (int i = 0; i < hourCounts.length; i++)
        {
            if (hourCounts[i] > maxCount) 
            {
                maxCount = hourCounts[i];
            }
        }
        return maxCount;
    }
    /**
     * Exercise 7.18 Return the busiest two-hour
     */
    public int busiestTwoHour()
    {
        int busTwoHour = 0;
        int maxCount = 0;
        int n = 0;
        for (int i = 0; i < hourCounts.length; i++)
        {
            if (i == 23)
            {
                n = 0;
            }
            else
            {
                n = i + 1;
            }
            if ((hourCounts[i] + hourCounts[n]) > maxCount)
            {
                maxCount = hourCounts[i] + hourCounts[n];
                busTwoHour = i;
            }
            System.out.println("i - " + i + "-" + (hourCounts[i] + hourCounts[n]));
                
        }
        return busTwoHour;
    }
    
    /** 
     * Exercise 7.19 Get the busiest day
     */
    public int busiestDay()
    {
        int maxCount = 0;
        int busDay = 0;
        for (int i = 0; i < dayCounts.length; i++)
        {
            if (dayCounts[i] > maxCount) 
            {
                maxCount = dayCounts[i];
                busDay = i;
            }
        }
        return busDay + 1;
    }
    /** 
     * Exercise 7.19 Return the quietest day
     */
    public int quietestDay()
    {
        int minCount = maxHitsDay();
        int quietDay = 0;
        for (int i = 0; i < dayCounts.length; i++)
        {
            if ((dayCounts[i] < minCount) && dayCounts[i] > 0)  
            {
                minCount = dayCounts[i];
                quietDay = i;
            }
        }
        return quietDay + 1;
    }
    
    /**
     * Return day with max hits
     */
    private int maxHitsDay()
    {
        int maxCount = 0;
        for (int i = 0; i < dayCounts.length; i++)
        {
            if (dayCounts[i] > maxCount) 
            {
                maxCount = dayCounts[i];
            }
        }
        return maxCount;
    }    
    
    /** 
     * Exercise 7.19 Get the busiest month
     */
    public int busiestMonth()
    {
        int maxCount = 0;
        int busMonth = 0;
        for (int i = 0; i < monthCounts.length; i++)
        {
            if (monthCounts[i] > maxCount) 
            {
                maxCount = monthCounts[i];
                busMonth = i;
            }
        }
        return busMonth + 1;
    }
    
    /** 
     * Exercise 7.19 Return the quietest month
     */
    public int quietestMonth()
    {
        int minCount = maxHitsMonth();
        int quietMonth = 0;
        for (int i = 0; i < monthCounts.length; i++)
        {
            if ((monthCounts[i] < minCount) && monthCounts[i] > 0)  
            {
                minCount = monthCounts[i];
                quietMonth = i;
            }
        }
        return quietMonth + 1;
    }
    
    /**
     * Return day with max hits
     */
    private int maxHitsMonth()
    {
        int maxCount = 0;
        for (int i = 0; i < monthCounts.length; i++)
        {
            if (monthCounts[i] > maxCount) 
            {
                maxCount = monthCounts[i];
            }
        }
        return maxCount;
    }    
}
