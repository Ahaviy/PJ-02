package areas;

import equipment.Armor;
import equipment.Weapon;
import game.GUtils;
import game.Item;
import game.MainCharacter;

import java.util.HashMap;

public class Forge extends Area {

    private HashMap<Weapon, Integer> weaponForSale;
    private HashMap<Armor, Integer> armorForSale;

    public Forge(String name, int dangerLevel) {
        super(name, dangerLevel);
        weaponForSale = new HashMap<>();
        armorForSale = new HashMap<>();
        weaponForSale.put(Weapon.SWORD, 200);
        weaponForSale.put(Weapon.BROADSWORD, 500);
        weaponForSale.put(Weapon.DAGGER, 400);
        weaponForSale.put(Weapon.PERFECTSWORD, 2000);
        armorForSale.put(Armor.LEATHERARMOR, 100);
        armorForSale.put(Armor.CHAIN, 500);
        armorForSale.put(Armor.PLATE, 1000);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вы находитесь в кузнице.\n");
        sb.append("Сдесь очень жарко и долго находиться не хочется. Поскорей бы купить оружие и доспехи и выйти наружу");
        return sb.toString();
    }

    @Override
    public String getLocalityName() {
        return "кузница";
    }

    @Override
    public String getAreaActionsList(int number) {
        StringBuilder sb = new StringBuilder();
        sb.append(number++).append(". Купить оружие.\n");
        sb.append(number++).append(". Купить броню.\n");
        sb.append(number).append(". Выйти в деревню.");
        return sb.toString();
    }

    @Override
    public String makeAction(int value) {
        switch (value) {
            case 1 -> {
                buyWeapon();
                GUtils.pressToContinue();
                return null;
            }
            case 2 -> {
                buyArmor();
                GUtils.pressToContinue();
                return null;
            }
            default -> {
                return "Village";
            }
        }
    }

    private void buyArmor() {
        if (armorForSale.isEmpty()) {
            System.out.println("Кузнецу нечего вам предложить");
            return;
        }
        purchaseArmor(GUtils.getActionValue(printArmorForSale()));
    }

    private int printArmorForSale() {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Что вы хотите? \n");
        for (Armor armor : armorForSale.keySet()) {
            sb.append(++count).append(". Купить ").append(armor.getRusName()).append(" за ").append(armorForSale
                    .get(armor)).append(" монет.\n");
        }
        sb.append(++count).append(". Ничего не покупать.");
        System.out.println(sb);
        return count;
    }

    private void purchaseArmor(int value){
        for (Armor armor : armorForSale.keySet()) {
            if (--value == 0){
                if (MainCharacter.getMainCharacter().takeCoins(armorForSale.get(armor))) {
                    MainCharacter.getMainCharacter().addArmor(armor);
                    armorForSale.remove(armor);
                    System.out.println("Вы купили " + armor.getRusName());
                }
                break;
            }
        }
    }

    private void buyWeapon() {
        if (weaponForSale.isEmpty()) {
            System.out.println("Кузнецу нечего вам предложить");
            return;
        }
        purchaseWeapon(GUtils.getActionValue(printWeaponForSale()));
    }

    private int printWeaponForSale() {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Что вы хотите? \n");
        for (Weapon weapon : weaponForSale.keySet()) {
        sb.append(++count).append(". Купить ").append(weapon.getRusName()).append(" за ").append(weaponForSale
                .get(weapon)).append(" монет.\n");
        }
        sb.append(++count).append(". Ничего не покупать.");
        System.out.println(sb);
        return count;
    }

    private void purchaseWeapon(int value){
        for (Weapon weapon : weaponForSale.keySet()) {
            if (--value == 0){
                if (MainCharacter.getMainCharacter().takeCoins(weaponForSale.get(weapon))) {
                    MainCharacter.getMainCharacter().addWeapon(weapon);
                    weaponForSale.remove(weapon);
                    System.out.println("Вы купили " + weapon.getRusName());


                }
                break;
            }
        }
    }


    @Override
    public int getCostOfStamina() {
        return 0;
    }

    @Override
    protected void generateEnemies() {

    }

    @Override
    protected void generateLoot() {

    }
}
