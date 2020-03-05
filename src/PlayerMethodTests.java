import org.junit.Test;

import static org.junit.Assert.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerMethodTests {
    private static ArrayList<Card> x = new ArrayList<>();

    @Test
    public void highCardTest() {
        Deck b = new Deck();
        for (char suit : new char[] {'D', 'C', 'H', 'S'}) {
            for (int cVal = 0; cVal < 13; cVal++) {
                x.add(new Card(cVal, suit));
            }
        }
        b.reset();
        Player a = new Player("a", 700);
        ArrayList<Card> test1 = new ArrayList<Card>();
        test1.add(x.get(0));
        test1.add(x.get(1));
        test1.add(x.get(2));
        test1.add(x.get(3));
        test1.add(x.get(4));
        test1.add(x.get(5));
        test1.add(x.get(6));

        ArrayList<Card> test2 = new ArrayList<Card>();
        test2.add(x.get(13));
        test2.add(x.get(15));
        test2.add(x.get(19));
        test2.add(x.get(35));
        test2.add(x.get(36));
        test2.add(x.get(37));
        test2.add(x.get(33));

        a.setHand(test1);
        assertEquals(87654, a.highCardChecker());
        a.setHand(test2);
        assertEquals(143198, a.highCardChecker());
    }
    @Test
    public void tripleCheckerTest() {
        Deck b = new Deck();
        for (char suit : new char[] {'D', 'C', 'H', 'S'}) {
            for (int cVal = 0; cVal < 13; cVal++) {
                x.add(new Card(cVal, suit));
            }
        }
        b.reset();
        Player a = new Player("a", 700);
        ArrayList<Card> test1 = new ArrayList<Card>();
        test1.add(x.get(0));
        test1.add(x.get(13));
        test1.add(x.get(26));
        test1.add(x.get(24));
        test1.add(x.get(43));
        test1.add(x.get(46));
        test1.add(x.get(25));

        ArrayList<Card> test2 = new ArrayList<Card>();
        test2.add(x.get(0));
        test2.add(x.get(24));
        test2.add(x.get(43));
        test2.add(x.get(26));
        test2.add(x.get(13));
        test2.add(x.get(46));
        test2.add(x.get(25));

        a.setHand(test1);
        assertEquals(353, a.tripleChecker());
        a.setHand(test2);
        assertEquals(353, a.tripleChecker());
    }
}
