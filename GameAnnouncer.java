package observer;

public class GameAnnouncer implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Game Announcer: " + message);  // выводит сообщение в консоль
    }
}
