package equipment;

public enum Weapon {
    UNARMED("Кулаки", 0),
    CUDGEL("Дубинка", 2),
    SWORD("Меч", 5),
    BROADSWORD("Палаш", 7);

    private final String rusName;
    private final int weaponPower;

    Weapon(String rusName, int weaponPower) {
        this.rusName = rusName;
        this.weaponPower = weaponPower;
    }

    public String getRusName() {
        return rusName;
    }

    public int getWeaponPower() {
        return weaponPower;
    }
}
