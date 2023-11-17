import java.util.Arrays;

public class Cinema {
    
    private int[][][] seats;

 
    public Cinema() {
       
        seats = new int[5][10][20];
    }

    
    public void bookSeats(int hallNumber, int row, int[] seatsToBook) {
        for (int seat : seatsToBook) {
            if (seats[hallNumber][row][seat] == 0) {
                seats[hallNumber][row][seat] = 1;
                System.out.println("Місце " + seat + " у ряду " + row + " залу " + hallNumber + " було успішно заброньоване.");
            } else {
                System.out.println("Місце " + seat + " у ряду " + row + " залу " + hallNumber + " вже заброньоване.");
            }
        }
    }

    
    public void cancelBooking(int hallNumber, int row, int[] seatsToCancel) {
        for (int seat : seatsToCancel) {
            if (seats[hallNumber][row][seat] == 1) {
                seats[hallNumber][row][seat] = 0;
                System.out.println("Бронювання для місця " + seat + " у ряду " + row + " залу " + hallNumber + " було скасоване.");
            } else {
                System.out.println("Місце " + seat + " у ряду " + row + " залу " + hallNumber + " не було заброньоване.");
            }
        }
    }

    
    public boolean checkAvailability(int hallNumber, int numSeats) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 20 - numSeats; j++) {
                boolean available = true;
                for (int k = j; k < j + numSeats; k++) {
                    if (seats[hallNumber][i][k] == 1) {
                        available = false;
                        break;
                    }
                }
                if (available) {
                    System.out.println("Доступні " + numSeats + " послідовних місць у ряду " + i + " залу " + hallNumber);
                    return true;
                }
            }
        }
        System.out.println("Немає достатньої кількості послідовних місць у залі " + hallNumber);
        return false;
    }

   
    public void printSeatingArrangement(int hallNumber) {
        System.out.println("Схема розміщення місць для залу " + hallNumber + ":");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (seats[hallNumber][i][j] == 0) {
                    System.out.print("O "); 
                } else {
                    System.out.print("X "); 
                }
            }
            System.out.println(); 
        }
        System.out.println();
    }

    
    public int[] findBestAvailable(int hallNumber, int numSeats) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 20 - numSeats; j++) {
                boolean available = true;
                for (int k = j; k < j + numSeats; k++) {
                    if (seats[hallNumber][i][k] == 1) {
                        available = false;
                        break;
                    }
                }
                if (available) {
                    return Arrays.copyOfRange(seats[hallNumber][i], j, j + numSeats);
                }
            }
        }
        return null;
    }


    public void autoBook(int hallNumber, int numSeats) {
        int[] bestAvailable = findBestAvailable(hallNumber, numSeats);
        if (bestAvailable != null) {
            int row = -1;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j <= 20 - numSeats; j++) {
                    if (Arrays.equals(Arrays.copyOfRange(seats[hallNumber][i], j, j + numSeats), bestAvailable)) {
                        row = i;
                        break;
                    }
                }
                if (row != -1) {
                    break;
                }
            }

            System.out.println("Автоматично заброньовані місця у ряду " + row + " залу " + hallNumber + ": " + Arrays.toString(bestAvailable));
            bookSeats(hallNumber, row, Arrays.copyOfRange(bestAvailable, 0, numSeats));
        } else {
            System.out.println("Немає достатньої кількості послідовних місць у залі " + hallNumber);
        }
    }

    public static void main(String[] args) {                        // Бронювання 
        Cinema cinema = new Cinema();                       
        int hallNumber = 1;
        int row = 4;
        int[] seatsToBook = {6, 7, 8, 9};
        cinema.bookSeats(hallNumber, row, seatsToBook);
        
        cinema.printSeatingArrangement(hallNumber);
    
        cinema.autoBook(hallNumber, 4);

        cinema.printSeatingArrangement(hallNumber);
    }
    
}
