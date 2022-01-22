public class Receiver implements Runnable {
    private Sender sender;

    public Receiver(Sender sender) {
        this.sender = sender;
    }

    @Override
    public void run() {
        Character oldKey = null;
        Character key;
        while (true) {
            // See if the Sender sent something
            sender.lock.readLock().lock();
            try {
                key = sender.message.getKey();
                if (key != null && !key.equals(oldKey)) {
                    System.out.println("Received new key " + key);
                    // Bail out if key was z
                    if (key == 'z') {
                        System.out.println("Key z received - Receiver thread will stop now too - bye bye!");
                        return;
                    }
                    oldKey = key;
                }
            } finally {
                sender.lock.readLock().unlock();
            }
            // Sleep
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
