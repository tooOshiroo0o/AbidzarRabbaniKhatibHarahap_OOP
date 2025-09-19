public class Main {
    public static void main (String[] args) {
        Model.Player player1 = new Model.Player("Agus Buntung");

        Model.Player player2 = new Model.Player("Heri Hermansyah");
        player2.updateHighScore(3200);
        player2.updatetotalCoins(750);
        player2.updatetotalDistance(12000);

        // Model.Model.Score score3 = new Model.Model.Score(player1.getPlayerId(), 1800, 300, 6000);

        player1.updateHighScore(1500);
        player1.updatetotalCoins(250);
        player1.updatetotalDistance(5000);

        //player1.updateHighScore(score3.getValue());
        //player1.updatetotalCoins(score3.getCoinsCollected());
        //player1.updatetotalDistance(score3.getDistance());

        player1.updateHighScore(1800);
        player1.updatetotalCoins(300);
        player1.updatetotalDistance(6000);

        System.out.println("================Model.Model.Player Detail================");
        player1.showDetail();
        System.out.println();
        player2.showDetail();

    }
}

