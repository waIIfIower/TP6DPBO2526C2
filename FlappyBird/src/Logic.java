import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    private int framewidth = 360;
    private int frameheight = 640;

    private int playerStartPosX = framewidth / 2;
    private int playerStartPosY = frameheight / 2;
    private int playerWidth = 34;
    private int playerheight = 24;

    private int pipeStartPosX = framewidth;
    private int pipeStartPosY = 0;
    private int pipeWidth = 64;
    private int pipeHeight = 512;

    private Timer gameloop;
    private Timer pipesCooldown;
    private int gravity = 1;
    private int pipeVelocityX = -2;

    private View view;
    private Image birdImage;
    private Player player;

    private Image lowerPipeImage;
    private Image upperPipeImage;
    private ArrayList<Pipe> pipes;

    // Modul Pengelola Audio
    private Sound sound;

    private boolean gameStarted = false;
    private boolean gameOver = false;
    private double score = 0;

    public Logic() {

        sound = new Sound();

        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerheight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (gameStarted && !gameOver) {
                    placePipes();
                }
            }
        });

        gameloop = new Timer(1000 / 60, this);
    }

    public void startGame() {
        this.gameStarted = true;
        this.gameOver = false;
        this.pipesCooldown.start();
        this.gameloop.start();

        // Putar BGM saat permainan dimulai
        sound.playBGM("assets/bgm.wav");
    }

    public void restartGame() {
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        score = 0;
        gameOver = false;

        gameloop.start();
        pipesCooldown.start();

        // ngsetop sound efek game over kalomasi jalan
        sound.stopSE();
        // Putar ulang BGM
        sound.playBGM("assets/bgm.wav");
    }

    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return (int) score;
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - (pipeHeight / 4) - Math.random() * (pipeHeight / 2));
        int openingSpace = frameheight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public boolean checkCollision(Player a, Pipe b) {
        return a.getPosX() < b.getPosX() + b.getWidth() &&
                a.getPosX() + a.getWidth() > b.getPosX() &&
                a.getPosY() < b.getPosY() + b.getHeight() &&
                a.getPosY() + a.getHeight() > b.getPosY();
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        if (player.getPosY() + player.getHeight() >= frameheight) {
            triggerGameOver();
        }

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            //bagian buat deteksi nambah skor pas ngeelwatin ujung pipe
            if (!pipe.isPassed() && player.getPosX() > pipe.getPosX() + pipe.getWidth()) {
                pipe.setPassed(true);
                score += 0.5;

                //sfx buat poin
                if (score % 1 == 0) {
                    sound.playSE("assets/score.wav");
                }
            }

            if (checkCollision(player, pipe)) {
                triggerGameOver();
            }
        }
    }

    private void triggerGameOver() {
        gameOver = true;
        gameloop.stop();
        pipesCooldown.stop();

        //game over stop bgm terus kasi sfx nabrak
        sound.stopBGM();
        sound.playSE("assets/hit.wav");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStarted && !gameOver) {
            move();
        }
        if (view != null) {
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameStarted && !gameOver) {
            player.setVelocityY(-9);

            //sfx buat tiap lompat
            sound.playSE("assets/wing.wav");
        }

        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}