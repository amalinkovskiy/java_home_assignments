package ua.stqa.pft.sandbox;

/**
 * Created by amalinkovskiy on 5/6/2017.
 */
public class Primes {
    public  static boolean isPrime(int n){
        for(int i = 2; i < n-1; i++){
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }

    public  static boolean isPrimeFast(int n){
        int t = (int)Math.sqrt(n);
        for(int i = 2; i < t; i++){
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }

    public  static boolean isPrimeWhile(int n){

        int i = 2;

        while (i < n && n%i != 0){

            i++;
        }
        return i==n;
    }

    public  static boolean isPrime(long n){
        for(long i = 2; i < n-1; i++){
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }

}
