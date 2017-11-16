package edu.technopolis;

import java.math.BigInteger;

public class FibonacciAlgorithmImplemetation implements FibonacciAlgorithm {
  @Override
  public String evaluate(int index) {
    BigLong first = new BigLong(0);
    BigLong second = new BigLong(1);
    BigLong tmp = new BigLong(0);
    if (index == 0)
      return first.toString();
    for (int i = 1; i < index; i++) {
      tmp.assign(second);
      second.addUnsigned(first);
      first.assign(tmp);
    }
    return second.toString();
  }
}
