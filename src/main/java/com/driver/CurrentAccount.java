package com.driver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name , balance , 5000);
        if (balance < 5000){
            throw new Exception("Insufficient Balance");
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean isValid = checkLicenseId();

        if (!isValid){
           String str = rearrangeString(this.tradeLicenseId.toLowerCase());

           if (Objects.equals(str, "")){
               throw new Exception("Valid License can not be generated");
           }
           this.tradeLicenseId = str.toUpperCase();
        }
    }

    private boolean checkCanBeValidated() throws Exception {
        HashMap<Character , Integer> map = new HashMap<>();

        char[] arr = this.tradeLicenseId.toCharArray();
        int len = arr.length%2 == 0 ? arr.length/2 : (arr.length + 1)/2;

        for (char ch : arr){
            if (map.containsKey(ch))
            {
                map.put(ch , map.get(ch) + 1 );
            }
            else
            {
                map.put(ch , 1);
            }
        }
        Collection<Integer> values = map.values();
        for (Integer val : values){
            if (val > len ){
                return false;
            }
        }
        return true;
    }
    static char getMaxCountChar(int[] count)
    {
        int max = 0;
        char ch = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > max) {
                max = count[i];
                ch = (char)((int)'a' + i);
            }
        }
        return ch;
    }

    static String rearrangeString(String S)
    {
        int N = S.length();
        if (N == 0)
            return "";

        int[] count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] = 0;
        }

        for (char ch : S.toCharArray()) {
            count[(int)ch - (int)'a']++;
        }

        char ch_max = getMaxCountChar(count);
        int maxCount = count[(int)ch_max - (int)'a'];

        if (maxCount > (N + 1) / 2)
            return "";

        String res = "";
        for (int i = 0; i < N; i++) {
            res += ' ';
        }

        int ind = 0;

        while (maxCount > 0) {
            res = res.substring(0, ind) + ch_max
                    + res.substring(ind + 1);
            ind = ind + 2;
            maxCount--;
        }
        count[(int)ch_max - (int)'a'] = 0;

        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                ind = (ind >= N) ? 1 : ind;
                res = res.substring(0, ind)
                        + (char)((int)'a' + i)
                        + res.substring(ind + 1);
                ind += 2;
                count[i]--;
            }
        }
        return res;
    }


    private boolean checkLicenseId() {
        String id = this.tradeLicenseId;

        for (int i = 0 ; i < id.length()-1; i++){
            char ch1 = id.charAt(i);
            char ch2 = id.charAt(i+1);;

            if (ch1 == ch2){
                return false;
            }
        }
        return true;
    }

}
