import java.util.ArrayList;

class Deck {
    private ArrayList<Card> _deck;
    private ArrayList<Card> _usedDeck;
    private ArrayList<Card> _hypoDeck;

    public Deck() {
        _deck = new ArrayList<>();
        _usedDeck = new ArrayList<>();
        _hypoDeck = new ArrayList<>();
        for (char suit : new char[] {'D', 'C', 'H', 'S'}) {
            for (int cVal = 0; cVal < 13; cVal++) {
                _deck.add(new Card(cVal, suit));
            }
        }
    }
    public void reset() {
        //TODO: maybe use method to get rid of the hands of players after round ends and also shuffle
        shuffle();
    }
    private void shuffle() {
        _deck.addAll(_usedDeck);
        _usedDeck = new ArrayList<>();

        ArrayList<Card> duplicate = new ArrayList<>();
        int index;
        for (int i = 0; i < 52; i++) {
            index = (int) (Math.random() * _deck.size());
            duplicate.add(_deck.remove(index));
        }
        _deck = duplicate;
    }
    public ArrayList<Card> deal(int count) {
        if (count != 2) {
            burn();
        }
        ArrayList<Card> dealt = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dealt.add(_deck.get(0));
            _usedDeck.add(_deck.remove(0));
        }
        return dealt;
    }
    private void burn() {
        _usedDeck.add(_deck.remove(0));
    }
    public ArrayList<Card> getDeck() {
        return _deck;
    }
    public String toString() {
        return _deck.toString();
    }
    public static void main(String[] args) {
        Deck x = new Deck();
        System.out.println(x);
        x.shuffle();
        System.out.println(x); }
}