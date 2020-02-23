import java.util.ArrayList;

class Deck {
    private ArrayList<Card> _deck;

    public Deck() {
        _deck = new ArrayList<>();
        for (char suit : new char[] {'D', 'C', 'H', 'S'}) {
            for (int cVal = 0; cVal < 13; cVal++) {
                _deck.add(new Card(cVal, suit));
            }
        }
    }

    public void shuffle() {

    }



}
