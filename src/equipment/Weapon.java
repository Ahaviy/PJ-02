package equipment;

public enum Weapon {
    UNARMED("Кулаки", 0, 500),
    CUDGEL("Дубинка", 4,800),
    SWORD("Меч", 5, 650),
    BROADSWORD("Палаш", 7,600);

    private final String rusName;
    private final int weaponPower;

    private final int weaponDelay;

    Weapon(String rusName, int weaponPower, int weaponDelay) {
        this.rusName = rusName;
        this.weaponPower = weaponPower;
        this.weaponDelay = weaponDelay;
    }

    public int getWeaponDelay() {
        return weaponDelay;
    }

    public String getRusName() {
        return rusName;
    }

    public int getWeaponPower() {
        return weaponPower;
    }
}
