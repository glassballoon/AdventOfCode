package Day5;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5pt1 {

    final static String SEED_TO_SOIL  = "seed-to-soil map:";
    final static String SOIL_TO_FERTILIZER  = "soil-to-fertilizer map:";
    final static String FERTILIZER_TO_WATER  = "fertilizer-to-water map:";
    final static String WATER_TO_LIGHT  = "water-to-light map:";
    final static String LIGHT_TO_TEMP  = "light-to-temperature map:";
    final static String TEMP_TO_HUMIDITY  = "temperature-to-humidity map:";
    final static String HUMIDITY_TO_LOCATION = "humidity-to-location map:";

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day4TestInput.txt");
        Path filePath = Paths.get("src/Input/Day4Input.txt");
        try {
            var lines = Files.readAllLines(filePath);

            // first line will be seeds:
            var seedsString = lines.get(0).substring(lines.get(0).indexOf(":")+2).split("\\ ");

            var seedArray = new ArrayList<Long>();
            for (String seed: seedsString) seedArray.add(Long.valueOf(seed));


            //var locationMaps = new ArrayList<LocationMapper>();

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

            calcNextLocation(seedArray, seedToSoil);
            calcNextLocation(seedArray, soilToFertilizer);
            calcNextLocation(seedArray, fertilizerToWater);
            calcNextLocation(seedArray, waterToLight);
            calcNextLocation(seedArray, lightToTemp);
            calcNextLocation(seedArray, tempToHumidity);
            calcNextLocation(seedArray, humidityToLocation);

//            seedArray.stream().min(Float::compare).orElseThrow();

            System.out.println(seedArray.stream().min(Long::compare).orElseThrow());

        } catch (Exception e){
            System.out.println(e);
        }

    }

    static LocationMapper createLocationMapper(String fileLine){
        //numbers are destination start, source start, range
        String[] values = fileLine.split("\\ ");
        return new LocationMapper(Long.parseLong(values[0]), Long.parseLong(values[1]), Long.parseLong(values[2]));
    }

    static void calcNextLocation(List<Long> seedArray, List<LocationMapper> locationMapper){
        for (int j = 0; j < seedArray.size(); j++){
            long seedNum = seedArray.get(j);

            var mappedLocation = locationMapper.stream()
                    .filter(n->seedNum >= n.getSourceStart()
                            && seedNum <= n.getSourceStart()+n.getRangeLength()).toList();

            // if nothing returned return itself
            if (mappedLocation.size() != 0) {
                var map = mappedLocation.get(0);
                seedArray.set(j, map.getDestinationLocation(seedArray.get(j)));
            }
        }
    }


}
