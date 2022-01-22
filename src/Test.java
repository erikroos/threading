public class Test {
    public static void main(String[] args) {
        Sender sender = new Sender();
        Thread senderThread = new Thread(sender);
        senderThread.start();

        Receiver receiver = new Receiver(sender); // Mind: we inject the sender object into the receiver
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();

        // Alternative: use ThreadPool
    }
}
