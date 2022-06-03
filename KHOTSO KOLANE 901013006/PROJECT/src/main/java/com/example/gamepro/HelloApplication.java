package com.example.gamepro;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;


public class HelloApplication extends Application {

    Label txt= new Label("SCORE: 0");

    int score=1;
    TranslateTransition tt=new TranslateTransition();

    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws IOException {

        //play_audio();
        Pane root = new Pane();
        Scene scene = new Scene(root,700, 450);

        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        txt.setEffect(is);
        txt.setFont(Font.font(null, FontWeight.BOLD, 25));
        txt.setTextFill(Color.YELLOW);
        txt.setLayoutX(300);
        txt.setLayoutY(20);


        BackgroundImage background = new BackgroundImage(new Image("back.jpg", 700, 455, false,true),
                BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        root.setBackground(new Background(background));



        //ImageView enemy =  Enemy(scene);
        ImageView enemy1 =  Enemy1(scene);
        ImageView enemy2 =  Enemy2(scene);
        ImageView enemy3 =  Enemy3(scene);
        ImageView enemy5 =  Enemy5(scene);
        ImageView v =  Coin1(scene);
        ImageView v1 =  Coin2(scene);


        ImageView ship = createFighter(scene);




        scene.addEventFilter(KeyEvent.KEY_PRESSED,event -> {


                    double x = ship.getX();
                    double y = ship.getY();


                    switch (event.getCode()) {
                        case RIGHT -> ship.setX(x + 10);
                        case LEFT -> ship.setX(x - 10);
                        case UP -> ship.setY(y - 10);
                        case DOWN -> ship.setY(y + 10);
                    }



                });
        AnimationTimer detector = new AnimationTimer() {
            @Override
            public void handle(long l) {
               // CollisionDetector(ship,enemy,stage);
                CollisionDetector(ship,enemy1,stage);
                CollisionDetector(ship,enemy2,stage);
                CollisionDetector(ship,enemy3,stage);
                CollisionDetector(ship,enemy5,stage);
                coinDetector(ship,v);
                coinDetector(ship,v1);
            }
        };
        detector.start();



        root.getChildren().addAll(ship,enemy1,enemy2,enemy3,enemy5,v,v1,txt);
        stage.setTitle("Aeroplane Game!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    public void CollisionDetector(ImageView ship,ImageView enemy, Stage stage){
        if(ship.getBoundsInParent().intersects(enemy.getBoundsInParent())){
            ship.setImage(new Image("crash.png"));
            PauseTransition pause =new PauseTransition(Duration.millis(1200));
            pause.setOnFinished(actionEvent -> stage.close());
            pause.play();

        }
    }


    public void coinDetector(ImageView ship,ImageView v){
        if(ship.getBoundsInParent().intersects(v.getBoundsInParent())){
            score=score+1;
            txt.setText("SCORE:" + score);

            v.setImage(null);
        }
        else {

            v.setImage(new Image("coin1.png"));
        }
    }
/*
    public ImageView Enemy(Scene scene) {
        Image cloud = new Image("cloud.png");
        ImageView cloudv = new ImageView(cloud);


        cloudv.setFitWidth(100);
        cloudv.setFitHeight(100);
        cloudv.setX(700);
        cloudv.setY(340);

       TranslateTransition tt = new TranslateTransition(Duration.millis(7000),  cloudv);
       tt.setByX(-1000);
       tt.setCycleCount(Integer.MAX_VALUE);
       tt.play();
        return  cloudv;
    }*/

    private ImageView Enemy1(Scene scene) {
        Image cloud1 = new Image("cloud.png");
        ImageView cloudv1 = new ImageView(cloud1);


        cloudv1.setFitWidth(100);
        cloudv1.setFitHeight(100);
        cloudv1.setX(850);
        cloudv1.setY(200);

        TranslateTransition tt = new TranslateTransition(Duration.millis(9000),  cloudv1);
        tt.setByX(-1000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();
        return  cloudv1;
    }

    private ImageView Enemy2(Scene scene) {
        Image cloud2 = new Image("cloud.png");
        ImageView cloudv2 = new ImageView(cloud2);


        cloudv2.setFitWidth(100);
        cloudv2.setFitHeight(90);
        cloudv2.setX(850);
        cloudv2.setY(100);

        TranslateTransition tt = new TranslateTransition(Duration.millis(7000),  cloudv2);
        tt.setByX(-1000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();
        return  cloudv2;
    }

    private ImageView Enemy3(Scene scene) {
        Image cloud3 = new Image("cloud.png");
        ImageView cloudv3 = new ImageView(cloud3);


        cloudv3.setFitWidth(100);
        cloudv3.setFitHeight(100);
        cloudv3.setX(850);
        cloudv3.setY(30);

        TranslateTransition tt = new TranslateTransition(Duration.millis(30000),  cloudv3);
        tt.setByX(-2000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();
        return  cloudv3;
    }

    private ImageView Enemy5(Scene scene) {
        Image cloud5 = new Image("cloud.png");
        ImageView cloudv5 = new ImageView(cloud5);


        cloudv5.setFitWidth(100);
        cloudv5.setFitHeight(100);
        cloudv5.setX(850);
        cloudv5.setY(10);

        TranslateTransition tt = new TranslateTransition(Duration.millis(8000),  cloudv5);
        tt.setByX(-2000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();
        return  cloudv5;
    }


    private ImageView Coin1(Scene scene) {
        Image coin= new Image("coin1.png");
        ImageView coin1 = new ImageView(coin);

        coin1.setFitWidth(30);
        coin1.setFitHeight(35);
        coin1.setX(850);
        coin1.setY(100);

        TranslateTransition tt = new TranslateTransition(Duration.millis(15000),  coin1);
        tt.setByX(-2000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();
        return  coin1;
    }

    private ImageView Coin2(Scene scene) {
        Image coin = new Image("coin2.png");
        ImageView coin2 = new ImageView(coin);


        coin2.setFitWidth(35);
        coin2.setFitHeight(35);
        coin2.setX(850);
        coin2.setY(200);

        TranslateTransition tt = new TranslateTransition(Duration.millis(50000),coin2);
        tt.setByX(-5000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();
        return coin2;
    }

    private ImageView createFighter(Scene scene){

        ImageView image = new ImageView(new Image("airplane.png"));
        image.setFitWidth(70);
        image.setFitHeight(70);
        image.setLayoutX(10);
        image.setLayoutY(-200);

        image.setY(scene.getHeight() - image.getFitHeight());
        return image;

    }




    public static void main(String[] args) {
        launch();
    }
}