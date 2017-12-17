import java.util.Random;

/**
 * Created by gaz-l on 17/12/2017.
 */
public class AscensionBows {
    public static long simBows(int bowgoal) {
        long kills = 0, bows = 0;

        int[] signets = {0,0,0,0,0,0};

        Random r = new Random();

        while (bows < bowgoal) {
            kills++;
            if (r.nextInt(64) == 0) {
                int k = r.nextInt(6);
                if (r.nextInt(50) == 0) {
                    signets[k]++;
                }
            }

            if (signets[0] > 0 && signets[1] > 0 && signets[2] > 0 && signets[3] > 0 && signets[4] > 0 && signets[5] > 0) {
                bows++;
                signets[0]--;
                signets[1]--;
                signets[2]--;
                signets[3]--;
                signets[4]--;
                signets[5]--;
            }
        }

        return kills;
    }

    public static void main(String... args) {
        int sample = 100000, bows = 2;
        long totalkills = 0;
        for (int i = 0; i < sample; i++) {
            totalkills += simBows(bows);
        }

        System.out.println("Bows to get: " + bows);
        System.out.println("Sample size: " + sample);
        System.out.println("Average kills: " + totalkills/sample);
    }
}
