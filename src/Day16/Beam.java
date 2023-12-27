package Day16;

public class Beam {

    private int currentLocation;
    private BeamDirection direction;

    public Beam(int currentLocation, BeamDirection direction) {
        this.currentLocation = currentLocation;
        this.direction = direction;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public BeamDirection getDirection() {
        return direction;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setDirection(BeamDirection direction) {
        this.direction = direction;
    }
}
