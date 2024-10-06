package dev.lpa;

public class Theatre {

  class Seat implements Comparable<Seat> {
    private final String seatNum;
    private boolean reserved;

    public Seat(char rowChar, int seatNum) {
      this.seatNum = "%c%03d".formatted(rowChar, seatNum).toUpperCase();
    }

    @Override
    public String toString() {
      return seatNum;
    }

    @Override
    public int compareTo(Seat o) {
      return seatNum.compareTo(o.seatNum);
    }
  }
}
