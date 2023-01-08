package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class GameView extends Application {
    private static final Random random = new Random();
    private static final int Player_size = 60;

    static final Image playerImage = new Image("https://clck.ru/33DG3q");
    static final Image explosion = new Image("https://clck.ru/33DG62");
    private GraphicsContext gc;
    private static final int Witdh = 800;
    private static final int Height = 600;
    static final int Explosion_W = 128;
    static final int Explosion_Rows = 3;
    static final int Explosion_Col = 3;
    static final int Explosion_H = 128;
    static final int Explosion_Steps = 15;


    static final Image bombsImage[] = {
            new Image("https://clck.ru/33DGAF"),
            new Image("https://clck.ru/33DGAF"),
            new Image("https://clck.ru/33DGAF"),
            new Image("https://clck.ru/33DGAF"),
            new Image("https://clck.ru/33DGAF"),
            new Image("https://clck.ru/33DGAF"),
            new Image("https://clck.ru/33DGAF"),
            new Image("https://clck.ru/33DGAF"),
            new Image("https://clck.ru/33DGAF"),
            new Image("https://clck.ru/33DGAF"),

    };
    final int maxbombs = 10, max_shots = maxbombs * 2;
    boolean gameOver = false;
    Rocket player;
    List<Shot> shots;
    List<Universe> univ;
    List<Bomb> Bombs;
    private double mouseX;
    private int score;
    private Stage menuStage;


    @Override
    public void start(Stage prefstage) throws Exception {
        Canvas canvas = new Canvas(Witdh,Height);
        gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),e ->run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(e -> mouseX = e.getX());
        canvas.setOnMouseClicked(e -> {
            if (shots.size() < max_shots) {
                shots.add(player.shoot());
                if (gameOver) {
                    gameOver = false;
                    setup();
                }
            }
        });
        setup();
        prefstage.setScene(new Scene(new StackPane(canvas)));
        prefstage.show();

    }

    private void setup() {
        univ = new ArrayList<>();
        shots = new ArrayList<>();
        Bombs = new ArrayList<>();
        player = new Rocket(Witdh / 2, Height - Player_size,Player_size,playerImage);
        score = 0;
        IntStream.range(0,maxbombs).mapToObj(i -> this.newBomb()).forEach(Bombs::add);
    }
    private void run(GraphicsContext gc) {
        this.gc.setFill(Color.grayRgb(20));
        this.gc.fillRect(0, 0, Witdh, Height);
        this.gc.setTextAlign(TextAlignment.CENTER);
        this.gc.setFont(Font.font(20));
        this.gc.setFill(Color.WHITE);
        this.gc.fillText("Score" + score, 60, 20);
        if (gameOver) {
            this.gc.setFont(Font.font(35));
            this.gc.setFill(Color.YELLOW);
            this.gc.fillText("Game over \n Your Score is:" + score + "\n Click to play again", Witdh / 2, Height / 2.5);
        }
        univ.forEach(Universe::draw);
        player.update();
        player.draw();
        player.posX = (int) mouseX;
        Bombs.stream().peek(Rocket::update).peek(Rocket::draw).forEach(e -> {
            if (player.colide(e) && player.exploding) {
                player.explode();
            }
        });

        for (int i = shots.size() - 1; i >= 0; i--) {
            Shot shot = shots.get(i);
            if (shot.posY < 0 || shot.toRemove) {
                shots.remove(i);
                continue;
            }
            shot.update();
            shot.draw();
            for (Bomb bomb : Bombs) {
                if (shot.colide(bomb) && !bomb.exploding) {
                    score++;
                    bomb.explode();
                    shot.toRemove = true;

                }
            }
        }
        for (int i = Bombs.size() - 1; i >= 0; i--) {
            if (Bombs.get(i).destroyed) {
                Bombs.set(i, newBomb());

            }
        }
        gameOver = player.destroyed;
        if (random.nextInt(10) > 2) {
            univ.add(new Universe());
        }
        for (int j = 0; j < univ.size(); j++) {
            if (univ.get(j).posX > Height) {
                univ.remove(j);
            }
        }
    }

    //player
    public class Rocket {
        int posX, posY, size;
        boolean exploding, destroyed;
        Image img;
        int explosionStep = 0;

        public Rocket(int posX, int posY, int size,  Image image) {
            this.posX = posX;
            this.posY = posY;
            this.size = size;
            img = image;
        }

        public Shot shoot() {
            return new Shot(posX + size / 2 - Shot.size / 2, posY - Shot.size);
        }

        public void update() {
            if(exploding) explosionStep++;
            destroyed = explosionStep > Explosion_Steps;
        }

        public void draw() {
            if(exploding) {
                gc.drawImage(explosion, explosionStep % Explosion_Col * Explosion_W, (explosionStep / Explosion_Rows) * Explosion_H + 1,
                        Explosion_W, Explosion_H,
                        posX, posY, size, size);
            }
            else {
                gc.drawImage(img, posX, posY, size, size);
            }
        }

        public boolean colide(Rocket other) {
            int d = distance(this.posX + size / 2, this.posY + size /2,
                    other.posX + other.size / 2, other.posY + other.size / 2);
            return d < other.size / 2 + this.size / 2 ;
        }

        public void explode() {
            exploding = true;
            explosionStep = -1;
        }

    }

    public class Bomb extends Rocket {
        int Speed = (score / 5) + 2;

        public Bomb(int posX, int posY, int size, Image image) {
            super(posX, posY, size, image);
        }

        public void update() {
            super.update();
            if (!exploding && !destroyed) posY += Speed;
            if (posY > Height) destroyed = true;
        }
    }

    public class Shot {
        public boolean toRemove;
        int posX, posY, speed = 10;
        static final int size = 6;

        public Shot(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        public void update() {
            posY -= speed;
        }

        public void draw() {
            gc.setFill(Color.RED);
            if (score >= 50 && score <=70 || score >=120){
                gc.setFill(Color.YELLOWGREEN);
                speed = 50;
                gc.fillRect(posX - 5, posY - 10, size + 10, size + 30);
            } else {
                gc.fillOval(posX, posY, size, size);
            }
        }

        public boolean colide(Rocket Rocket) {
            int distance = distance(this.posX + size / 2, this.posY + size / 2, Rocket.posX + Rocket.size / 2, Rocket.posY + Rocket.size / 2);
            return distance < Rocket.size / 2 + size / 2;

        }

    }
    public class Universe {
        int posX, posY;
        private int h, w, r, g, b;
        private double opacity;

        public Universe() {
            posX = random.nextInt(Witdh);
            posY = 0;
            w = random.nextInt(5) + 1;
            h = random.nextInt(5) + 1;
            r = random.nextInt(100) + 150;
            g = random.nextInt(100) + 150;
            b = random.nextInt(100) + 150;
            opacity = random.nextFloat();
            if (opacity < 0) {
                opacity *= -1;
            }
            if (opacity > 0.5) {
                opacity = 0.5;
            }
        }
        public void draw() {
            if (opacity > 0.8) opacity -=0.01;
            if (opacity < 0.1) opacity +=0.01;
            gc.setFill(Color.rgb(r, g, b, opacity));
            gc.fillOval(posX, posY, w, h);
            posY += 20;
        }

    }
    Bomb newBomb() {
        return new Bomb(50 + random.nextInt(Witdh - 100), 0, player.size, bombsImage[random.nextInt(bombsImage.length)]);
    }
    int distance(int x1, int y1,int x2,int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2),2) + Math.pow((y1 - y2) ,2));

    }
}