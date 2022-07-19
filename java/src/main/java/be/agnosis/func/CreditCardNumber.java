// This file: CreditCardNumber.java (UTF-8/LF/4 SP)
// By: agnosis.be
package be.agnosis.func;

/**
 * A Credit Card Number
 *
 * Properties described in ISO/IEC 7812
 *
 * Of which implemented:
 * - consists of 15 or 16 digits
 * - starts with 3 || 4 || 5 (most common)
 * - satisfies luhn algo
 */
class CreditCardNumber {

    String number;

    CreditCardNumber(String number) {
        this.number = parseNumber(number);
    }

    String getNumber() {
        return this.number;
    }

    String parseNumber(String number) {
        return number.replaceAll("[^0-9]", "");
    }

    Boolean isValid() {
        if (this.number.length() == 15 || this.number.length() == 16) {
            //pass
        } else {
            return false;
        }

        if (this.number.startsWith("3") || this.number.startsWith("4") || this.number.startsWith("5")) {
            // American Expr*ss || Vis* || Masterc*rd;
            // pass
        } else {
            return false;
        }

        // Luhn algorithm
        int sum = 0;
        int parity = this.number.length() % 2;
        int digit = 0;

        for (int i=0; i<this.number.length(); i++) {
            digit = Integer.parseInt(this.number.substring(i, i+1));
            if (i % 2 == parity) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = digit - 9;
                }
            }
            sum = sum + digit;
        }
        return sum % 10 == 0;
    }
}
