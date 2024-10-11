package pong;

import java.util.*;

public class PongBallTimer 
    extends TimerTask {

    private PongBall ball;
    private PongCanvas canvas;
    private PongScore p1;
    private PongScore p2;
    private PongPaddle paddle1;
    private PongPaddle paddle2;

    public PongBallTimer(PongBall ball, 
                         PongCanvas canvas,
                         PongPaddle paddle1,
                         PongPaddle paddle2,
                         PongScore player1,
                         PongScore player2) {
        this.ball = ball;
        this.canvas = canvas;
        p1 = player1;
        p2 = player2;

        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
    }

    public void run() {

        // There is some bad hard coding here that should be
        // replaced with calls the the PongCanvas to determine
        // when the ball hits the borders!

        // Also there are thread synchronization issues with the
        // paddle motion and the ball motion that lead to the ball
        // occasionally getting stuck to the paddle.  Right now
        // it seems that the ball will always disloge itself, 
        // particuarly if the paddle is moved.

        // Walls...
        if (ball.getHorizPos() <= 9 ||
            ball.getHorizPos() >= canvas.getBounds().getWidth() - 5 - 4) {
            ball.horizBounce();

            if (ball.getHorizPos() <= 9) {
                if (ball.getVertPos() <= (canvas.getBounds().getHeight() - 30)/2 - 45 ||
                    ball.getVertPos() >= (canvas.getBounds().getHeight() - 30)/2 + 45) {
                    p2.scorePoints(1);
                }
                else if (ball.getVertPos() >= (canvas.getBounds().getHeight() - 30)/2 - 15 &&
                         ball.getVertPos() <= (canvas.getBounds().getHeight() - 30)/2 + 15) {
                    p2.scorePoints(5);
                }
                else {
                    p2.scorePoints(3);
                }
            }
            else {
                if (ball.getVertPos() <= (canvas.getBounds().getHeight() - 30)/2 - 45 ||
                    ball.getVertPos() >= (canvas.getBounds().getHeight() - 30)/2 + 45) {
                    p1.scorePoints(1);
                }
                else if (ball.getVertPos() >= (canvas.getBounds().getHeight() - 30)/2 - 15 &&
                         ball.getVertPos() <= (canvas.getBounds().getHeight() - 30)/2 + 15) {
                    p1.scorePoints(5);
                }
                else {
                    p1.scorePoints(3);
                }
            }
            
            ball.move();
        }

        if (ball.getVertPos() <= 9 ||
            ball.getVertPos() >= canvas.getBounds().getHeight() - 32 - 4) {
            ball.vertBounce();
        }
       
        // Paddles...
        if (ball.getVertPos() >= paddle1.getTopEdgePos() &&
            ball.getVertPos() <= paddle1.getBottomEdgePos() &&
            ball.getHorizPos() >= paddle1.getLeftEdgePos() - 4 &&
            ball.getHorizPos() <= paddle1.getRightEdgePos() + 4) {
            ball.horizBounce();
            ball.move();
        }
        else if (ball.getVertPos() >= paddle1.getTopEdgePos() - 4 &&
                 ball.getVertPos() <= paddle1.getBottomEdgePos() + 4 &&
                 ball.getHorizPos() >= paddle1.getLeftEdgePos() &&
                 ball.getHorizPos() <= paddle1.getRightEdgePos()) {
            ball.vertBounce();
            ball.move();
        }
        else if (ball.getVertPos() >= paddle1.getTopEdgePos() - 4 &&
                 ball.getVertPos() <= paddle1.getBottomEdgePos() + 4 &&
                 ball.getHorizPos() >= paddle1.getLeftEdgePos() - 4 &&
                 ball.getHorizPos() <= paddle1.getRightEdgePos() + 4) {
            ball.vertBounce();
            ball.horizBounce();
            ball.move();
        }


        if (ball.getVertPos() >= paddle2.getTopEdgePos() &&
            ball.getVertPos() <= paddle2.getBottomEdgePos() &&
            ball.getHorizPos() >= paddle2.getLeftEdgePos() - 4 &&
            ball.getHorizPos() <= paddle2.getRightEdgePos() + 4) {
            ball.horizBounce();
            ball.move();
        }
        else if (ball.getVertPos() >= paddle2.getTopEdgePos() - 4 &&
                 ball.getVertPos() <= paddle2.getBottomEdgePos() + 4 &&
                 ball.getHorizPos() >= paddle2.getLeftEdgePos() &&
                 ball.getHorizPos() <= paddle2.getRightEdgePos()) {
            ball.vertBounce();
            ball.move();
        }
        else if (ball.getVertPos() >= paddle2.getTopEdgePos() - 4 &&
                 ball.getVertPos() <= paddle2.getBottomEdgePos() + 4 &&
                 ball.getHorizPos() >= paddle2.getLeftEdgePos() - 4 &&
                 ball.getHorizPos() <= paddle2.getRightEdgePos() + 4) {
            ball.vertBounce();
            ball.horizBounce();
            ball.move();
        }

        ball.move();
        canvas.repaint();
    }
}

