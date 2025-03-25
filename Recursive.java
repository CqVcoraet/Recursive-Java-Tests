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

    public static void main(String[] args) {
        int num = 12345;
        int digits = (int) Math.log10(num) + 1;
        Console.println("Original number: " + num);
        Console.println("Reversed number (iterative): " + reverseNum(num, digits));
        Console.println("Reversed number (recursive): " + reverseNumRecursion(num, digits));
        Console.println(1.0 / 0.0);
    }
}