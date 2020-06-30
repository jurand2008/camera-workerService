package projekt;

public class WorkerService {
    private int imagePxSize;
    private int numberOfWorkers;
    private double sliderValue;
    public WorkerService(int imgHeight, int imgWidth){
        this.imagePxSize = imgHeight*imgWidth;
    }
    public int getNumberOfWorkers(){
        numberOfWorkers = (int)Math.pow(4,sliderValue);
        return this.numberOfWorkers;
    }
    public int getLimit(){
        int limit = this.imagePxSize/numberOfWorkers;
        return limit+5;
    }
    public int getNumberOfPxAssignedToOneWorker(){
        int Pixels = this.imagePxSize/numberOfWorkers;
        return Pixels;
    }
    public void setSliderValue(double value){
        this.sliderValue = (int)value;
    }
}