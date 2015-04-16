/**
 * Created by Anders on 14.04.2015.
 */
public class Cpu {
    private Queue cpuQueue;
    private Statistics statistics;
    private long maxCpuTime;
    private Simulator simulator;
    private Io io;

    public Cpu(Queue cpuQueue, long maxCpuTime, Statistics statistics, Simulator simulator, Io io){
        this.cpuQueue = cpuQueue;
        this.statistics = statistics;
        this.maxCpuTime = maxCpuTime;
        this.simulator = simulator;
        this.io = io;
    }

    public void insertProcess(Process p) {
        cpuQueue.insert(p);
    }

    public long getMaxCpuTime() {
        return maxCpuTime;
    }

    public void use_cpu(){
        if (cpuQueue.isEmpty()){
            return;
        }
        Process process = (Process) cpuQueue.getNext();
        simulator.activeCPU(process);
        System.out.println(process);
        long time = 0;
        // Case, it gets finished -> free memory
        if (process.getCpuTimeNeeded() <= maxCpuTime && process.getTimeToNextIoOperation() >= process.getCpuTimeNeeded()){
            System.out.println("case1");
            simulator.queueEvent(Constants.END_PROCESS,process.getCpuTimeNeeded());
            process.setCpuTimeNeeded(0);
        }
        // Case, it gets interrupted (io) -> IO queue
        else if (process.getCpuTimeNeeded() > process.getTimeToNextIoOperation() && process.getTimeToNextIoOperation() <  maxCpuTime){
            System.out.println("case2");
            simulator.queueEvent(Constants.IO_REQUEST, process.getTimeToNextIoOperation());
            System.out.println("new time should be" + process.getTimeToNextIoOperation() + " " + process.getCpuTimeNeeded());
            process.setCpuTimeNeeded(process.getCpuTimeNeeded() - process.getTimeToNextIoOperation());
            process.generateNewIotime();
            io.insertProcess((Process) cpuQueue.removeNext());
        }
        // Case, doesn't get finished (interrupted by Round Robin processing time) -> back in queue
        else if (process.getCpuTimeNeeded() > maxCpuTime && process.getTimeToNextIoOperation() > maxCpuTime){
            System.out.println("case3");
            simulator.queueEvent(Constants.SWITCH_PROCESS, maxCpuTime);
            process.setCpuTimeNeeded(process.getCpuTimeNeeded() - maxCpuTime);
            process.setTimeToNextIoOperation(process.getTimeToNextIoOperation() - maxCpuTime);
            cpuQueue.insert(cpuQueue.removeNext());

        }
    }
    public void remove_process(){
        System.out.println("FINISHED A PROCESS");
        if(cpuQueue.isEmpty()){
            return;
        }
        simulator.releaseMemory((Process) cpuQueue.removeNext());
    }

    public Process checkCpu() {
        if(!cpuQueue.isEmpty()) {
            return (Process)cpuQueue.getNext();
        }
        return null;
    }
}


