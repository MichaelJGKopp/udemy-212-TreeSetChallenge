package dev.lpa;

public class BookingAgent {

  public static void main(String[] args) {

    int rows = 10;
    int totalSeats = 100;

    Theatre theatre = new Theatre("Richard Rodgers", rows, totalSeats);
    theatre.printSeatMap();
  }
}
