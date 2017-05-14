package Controller;

import Gui.Gui;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by piter on 2017-02-28.
 */
public class Controller {

    private String currentChord = "C";
    private String currentType = "maj";
    private int maxVersion = 0;

    private int currentVersion = 0;

    private Model model;
    private Gui gui;

    public Controller(Gui gui, Model model){
        this.model = model;
        this.gui = gui;

        this.gui.setVisible(true);

        gui.addChordListener(new GuiActionListener());
        gui.addNavigationListener(new NavigationListener());

        gui.clearCircles();

        FretboardSet fretboardSet = model.getChord(currentChord + " " + currentType);
        maxVersion = fretboardSet.chordPosition.size();
        System.out.println(maxVersion);
        currentVersion = 0;

        gui.setSounds(fretboardSet.chordPosition.get(0).getSet());
    }

    class NavigationListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clicked = null;

            if(e.getSource() instanceof  JButton){
                clicked = (JButton)e.getSource();
            }

            String clickedLabel = clicked.getText();

            if(clickedLabel == "<" && currentVersion > 0){
                gui.clearCircles();
                currentVersion--;
                FretboardSet fretboardSet = model.getChord(currentChord + " " + currentType);
                gui.setSounds(fretboardSet.chordPosition.get(currentVersion).getSet());
            }
            else if(clickedLabel == ">" && currentVersion < maxVersion-1){
                gui.clearCircles();
                currentVersion++;
                System.out.println(currentVersion);
                System.out.println(maxVersion);
                FretboardSet fretboardSet = model.getChord(currentChord + " " + currentType);
                gui.setSounds(fretboardSet.chordPosition.get(currentVersion).getSet());
            }
        }
    }

    class GuiActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clicked = null;

            if(e.getSource() instanceof  JButton){
                clicked = (JButton)e.getSource();
            }

            String clickedLabel = clicked.getText();

            if(clickedLabel.equals(currentChord) || clickedLabel.equals(currentType))
                return;

            if(clickedLabel.equals("C") || clickedLabel.equals("G") || clickedLabel.equals("D") ||
                    clickedLabel.equals("C") || clickedLabel.equals("A") || clickedLabel.equals("E") ||
                        clickedLabel.equals("F") || clickedLabel.equals("B") || clickedLabel.equals("C#") ||
                            clickedLabel.equals("Eb") || clickedLabel.equals("F#") || clickedLabel.equals("Ab") ||
                                clickedLabel.equals("Bb")){

                currentChord = clickedLabel;

                gui.setButtonActivated(clicked, 0);
            }
            else{
                currentType = clickedLabel;
                gui.setButtonActivated(clicked, 1);
            }

            gui.clearCircles();

            FretboardSet fretboardSet = model.getChord(currentChord + " " + currentType);
            maxVersion = fretboardSet.chordPosition.size();
            currentVersion = 0;

            gui.setSounds(fretboardSet.chordPosition.get(0).getSet());
        }
    }
}
