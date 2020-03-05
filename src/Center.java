import java.util.ArrayList;

public class Center {
    private static ArrayList<Card> _cards = new ArrayList<>();
    private static int _pot = 0;

    public static void increasePot(int amount) {
        _pot += amount;
    }
    public static void resetPot() {
        _pot = 0;
    }
    public static void draw(Deck a) {
        if (_cards.size() == 0) {
            _cards.addAll(a.deal(3));
        } else if (_cards.size() == 3 || _cards.size() == 4) {
            _cards.addAll(a.deal(1));
        } else {
            throw new IllegalStateException("It is not possible to deal when not 0, 3, or 4 cards in the center.");
        }
    }
    public static ArrayList<Card> getCenter() {
        return _cards;
    }
}
