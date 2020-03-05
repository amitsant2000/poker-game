import java.sql.SQLOutput;
import java.util.ArrayList;

public class ProbabilityCalc {
    private static int _probability = 0;

    public static double calcProb(Player a, Deck x) {
        int numSim = 7 - Center.getCenter().size();
        ArrayList<Card> temp = new ArrayList<>();
        temp.addAll(a.getHand());
        a.getHand().addAll(Center.getCenter());
        int[] checkPlayer = new int[9];

        int contained;
        for (int i = 8; i >= 0; i--) {
            if (i == 8) {
                contained = a.straightFlushChecker();
            } else if (i == 7) {
                contained = a.fourPairChecker();
            } else if (i == 6) {
                contained = a.fullHouseChecker();
            } else if (i == 5) {
                contained = a.flushChecker();
            } else if (i == 4) {
                contained = a.straightChecker();
            } else if (i == 3) {
                contained = a.tripleChecker();
            } else if (i == 2) {
                contained = a.doublePairChecker();
            } else if (i == 1) {
                contained = a.pairChecker();
            } else {
                contained = a.highCardChecker();
            }
            if (contained != -1) {
                checkPlayer[i] = contained;
                break;
            }
        }
        int maxIndex = 0;
        for (int i = 8; i > 0; i--) {
            if (checkPlayer[i] != 0) {
                maxIndex = i;
                break;
            }
        }
        int beatCount = 0;


        ArrayList<Card> center = new ArrayList<>();
        center.addAll(Center.getCenter());
        for (int i = 0; i < x.getDeck().size() - 1; i++) {
            center.add(x.getDeck().get(i));
            for (int j = i + 1; j < x.getDeck().size(); j++) {
                Player opp = new Player("opp", 0);
                opp.setHand(center);
                int[] checkOpp = new int[9];
                center.add(x.getDeck().get(j));
                int contained2;
                for (int k = 8; k >= 0; k--) {
                    if (k == 8) {
                        contained2 = opp.straightFlushChecker();
                    } else if (k == 7) {
                        contained2 = opp.fourPairChecker();
                    } else if (k == 6) {
                        contained2 = opp.fullHouseChecker();
                    } else if (k == 5) {
                        contained2 = opp.flushChecker();
                    } else if (k == 4) {
                        contained2 = opp.straightChecker();
                    } else if (k == 3) {
                        contained2 = opp.tripleChecker();
                    } else if (k == 2) {
                        contained2 = opp.doublePairChecker();
                    } else if (k == 1) {
                        contained2 = opp.pairChecker();
                    } else {
                        contained2 = opp.highCardChecker();
                    }
                    if (contained2 != -1) {
                        checkOpp[k] = contained2;
                        break;
                    }
                }
                int maxIndex2 = 9;
                for (int r = 8; r > 0; r--) {
                    if (checkOpp[r] != 0) {
                        maxIndex2 = r;
                        break;
                    }
                }
                if (maxIndex > maxIndex2) {
                    beatCount++;
                }
                else if (maxIndex == maxIndex2) {
                    if (checkPlayer[maxIndex] >= checkOpp[maxIndex2]) {
                        beatCount++;
                    }
                }
                center.remove(center.size() - 1);
            }
            center.remove(center.size() - 1);
        }
        System.out.println(beatCount);
        if (beatCount == 0) {
            System.out.println(a.getHand() + ", " + maxIndex);
        }
        a.setHand(temp);
        int total = x.getDeck().size() * (x.getDeck().size() - 1) / 2;
        return (double) beatCount / total; //FIXME: doesn't work; outputs same thing every time; also base 10 doesnt work; need to figure out base 14
    }
    public int highCardProbability() {
        return 0;
    }
    public int getProbability() {
        return _probability;
    }
    public static void main(String[] args) {
        Player a = new Player("bigdickus", 0);
        Deck x = new Deck();
        x.reset();
        a.draw(x);
        Center.draw(x);
        Center.draw(x);
        Center.draw(x);
        System.out.println(calcProb(a, x));
    }
}
