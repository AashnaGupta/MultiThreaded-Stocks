import java.io.*;
/**
 * @author  Aashna Gupta
 * @version 1.0
 */
public class StockGame extends Thread {
    private static double stockPrice = 100.00;
    private static int availableShares = 100;
    private static int tradeCount = 1;
    private String name;
    private int numShares;
    private String fileName;

    public StockGame(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
        numShares = 0;
    }


    public String toString() {
        String rv = "----------\nStock Price:  " + stockPrice + "\nAvailable Shares: " + availableShares +
                "\nTrade number: " + tradeCount + "\nName: " + name;
        return rv;
    }

    public void run() {

        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fileName));
            String[] lines = bfr.lines().toArray(String[]::new);

            for (int i = 0; i < lines.length; i++) {
                String[] info = lines[i].split(",");

                if ((!(info[0].equals("BUY")) && !(info[0]).equals("SELL")) || Integer.parseInt(info[1]) < 0)
                    synchronized (StockGame.class) {
                        System.out.println("Error, invalid input!");
                    }

                if (info[0].equals("BUY")) {
                    synchronized (StockGame.class) {
                        if (Integer.parseInt(info[1]) <= availableShares) {
                            System.out.println(toString());
                            System.out.println(String.format("Purchasing %d shares at %.2f...",
                                    Integer.parseInt(info[1]), stockPrice));
                            numShares = numShares + Integer.parseInt(info[1]);
                            availableShares = availableShares - Integer.parseInt(info[1]);
                            stockPrice = stockPrice + (Integer.parseInt(info[1]) * 1.5);
                            tradeCount++;
                        } else
                            System.out.println("Insufficient shares available, cancelling order...");
                    }
                }
                if (info[0].equals("SELL")) {
                    synchronized (StockGame.class) {
                        if (Integer.parseInt(info[1]) <= availableShares) {
                            System.out.println(toString());
                            System.out.println(String.format("Selling %d shares at %.2f...",
                                    Integer.parseInt(info[1]), stockPrice));
                            numShares = numShares - Integer.parseInt(info[1]);
                            availableShares = availableShares + Integer.parseInt(info[1]);
                            stockPrice = stockPrice - (Integer.parseInt(info[1]) * 2.0);
                            tradeCount++;
                        } else
                            System.out.println("Insufficient owned shares, cancelling order...");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}