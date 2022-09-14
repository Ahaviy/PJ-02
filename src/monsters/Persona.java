package monsters;

public abstract class Persona {
    int hp;

    int maxHp;
    int power;
    int dexterity;

    int agility;
    int defence;

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

    public void changeHp(int value) {
        hp += value;
        if (hp > maxHp) hp = maxHp;
    }

    public int attackThe(Persona enemy) {
        if (getDexterity() - enemy.getAgility() + (int) (Math.random() * 20) - 10 > 0) { //если атака успешна
            return getPower() > enemy.getDefence() ? getPower() - enemy.getDefence() : 0;
        }
        return -1; //Если промах
    }


}
