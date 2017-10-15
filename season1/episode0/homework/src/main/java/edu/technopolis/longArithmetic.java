package edu.technopolis;

class longArithmetic {

    static LongNumber sum(LongNumber a, LongNumber b) {
        int maxLength = Math.max(a.getLength(), b.getLength());
        if (a.getNumber(a.getLength() - 1) > 0 || b.getNumber(b.getLength() - 1) > 0) {
            maxLength++;
        }
        LongNumber res = new LongNumber(maxLength);
        long temp = 0;
        for (int i = 0; i < maxLength; i++) {
            res.setNumber(i, temp + a.getNumber(i) + b.getNumber(i));
            if (res.getNumber(i) < 0) {
                res.setNumber(i, res.getNumber(i) - Long.MIN_VALUE);
                temp = 1L;
            } else {
                temp = 0;
            }
        }
        return res;
    }
}
