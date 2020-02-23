class Card {
    Card(int val, char suit){
        this._val = val + 2;
        this._suit = suit;
        if (this._val > 1 && this._val < 11) {
            this._valName = Integer.toString(this._val);
        } else if (this._val == 14) {
            this._valName = "Ace";
        } else if (this._val == 11) {
            this._valName = "Jack";
        } else if (this._val == 12) {
            this._valName = "Queen";
        } else if (this._val == 13) {
            this._valName = "King";
        } else {
            throw new IllegalArgumentException("val must be between 0 and 12");
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
            throw new IllegalArgumentException("suit must be one of 'D', 'C', 'H', or 'S'");
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

    public static void main(String[] args) {
        Card x = new Card(12, 'H ');
        System.out.println(x);
    }
}