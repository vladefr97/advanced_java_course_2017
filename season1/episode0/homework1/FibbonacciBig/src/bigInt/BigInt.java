package bigInt;

public class BigInt {
    private long[] numParts;
    private static final long base = 1000*1000*1000;
    private static final int numOfChars = 9;

    public BigInt(){
        this(2);
    }

    private BigInt(int size){
        numParts = new long[size];
        /* was used for different bases, ignore */
        /*for (long i = base/10; i > 0; i/=10) {
            numOfChars++;
        }*/
    }

    public BigInt(String value){
        //add check that all characters are numbers
        this(value.length()/9+2);
        int usedParts = (value.length()/numOfChars);
        if (value.length() % numOfChars != 0) usedParts++;
        for (int i = 0; i < usedParts; i++) {
            int rightPos = value.length()-i*numOfChars;
            int leftPos = rightPos - numOfChars;
            if (leftPos < 0) leftPos = 0;
            if (leftPos == rightPos) return;
            numParts[i] = Long.parseLong(value.substring(leftPos,rightPos));
        }
    }
    public BigInt(BigInt other){
        this(other.numParts.length);
        System.arraycopy(other.numParts, 0, numParts, 0, numParts.length);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String format = "%0" + numOfChars + "d";
        boolean passedFirst = false;
        for (int i = numParts.length-1; i >= 0; i--){
            if (passedFirst) {
                sb.append(String.format(format, numParts[i]));
            } else {
                if (numParts[i] != 0){
                    sb.append(numParts[i]);
                    passedFirst = true;
                }
            }
        }
        if (sb.length() == 0) sb.append("0");
        return sb.toString();
    }
    public void add(BigInt other){

        int minSize = Math.min(numParts.length, other.numParts.length);
        /* summing */
        for (int i = 0; i < minSize; i++) {
            numParts[i] += other.numParts[i];
        }
        /* fix carry */
        long carry = 0;
        for (int i = 0; i < numParts.length; i++) {
            numParts[i] += carry;
            carry = (numParts[i] >= base)? 1 : 0;
            if (carry == 1) numParts[i] -= base;
        }
        /* resize and copy */
        if (numParts[numParts.length-1]!=0){
            long[] numPartsTemp = new long[numParts.length+1];
            System.arraycopy(numParts, 0, numPartsTemp, 0, numParts.length);
            numParts = numPartsTemp;
        }
        assert carry == 0;

    }
   /*
    TODO: add multiplication,
    problems:
        harder to find appropriate size,
        it will be very slow (there are few algorithms, but it is tricky)
    public void mulSelf(bigInt other){

    }
    public bigInt mul(bigInt other){
        return b;
    }*/
    public static BigInt sum(BigInt one,BigInt two){
        BigInt b = new BigInt(one);
        b.add(two);
        return b;
    }
}
