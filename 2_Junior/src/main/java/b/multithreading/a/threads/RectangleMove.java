package b.multithreading.a.threads;

import javafx.scene.shape.Rectangle;
/**
 * Class RectangleMove.
 *
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1
 * @since 0.1
 */
public class RectangleMove implements Runnable {

    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int deltaX = 1;
        int deltaY = 1;
        while (!Thread.currentThread().isInterrupted()) {
            if (rect.getX() == 290) {
                deltaX = -1;
            } else if (rect.getX() == 0) {
                deltaX = 1;
            }
            if (rect.getY() == 290) {
                deltaY = -1;
            } else if (rect.getY() == 0) {
                deltaY = 1;
            }
            this.rect.setX(this.rect.getX() + deltaX);
            this.rect.setY(this.rect.getY() + deltaY);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Stop thread");
                Thread.currentThread().interrupt();
            }
        }
    }
}