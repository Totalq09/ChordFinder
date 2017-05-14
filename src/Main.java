import Controller.Controller;
import Gui.*;
import Model.*;

public class Main {

    public static void main(String[] args){

        Model model = new Model();
        Gui gui = new Gui();

        Controller controller = new Controller(gui, model);

    }
}
