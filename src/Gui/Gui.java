package Gui;

import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by piter on 2017-02-27.
 */
public class Gui {

    private ImagePanel fretboardPanel;

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel chordSelector;
    private JPanel chordInfo;

    private JPanel chordNames;
    private JPanel chordsTypes;

    private JPanel chordNavigation;
    private JPanel info1;
    private JPanel info2;

    private JButton[] chordsName;
    private JButton[] types;

    private JButton next;
    private JButton previous;

    private JButton activeChord;
    private JButton activeType;

    public Gui() {

    /*    URL resource = getClass().getResource("/res/note.png");
        System.out.println(resource);
        BufferedImage image = null;
        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setIconImage(image);*/

        fretboardPanel = new ImagePanel("fretboard.jpg");

        frame = new JFrame("Chord Finder");
        mainPanel = new JPanel();
        chordSelector = new JPanel();
        chordInfo = new JPanel();
        chordNavigation = new JPanel();
        info1 = new JPanel();
        info2 = new JPanel();

        next = new JButton(">");
        previous = new JButton("<");

        chordsName = new JButton[12];
        types = new JButton[10];

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10, 10, 10, 10);

        mainPanel.add(fretboardPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 10, 10, 10);

        chordSelector.setPreferredSize(new Dimension((fretboardPanel.getWidth() - 10) / 2, fretboardPanel.getHeight()));
        chordSelector.setBackground(Color.BLACK);

        //chordSelector
        {
            chordNames = new JPanel();
            chordNames.setBackground(Color.GRAY);
            chordNames.setLayout(new GridLayout(2, 6));

            chordsName[0] = new JButton("C");
            chordsName[1] = new JButton("G");
            chordsName[2] = new JButton("D");
            chordsName[3] = new JButton("A");
            chordsName[4] = new JButton("E");
            chordsName[5] = new JButton("F");
            chordsName[6] = new JButton("B");
            chordsName[7] = new JButton("C#");
            chordsName[8] = new JButton("Eb");
            chordsName[9] = new JButton("F#");
            chordsName[10] = new JButton("Ab");
            chordsName[11] = new JButton("Bb");

            for (int i = 0; i < 12; i++) {
                chordsName[i].setBackground(Color.ORANGE);
                chordNames.add(chordsName[i]);
                chordsName[i].setFocusPainted(false);
            }

            chordsTypes = new JPanel();
            chordsTypes.setBackground(Color.GRAY);
            chordsTypes.setLayout(new GridLayout(2, 5));

            types[0] = new JButton("maj");
            types[1] = new JButton("min");
            types[2] = new JButton("5");
            types[3] = new JButton("sus2");
            types[4] = new JButton("sus4");
            types[5] = new JButton("7");
            types[6] = new JButton("7sus4");
            types[7] = new JButton("m7");
            types[8] = new JButton("Maj7");
            types[9] = new JButton("m7b5");

            for (int i = 0; i < 10; i++) {
                types[i].setBackground(new Color(0, 100, 200));
                types[i].setForeground(Color.WHITE);
                chordsTypes.add(types[i]);
                types[i].setFocusPainted(false);
            }

            chordSelector.setLayout(new GridLayout(2, 1));
            chordSelector.add(chordNames);
            chordSelector.add(chordsTypes);
        }

        mainPanel.add(chordSelector, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 10, 10);

        chordInfo.setPreferredSize(new Dimension((fretboardPanel.getWidth() - 10) / 2, fretboardPanel.getHeight()));
        chordInfo.setBackground(Color.GRAY);
        mainPanel.add(chordInfo, c);

        //chordInfo
        {
            chordNavigation.setLayout( new GridLayout(1,2));

            next.setBackground(Color.BLACK);
            previous.setBackground(Color.BLACK);
            next.setForeground(Color.ORANGE);
            previous.setForeground(Color.ORANGE);
            next.setFocusPainted(false);
            previous.setFocusPainted(false);

            chordNavigation.add(previous);
            chordNavigation.add(next);
            chordNavigation.setBackground(Color.GRAY);

            chordInfo.setLayout(new GridBagLayout());
            c = new GridBagConstraints();

            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.2;
            c.weighty = 0.25;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.insets = new Insets(0, 0, 8, 8);

            chordInfo.add(chordNavigation, c);

         //   c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.8;
            c.weighty = 0.25;
            c.gridwidth = 3;
            c.insets = new Insets(0, 0, 8, 0);

            chordInfo.add(info1, c);

         //   c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0.75;
            c.weighty = 1.0;
            c.gridwidth = 4;
            c.gridheight = 3;
            c.insets = new Insets(0, 0, 0, 0);

            chordInfo.add(info2, c);
        }

        mainPanel.setBackground(Color.GRAY);
        frame.add(mainPanel);

