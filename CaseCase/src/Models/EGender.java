package Models;

public enum EGender {
    MALE(1, "Male"),
    FEMALE(2, "Female"),
    ;

    EGender(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long id;
    public String name;

    public static EGender finGenderById(long id) {
        for (EGender e : EGender.values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
