package edu.technopolis;

import java.math.BigInteger;
import java.util.ArrayList;

public class BigLong {
  public static final long MAX_UNSIGNED_LONG = -1L;
  private ArrayList<Long> number = new ArrayList<>();
  public BigLong(long val) {
    number.add(val);
  }

  public ArrayList<Long> getNumber() {
    return number;
  }
  public Long getLong(int index) {
    return number.get(index);
  }

  public void assign(BigLong val) {
    number.clear();
    number.addAll(val.getNumber());
  }

  public void addUnsigned(BigLong val) {
    for (int i = 0; i < val.getNumber().size(); i++) {
      long leftSpace = MAX_UNSIGNED_LONG - number.get(i);
      if (Long.compareUnsigned(leftSpace, val.getLong(i)) < 0) {
        if (i+1 > number.size() - 1) {
          number.add(new Long(0));
        }
        number.set(i, val.getLong(i) - leftSpace - 1);
        int j = i+1;
        while (number.get(j) == MAX_UNSIGNED_LONG) { //when the higher long is MAX_UNSIGNED_LONG
          if (j + 1 > number.size() - 1) {
            number.add(new Long(0));
          }
          number.set(j, 0L);
          j++;
        }
        number.set(j, number.get(j) + 1);
      } else {
        number.set(i, number.get(i) + val.getLong(i));
      }
    }
  }

  @Override
  public String toString() {
    BigInteger bigInteger = new BigInteger(Long.toUnsignedString(number.get(0)));
    BigInteger radix = new BigInteger(Long.toUnsignedString(MAX_UNSIGNED_LONG)).add(new BigInteger("1"));
    for (int i = 1; i < number.size(); i++) {
      bigInteger = bigInteger
              .add(new BigInteger(Long.toUnsignedString(number.get(i)))
                      .multiply(radix.pow(i)));
    }
    return bigInteger.toString();
  }
}
