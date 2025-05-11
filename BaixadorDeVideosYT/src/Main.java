import view.MainFrame;
import controller.MainController;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        new MainController(frame);
        frame.setVisible(true);
    }
}
