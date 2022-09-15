package game;

import equipment.Armor;
import equipment.Weapon;
import monsters.Persona;

import java.util.HashSet;

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
        setMaxHp(GUtils.generateParam(40, 10));
        setAgility(GUtils.generateParam(10, 5));
        setDexterity(GUtils.generateParam(10, 5));
        setPower(GUtils.generateParam(4, 0));
        setDefence(GUtils.generateParam(1, 0));
        setHp(getMaxHp());


        currentWeapon = Weapon.UNARMED;
        currentArmor = Armor.UNARMOURED;

        weaponList.add(Weapon.CUDGEL);

        weaponList.add(Weapon.SWORD);
        weaponList.add(Weapon.BROADSWORD);
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
            case 1 -> printStatus();
            case 2 -> printInventory();
            case 3 -> changeEquipment();
        }
    }

    private void printStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вас зовут ").append(name).append("\n");
        sb.append("Вы используете оружие: ").append(currentWeapon.getRusName()).append("\n");
        sb.append("На вас надето: ").append(currentArmor.getRusName()).append("\n");
        //TODO реализовать статус
        System.out.println(sb);
        GUtils.pressToContinue();
    }

    private void printInventory() {
        System.out.println("пока не реализовано");
        //TODO реализовать инвентарь
    }

    private void changeEquipment() {
        int count = printEquipmentList();
        if (count == 0) {
            System.out.println("У вас ни чего нет, что бы одеть.");
        } else {
            wearOrRemoveEquipment(GUtils.getActionValue(count));
        }
        GUtils.pressToContinue();
    }

    private void wearOrRemoveEquipment(int value) {
        if (value > weaponList.size() + armorList.size()) return;//если опция ни чего менять
        if (value > weaponList.size()) {
            value -= weaponList.size();
            Armor soughtArmor = null;
            for (Armor armor : armorList) {
                if (value == 1) {
                    soughtArmor = armor;
                    break;
                }
                value--;
            }
            if (soughtArmor == null) return;
            if (soughtArmor.equals(currentArmor)) {
                System.out.println("Вы снимаете " + soughtArmor.getRusName());
                currentArmor = Armor.UNARMOURED;
            } else {
                System.out.println("Вы одеваете " + soughtArmor.getRusName());
                currentArmor = soughtArmor;
            }
        } else {
            Weapon soughtWeapon = null;
            for (Weapon weapon : weaponList) {
                if (value == 1) {
                    soughtWeapon = weapon;
                    break;
                }
                value--;
            }
            if (soughtWeapon == null) return;
            if (soughtWeapon.equals(currentWeapon)) {
                System.out.println("Вы снимаете " + soughtWeapon.getRusName());
                currentWeapon = Weapon.UNARMED;
            } else {
                System.out.println("Вы одеваете " + soughtWeapon.getRusName());
                currentWeapon = soughtWeapon;
            }
        }

    }

    private int printEquipmentList() {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (Weapon weapon : weaponList) {
            sb.append(++count).append(". ");
            if (weapon.equals(currentWeapon)) {
                sb.append("Снять ");
            } else {
                sb.append("Надеть ");
            }
            sb.append(weapon.getRusName()).append(".\n");
        }
        for (Armor armor : armorList) {
            sb.append(++count).append(". ");
            if (armor.equals(currentArmor)) {
                sb.append("Снять ");
            } else {
                sb.append("Надеть ");
            }
            sb.append(armor.getRusName()).append(".\n");
        }
        if (count != 0) {
            sb.append(count + 1).append(". ничего не менять.");
            System.out.println(sb.toString());
        }
        return count;
    }


}
