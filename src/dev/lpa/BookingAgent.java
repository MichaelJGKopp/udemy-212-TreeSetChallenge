package dev.lpa;

public class BookingAgent {

  public static void main(String[] args) {

    int rows = 10;
    int totalSeats = 100;

    Theatre theatre = new Theatre("Richard Rodgers", rows, totalSeats);
    theatre.printSeatMap();

    bookSeat(theatre, 'A', 3);
    bookSeat(theatre, 'A', 3);
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

}
