import java.io.*;
import java.util.ArrayList;

public class WorkLoad
{


    private static final int JOB_SORT_WAYS = 12;
    private static final int WORKSTATION_SORT_WAYS = 8;


    public static void main(String[] args)
    {
        ArrayList<Integer> inputs = readFile("input.txt");

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

        int min = -1;
        WorkStation[] resultWorkStation = null;
        for (int i = 0; i < JOB_SORT_WAYS; i++)
        {
            WorkStation[][] workStationsSet = findResults(i + 1, jobs, m, n);

            int minRes = findResult(workStationsSet[0], n);
            int indexMin = 0;
            for (int j = 1; j < workStationsSet.length; j++)
            {
                int tmp = findResult(workStationsSet[j], n);
                if (tmp < minRes)
                {
                    minRes = tmp;
                    indexMin = j;
                }
            }

            if (minRes < min || min == -1)
            {
                min = minRes;
                resultWorkStation = workStationsSet[indexMin];
            }

        }

        printResult(resultWorkStation, n);      // Show in console
        writeInFile("output.txt", resultWorkStation, n); // print in File
    }

    private static Job[] resetJob(Job[] jobs)
    {
        for (int i = 0; i < jobs.length; i++)
        {
            for (int i1 = 0; i1 < jobs[i].getTasks().length; i1++)
            {
                jobs[i].getTasks()[i1].setHasDone(false);
            }
        }
        return jobs;
    }

