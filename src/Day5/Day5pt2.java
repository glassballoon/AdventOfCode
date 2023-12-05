package Day5;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day5pt2 {

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day4TestInput.txt");
        Path filePath = Paths.get("src/Input/Day4Input.txt");
        try {
            var lines = Files.readAllLines(filePath);

            // first line will be seeds:
            var seedsString = lines.get(0).substring(lines.get(0).indexOf(":")+2).split("\\ ");

            var seedArray = new HashSet<Long>();

            // ranges now of seeds
            for (int i = 0; i < seedsString.length; i+=2){
                System.out.println(i);
                long start = Long.valueOf(seedsString[i]);
                long range = Long.valueOf(seedsString[i+1]);
                long end = start+range;
                while (start <= end) {
                    if (!seedArray.contains(start))
                        seedArray.add(start);
                    start++;
                }
            }


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

            var seedList = seedArray.stream().toList();

            seedList = calcNextLocation(seedList, seedToSoil);
            seedList = calcNextLocation(seedList, soilToFertilizer);
            seedList = calcNextLocation(seedList, fertilizerToWater);
            seedList = calcNextLocation(seedList, waterToLight);
            seedList = calcNextLocation(seedList, lightToTemp);
            seedList = calcNextLocation(seedList, tempToHumidity);
            seedList = calcNextLocation(seedList, humidityToLocation);

            System.out.println(seedList.stream().min(Long::compare).orElseThrow());

        } catch (Exception e){
            System.out.println(e);
        }

    }

    static LocationMapper createLocationMapper(String fileLine){
        //numbers are destination start, source start, range
        String[] values = fileLine.split("\\ ");
        return new LocationMapper(Long.parseLong(values[0]), Long.parseLong(values[1]), Long.parseLong(values[2]));
    }

    static List calcNextLocation(List<Long> seedArray, List<LocationMapper> locationMapper){
        var lastestSeeds = new ArrayList<Long>();
        for (int j = 0; j < seedArray.size(); j++){
            long seedNum = seedArray.get(j);

            var mappedLocation = locationMapper.stream()
                    .filter(n->seedNum >= n.getSourceStart()
                            && seedNum <= n.getSourceStart()+n.getRangeLength()).toList();

            // if nothing returned return itself
            if (mappedLocation.size() != 0) {
                var map = mappedLocation.get(0);
//                seedArray.set(j, map.getDestinationLocation(seedArray.get(j)));
                lastestSeeds.add(map.getDestinationLocation(seedArray.get(j)));
            } else lastestSeeds.add(seedNum);
        }
        return lastestSeeds.stream().distinct().toList();
    }
}
