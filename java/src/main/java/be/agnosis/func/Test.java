// This file: Test.java (UTF-8/LF/4 SP)
// By: agnosis.be
package be.agnosis.func;

import java.util.function.Function;
import java.util.function.Predicate;

import be.agnosis.func.CreditCardNumber;
import be.agnosis.func.IMEI;
import be.agnosis.func.Functional;

/**
 * Package Test
 *
 * Usage:
 * $ java be.agnosis.func.Test <mode> <candidate>
 *
 * where <mode> in
 *   1 := assume <candidate> is a CreditCardNumber
 *   2 := assume <candidate> is an IMEI
 *   3 := assume nothing
 *
 * and
 *   <candidate> is a string
 */
class Test {

    public static void main (String[] args) {
        String mode = args[0];
        String candidate = args[1];

        if (mode.equals("1")) {
            // Assume CreditCardNumber

            CreditCardNumber cc = new CreditCardNumber(candidate);
            System.out.print(cc.getNumber());
            System.out.println(cc.isValid() ? " is a valid credit card number" : " is not a valid credit card number");
        } else if (mode.equals("2")) {
            // Assume IMEI

            IMEI imei = new IMEI(candidate);
            System.out.print(imei.getNumber());
            System.out.println(imei.isValid() ? " is a valid IMEI" : " is not a valid IMEI");
        } else if (mode.equals("3")) {
            // Assume nothing

            Boolean detected = false;

            String parsed = Functional.toNumeric.apply(candidate);

            Predicate<String> isCreditCardNumber = Functional.satisfiesLuhn.and(Functional.hasLength15.or(Functional.hasLength16)).and(Functional.hasValidCreditCardPrefix);
            Predicate<String> isIMEI = Functional.satisfiesLuhn.and(Functional.hasLength15);

            System.out.print(parsed);
            if (isCreditCardNumber.test(parsed)) {
                detected = true;
                System.out.print(" could be a credit card number");
            }
            if (isIMEI.test(parsed)) {
                detected = true;
                System.out.print(" could be an IMEI");
            }
            if (!detected) {
                System.out.print(" could be anything, but not a credit card number or IMEI");
            }
            System.out.println("");
        }
    }
}