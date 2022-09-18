package monsters;

public class Squirrel extends Monster{
    public Squirrel() {
        rusName = "Дикая белка";
    }

    @Override
    public int getWeaponDelay() {
        return 600;
    }
}
