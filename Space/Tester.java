package space;

import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        Descriptor galaxy = new Descriptor(5.0);
        double[] nums = galaxy.getIndexables();

        System.out.printf("Galaxy:\n%s\n\n", Arrays.toString(nums));

        Descriptor star = new Descriptor(galaxy);
        nums = star.getIndexables();

        System.out.println("Stars:");
        for (double d : nums) {
            System.out.printf("%2.2f, ", d);
        }
        System.out.println("\n");

        Descriptor planet = new Descriptor(star);
        nums = planet.getIndexables();

        System.out.println("Planet:");
        for (double d : nums) {
            System.out.printf("%2.2f, ", d);
        }
        System.out.println("\n");

    }
}
