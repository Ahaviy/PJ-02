package monsters;

public class Wolf extends Monster{
    public Wolf() {
        rusName = "Волк";
    }

    @Override
    public int getWeaponDelay() {
        return 800;
    }
}
