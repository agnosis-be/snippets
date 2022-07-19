// This file: Functional.java (UTF-8/LF/4 SP)
// By: agnosis.be
package be.agnosis.func;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Static methods of this class implement the Function interface
 *
 * @see Test::main() <-- caller
 * @see https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/package-summary.html
 */
class Functional {

    static Function<String, String> toNumeric = (input) -> {
        return input.replaceAll("[^0-9]", "");
    };

    static Predicate<String> satisfiesLuhn = (input) -> {
        int sum = 0;
        int parity = input.length() % 2;
        int digit = 0;

        for (int i=0; i<input.length(); i++) {
            digit = Integer.parseInt(input.substring(i, i+1));
            if (i % 2 == parity) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = digit - 9;
                }
            }
            sum = sum + digit;
        }
        return sum % 10 == 0;
    };

    static Predicate<String> hasLength15 = (input) -> {
        return input.length() == 15;
    };

    static Predicate<String> hasLength16 = (input) -> {
        return input.length() == 16;
    };

    static Predicate<String> hasValidCreditCardPrefix = (input) -> {
        return input.startsWith("3") || input.startsWith("4") || input.startsWith("5");
    };
}
