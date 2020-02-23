import java.util.ArrayList;

class Deck {
    private ArrayList<Card> _deck;

    public Deck() {
        _deck = new ArrayList<>();
        char[] suits = {'D', 'C', 'H', 'S'};
        for (int nSuits = 0; nSuits < 4; nSuits++) {
            char suit = suits[nSuits];
            for (int cVal = 0; cVal < 13; cVal++) {
                _deck.add(new Card(cVal, suit));
            }
        }
    }



}
