public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        player.name = "Player";
        Game game = new Game();
        game.round(player);
    }
}
