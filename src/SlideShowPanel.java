
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dave on 2/23/16.
 */
public class SlideShowPanel extends JPanel {
    private JButton pictureLabel;
    private boolean slideShowClicked = false;
    private static int IMAGES_NUMBER = 48;
    private int currImgNumber = 1;

    public SlideShowPanel(){
        super(new BorderLayout());
        setOpaque(false);
        initPictureLabel();
        initSlideShow(10000);
    }

    private void initPictureLabel(){
        pictureLabel = new JButton(getRandomPicture());
        pictureLabel.addActionListener(e -> {
            slideShowClicked = true;
            changeImage();
        });
        this.add(pictureLabel, BorderLayout.CENTER);
    }

    /**
     * timer to transition to next picture
     * @param millis between pictures
     */
    private void initSlideShow(int millis){
        Timer timer = new Timer(millis, e -> {
            synchronized (this){
                if(!slideShowClicked){
                    changeImage();
                }
                slideShowClicked = false;
            }

        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();

    }

    /**
     * chooses a random image from SlideShowPictures dir
     * and sets it as pictureLabel
     */
    private void changeImage(){
        try{
            pictureLabel.setIcon(getRandomPicture());
        } catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * gets random image from SlideSlowPictures
     * @return StretchIcon
     * @throws NullPointerException
     */
    private StretchIcon getRandomPicture() throws NullPointerException{
        return new StretchIcon("SlideShowPictures/" + getRandomLoc() + ".jpg", false);
    }

    /**
     * gets random int in range of images
     * @return location
     */
    private int getRandomLoc(){
        int loc = (int) (Math.random() * IMAGES_NUMBER) + 1;
        if(loc==currImgNumber)
            return getRandomLoc();
        return loc;
    }


}
