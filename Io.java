/**
 * Created by Anders on 14.04.2015.
 */
public class Io {
    private Queue ioQueue;
    private Statistics statistics;
    private long avgIoTime;
    private Simulator simulator;
    private Cpu cpu;

    public Io(Queue ioQueue, long avgIoTime, Statistics statistics, Simulator simulator){
        this.ioQueue = ioQueue;
        this.statistics = statistics;
        this.avgIoTime = avgIoTime;
        this.simulator = simulator;
    }

    public void addCpu(Cpu cpu){
        this.cpu = cpu;
    }

    public void use_io(){
        // Process process = (Process) ioQueue.getNext();
        simulator.queueEvent(Constants.END_IO, avgIoTime);
    }

    public void completed_io(){
        Process process = (Process) ioQueue.removeNext();
        cpu.insertProcess(process);
        simulator.queueEvent(Constants.SWITCH_PROCESS,avgIoTime);

    }

    public void insertProcess(Process p) {
        ioQueue.insert(p);
    }
}
