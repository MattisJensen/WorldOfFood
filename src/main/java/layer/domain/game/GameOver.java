package layer.domain.game;

public class GameOver {
    public static void endGame(double climatePoints) {  // static for at der ikke behøves at oprettes et GameOver objekt for at kunne udføre metoden
        if (climatePoints >= 0) {
            System.out.println("You did well! Your eating habits didnt have a negative impact on the climate.");
            System.out.println("Your score was " + climatePoints + " Climate Points.");
        } else {
            System.out.println("Better luck next time! Your eating habits had a negative impact on the climate.");
            System.out.println("Your score was " + climatePoints + " Climate Points.");
        }

//        CommandLineClient.setFinished(true);
        System.exit(0);
    }
}
