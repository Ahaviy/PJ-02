package Monsters;

public abstract class Persona {
    int hp;
    int power;
    int dexterity;

    int agility;
    int defence;

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

    public void changeHp(int value) {
        hp+=value;
    }

    public int attackThe(Persona enemy) {
        if (getDexterity() - enemy.getAgility() + (int) (Math.random() * 20) - 10 > 0) { //если атака успешна
            return getPower() > enemy.getDefence() ? getPower() - enemy.getDefence() : 0;
        }
        return -1; //Если промах
    }


}
