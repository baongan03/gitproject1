package Models;

public enum ERole {
    USER("User"),
    ADMIN("Admin");
    private String value;

    ERole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public static ERole parseRole(String value) throws IllegalAccessException {
        ERole[] item = values();
        for (ERole role : item) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalAccessException("invalid");
    }
}
