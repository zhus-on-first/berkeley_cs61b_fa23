package bomb;

import common.IntList;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct inputs (passwords) to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {
            b.phase1(IntList.of(0, 9, 3, 0, 8)); // Figure this out too
        }
        if (phase >= 2) {
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < 100001; i++) {
                if (i == 1337) {
                    password.append(Integer.toString(-81201430));
                } else {
                    password.append(" ");
                }
            }
            b.phase2(password.toString());
        }
    }
}
