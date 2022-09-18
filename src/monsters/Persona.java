package monsters;

public abstract class Persona {

    protected String rusName;
    private int hp;
    private int maxHp;
    private int power;
    private int dexterity;
    private int agility;
    private int defence;

    private int weaponDelay;

    public int getWeaponDelay() {
        return weaponDelay;
    }

    public void setWeaponDelay(int weaponDelay) {
        this.weaponDelay = weaponDelay;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHp() {
        return hp;
    }

    public int getPower() {
        return power;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public int getDefence() {
        return defence;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }

    public synchronized void changeHp(int value)  {
        hp += value;
        if (hp > maxHp) hp = maxHp;
    }




}