    private static WorkStation[][] findResults(int indexSort, Job[] jobs, int m, int n)
    {

        Job[] jobs1 = jobs.clone();

        Job[][] sortedJob1 = new Job[m][n];

        for (int i = 0; i < m; i++)
        {
            if (indexSort == 1)
            {
                sortedJob1[i] = Job.sortJob1(jobs1, i);
            }
            if (indexSort == 2)
            {
                sortedJob1[i] = Job.sortJob2(jobs1, i);
            }
            if (indexSort == 3)
            {
                sortedJob1[i] = Job.sortJob3(jobs1, i);
            }
            if (indexSort == 4)
            {
                sortedJob1[i] = Job.sortJob4(jobs1, i);
            }
            if (indexSort == 5)
            {
                sortedJob1[i] = Job.sortJob5(jobs1, i);
            }
            if (indexSort == 6)
            {
                sortedJob1[i] = Job.sortJob6(jobs1, i);
            }
            if (indexSort == 7)
            {
                sortedJob1[i] = Job.sortJob7(jobs1, i);
            }
            if (indexSort == 8)
            {
                sortedJob1[i] = Job.sortJob8(jobs1, i);
            }
            if (indexSort == 9)
            {
                sortedJob1[i] = Job.sortJob9(jobs1, i);
            }
            if (indexSort == 10)
            {
                sortedJob1[i] = Job.sortJob10(jobs1, i);
            }
            if (indexSort == 11)
            {
                sortedJob1[i] = Job.sortJob11(jobs1, i);
            }
            if (indexSort == 12)
            {
                sortedJob1[i] = Job.sortJob12(jobs1, i);
            }
        }
        WorkStation[][] workStationsSet = new WorkStation[WORKSTATION_SORT_WAYS][m];

        Factory factory1 = new Factory(m, sortedJob1, 1);

        workStationsSet[0] = factory1.proccess();
        resetJob(sortedJob1[0]);
        Factory factory2 = new Factory(m, sortedJob1, 2);

        workStationsSet[1] = factory2.proccess();
        resetJob(sortedJob1[0]);

        Factory factory3 = new Factory(m, sortedJob1, 3);

        workStationsSet[2] = factory3.proccess();
        resetJob(sortedJob1[0]);


        Factory factory4 = new Factory(m, sortedJob1, 4);

        workStationsSet[3] = factory4.proccess();
        resetJob(sortedJob1[0]);
        Factory factory5 = new Factory(m, sortedJob1, 5);

        workStationsSet[4] = factory5.proccess();
        resetJob(sortedJob1[0]);

        Factory factory6 = new Factory(m, sortedJob1, 6);

        workStationsSet[5] = factory6.proccess();
        resetJob(sortedJob1[0]);

        Factory factory7 = new Factory(m, sortedJob1, 7);

        workStationsSet[6] = factory7.proccess();
        resetJob(sortedJob1[0]);

        Factory factory8 = new Factory(m, sortedJob1, 8);

        workStationsSet[7] = factory8.proccess();
        resetJob(sortedJob1[0]);

        return workStationsSet;
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

    public static Job[] sortJob1(Job[] inputJobs, int indexWorkStaion)
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

    public static Job[] sortJob2(Job[] inputJobs, int indexWorkStaion)
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
                    if (jobs[i].getTasks()[indexWorkStaion].getDuration() > jobs[j].getTasks()[indexWorkStaion].getDuration())
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

    public static Job[] sortJob3(Job[] jobs, int indexWorkStaion)
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

    public static Job[] sortJob4(Job[] jobs, int indexWorkStaion)
    {
        return jobs;
    }


    public static Job[] sortJob5(Job[] jobs, int indexWorkStaion)
    {
        for (int i = 0; i < jobs.length; i++)
        {
            for (int j = i + 1; j < jobs.length; j++)
            {
                if (jobs[i].getTasks()[indexWorkStaion].getDuration() > jobs[j].getTasks()[indexWorkStaion].getDuration())
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


    public static Job[] sortJob6(Job[] inputJobs, int indexWorkStaion)
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
                if (jobs[i].getStartTimeAccess() < jobs[j].getStartTimeAccess())
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

    public static Job[] sortJob7(Job[] inputJobs, int indexWorkStaion)
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
                if (jobs[i].getStartTimeAccess() < jobs[j].getStartTimeAccess())
                {
                    Job temp = jobs[j];
                    jobs[j] = jobs[i];
                    jobs[i] = temp;
                }
                else if (jobs[i].getStartTimeAccess() == jobs[j].getStartTimeAccess())
                {
                    if (jobs[i].getTasks()[indexWorkStaion].getDuration() > jobs[j].getTasks()[indexWorkStaion].getDuration())
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


    public static Job[] sortJob8(Job[] jobs, int indexWorkStaion)
    {
        Job[] res = new Job[jobs.length];
        for (int i = 0; i < jobs.length; i++)
        {
            res[jobs.length - 1 - i] = jobs[i];
        }
        return res;
    }


    public static Job[] sortJob9(Job[] inputJobs, int indexWorkStaion)
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
                if (jobs[i].getStartTimeAccess() < jobs[j].getStartTimeAccess())
                {
                    Job temp = jobs[j];
                    jobs[j] = jobs[i];
                    jobs[i] = temp;
                }

            }
        }
        return jobs;
    }


    public static Job[] sortJob10(Job[] inputJobs, int indexWorkStaion)
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

            }
        }
        return jobs;
    }


    public static Job[] sortJob11(Job[] jobs, int indexWorkStaion)
    {
        for (int i = 0; i < jobs.length; i++)
        {
            for (int j = i + 1; j < jobs.length; j++)
            {
                if (jobs[i].getTasks()[indexWorkStaion].getDuration() > jobs[j].getTasks()[indexWorkStaion].getDuration())
                {
                    Job temp = jobs[j];
                    jobs[j] = jobs[i];
                    jobs[i] = temp;
                }
            }
        }
        return jobs;
    }

    public static Job[] sortJob12(Job[] jobs, int indexWorkStaion)
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

    public void insertJob(int duration, int term)
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
    private int witchMehode;

    public Factory(int m, Job[][] jobs, int witchMehode)
    {
        this.sortJobs = jobs;
        this.witchMehode = witchMehode;
        this.workStations = new WorkStation[m];
        for (int i = 0; i < m; i++)
        {
            workStations[i] = new WorkStation(sortJobs[i]);
        }
    }

    public WorkStation[] proccess()
    {

        int skipcounter = 0;
        int counter = 0;
        while (counter <= sortJobs[0].length * workStations.length - 1)
        {
            int indexMin = 0;
            if (witchMehode == 1)
            {
                indexMin = findMin(workStations);
            }
            if (witchMehode == 2)
            {
                indexMin = findMin2(workStations);
            }
            if (witchMehode == 3)
            {
                indexMin = findMin3(workStations);
            }
            if (witchMehode == 4)
            {
                indexMin = findMin4(workStations);
            }
            if (witchMehode == 5)
            {
                indexMin = findMin5(workStations);
            }
            if (witchMehode == 6)
            {
                indexMin = findMin6(workStations);
            }
            if (witchMehode == 7)
            {
                indexMin = findMin7(workStations);
            }
            if (witchMehode == 8)
            {
                indexMin = findMin8(workStations);
            }
            Job[] jobs = sortJobs[indexMin];
            ArrayList<Job> availableJobs = availbleJobs(workStations[indexMin], jobs);
            boolean noJob = true;
            for (int i = 0; i < availableJobs.size(); i++)
            {
                if (!checkConflict(availableJobs.get(i), workStations, indexMin, workStations[indexMin].getTime().size()))
                {
                    workStations[indexMin].insertJob(availableJobs.get(i).getTasks()[indexMin].getDuration(), availableJobs.get(i).getTerm());
                    noJob = false;
                    availableJobs.get(i).getTasks()[indexMin].setHasDone(true);
                    counter++;
                    break;
                }
            }
            if (noJob)
            {
                if (witchMehode == 5 || witchMehode == 6 || witchMehode == 9 || witchMehode == 10)
                {
                    skipcounter++;
                    if (skipcounter == 2)
                    {
                        skipcounter = 0;
                        workStations[indexMin].insertJob(1, -1);
                    }
                }
                else
                {
                    workStations[indexMin].insertJob(1, -1);
                }

            }

        }


        return workStations;
    }


    private boolean checkConflict(Job job, WorkStation[] workStations, int workStationIndex, int time)
    {
        for (int i = 0; i < workStations.length; i++)
        {
            if (i == workStationIndex)
            {
                if (job.getTasks()[workStationIndex].isHasDone())
                {
                    return true;
                }
            }
            else
            {
                if (workStations[i].getTime().size() > time)
                {
                    int duration = job.getTasks()[workStationIndex].getDuration();
                    for (int i1 = 0; workStations[i].getTime().size() > i1 + time && i1 < duration; i1++)
                    {
                        if (workStations[i].getTime().get(time + i1) == job.getTerm())
                        {
                            return true;
                        }
                    }
                }

            }
        }

        return false;
    }

    private ArrayList<Job> availbleJobs(WorkStation workStation, Job[] jobs)
    {
        ArrayList<Job> jobs1 = new ArrayList<>();
        for (int i = 0; i < jobs.length; i++)
        {
            if (workStation.getTime().size() >= jobs[i].getStartTimeAccess())
            {
                jobs1.add(jobs[i]);
            }
        }
        return jobs1;
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

    private int findMin4(WorkStation[] workStations)
    {
        int i = workStations.length - 1;
        while (workStations[i].isHasDone())
        {
            i--;
        }

        return i;
    }

    static int c = -1;

    private int findMin5(WorkStation[] workStations)
    {
        c++;
        return c % workStations.length;
    }

    static int c2 = -1;

    private int findMin6(WorkStation[] workStations)
    {
        c2++;
        int temp = workStations.length - 1 - (c2 % workStations.length);
        return temp;
    }

    private int findMin7(WorkStation[] workStations)
    {
        int i = 0;
        if (workStations[0].isHasDone())
        {
            i = findMin(workStations);
        }
        return i;
    }

    private int findMin8(WorkStation[] workStations)
    {
        int i = 0;
        if (workStations[0].isHasDone())
        {
            i = findMin2(workStations);
        }
        return i;
    }

}