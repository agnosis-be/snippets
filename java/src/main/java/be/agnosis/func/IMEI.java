// This file: IMEI.java (UTF-8/LF/4 SP)
// By: agnosis.be
package be.agnosis.func;

/**
 * An International Mobile Equipment Identifier (IMEI)
 *
 * Uniquely identifies a SIM slot of a cell phone
 *
 * Properties described in 3GPP TS 23.003
 *
 * Of which implemented:
 * - consists of 15 digits
 * - satisfies luhn algo
 */
class IMEI {

    String number;

    IMEI(String number) {
        this.number = parseNumber(number);
    }

    String getNumber() {
        return this.number;
    }

    String parseNumber(String number) {
        return number.replaceAll("[^0-9]", "");
    }

    Boolean isValid() {
        if (this.number.length() != 15) {
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
