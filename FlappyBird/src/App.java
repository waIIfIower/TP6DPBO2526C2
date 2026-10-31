import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("flappyBird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Logic logika = new Logic();
        View tampilan = new View(logika);
        logika.setView(tampilan);

        frame.add(tampilan);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // untuk meminta fokus keyborad
        tampilan.requestFocusInWindow();
    }
}