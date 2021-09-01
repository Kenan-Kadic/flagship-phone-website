package org.launchcode.flagshipphonewebsite.models;

import java.util.ArrayList;

public class PhoneData {

    public static ArrayList<Phone> findByColumnAndValue(String column, String value, Iterable<Phone> allPhones) {

        ArrayList<Phone> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")) {
            return (ArrayList<Phone>) allPhones;
        }

        if (column.equals("all")) {
            results = findByValue(value, allPhones);
            return results;
        }
        for (Phone phone : allPhones) {

            String aValue = getFieldValue(phone, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(phone);
            }
        }

        return results;
    }

    public static String getFieldValue(Phone phone, String fieldName){
        String theValue;
        if (fieldName.equals("brand")){
            theValue = phone.getBrand().toString();
        } else if (fieldName.equals("name")){
            theValue = phone.getName();
        } else {
            theValue = phone.getPrice();
        }

        return theValue;
    }
    public static ArrayList<Phone> findByValue(String value, Iterable<Phone> allPhones) {
        String lower_val = value.toLowerCase();

        ArrayList<Phone> results = new ArrayList<>();

        for (Phone phone : allPhones) {

            if (phone.getBrand().toString().toLowerCase().contains(lower_val)) {
                results.add(phone);
            } else if (phone.getName().toLowerCase().contains(lower_val)) {
                results.add(phone);
            } else if (phone.getPrice().toLowerCase().contains(lower_val)) {
                results.add(phone);
            }
        }

        return results;
    }

}


