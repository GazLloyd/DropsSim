import java.util.Random;

/**
 * Created by gaz-l on 17/12/2017.
 */
public class AscensionBows {

    private int[] signets = {0,0,0,0,0,0};
    private Random r = new Random();
    public int creatureKills = 0, legioKills = 0, goal;
    public int[] legioKillsEach = {0,0,0,0,0,0}, extraKeys = {0,0,0,0,0,0};


    public AscensionBows(int num) {
        goal = num;
        sim();
    }

    private boolean checkSignet(int k) {
        return signets[k] >= goal;
    }

    private boolean checkSignets() {
        return signets[0] >= goal &&
                signets[1] >= goal &&
                signets[2] >= goal &&
                signets[3] >= goal &&
                signets[4] >= goal &&
                signets[5] >= goal;
    }


    private void keyDrop(int k) {
        if (checkSignet(k)) {
            extraKeys[k]++;
        } else {
            killLegio(k);
        }
    }

    private void killLegio(int k) {
        legioKills++;
        legioKillsEach[k]++;
        // 1/50 chance of a signet
        if (r.nextInt(50) == 0) {
            signets[k]++;
        }
        // 1/64 chance of another key (can get both signet and key)
        if (r.nextInt(64) == 0) {
            keyDrop(r.nextInt(6));
        }
    }


    private void sim() {
        while (!checkSignets()) {
            creatureKills++;
            // 1/64 chance of a key
            if (r.nextInt(64) == 0) {
                //each key equally likely (1/6)
                keyDrop(r.nextInt(6));
            }
        }
    }

    public static void main(String... args) {
        int sample = 100000, bows = 1;
        double totalkills = 0, totallegkills = 0;
        double[] totallegkillseach = {0,0,0,0,0,0}, totalextrakeys = {0,0,0,0,0,0};

        for (int i = 0; i < sample; i++) {
            AscensionBows ab = new AscensionBows(bows);
            totalkills += ab.creatureKills;
            totallegkills += ab.legioKills;
            for (int j = 0; j < 6; j++) {
                totallegkillseach[j] += ab.legioKillsEach[j];
                totalextrakeys[j] += ab.extraKeys[j];
            }
        }

        System.out.println("Bows to get: " + bows);
        System.out.println("Sample size: " + sample);
        System.out.println("Average Ascension creature kills: " + totalkills/sample);
        System.out.println("Average Legiones kills (total): " + totallegkills/sample);
        System.out.println();
        System.out.println("Average Primus kills: " + totallegkillseach[0]/sample);
        System.out.println("Average Secundus kills: " + totallegkillseach[1]/sample);
        System.out.println("Average Tertius kills: " + totallegkillseach[2]/sample);
        System.out.println("Average Quartus kills: " + totallegkillseach[3]/sample);
        System.out.println("Average Quintus kills: " + totallegkillseach[4]/sample);
        System.out.println("Average Sextus kills: " + totallegkillseach[5]/sample);
        System.out.println();
        System.out.println("Average extra keys: " + (totalextrakeys[0]+totalextrakeys[1]+totalextrakeys[2]+totalextrakeys[3]+totalextrakeys[4]+totalextrakeys[5])/sample);
        System.out.println("Average extra Primus keys: " + totalextrakeys[0]/sample);
        System.out.println("Average extra Secundus keys: " + totalextrakeys[1]/sample);
        System.out.println("Average extra Tertius keys: " + totalextrakeys[2]/sample);
        System.out.println("Average extra Quartus keys: " + totalextrakeys[3]/sample);
        System.out.println("Average extra Quintus keys: " + totalextrakeys[4]/sample);
        System.out.println("Average extra Sextus keys: " + totalextrakeys[5]/sample);
    }
}
