package equipment;

public enum Armor {
    UNARMOURED("Легкая одежда", 0),
    LEATHERARMOR("Коженая броня", 3),
    PLATE("Пластичный доспех", 5);

    private final String rusName;
    private final int armorDefence;

    Armor(String rusName, int armorDefence) {
        this.rusName = rusName;
        this.armorDefence = armorDefence;
    }

    public String getRusName() {
        return rusName;
    }

    public int getArmorDefence() {
        return armorDefence;
    }
}
