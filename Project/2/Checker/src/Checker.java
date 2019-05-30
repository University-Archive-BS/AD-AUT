import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Checker
{
    public static void main(String[] args)
    {
        ArrayList<Integer> inputs = readFile("input.txt");
        ArrayList<Integer> inputsResult = readFile("output.txt");

        int n = inputs.get(0); // number of jobs
        int m = inputs.get(1); // number of workStations

        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++)
        {
            int startTimeAccess = inputs.get(i * (m + 1) + 2);
            Task[] tasks = new Task[m];
            for (int j = 0; j < m; j++)
            {
                tasks[j] = new Task(inputs.get(i * (m + 1) + j + 3));
            }
            jobs[i] = new Job(startTimeAccess, tasks, i + 1);
        }

        int[][] result = new int[n][m];

        Factory factory = new Factory(m, jobs);
        int finalResult = inputsResult.get(0);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                result[i][j] = inputsResult.get(i * m + j + 1);
            }
        }
        boolean success = factory.check(result, finalResult, jobs);
        if (success)
        {
            System.out.println("true");
        }
        else
        {
            System.out.println("false");
        }
    }

    public static ArrayList<Integer> readFile(String s)
    {
        ArrayList<String> strings = new ArrayList<>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(s));
            String line;
            while ((line = br.readLine()) != null)
            {
                strings.add(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++)
        {
            String[] split = strings.get(i).split("\\s+");
            for (int i1 = 0; i1 < split.length; i1++)
            {
                integers.add(Integer.valueOf(split[i1]));
            }
        }
        return integers;
    }
}

class Task
{
    private int duration;
    private boolean hasDone;

    public Task(int duration)
    {
        this.duration = duration;
        this.hasDone = false;
    }

    public int getDuration()
    {
        return duration;
    }
}

class Job
{
    private boolean isWorking;
    private int startTimeAccess;
    private int term;
    private Task[] tasks;

    public Job(int startTimeAccess, Task[] tasks, int term)
    {
        this.startTimeAccess = startTimeAccess;
        this.tasks = tasks;
        this.term = term;
    }

    public int getStartTimeAccess()
    {
        return startTimeAccess;
    }

    public Task[] getTasks()
    {
        return tasks;
    }
}

class WorkStation
{
    private ArrayList<Integer> time;
    private boolean hasDone;
    private Job[] jobs;
    private int jobAdded = 0;

    public WorkStation(Job[] jobs)
    {
        this.time = new ArrayList<>();
        this.jobs = jobs;
        hasDone = false;
    }
}

class Factory
{
    private WorkStation[] workStations;
    private Job[] jobs;

    public Factory(int m, Job[] jobs)
    {
        this.jobs = jobs;
        this.workStations = new WorkStation[m];
        for (int i = 0; i < m; i++)
        {
            workStations[i] = new WorkStation(jobs);
        }
    }

    private boolean hasConflict(int pointA, int durationA, int pointB, int durationB)
    {
        if (pointA == pointB)
        {
            return true;
        }
        else if (pointA < pointB)
        {
            if (pointA + durationA > pointB)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (pointB + durationB > pointA)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public boolean check(int[][] result, int finalResult, Job[] jobs)
    {
        int max = -1;
        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[i].length; j++)
            {
                if (result[i][j] < jobs[i].getStartTimeAccess())
                {
                    return false;
                }
                if (max < result[i][j] + jobs[i].getTasks()[j].getDuration())
                {
                    max = result[i][j] + jobs[i].getTasks()[j].getDuration();
                }
                for (int k = j + 1; k < result[i].length; k++)
                {
                    if (hasConflict(result[i][j], jobs[i].getTasks()[j].getDuration(), result[i][k], jobs[i].getTasks()[k].getDuration()))
                    {
                        return false;
                    }
                }
                for (int k = i + 1; k < result.length; k++)
                {
                    if (hasConflict(result[i][j], jobs[i].getTasks()[j].getDuration(), result[k][j], jobs[k].getTasks()[j].getDuration()))
                    {
                        return false;
                    }
                }
            }
        }
        if (max != finalResult)
        {
            return false;
        }
        return true;
    }
}