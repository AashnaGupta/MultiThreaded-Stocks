public class Main {
    public static void main(String[] args) {
        try {
            StockGame[] stockTraders = {new StockGame("Xander", "TraderOneMoves.txt"),
                    new StockGame("Afua", "TraderTwoMoves.txt")};

            for (int i = 0; i < stockTraders.length; i++) {
                stockTraders[i].start();
            }
            for (int i = 0; i < stockTraders.length; i++) {
                stockTraders[i].join();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

    }
}
