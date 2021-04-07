#Задача 11.1
Программа при каждом запуске выводит разные результаты, необходимо разобраться изза чего это происходит и исправить программу так, чтобы она выводила правильный результат.

Убедитесь, что потоки работают параллельно и блокируют друг друга минимально возможное время.

Выложите исправленный код на github отдельным репозиторием и вставьте в поле ссылку на него.
```
public class Lucky {
    static int x = 0;
    static int count = 0;

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (x < 999999) {
                x++;
                if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                        % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                    System.out.println(x);
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}
```