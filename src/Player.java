import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.Collections.disjoint;
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
    public void getBestHand() {
        //TODO:
        // 2. Implement checker for each possible hand of 7C5.
        // 3. Place the results of each checker in a boolean array, and get the index of highest "true" value.
        // 4. Need to account for high card being a value,
        // and how do I compare the respective values of certain hands (e.g. AAA88 vs KKK99 for full house)
    }
    public int highCardChecker() {
        if (_hand.size() == 0) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else { //FIXME: possible to get rid of this else?
            int high = -1;
            for (Card a: _hand) {
                int val = a.getVal();
                if (val > high) {
                    high = val;
                }
            }
            return high;
        }
    }

    //FIXME: All checks only verify whether that exists.

    public int pairChecker() {
        if (_hand.isEmpty()) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else {
            ArrayList<Integer> pairContainer = pairFinder();
            return max(pairContainer);
        }
    }
    public int doublePairChecker() {
        if (_hand.isEmpty()) {
            throw new IllegalCallerException("It is not possible to check for cards when there are no cards dealt.");
        } else {
            ArrayList<Integer> pairContainer = pairFinder();
            ArrayList<Integer[]> doublePairContainer = new ArrayList<>();
            for (int i = 0; i < pairContainer.size() - 1; i++) {
                for (int j = i + 1; j < pairContainer.size(); j++) {
                    doublePairContainer.add(new Integer[] {pairContainer.get(0), pairContainer.get(1)});
                }
            }
            int max = -1;
            for (Integer[] elem: doublePairContainer) {
                int sum;
                if (elem[0] >= elem[1]) {
                    sum = 10 * elem[0] + elem[1];
                } else {
                    sum = 10 * elem[1] + elem[0];
                }
                if (max < sum) {
                    max = sum;
                }
            }
            return max;
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
                for (int k = j + 1; j < _hand.size(); k++) {
                    int c = _hand.get(k).getVal();
                    if (a == b && b == c) {
                        tripleContainer.add(a);
                    }
                }
            }
        }
        return tripleContainer;
    }
}
