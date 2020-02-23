class Card {
    Card(int val, char suit){
        this._val = val;
        this._suit = suit;
        if (val > 1 && val < 11) {
            this._valName = Integer.toString(val);
        } else if (val == 1) {
            this._valName = "Ace";
        } else if (val == 11) {
            this._valName = "Jack";
        } else if (val == 12) {
            this._valName = "Queen";
        } else if (val == 13) {
            this._valName = "King";
        } else {
            this._valName = "null";
        }

        if (suit == 'D') {
            this._suitName = "Diamonds";
        } else if (suit == 'C') {
            this._suitName = "Clubs";
        } else if (suit == 'H') {
            this._suitName = "Hearts";
        } else if (suit == 'S') {
            this._suitName = "Spades";
        } else {
            this._suitName = "null";
        }
    }
    int getVal(){
        return this._val;
    }
    String getValName(){
        return this._valName;
    }
    char getSuit() {
        return this._suit;
    }
    String getSuitName(){
        return this._suitName;
    }
    public String toString() {
        return this._valName + " of " + this._suitName;
    }

    private String _valName;
    private String _suitName;
    private int _val;
    private char _suit;
}