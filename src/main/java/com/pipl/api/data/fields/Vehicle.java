package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Vehicle extends AbstractField {

    private static final long serialVersionUID = 1L;
    @Expose
    public String vin;

    @Expose
    public Integer year;

    @Expose
    public String make;

    @Expose
    public String model;

    // TODO: redundant since it is not suppoerted by the API
    @Expose
    public String color;

    @Expose
    public String vehicleType;

    public Vehicle(String vin) {
        this.vin = vin;
    }
    public Vehicle(String vin, Integer year, String make, String model, String color, String vehicleType) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public String getDisplay() {
        String make = (this.make != null) ? this.make.substring(0, 1).toUpperCase() + this.make.substring(1) + " " : "";
        String model = (this.model != null) ? this.model.substring(0, 1).toUpperCase() + this.model.substring(1) + " " : "";
        String make_and_model = (!make.isEmpty() || !model.isEmpty()) ? make + model : "";
        String year = (this.year != null) ? this.year + " " : "";
        String vin = (this.vin != null) ? this.vin : "";
        String color = (this.color != null) ? ", " + this.color.substring(0, 1).toUpperCase() + this.color.substring(1) : "";
        String vehicle_type = (this.vehicleType != null) ? "(" + this.vehicleType.substring(0, 1).toUpperCase() + this.vehicleType.substring(1) + ")" : "";
        String type_and_color = (!vehicle_type.isEmpty() || !color.isEmpty()) ? vehicle_type + color + " " : "";
        String hyphen = (!year.isEmpty() || !make_and_model.isEmpty() || !vehicle_type.isEmpty() || !color.isEmpty()) ? "- " : "";
        return year + make_and_model + type_and_color + hyphen + "VIN " + vin;
    }

    public boolean isVinValid() {
        boolean vinValid = true;
        if (vin != null) {
            vinValid = vin.length() == 17
                    && !Pattern.compile("[ioqIOQ]").matcher(vin).find()
                    && !Pattern.compile("[uzUZ0]").matcher(Character.toString(vin.charAt(8))).find()
                    && vin.matches("[a-zA-Z0-9]+");
            if (vinValid) {
                vinValid = isVinChecksumValid();
            }
        }
        return vinValid;
    }

    public boolean isVinChecksumValid() {
        String vinLC = vin.toLowerCase();
        char checkDigit = vinLC.charAt(8);
        HashMap<String, List<String>> replaceMap = new HashMap<>();
        replaceMap.put("1", Arrays.asList("a", "j"));
        replaceMap.put("2", Arrays.asList("b", "k", "s"));
        replaceMap.put("3", Arrays.asList("c", "l", "t"));
        replaceMap.put("4", Arrays.asList("d", "m", "u"));
        replaceMap.put("5", Arrays.asList("e", "n", "v"));
        replaceMap.put("6", Arrays.asList("f", "w"));
        replaceMap.put("7", Arrays.asList("g", "p", "x"));
        replaceMap.put("8", Arrays.asList("h", "y"));
        replaceMap.put("9", Arrays.asList("r", "z"));

        int[] positionalWeights = {8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2};

        for (Map.Entry<String, List<String>> entry : replaceMap.entrySet()) {
            String digit = entry.getKey();
            List<String> replacements = entry.getValue();
            for (String c : replacements) {
                vinLC = vinLC.replace(c, digit);
            }
        }
        int checksum = 0;

        for (int i = 0; i < vinLC.length(); i++) {
            char num = vinLC.charAt(i);
            if (i != 8) {
                checksum += Integer.parseInt(String.valueOf(num)) * positionalWeights[i];
            }
        }
        checksum %= 11;
        if (checksum == 10) {
            checksum = 'x';
        }
        return Objects.equals(String.valueOf(checksum), String.valueOf(checkDigit));
    }

    /**
     * A bool value that indicates whether it's possible to search using the data in this vehicle field
     *
     * @return boolean
     */
    @Override
    public boolean isSearchable() {
        return this.isVinValid();
    }
}
