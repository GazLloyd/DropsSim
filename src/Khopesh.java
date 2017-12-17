import java.util.Random;

/**
 * Created by gaz-l on 17/12/2017.
 */
public class Khopesh {

    private int scraps = 0, phylChance = 18;
    Random r;

    public int creatureKills = 0, magisterKills = 0;


    public Khopesh(int num, boolean lotd) {
        if (lotd)
            phylChance = 17;
        sim(num);
    }

    private void killMagister() {
        magisterKills++;
        // 1/18 chance of a phyl (1/17 lotd)
        if (r.nextInt(phylChance) == 0) {
            // phyl distribution: 40/100 of 5 scraps, 30/100 of 10, 25/100 15, 5/100 of 25
            int scrapN = r.nextInt(100);
            if (scrapN < 40) {
                scraps += 5;
            } else if (scrapN < 70) {
                scraps += 10;
            } else if (scrapN < 95) {
                scraps += 15;
            } else {
                scraps += 25;
            }
        }
        // key dropped, do another kill right away (with no extra
        if (r.nextInt(128) == 0) {
            killMagister();
        }
    }

    private void sim(int goal) {
        long khops = 0;
        scraps = 0;
        r = new Random();

        while (khops < goal) {
            creatureKills++;
            // 1/128 chance of a key
            if (r.nextInt(128) == 0) {
                // perform a magister kill
                killMagister();
            }
            if (scraps >= 300) {
                scraps -= 300;
                khops++;
            }
        }
    }

    public static void main(String... args) {
        int sample = 100000, khopeshes = 1;
        boolean lotd = true;
        long totalkills = 0, totalmagkills = 0;
        for (int i = 0; i < sample; i++) {
            Khopesh k = new Khopesh(khopeshes, lotd);
            totalkills += k.creatureKills;
            totalmagkills += k.magisterKills;
        }

        System.out.println("Khopeshes to get: " + khopeshes);
        System.out.println("LotD?: "+lotd);
        System.out.println("Sample size: " + sample);
        System.out.println("Average corrupted creature/soul devourer kills: " + totalkills / sample);
        System.out.println("Average Magister kills: " + totalmagkills / sample);
    }
}
