import java.util.concurrent.atomic.AtomicInteger;

class Lucky {
    static AtomicInteger xAtom = new AtomicInteger(0);
    static AtomicInteger count = new AtomicInteger(0);

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (xAtom.get() >= 999999) break;
                int x = xAtom.getAndIncrement();

                if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                        % 10 + (x / 10000) % 10 + (x / 100000) % 10) {

                    System.out.println(xAtom.get());
                    count.incrementAndGet();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        long start = System.nanoTime();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count.get());

        long finish = System.nanoTime();
        long elapsed = finish - start;
        System.out.println("Прошло времени, ms: " + elapsed / 1_000_000);
        //t1            = 771, 751, 731, 728
        //t1-3          = 771, 759, 734
        //sync this     = 714, 735, 734
        //atoms         = 723, 787, 777, 747
        //ReentrantLock = 730, 760, 779, 753

    }
}