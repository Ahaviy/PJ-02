package game;

import equipment.Armor;
import equipment.Weapon;
import monsters.Persona;

import java.util.HashSet;
import java.util.Scanner;

public class MainCharacter extends Persona {
    private static MainCharacter character;
    private String name;
    private Weapon currentWeapon;
    private Armor currentArmor;
    private HashSet<Weapon> weaponList;
    private HashSet<Armor> armorList;


    public static MainCharacter getMainCharacter() {
        if (character == null) {
            character = new MainCharacter();
        }
        return character;
    }

    private MainCharacter() {
        weaponList = new HashSet<>();
        armorList = new HashSet<>();
    }

    @Override
    public int getPower() {
        return super.getPower() + currentWeapon.getWeaponPower();
    }

    @Override
    public int getDefence() {
        return super.getDefence() + currentArmor.getArmorDefence();
    }

    public void newCharacter() {
        //TODO незабыть заменить обратно
        name = "Ivan";
        //name = GUtils.getCharacterName();
        setMaxHp(GUtils.generateParam(40,10));
        setAgility(GUtils.generateParam(10,5));
        setDexterity(GUtils.generateParam(10,15));
        setPower(GUtils.generateParam(4,0));
        setDefence(GUtils.generateParam(1,0));
        setHp(getMaxHp());






        currentWeapon = Weapon.UNARMED;
        currentArmor = Armor.UNARMOURED;
        weaponList.add(Weapon.UNARMED);
        weaponList.add(Weapon.CUDGEL);

        weaponList.add(Weapon.SWORD);
        weaponList.add(Weapon.BROADSWORD);

        armorList.add(Armor.UNARMOURED);
        armorList.add(Armor.LEATHERARMOR);
        armorList.add(Armor.PLATE);
    }



    public String getCharacterActionsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Выберете действие:\n");
        sb.append("1. Посмотреть статус.\n");
        sb.append("2. Посмотреть инвентарь.\n");
        sb.append("3. Снять\\Одеть\n");
        return sb.toString().trim();
    }

    public void makeAction(int value) {
        switch (value) {
            case 1 -> MainCharacter.getMainCharacter().printStatus();
            case 2 -> MainCharacter.getMainCharacter().printInventory();
            case 3 -> MainCharacter.getMainCharacter().changeEquipment();
        }
    }

    private void printStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вас зовут ").append(name).append("\n");
        sb.append("Вы используете оружие: ").append(currentWeapon.getRusName()).append("\n");
        sb.append("На вас одето: ").append(currentArmor.getRusName()).append("\n");
        //TODO реализовать статус
        System.out.println(sb);
        GUtils.pressToContinue();
    }

    private void printInventory() {
        System.out.println("пока не реализовано");
        //TODO реализовать инвентарь
    }

    private void changeEquipment() {
        System.out.println("пока не реализовано");
        //TODO реализовать смену оснащения
    }



}
