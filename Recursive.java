// Imported Packages
import java.math.BigInteger;
import java.math.BigDecimal;
public class Recursive {
    public static int reverseNum(int n, int digits) {
        int reverse = 0;
        while (n > 0) {
            reverse += n%10 * Math.pow(10, digits);
            digits--;
            n/= 10;
        }
        return reverse;
    }

    public static int reverseNumRecursion(int n, int digits) {
        int reverse = 0;
        if (n == 0) {
            return reverse;
        } else {
            return (int)(n%10 * Math.pow(10, digits)) + reverseNumRecursion(n/10, digits - 1); 
        }
    }

    public static int countFlips(int headsInARow) {
        int flips = 0;
        int headsAtOnce = 0;
        // Base case: If required number of heads reached, return 0 flips needed
        if (headsInARow == 0) {
            return 0;
        }
        while (headsAtOnce < 3) {
            if (Math.random() < 0.5) {
                flips++;
                headsAtOnce++;
            } else {
                flips++;
                headsAtOnce = 0;
            }
        }
        return flips;
    }

    public static long decToBin(int decimal) { 
        int count = 0; 
        long output = 0; 
        while(decimal > 0) { 
            output += (long)((decimal%2)*Math.pow(10, count)); 
            decimal = decimal/2; count++; 
        } 
        return output; 
    }

    public static long decToBinRecursion(int decimal) {
        if (decimal == 0) {
            return 0;
        }
        return (decimal%2) + 10 * decToBinRecursion(decimal/2);
    }

    public static void main(String[] args) {
        BigInteger ultralong = new BigInteger("9,223,372,036,854,775,808".replace(",", ""));

        int num = 12345;
        int digits = (int) Math.log10(num) + 1;
        Console.println("Hello println!");
        Console.warnprintln("Hello warnprintln!");
        Console.errprintln("Hello errprintln!");
        Console.println("Original number: " + num);
        Console.println("Reversed number (iterative): " + reverseNum(num, digits));
        Console.println("Reversed number (recursive): " + reverseNumRecursion(num, digits));
        try {
            Console.println(0 / 0);
        } catch (ArithmeticException e) {
            Console.errprintln("Error: ArithmeticException " + e.getMessage());
            Console.errprintln("Stack: " + e.getStackTrace());
        }
        Console.println(1.0 / 0.0);
        Console.println(-1.0 / 0.0);
        Console.warnprintln(0.0 / 0.0);
        Console.println("Count Flips: " + countFlips(3));
        Console.println(decToBin(666));
        Console.println(decToBinRecursion(10));
        Console.println(Integer.MAX_VALUE + 1);
        Console.println((long) Integer.MAX_VALUE + 1);
        Console.println(Long.MAX_VALUE + 1);
        Console.println(ultralong + "");
    }
}