import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Sender implements Runnable {
    public final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public final Package message = new Package();

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        char key;
        while (true) {
            // See if user pressed a key
            if (scanner.hasNext()) {
                key = scanner.next().charAt(0);
                // Send it to our subscribers
                lock.writeLock().lock();
                try {
                    System.out.println("Sending key: " + key);
                    message.setKey(key);
                } finally {
                    lock.writeLock().unlock();
                }
                // Bail out if key was z
                if (key == 'z') {
                    System.out.println("You pressed z - Sender thread will stop now - bye!");
                    return;
                }
            }
            // Sleep
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
