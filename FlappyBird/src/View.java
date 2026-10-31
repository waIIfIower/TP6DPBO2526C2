import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private int width = 360;
    private int height = 640;

    private Logic logic;
    private JButton btnStart;
    private JButton btnExit;

    public View(Logic logic) {
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);
        setLayout(null);

        setFocusable(true);
        addKeyListener(logic);


        initMainMenu();
    }

    // menu biar ga langsung mulai soalnya kaget
    private void initMainMenu() {
        btnStart = new JButton("Start");
        btnStart.setBounds(110, 250, 140, 40);
        btnStart.setFocusable(false);
        btnStart.addActionListener(e -> {
            btnStart.setVisible(false);
            btnExit.setVisible(false);
            logic.startGame();
            requestFocusInWindow();
        });

        btnExit = new JButton("Exit");
        btnExit.setBounds(110, 310, 140, 40);
        btnExit.setFocusable(false);
        btnExit.addActionListener(e -> System.exit(0));

        add(btnStart);
        add(btnExit);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        }


        if (logic.getPipes() != null) {
            for (Pipe pipe : logic.getPipes()) {
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // bagian namoilin skor
        // times new roman karena kedengaran elegan seoerti gua
        g.setColor(Color.yellow);
        g.setFont(new Font("Times New Roman", Font.BOLD, 28));
        if (logic.isGameStarted()) {
            g.drawString("Score: " + logic.getScore(), 20, 40);
        }

        //bagian game over
        if (logic.isGameOver()) {
            g.setColor(Color.red);
            g.setFont(new Font("Times New Roman", Font.BOLD, 36));
            g.drawString("GAME OVER", 70, 280);

            //restart
            g.setColor(Color.black);
            g.setFont(new Font("Times New Roman", Font.BOLD, 18));
            g.drawString("Press 'R' to Restart", 100, 320);
        }
    }
}