package dev.lpa;

import java.util.*;
import java.util.stream.Stream;

public class Theatre {
  private final NavigableSet<Seat> seats;
  private final String name;
  private final int seatsPerRow;

  public Theatre(String name, int seatPerRow, int seatsTotal) {
    this.name = name;
    this.seatsPerRow = seatPerRow;
    this.seats = new TreeSet<>(
      Comparator.comparing(Seat::toString)
        .thenComparing(Seat::isReserved));

    int rows = seatsTotal / seatPerRow;
    for (int i = 'A'; i < 'A' + rows; i++) {
      for (int j = 0; j < seatPerRow; j++) {
        seats.add(new Seat((char) i, j + 1)); // random reservation to init seating
      }
    }
  }

  public void printSeatMap() {

    printHeader();

    char lastRow = 'A';
    System.out.print("\n" + 'A' + "\t");

    for ( var s : seats) {
      char currentRow = s.toString().charAt(0);
      if (lastRow != currentRow) {
        System.out.print("\n" + currentRow + "\t");
        lastRow = currentRow;
      }
      System.out.print((!s.isReserved ? "o" : "x") + "\t");
    }
    System.out.println();
  }

  private void printHeader() {
    System.out.print(" \t");
    for (int i = 0; i < seatsPerRow; i++) {
      System.out.print((i + 1) + "\t");
    }
  }

  public void reserveSeat(char row, int number) {
    Seat reserved = new Seat(row, number, true);
    Seat unreserved = new Seat(row, number, false);
    String message = seats.contains(reserved) ?
      "Seat " + reserved + " already reserved. Could not be booked." :
      (seats.remove(unreserved) && seats.add(reserved) ?
        "Seat " + reserved + " successfully booked." : "Seat " + reserved + " not found.");
    System.out.println(message);
  }

  public void reserveSeats(int count, char rowStart, char rowEnd, int numberStart, int numberEnd) {
    var availableSeats = getavailableSeatStream(rowStart, rowEnd, numberStart, numberEnd)
      .limit(count)
      .toList();

    if(availableSeats.size() < count) {
      System.out.println("Not enough seats, please change booking order.");
      return;
    }

    for (var s : availableSeats)
      reserveSeat(s.row(), s.number());
    System.out.println("Order booked successfully.");
  }

  public IntSummaryStatistics seatStatistic() {
    return seats.stream()
      .mapToInt(s -> s.isReserved() ? 1 : 0)
      .summaryStatistics();
  }

  private Stream<Seat> getavailableSeatStream(char rowStart, char rowEnd, int numberStart, int numberEnd) {
    return seats.stream()
      .filter(s -> {
        char row = s.row();
        int num = s.number();
        return row >= rowStart && row <= rowEnd &&
          num >= numberStart && num <= numberEnd &&
          !s.isReserved;
      });
  }

  record Seat(char row, int number, boolean isReserved) {

    public Seat(char row, int number) {
      this(row, number, new Random().nextBoolean());
    }

    @Override
    public String toString() {
      return "%c%03d".formatted(row, number); // padded with 0 to 3 digits
    }
  }

  public static void main(String[] args) {
    System.out.println(new Seat('A', 5, true));
    Theatre theatre = new Theatre("Stadttheater", 10, 50);
    theatre.printSeatMap();
    System.out.println("--------------------");
    theatre.reserveSeat('C', 5);
    theatre.printSeatMap();
    System.out.println("--------------------");
    theatre.reserveSeats(5, 'C', 'D', 3, 8);
    System.out.println("--------------------");
    System.out.println(theatre.seatStatistic());
  }
}
