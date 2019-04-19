package projekt;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Controller {
    public Label lblTime;
    public ImageView imgID;
    public Button butStart;
    public Pane rgbChangePane;
    public Slider rSlider;
    public Slider gSlider;
    public Slider bSlider;
    public Button colorChange;
    public Label redValue;
    public Label greenValue;
    public Label blueValue;
    public Label workers;
    public Label workersPx;
    public Slider sliderWorkers;
    private boolean isStarted = true;
    private boolean timerCreated = false;
    private ScheduledExecutorService executor;
    private int FPS;
    private static VideoCapture Camera ;
    volatile private ArrayList filtrCase = new ArrayList();
    public Timeline timer;
    private WorkerService workerService=new WorkerService(640,480);


    @FXML
    protected void startCamera(javafx.event.ActionEvent actionEvent)
    {
        if (filtrCase.size()==0){
            for(int i=0;i<10;i++) {
                filtrCase.add(i,0);
            }
        }else{
            filtrCase.set(0,0);
        }
        if(!timerCreated) {
            createTimer();
            timerCreated = true;
        }

        if (isStarted) {
            executor = Executors.newScheduledThreadPool(1);
            butStart.setText("Stop Camera");
            isStarted=false;

            MatToImg MTI = new MatToImg();
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Camera = new VideoCapture(0);
            Mat cam_img = new Mat();
            System.out.println(cam_img);

            if (Camera.isOpened()) {
                final Runnable runnable;
                runnable = () -> {
                    long startTime = System.currentTimeMillis();

                    Camera.read(cam_img);

                    if (!cam_img.empty()) {
                        MTI.setMatrix(cam_img, ".jpg");
                        BufferedImage bufImg = MTI.GetBufferedImage();
                        BufferedImage dstImage = bufImg;

                        SplitAndDo split = new SplitAndDo(bufImg, dstImage, workerService.getLimit(), 0, 0, bufImg.getWidth(), bufImg.getHeight(),filtrCase);
                        ForkJoinPool pool = new ForkJoinPool();
                        pool.invoke(split);

                        Image image = SwingFXUtils.toFXImage(dstImage, null);
                        imgID.setImage(image);
                    }
                    long endTime = System.currentTimeMillis();
                    FPS = (int) (1000 / (endTime - startTime));
                };

                executor.scheduleWithFixedDelay(runnable, 0, 1, TimeUnit.NANOSECONDS);
            } else {
                Camera.release();
            }
        }else{
            executor.shutdown();
            butStart.setText("Start Camera");
            isStarted=true;
            Camera.release();

        }
    }

    @FXML
    private void original(javafx.event.ActionEvent actionEvent)
    {
        filtrCase.set(0,0);
        changeControlPanel();
    }
    @FXML
    protected void grayFilt(javafx.event.ActionEvent actionEvent)
    {
        filtrCase.set(0,1);
        changeControlPanel();
    }
    @FXML
    protected void mirrorHorizontal(javafx.event.ActionEvent actionEvent)
    {
        filtrCase.set(0,2);
        changeControlPanel();
    }
    @FXML
    protected void mirrorVertical(javafx.event.ActionEvent actionEvent)
    {
        filtrCase.set(0,3);
        changeControlPanel();
    }
    @FXML
    protected void colorChange(javafx.event.ActionEvent actionEvent)
    {
        filtrCase.set(0,4);
        filtrCase.set(1,rSlider.getValue());
        filtrCase.set(2,gSlider.getValue());
        filtrCase.set(3,bSlider.getValue());
        changeControlPanel();
    }
    @FXML
    protected void flipFilter(javafx.event.ActionEvent actionEvent)
    {

        filtrCase.set(0,5);
        changeControlPanel();
    }
    @FXML
    protected void Zoom(javafx.event.ActionEvent actionEvent)
    {

        filtrCase.set(0,6);
        changeControlPanel();
    }
    @FXML
    protected void test(javafx.event.ActionEvent actionEvent)
    {

        filtrCase.set(0,7);
        changeControlPanel();
    }

    @FXML
    protected void resetColorChangeSlider(javafx.event.ActionEvent actionEvent)
    {

        rSlider.setValue(1.0);
        gSlider.setValue(1.0);
        bSlider.setValue(1.0);
    }

    public void createTimer() {
        timer = new Timeline(
                new KeyFrame(Duration.millis(150),
                        evt ->
                        {
                            lblTime.setText("FPS : " + FPS);
                            workers.setText(String.valueOf(workerService.getNumberOfWorkers())+" workerów");
                            workersPx.setText(String.valueOf(workerService.getNumberOfPxAssignedToOneWorker())+" pikseli");
                            workerService.setSliderValue(sliderWorkers.getValue());
                            if((int)filtrCase.get(0)==4) {
                                filtrCase.set(1, rSlider.getValue());
                                filtrCase.set(2, gSlider.getValue());
                                filtrCase.set(3, bSlider.getValue());
                                redValue.setText(String.format("%,.2f", rSlider.getValue()));
                                greenValue.setText(String.format("%,.2f", gSlider.getValue()));
                                blueValue.setText(String.format("%,.2f", bSlider.getValue()));
                            }

                        }
                ));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }
    static  void closeCamera(){
        Camera.release();
    }
    void changeControlPanel(){
        int activeFilter = (int) filtrCase.get(0);
        switch(activeFilter){
            case 0:
                hideAllControlPanels();
                break;
            case 1:
                hideAllControlPanels();
                break;
            case 2:
                hideAllControlPanels();
                break;
            case 3:
                hideAllControlPanels();
                break;
            case 4:
                hideAllControlPanels();
                rgbChangePane.setVisible(true);
                break;
            case 5:
                hideAllControlPanels();
                break;
            case 6:
                hideAllControlPanels();
                break;
            case 7:
                hideAllControlPanels();
                break;

        }
    }
    void hideAllControlPanels(){
        rgbChangePane.setVisible(false);
    }



}


