package game;

import monsters.Persona;

import java.util.LinkedList;

public class Battle {
    static Battle instance;
    Fighter characterFighter;
    private LinkedList<Fighter> fighterList;

    private Battle() {
        fighterList = new LinkedList<>();
        characterFighter = new Fighter(MainCharacter.getMainCharacter());
        fighterList.add(characterFighter);
    }


    public static Battle getBattle(){
        return instance;
    }

    public static void newBattle() {
        instance = new Battle();
    }

    public static void reset(){
        instance = null;
    }

    public void addEnemy(Persona enemy){
        characterFighter.getEnemyList().add(enemy);
        Fighter enemyFighter = new Fighter(enemy);
        enemyFighter.getEnemyList().add(MainCharacter.getMainCharacter());
        fighterList.add(enemyFighter);
    }

    public void start(){
        for (Fighter fighter : fighterList){
            fighter.start();
        }
        try {
            characterFighter.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Конец боя");
    }
}
