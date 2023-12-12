package Models;

public enum ELocation {
    HANOI(1,"HaNoi",  20),
    HUE(2, "Hue",15),
    DANANG(3, "DaNang", 25),
    SAIGON(4, "SaiGon", 30);

    private long id;
    private String name;
    private long seatQuantity;



    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ELocation(long id, String name, long seatQuantity) {
        this.id = id;
        this.name = name;
        this.seatQuantity = seatQuantity;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setSeatQuantity(long seatQuantity){
        this.seatQuantity = seatQuantity;
    }
    public static ELocation findLocationById(long id) {
        for (ELocation e : ELocation.values()){
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

}