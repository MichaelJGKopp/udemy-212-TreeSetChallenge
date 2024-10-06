package dev.lpa;

public class BookingAgent {

  public static void main(String[] args) {

    int rows = 10;
    int totalSeats = 100;

    Theatre theatre = new Theatre("Richard Rodgers", rows, totalSeats);
    theatre.printSeatMap();

    bookSeat(theatre, 'A', 3);
    bookSeat(theatre, 'A', 3);

    bookSeat(theatre, 'B', 1);
    bookSeat(theatre, 'B', 11);
    bookSeat(theatre, 'M', 1);

    bookSeats(theatre, 4, 'B', 3, 10); // booking contiguous seats
    bookSeats(theatre, 6, 'B', 'C', 3, 10);
    bookSeats(theatre, 4, 'B', 1, 10);
    bookSeats(theatre, 4, 'B', 'C', 1, 10);
    bookSeats(theatre, 1, 'B', 'C', 1, 10);
    bookSeats(theatre, 4, 'M', 'Z', 1, 10);
    bookSeats(theatre, 10, 'A', 'E', 1, 10);
  }

  private static void bookSeat(Theatre theatre, char row, int seatNo) {

    String seat = theatre.reserveSeat(row, seatNo);
    if (seat != null) {
      System.out.println("Congratulations! Your reserved seat is " + seat);
      theatre.printSeatMap();
    } else {
      System.out.println("Sorry! Unable to reserve " + row + seatNo);
    }
  }

  private static void bookSeats(Theatre theatre, int tickets,
                                char minRow, int minSeat, int maxSeat) {
    bookSeats(theatre, tickets, minRow, minRow, minSeat, maxSeat);
  }

  private static void bookSeats(Theatre theatre, int tickets,
                                char minRow, char maxRow, int minSeat, int maxSeat) {

    var seats = theatre.reserveSeats(tickets, minRow, maxRow, minSeat, maxSeat);
    if (seats != null) {
      System.out.println("Congratulations! Your reserved seats are " + seats);
      theatre.printSeatMap();
    } else {
      System.out.println("Sorry! No matching contiguous seats in rows: " +
        minRow + " - " + maxRow);
    }
  }
}
