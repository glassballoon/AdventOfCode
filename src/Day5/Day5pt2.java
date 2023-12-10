package Day5;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Day5pt2 {

    public static void main(String[] args) {

        System.out.println(new Date());

        Path filePath = Paths.get("src/Input/Day4TestInput.txt");
//        Path filePath = Paths.get("src/Input/Day4Input.txt");
        try {
            var lines = Files.readAllLines(filePath);

            int i = 3; //ignore seeds and blank line and header line

            var seedToSoil = new ArrayList<LocationMapper>();
            while (i < lines.size()){
                // do seed-to-soil map:
                if (lines.get(i).trim().isEmpty()) {
                    i+=2;
                    break; // move to next section
                }
                seedToSoil.add(createLocationMapper(lines.get(i)));
                i++;
            }

            var soilToFertilizer = new ArrayList<LocationMapper>();
            while (i < lines.size()){
                // do seed-to-soil map:
                if (lines.get(i).trim().isEmpty()) {
                    i+=2;
                    break; // move to next section
                }
                soilToFertilizer.add(createLocationMapper(lines.get(i)));
                i++;
            }

            var fertilizerToWater = new ArrayList<LocationMapper>();
            while (i < lines.size()){
                // do seed-to-soil map:
                if (lines.get(i).trim().isEmpty()) {
                    i+=2;
                    break; // move to next section
                }
                fertilizerToWater.add(createLocationMapper(lines.get(i)));
                i++;
            }

            var waterToLight = new ArrayList<LocationMapper>();
            while (i < lines.size()){
                // do seed-to-soil map:
                if (lines.get(i).trim().isEmpty()) {
                    i+=2;
                    break; // move to next section
                }
                waterToLight.add(createLocationMapper(lines.get(i)));
                i++;
            }

            var lightToTemp = new ArrayList<LocationMapper>();
            while (i < lines.size()){
                // do seed-to-soil map:
                if (lines.get(i).trim().isEmpty()) {
                    i+=2;
                    break; // move to next section
                }
                lightToTemp.add(createLocationMapper(lines.get(i)));
                i++;
            }

            var tempToHumidity = new ArrayList<LocationMapper>();
            while (i < lines.size()){
                // do seed-to-soil map:
                if (lines.get(i).trim().isEmpty()) {
                    i+=2;
                    break; // move to next section
                }
                tempToHumidity.add(createLocationMapper(lines.get(i)));
                i++;
            }

            var humidityToLocation = new ArrayList<LocationMapper>();
            while (i < lines.size()){
                // do seed-to-soil map:
                if (lines.get(i).trim().isEmpty()) {
                    i+=2;
                    break; // move to next section
                }
                humidityToLocation.add(createLocationMapper(lines.get(i)));
                i++;
            }

            // first line will be seeds:
            var seedsString = lines.get(0).substring(lines.get(0).indexOf(":")+2).split("\\ ");

            long smallestNum = -1;

            // ranges now of seeds
            for (int j = 0; j < seedsString.length; j+=2){
                //System.out.println(j);
                long start = Long.valueOf(seedsString[j]);
                long range = Long.valueOf(seedsString[j+1]);
                long end = start+range;
                while (start <= end) {
                    long currentLocation = start;
                    currentLocation = calcNextLocation(currentLocation, seedToSoil);
                    currentLocation = calcNextLocation(currentLocation, soilToFertilizer);
                    currentLocation = calcNextLocation(currentLocation, fertilizerToWater);
                    currentLocation = calcNextLocation(currentLocation, waterToLight);
                    currentLocation = calcNextLocation(currentLocation, lightToTemp);
                    currentLocation = calcNextLocation(currentLocation, tempToHumidity);
                    currentLocation = calcNextLocation(currentLocation, humidityToLocation);

                    if ((smallestNum == -1) || (currentLocation < smallestNum))
                        smallestNum = currentLocation;
                    start++;
                }
            }
            System.out.println(new Date());
            System.out.println(smallestNum);

        } catch (Exception e){
            System.out.println(e);
        }

    }

    static LocationMapper createLocationMapper(String fileLine){
        //numbers are destination start, source start, range
        String[] values = fileLine.split("\\ ");
        return new LocationMapper(Long.parseLong(values[0]), Long.parseLong(values[1]), Long.parseLong(values[2]));
    }

    static long calcNextLocation(long startingLocation, List<LocationMapper> locationMapper){
        var mappedLocation = locationMapper.stream()
                .filter(n->startingLocation >= n.getSourceStart()
                        && startingLocation <= n.getSourceStart()+n.getRangeLength()).toList();

            // if nothing returned return itself
        if (mappedLocation.size() != 0) {
            var map = mappedLocation.get(0);
            return map.getDestinationLocation(startingLocation);
        } else return startingLocation;
    }

}
