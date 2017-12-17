import java.util.Random;

/**
 * Created by gaz-l on 17/12/2017.
 */
public class Khopesh {
    public static long sim(int goal) {
        long kills = 0, khops = 0, scraps = 0;

        Random r = new Random();

        while (khops < goal) {
            kills++;
            // 1/128 chance of a key
            if (r.nextInt(128) == 0) {
                // 1/18 chance of a phyl
                if (r.nextInt(18) == 0) {
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
            }
            if (scraps >= 300) {
                scraps -= 300;
                khops++;
            }
        }

        return kills;
    }

    public static void main(String... args) {
        int sample = 100000, khopeshes = 1;
        long totalkills = 0;
        for (int i = 0; i < sample; i++) {
            totalkills += sim(khopeshes);
        }

        System.out.println("Khopeshes to get: " + khopeshes);
        System.out.println("Sample size: " + sample);
        System.out.println("Average corrupted creature/soul devourer kills: " + totalkills / sample);
    }
}
