//package mock1;
//
//import java.util.PriorityQueue;
//
//public class Req {
//    public int submitJob(int job_id){
//        // I need a priorityQueue to find the nearest machine in terms of capacity
//        try{
//            Job j = jobMap.get(job_id); // no job present in scheduler
//            PriorityQueue<Machines> pq = new PriorityQueue<>();
//            for(Machine m: machineMap.valSet()){ // empty hashmap no machines present
//                if (m.capacity >= j.capacity){
//                    pq.add(m);
//                }
//            }
//            sendMachine(pq.peek(), Job j); // pq is empty
//            return pq.peek().id;
//        } catch (Exception e){
//            throw new IllegalArgumentException(e);
//        }
//
//    }
//
//}
///**
// * Job - (transId, transaction, CPUCapicity, States - (Ready, Running, Done, Failed)) // dummy
// *  - execute() - excute transactions and commit in DB. // job is executable, thats important
// *  - statusFinished() // optional
// *
// * Scheduler - (Jobs, runs them) // What is the time table for each job
// *  // job_id, (currTime, runningTime)
// *  - Map<m_id, Machine> ()
// *  - Map<Integer, Integer> (job_id, currTime)
// *  - Map<Integer, Integer> (job_id, scheduledTime)
// *  - listAllJobs() - print all job Ids
// *  - addNewJob(job_id)
// *  - removeJob(job_id)
// *  - excutedJob(int job_id) - find the optimal machine which has enough cpu bandwith to execute.
// *  - findTime(int JobId)
// *
// * Machine(CPUCapacity, List<Jobs>) // provide information about machine status, job status and total capacity
// *  - Map<int, JobStatus>
// *  - Queue<Jobs> -
// *  - execJob() - traverse the queue and excute each job and update the job status - StatusDecorator
// *  - returnStatus(int job_id)
// *
// */
//
///**
// * Suggestions:
// *  - write a design to get a clear segregation of roles
// *  - regular Job, cron Job object
// *  - ask interviewer to find which entity to implement and the method.
// *  - final keywords to prevent updation of variables.
// *  - try to design everything before jumping into code
// *
// */