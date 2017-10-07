import java.util.ArrayList;

public class BigNum {
    private static final long BASE = 1000000000000000000L;
    private ArrayList<Long> digits = new ArrayList<Long>(5000);


    BigNum() {
    }

    BigNum(BigNum a) {
        assign(a);
    }

    BigNum(long x) {
        int i = 0;
        while (x > 0) {
            digits.add(i, x % BASE);
            x /= BASE;
        }

    }

    BigNum add(BigNum a) {
        int maxsize = Math.max(digits.size(), a.digits.size());
        for (int i = 0; i < maxsize; i++) {
            if (digits.size() <= i) {
                digits.add(i, 0L);
            }
            if (a.digits.size() <= i) {
                a.digits.add(i, 0L);
            }
            digits.set(i, digits.get(i) + a.digits.get(i));
        }
        maxsize = digits.size();
        for (int i = 0; i < maxsize; i++) {
            if (digits.get(i) > BASE) {
                digits.set(i, digits.get(i) - BASE);
                if (i + 1 >= digits.size()) {
                    digits.add(i + 1, 0L);
                }
                digits.set(i + 1, digits.get(i + 1) + 1);
            }
        }
        return this;
    }

    public static BigNum add(BigNum a, BigNum b) {
        BigNum c = new BigNum(a);
        c.add(b);
        return c;
    }

    StringBuilder getStr() {

        StringBuilder s = new StringBuilder();
        for (int i = digits.size() - 1; i >= 0; i--) {
            s.append(String.format("%018d", digits.get(i)));
        }

        int ind = 0;
        boolean ok = false;
        while (ind < s.length()) {
            if (s.charAt(ind) == '0') {
                ind++;
            } else {
                ok = true;
                break;
            }
        }
        if (!ok) {
            s = new StringBuilder("0");
        } else {
            s = new StringBuilder(s.substring(ind));
        }

        return s;
    }

    void assign(BigNum a) {
        digits = new ArrayList<Long>(a.digits);
    }

}
