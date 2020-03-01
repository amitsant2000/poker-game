import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static java.util.Collections.max;

public class Player {
    private String _name;
    private ArrayList<Card> _hand;
    private int _balance;
    //TODO: Implement dictionary or some other data structure to hold ranks of each hand.

    public Player(String name, int balance) {
        _name = name;
        _balance = balance;
        _hand = new ArrayList<>();
    }
    public void draw(Deck a) {
        _hand = a.deal(2);
    }
    public void placeBet(int amount) {
        if (amount <= _balance) {
            _balance -= amount;
            Center.increasePot(amount);
        }
        else {
            throw new IllegalArgumentException("The amount must be less than the balance.");
        }
    }

    public int highCardChecker() {
        if (_hand.size() == 0) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else { //FIXME: possible to get rid of this else?
            if (_hand.isEmpty()) {
                return -1;
            }
            ArrayList<Integer> temp = new ArrayList<>();
            for (Card elem: _hand) {
                temp.add(elem.getVal());
            }
            int[] container = new int[5]; //possibly a problem if _hand is less than 5 elems in case of
            for (int i = 0; i < 5; i++) {
                container[i] = max(temp);
                temp.remove(Integer.valueOf(container[i]));
            }
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += Math.pow(10, i) * container[4-i];
            }
            return sum;
        }
    }

    //FIXME: All checks only verify whether that exists.

    public int pairChecker() { //FIXME: CHECK THIS METHOD!
        if (_hand.isEmpty()) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else {
            ArrayList<Integer> pairContainer = pairFinder();
            if (pairContainer.isEmpty()) {
                return -1;
            }
            ArrayList<Integer> temp = new ArrayList<>();
            for (Card elem: _hand) {
                temp.add(elem.getVal());
            }
            int pair = max(pairContainer);
            temp.removeAll(Arrays.asList(pair));
            int thirdCard = max(temp);
            temp.remove(Integer.valueOf(thirdCard));
            int fourthCard = max(temp);
            temp.remove(Integer.valueOf(fourthCard));
            int fifthCard = max(temp);
            return 1000 * pair + 100 * thirdCard + 10 * fourthCard + fifthCard;
        }
    }
    public int doublePairChecker() {
        if (_hand.isEmpty()) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else {
            ArrayList<Integer> pairContainer = pairFinder();
            if (pairContainer.size() <= 1) {
                return -1;
            }
            ArrayList<Integer> temp = new ArrayList<>();
            for (Card elem : _hand) {
                temp.add(elem.getVal());
            }
            int doublePair = 0;
            int max = max(pairContainer);
            doublePair += 100 * max;
            pairContainer.removeAll(Arrays.asList(max));
            temp.removeAll(Arrays.asList(max));
            max = max(pairContainer);
            doublePair += 10 * max;
            temp.removeAll(Arrays.asList(max));
            doublePair += max(temp);
            return doublePair;
        }
    }
    public int tripleChecker() {
        if (_hand.isEmpty()) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else {
            ArrayList<Integer> tripleContainer = tripleFinder();
            return max(tripleContainer);
        }
    }
    public int fullHouseChecker() {
        if (_hand.isEmpty()) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else {
            ArrayList<Integer> pairContainer = pairFinder();
            ArrayList<Integer> tripleContainer = tripleFinder();
            int max = -1;
            for (Integer a: tripleContainer) {
                for (Integer b: pairContainer) {
                    int sum = 10 * a + b;
                    if (sum > max) {
                        max = sum;
                    }
                }
            }
            return max;
        }
    }
    public int fourPairChecker() {
        if (_hand.isEmpty()) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else {
            int[] vals = new int[13];
            for (Card elem: _hand) {
                vals[elem.getVal() - 2]++;
            }
            int fourPair = -1;
            for (int i = 0; i < 13; i++) {
                if (vals[i] == 4) {
                    fourPair = i + 2;
                }
            }
            return fourPair;
        }
    }
    public int straightChecker() {
        if (_hand.isEmpty()) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else {
            ArrayList<Integer> straightMaxes = straightFinder();
            return straightMaxes.isEmpty() ? -1 : max(straightMaxes);
        }
    }
    public int flushChecker() {
        if (_hand.isEmpty()) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else {
            String[] suits = new String[] {"", "", "", ""};
            for (Card elem : _hand) {
                if (elem.getSuit() == 'D') {
                    suits[0] += "D";
                } else if (elem.getSuit() == 'C') {
                    suits[1] += "C";
                } else if (elem.getSuit() == 'H') {
                    suits[2] += "H";
                } else {
                    suits[3] += "S";
                }
            }
            char flushSuit = 'A';
            for (String count: suits) {
                if (count.length() >= 5) {
                    flushSuit = count.charAt(0);
                }
            }
            if (flushSuit == 'A') {
                return -1;
            }
            int max = -1;
            for (Card elem: _hand) {
                int cardVal = elem.getVal();
                if (elem.getSuit() == flushSuit && cardVal > max) {
                    max = cardVal;
                }
            }
            return max;
        }
    }
    public int straightFlushChecker() {
        if (flushChecker() == -1 || straightChecker() == -1) {
            return -1;
        }
        else {
            ArrayList<Integer> straightMaxes = straightFinder();
            ArrayList<Integer> straightFlushes = new ArrayList<>();
            _hand.sort(Comparator.comparing(Card::getVal));
            for (int max: straightMaxes) {
                for (int i = 5; i < _hand.size(); i++) { //check for when not 7 cards and can possibly optimize by going back from i = _hand.size() - 1 so do not have to make arrayList
                    Card maxCard = _hand.get(i);
                    if (maxCard.getVal() == max) {
                        char suit = maxCard.getSuit();
                        boolean straightFlush = true;
                        for (int j = i - 1; j > i - 5; j++) {
                            if (_hand.get(j).getSuit() != suit || _hand.get(j).getVal() != max + j - i) { //FIXME: check whether second condition necessary
                                straightFlush = false;
                                break;
                            }
                        }
                        if (straightFlush) {
                            straightFlushes.add(max);
                        }
                    }
                }
            }
            if (straightFlushes.isEmpty()) {
                return -1;
            }
            return max(straightFlushes);
        }
    }
    public ArrayList<Integer> pairFinder() {
        ArrayList<Integer> pairContainer = new ArrayList<>();
        for (int i = 0; i < _hand.size() - 1; i++) {
            int a = _hand.get(i).getVal();
            for (int j = i + 1 ; j < _hand.size(); j++) {
                int b = _hand.get(j).getVal();
                if (a == b) {
                    pairContainer.add(a);
                }
            }
        }
        return pairContainer;
    }
    public ArrayList<Integer> tripleFinder() {
        ArrayList<Integer> tripleContainer = new ArrayList<>();
        for (int i = 0; i < _hand.size() - 2; i++) {
            int a = _hand.get(i).getVal();
            for (int j = i + 1 ; j < _hand.size() - 1; j++) {
                int b = _hand.get(j).getVal();
                for (int k = j + 1; k < _hand.size(); k++) {
                    int c = _hand.get(k).getVal();
                    if (a == b && b == c) {
                        tripleContainer.add(a);
                    }
                }
            }
        }
        return tripleContainer;
    }
    public ArrayList<Integer> straightFinder() {
        ArrayList<Integer> straightMaxes = new ArrayList<>();
        int[] vals = new int[14];
        for (Card elem: _hand) {
            if (elem.getValName().equals("Ace")) {
                vals[0]++;
                vals[13]++;
            }
            else {
                vals[elem.getVal() - 1]++;
            }
        }
        for (int i = 0; i < 10; i++) {
            boolean straight = true;
            int j;
            for (j = i ; j < i + 5; j++) {
                if (vals[j] < 1) {
                    straight = false;
                    break;
                }
            }
            if (straight) {
                straightMaxes.add(j);
            }
        }
        return straightMaxes;
    }
}
