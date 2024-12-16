package ANASSCADALLLABS;

class Printer {
    private int availablePages;

    public Printer(int totalPages) {
        this.availablePages = totalPages;
    }

    // Method to check and print pages
    public synchronized void printPages(int pagesToPrint) {
        System.out.println("Print request for " + pagesToPrint + " pages received.");
        while (availablePages < pagesToPrint) {
            System.out.println("Not enough pages. Waiting for refill...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Print thread interrupted.");
            }
        }
        availablePages -= pagesToPrint;
        System.out.println(pagesToPrint + " pages printed. Remaining pages: " + availablePages);
    }

    // Method to refill pages
    public synchronized void addPages(int pagesToAdd) {
        availablePages += pagesToAdd;
        System.out.println(pagesToAdd + " pages added to the printer. Total available pages: " + availablePages);
        notifyAll();
    }
}

public class Lab6ANASques2 {
    public static void main(String[] args) {
        Printer printer = new Printer(10);

        // Thread to handle printing jobs
        Thread printThread = new Thread(() -> {
            printer.printPages(15); // Trying to print 15 pages
        });

        // Thread to refill the printer tray
        Thread refillThread = new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulating delay for refill
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            printer.addPages(10); // Adding 10 more pages
        });

        printThread.start();
        refillThread.start();
    }
}
