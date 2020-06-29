public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        player.name = "Player";
        player.cash = Settings.GAME_START_CASH;

        Game game = new Game();
        game.newProject(player);
        game.newProject(player);
        game.newProject(player);
        game.newProject(player);

        game.round(player);
    }
}
