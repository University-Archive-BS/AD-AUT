import java.io.*;
import java.util.ArrayList;

public class WorkLoad
{
    public static void main(String[] args)
    {
        ArrayList<Integer> inputs = readFile("input.txt");

        int n = inputs.get(0); // number of jobs
        int m = inputs.get(1); // number of workStations

        Job[] jobs1 = new Job[n];
        Job[] jobs2 = new Job[n];
        Job[] jobs3 = new Job[n];

        for (int i = 0; i < n; i++)
        {
            int startTimeAccess = inputs.get(i * (m + 1) + 2);
            Task[] tasks1 = new Task[m];
            Task[] tasks2 = new Task[m];
            Task[] tasks3 = new Task[m];
            for (int j = 0; j < m; j++)
            {
                tasks1[j] = new Task(inputs.get(i * (m + 1) + j + 3));
                tasks2[j] = new Task(inputs.get(i * (m + 1) + j + 3));
                tasks3[j] = new Task(inputs.get(i * (m + 1) + j + 3));
            }
            jobs1[i] = new Job(startTimeAccess, tasks1, i + 1);
            jobs2[i] = new Job(startTimeAccess, tasks2, i + 1);
            jobs3[i] = new Job(startTimeAccess, tasks3, i + 1);
        }

        Job[][] sortedJob1 = new Job[3][n];
        Job[][] sortedJob2 = new Job[3][n];
        Job[][] sortedJob3 = new Job[3][n];
        for (int i = 0; i < m; i++)
        {
            sortedJob1[i] = Job.sortJob(jobs1, i);
            sortedJob2[i] = Job.sortJob(jobs2, i);
            sortedJob3[i] = Job.sortJob(jobs3, i);
        }

        Factory factory = new Factory(m, sortedJob1, 1);
        Factory factory2 = new Factory(m, sortedJob2, 2);
        Factory factory3 = new Factory(m, sortedJob3, 3);


        WorkStation[][] workStationsSet = new WorkStation[3][m];
        workStationsSet[0] = factory.process();
        workStationsSet[1] = factory2.process();
        workStationsSet[2] = factory3.process();

        int minRes = findResult(workStationsSet[0], n);
        int indexMin = 0;
        for (int i = 1; i < workStationsSet.length; i++)
        {
            int tmp = findResult(workStationsSet[i], n);
            if (tmp < minRes)
            {
                minRes = tmp;
                indexMin = i;
            }
        }
//        printResult(workStationsSet[indexMin], n);      // Show in console
        writeInFile("output.txt", workStationsSet[indexMin], n); // print in File
    }

