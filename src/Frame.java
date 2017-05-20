
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by dave on 2/23/16.
 *
 * main frame for application. Container for all main buttons
 */
public class Frame extends JFrame{
    private Font titleFont;

    /**
     * init
     */
    public Frame() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(482,590));
        init();
        pack();
        setVisible(true);
    }

    /**
     * adds panels and buttons to this frame
     */
    private void init(){
        Font standardFont = new Font("Helvetica",Font.PLAIN,14);
        UIManager.put("Button.titleFont", standardFont);
        UIManager.put("Label.titleFont", standardFont);
        titleFont = new Font(standardFont.getName(), Font.BOLD, standardFont.getSize() + 10);

        //Slideshow panel
        this.add(new SlideShowPanel(),BorderLayout.CENTER);

        //north panel
        JPanel northPanel = new JPanel(new GridLayout(1,3));
        JLabel title = new JLabel("SARAH IS THE BEST SISTER IN THE WORLD!! (click imgs)");
        title.setFont(standardFont);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setOpaque(false);
        northPanel.add(title);
        this.add(northPanel,BorderLayout.NORTH);


        //adjectives Panel
        AdjectivesButton adjButton = new AdjectivesButton("press me!");
        adjButton.addActionListener(e -> {
            if(!adjButton.hasBeenPressed()){
                adjButton.setFontPlain();
            }
            adjButton.setText(adjButton.getRandomAdjective());
        });
        JLabel adjLabel = new JLabel("You are ");
        adjLabel.setFont(titleFont);
        adjLabel.setOpaque(false);
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(adjLabel);
        bottomPanel.add(adjButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }


}
