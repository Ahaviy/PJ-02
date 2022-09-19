package game;

import monsters.Persona;

import java.util.Iterator;
import java.util.LinkedList;

public class Fighter extends Thread {

    final Persona selfPersona;

    private final LinkedList<Persona> enemyList;

    public Fighter(Persona selfPersona) {
        this.selfPersona = selfPersona;
        enemyList = new LinkedList<>();
    }

    public LinkedList<Persona> getEnemyList() {
        return enemyList;
    }

    @Override
    public void run() {
        while (!selfPersona.isDead() && !enemyList.isEmpty()) {
            Iterator<Persona> iterator = enemyList.listIterator();
            while (iterator.hasNext()) {
                Persona enemy = iterator.next();
                if (enemy.isDead()) {
                    iterator.remove();
                } else {
                    printAndApplyAttackResult(enemy, getAttackResult(selfPersona, enemy));
                    try {
                        Thread.sleep(selfPersona.getWeaponDelay());
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
    }

    private void printAndApplyAttackResult(Persona enemy, int attackResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(selfPersona.getRusName()).append(" атакует ").append(enemy.getRusName());
        switch (attackResult) {
            case -1 -> {
                sb.append(", но промахивается.\n");
            }
            case 0 -> {
                sb.append(", но не наносит урона.\n");
            }
            default -> {
                sb.append(" и наносит ").append(attackResult).append(" ед урона.\n");
                enemy.changeHp(-attackResult);
                if (enemy.isDead()) {
                    sb.append(enemy.getRusName()).append(" убит.\n");
                }
            }
        }
        System.out.println(sb.toString().trim());
    }


    private int getAttackResult(Persona whoAttacks, Persona whoAttacked) {
        if (whoAttacks.getDexterity() - whoAttacked.getAgility() + (int) (Math.random() * 20) - 10 > 0) { //если атака успешна
            return whoAttacks.getPower() > whoAttacked.getDefence() ? whoAttacks.getPower() - whoAttacked.getDefence() : 0;
        }
        return -1; //Если промах
    }
}