    public static void writeInFile(String path, WorkStation[] workStations, int n)
    {
        try
        {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            int max = workStations[0].getTime().size();

            int[][] starts = new int[n][workStations.length];
            for (int i = 1; i < workStations.length; i++)
            {
                if (max < workStations[i].getTime().size())
                {
                    max = workStations[i].getTime().size();
                }
            }
            fileWriter.write(max + "");
            fileWriter.write("\n");

            for (int i = 0; i < workStations.length; i++)
            {
                int temp = -1;
                for (int j = 0; j < workStations[i].getTime().size(); j++)
                {
                    if (workStations[i].getTime().get(j) != -1)
                    {
                        if (temp != workStations[i].getTime().get(j))
                        {
                            temp = workStations[i].getTime().get(j);
                            starts[workStations[i].getTime().get(j) - 1][i] = j;
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < workStations.length - 1; j++)
                {
                    fileWriter.write(starts[i][j] + " ");
                }
                fileWriter.write(starts[i][workStations.length - 1] + "");

                fileWriter.write("\n");
            }

            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
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

    private static int findResult(WorkStation[] workStations, int n)
    {
        int max = workStations[0].getTime().size();
        int[][] starts = new int[n][workStations.length];
        for (int i = 1; i < workStations.length; i++)
        {
            if (max < workStations[i].getTime().size())
            {
                max = workStations[i].getTime().size();
            }
        }
//        System.out.println(max);
        return max;
    }

    private static void printResult(WorkStation[] workStations, int n)
    {
        int max = workStations[0].getTime().size();
        int[][] starts = new int[n][workStations.length];
        for (int i = 1; i < workStations.length; i++)
        {
            if (max < workStations[i].getTime().size())
            {
                max = workStations[i].getTime().size();
            }
        }
        System.out.println(max);

        for (int i = 0; i < workStations.length; i++)
        {
            int temp = -1;
            for (int j = 0; j < workStations[i].getTime().size(); j++)
            {
                if (workStations[i].getTime().get(j) != -1)
                {
                    if (temp != workStations[i].getTime().get(j))
                    {
                        temp = workStations[i].getTime().get(j);
                        starts[workStations[i].getTime().get(j) - 1][i] = j;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < workStations.length; j++)
            {
                System.out.print(starts[i][j] + " ");
            }
            System.out.println();
        }
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

    public boolean isHasDone()
    {
        return hasDone;
    }

    public void setHasDone(boolean hasDone)
    {
        this.hasDone = hasDone;
    }
}

class Job
{
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

    public int getTerm()
    {
        return term;
    }

    public static Job[] sortJob(Job[] inputJobs, int indexWorkStaion)
    {
        Job[] jobs = new Job[inputJobs.length];
        for (int i = 0; i < inputJobs.length; i++)
        {
            jobs[i] = inputJobs[i];
        }
        for (int i = 0; i < jobs.length; i++)
        {
            for (int j = i + 1; j < jobs.length; j++)
            {
                if (jobs[i].getStartTimeAccess() > jobs[j].getStartTimeAccess())
                {
                    Job temp = jobs[j];
                    jobs[j] = jobs[i];
                    jobs[i] = temp;
                }
                else if (jobs[i].getStartTimeAccess() == jobs[j].getStartTimeAccess())
                {
                    if (jobs[i].getTasks()[indexWorkStaion].getDuration() < jobs[j].getTasks()[indexWorkStaion].getDuration())
                    {
                        Job temp = jobs[j];
                        jobs[j] = jobs[i];
                        jobs[i] = temp;
                    }

                }
            }
        }
        return jobs;
    }

    public static Job[] sortJob2(Job[] jobs, int indexWorkStaion)
    {
        for (int i = 0; i < jobs.length; i++)
        {
            for (int j = i + 1; j < jobs.length; j++)
            {
                if (jobs[i].getTasks()[indexWorkStaion].getDuration() < jobs[j].getTasks()[indexWorkStaion].getDuration())
                {
                    Job temp = jobs[j];
                    jobs[j] = jobs[i];
                    jobs[i] = temp;
                }
                else if (jobs[i].getTasks()[indexWorkStaion].getDuration() == jobs[j].getTasks()[indexWorkStaion].getDuration())
                {
                    if (jobs[i].getStartTimeAccess() > jobs[j].getStartTimeAccess())
                    {
                        Job temp = jobs[j];
                        jobs[j] = jobs[i];
                        jobs[i] = temp;
                    }
                }
            }
        }
        return jobs;
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

    public void insertJob(int indexJob, int duration, int term)
    {
        for (int i = 0; i < duration; i++)
        {
            time.add(term);
        }
        if (term != -1)
        {
            jobAdded++;
            if (jobAdded == jobs.length)
            {
                hasDone = true;
            }
        }
    }

    public ArrayList<Integer> getTime()
    {
        return time;
    }

    public boolean isHasDone()
    {
        return hasDone;
    }

    public int getJobAdded()
    {
        return jobAdded;
    }
}

class Factory
{
    private WorkStation[] workStations;
    private Job[][] sortJobs;
    private int whichMethod;

    public Factory(int m, Job[][] jobs, int whichMethod)
    {
        this.sortJobs = jobs;
        this.whichMethod = whichMethod;
        this.workStations = new WorkStation[m];
        for (int i = 0; i < m; i++)
        {
            workStations[i] = new WorkStation(sortJobs[i]);
        }
    }

    public WorkStation[] process()
    {
        int counter = 0;
        while (counter <= sortJobs[0].length * workStations.length - 1)
        {
            int indexMin = 0;
            if (whichMethod == 1)
            {
                indexMin = findMin(workStations);
            }
            if (whichMethod == 2)
            {
                indexMin = findMin2(workStations);
            }
            if (whichMethod == 3)
            {
                indexMin = findMin3(workStations);
            }
            Job[] jobs = sortJobs[indexMin];
            int availableLastJob = availableJobs(workStations[indexMin], jobs);
            boolean noJob = true;
            for (int i = 0; i <= availableLastJob; i++)
            {
                if (!checkConflict(i, jobs, workStations, indexMin, workStations[indexMin].getTime().size()))
                {
                    workStations[indexMin].insertJob(i, jobs[i].getTasks()[indexMin].getDuration(), jobs[i].getTerm());
                    noJob = false;
                    jobs[i].getTasks()[indexMin].setHasDone(true);
                    counter++;
                    break;
                }
            }
            if (noJob)
            {
                workStations[indexMin].insertJob(-1, 1, -1);
            }
        }
        return workStations;
    }

    private boolean checkConflict(int jobIndex, Job[] jobs, WorkStation[] workStations, int workStationIndex, int time)
    {
        for (int i = 0; i < workStations.length; i++)
        {
            if (i == workStationIndex)
            {
                if (jobs[jobIndex].getTasks()[workStationIndex].isHasDone())
                {
                    return true;
                }
            }
            else
            {
                if (workStations[i].getTime().size() > time)
                {
                    int duration = jobs[jobIndex].getTasks()[workStationIndex].getDuration();
                    for (int i1 = 0; workStations[i].getTime().size() > i1 + time && i1 < duration; i1++)
                    {
                        if (workStations[i].getTime().get(time + i1) == jobs[jobIndex].getTerm())
                        {
                            return true;
                        }
                    }
                }

            }
        }

        return false;
    }

    private int availableJobs(WorkStation workStation, Job[] jobs)
    {
        for (int i = 0; i < jobs.length; i++)
        {
            if (workStation.getTime().size() < jobs[i].getStartTimeAccess())
            {
                return i - 1;
            }
        }
        return jobs.length - 1;
    }

    private int findMin(WorkStation[] workStations)
    {
        int min = workStations[0].getTime().size();
        int indexMin = 0;
        for (int i = 1; i < workStations.length; i++)
        {
            int temp = workStations[i].getTime().size();
            if (min > temp && !workStations[i].isHasDone())
            {
                indexMin = i;
                min = temp;
            }
        }

        return indexMin;
    }

    private int findMin2(WorkStation[] workStations)
    {
        int min = workStations[0].getJobAdded();
        int indexMin = 0;
        for (int i = 1; i < workStations.length; i++)
        {
            int temp = workStations[i].getJobAdded();
            if (min > temp && !workStations[i].isHasDone())
            {
                indexMin = i;
                min = temp;
            }
        }

        return indexMin;
    }

    private int findMin3(WorkStation[] workStations)
    {
        int i = 0;
        while (workStations[i].isHasDone())
        {
            i++;
        }

        return i;
    }
}