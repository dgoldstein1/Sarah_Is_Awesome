
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by dave on 2/25/16.
 *
 * Label that displays random adjective that reminds me of Jolena
 * adjectives are read from hardcoded file "adjList"
 *
 */
public class AdjectivesButton extends JButton {
    private ArrayList<String> adjList;
    private boolean hasBeenPressed;

    public AdjectivesButton(String initalText) {
        super(initalText);
        this.setFont(new Font("Helvetica",Font.BOLD,16));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setOpaque(false);
        hasBeenPressed=false;

        //init adj list
        adjList = new ArrayList<>(500000);
        String filename = "/Data/adjList";
        try{
            InputStream is = getClass().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader((new InputStreamReader(is,"UTF-8")));
            String word="";
            System.out.print("Loading " + filename + " ...");
            while(word!=null) {
                word = br.readLine();
                if(word!=null && word.length() > 2){//no blank lines
                    adjList.add(word);
                }
            }
            System.out.println("done");
        } catch(NullPointerException e){
            System.out.println("could not find file: " + filename);
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){
            System.out.println("could not encode " + filename);
            e.printStackTrace();
        } catch(IOException e){
            System.out.println("IO exception");
            e.printStackTrace();
        }

    }

    /**
     * gets random adjective from list
     * @return adjective, except for current showing
     */
    public String getRandomAdjective(){
        hasBeenPressed=true;
        if(adjList.size() < 2){ //must be more than one adj in list
            throw new IndexOutOfBoundsException("Could not select new label");
        }
        int loc = (int) (Math.random() * adjList.size());
        if(this.getText().equals(adjList.get(loc))){ //cannot be current adj
            return getRandomAdjective();
        }
        return adjList.get(loc);
    }

    /**
     * has this button been pressed?
     * @return
     */
    public boolean hasBeenPressed(){
        return hasBeenPressed;
    }

    /**
     * sets font style as plain
     */
    public void setFontPlain(){
        Font f = new Font(this.getFont().getName(),
                Font.PLAIN,
                this.getFont().getSize());
        this.setFont(f);
    }

}
