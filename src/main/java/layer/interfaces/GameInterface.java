package layer.interfaces;

public interface GameInterface {

    public void newGame();

    public void restartGame();

    public void go(String direction);


    /* Current information */

    public int[] getCurrentPosition();

    public String getCurrentFoodName();

    public String getCurrentGrammaticalNumber();

    public String getGrammaticalSingularArticle();

    public String getGrammaticalPlural();

    public int getCurrentFoodAmount();

    public String getCurrentDescription();

    public boolean hasCurrentFood();

    public void startGrowthTimer();

}
