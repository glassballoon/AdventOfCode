package Day5;

public class LocationMapper {

    long destinationStart;
    long sourceStart;
    long rangeLength;

    public LocationMapper(long destinationStart, long sourceStart, long rangeLength) {
        this.destinationStart = destinationStart;
        this.sourceStart = sourceStart;
        this.rangeLength = rangeLength;
    }

    public long getDestinationStart() {
        return destinationStart;
    }

    public long getSourceStart() {
        return sourceStart;
    }

    public long getRangeLength() {
        return rangeLength;
    }

    public long getDestinationLocation(long sourceLocation){
        long increment = sourceLocation - sourceStart;
        return destinationStart + increment;
    }
}