        setButtonActivated(chordsName[0], 0);
        setButtonActivated(types[0], 1);

        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setButtonActivated(JButton button, int type){

        if(type == 0){
            if(activeChord == null){
                activeChord = button;
                activeChord.setBackground(new Color(250,50,50));
            }
            else
                activeChord.setBackground(Color.ORANGE);
                activeChord = button;
                activeChord.setBackground(new Color(250,50,50));
        }
        else if(type == 1){
            if(activeType == null){
                activeType = button;
                activeType.setBackground(new Color(250, 50, 50));
            }
            else{
                activeType.setBackground(new Color(0, 100, 200));
                activeType = button;
                activeType.setBackground(new Color(250, 50, 50));
            }
        }
    }

    public void setSounds(int[][] positions){
        for(int i = 0; i < 6; i++) {

            if (positions[i][0] >= 1) {
                //minus 1, bo pozycje numeruje od 1
                fretboardPanel.addCircle(i, positions[i][0], positions[i][1]);
            }

            else if(positions[i][0] == -1)
                fretboardPanel.addCross(i);

           // else
            //    fretboardPanel.addEmpty(i, positions[i][0]);
        }
    }

    public void setVisible(boolean val) {
        frame.setVisible(val);
    }


    public void addChordListener(ActionListener listener){
        for(int i = 0 ; i < chordsName.length ; i++){
            chordsName[i].addActionListener(listener);
        }

        for(int i = 0; i < types.length; i++){
            types[i].addActionListener(listener);
        }
    }

    public void addNavigationListener(ActionListener listener){
        next.addActionListener(listener);
        previous.addActionListener(listener);
    }

    public void clearCircles(){
        fretboardPanel.clearCircles();
    }

}

class ImagePanel extends JPanel{

    private BufferedImage image;
    private int[] xoffsets;
    private int[] yoffsets;

    private ArrayList<Circle> circles = new ArrayList<>();
    private ArrayList<Circle> empty = new ArrayList<>();
    private int[] cross = new int[6];

    public ImagePanel(String fileName){

        setOffsets();

        try{

            image = ImageIO.read(getClass().getResourceAsStream("/res/" + fileName));

        } catch(IOException e){
            e.printStackTrace();
        }

        Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));

        this.setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void addCircle(int y, int x, int val){

        circles.add(new Circle(xoffsets[x],yoffsets[y],val));
        repaint();
    }

    public void addEmpty(int y, int x){

        circles.add(new Circle(xoffsets[x],yoffsets[y], 0));
        repaint();
    }

    public void addCross(int y){

        cross[y] = 1;
        repaint();
    }

    public void clearCircles(){
        circles.clear();
        empty.clear();

        for(int i = 0; i < 6; i++){
            cross[i] = 0;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);

        Font font = new Font("Arial", 1, 24);

        Graphics2D g2 = (Graphics2D) g;

        for(int i = 0 ; i < circles.size(); i++){
            g2.setColor(Color.BLACK);
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.fillOval((int)(circles.get(i).getCenterX() - 15),(int)( circles.get(i).getCenterY() - 15  ), 30, 30);

            g2.setColor(Color.ORANGE);
            g2.setFont(font);
            g2.drawString(Integer.toString((int)(circles.get(i).getRadius())), (int)(circles.get(i).getCenterX() - 6),(int)( circles.get(i).getCenterY() + 9  ));
        }

        for(int i = 0; i < 6; i++){
            if(cross[i] == 1){
                g2.setColor(new Color(250,70,70));

                for(int j = 0; j <= 15; j+= 3){
                    g2.setStroke(new BasicStroke(5));
                    g2.drawLine(xoffsets[j]-5, yoffsets[i] - 5, xoffsets[j]+5, yoffsets[i]+5);
                    g2.drawLine(xoffsets[j]-5, yoffsets[i] + 5, xoffsets[j]+5, yoffsets[i]-5);
                }
            }
        }

    }

    public int getWidth(){

        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }

    private void setOffsets() {

        xoffsets = new int[22];
        yoffsets = new int[6];

        xoffsets[0] = 7;
        xoffsets[1] = 45;
        xoffsets[2] = 122;
        xoffsets[3] = 192;
        xoffsets[4] = 258;
        xoffsets[5] = 324;
        xoffsets[6] = 380;
        xoffsets[7] = 438;
        xoffsets[8] = 492;
        xoffsets[9] = 542;
        xoffsets[10] = 589;
        xoffsets[11] = 634;
        xoffsets[12] = 676;
        xoffsets[13] = 716;
        xoffsets[14] = 753;
        xoffsets[15] = 788;


        yoffsets[0] = 274;
        yoffsets[1] = 225;
        yoffsets[2] = 175;
        yoffsets[3] = 126;
        yoffsets[4] = 77;
        yoffsets[5] = 27;
    }

}
