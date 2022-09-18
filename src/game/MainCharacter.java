package game;

import equipment.Armor;
import equipment.Weapon;
import monsters.Persona;

import java.util.HashMap;
import java.util.HashSet;

public class MainCharacter extends Persona {
    private static MainCharacter character;
    private String name;
    private Weapon currentWeapon;
    private Armor currentArmor;
    private int level;
    private int currentExp;
    private int expToNextLevel;
    private HashSet<Weapon> weaponList;
    private HashSet<Armor> armorList;
    private HashMap<Item, Integer> backpack;

    @Override
    public String getRusName() {
        return name;
    }

    public static MainCharacter getMainCharacter() {
        if (character == null) {
            character = new MainCharacter();
        }
        return character;
    }

    private MainCharacter() {
        weaponList = new HashSet<>();
        armorList = new HashSet<>();
        backpack = new HashMap<>();
    }

    @Override
    public int getWeaponDelay() {
        return currentWeapon.getWeaponDelay();
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
        level = 1;
        currentExp = 0;
        setExpToNextLevel();
        currentWeapon = Weapon.UNARMED;
        currentArmor = Armor.UNARMOURED;
        addToBackpack(Item.COIN, 200);
        weaponList.add(Weapon.CUDGEL);
    }

    public void gainExp(int expAmount) {
        System.out.println("Вы получили очки опыта: " + expAmount);
        currentExp += expAmount;
        while (currentExp >= expToNextLevel) {
            currentExp -= expToNextLevel;
            levelUp();
            setExpToNextLevel();
        }
    }

    private void levelUp() {
        level++;
        agility++;
        dexterity++;
        maxHp += (int) (Math.random() * 5);
        hp = maxHp;
        if (level % 5 == 0) {
            power++;
            defence++;
        }

        System.out.println("Поздравляю вы достигли уровня: " + level + "!");
        System.out.println("С получением уровня вы востановили здоровье.");
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

    public void addToBackpack(Item item, int count) {
        if (backpack.containsKey(item)) {
            int newCount = backpack.get(item) + count;
            backpack.replace(item, newCount);
        } else {
            backpack.put(item, count);
        }
    }

    private void printStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вас зовут: ").append(name).append(".\n");
        sb.append("Текущий уровень: ").append(level).append(".\n");
        sb.append("Опыт: ").append(currentExp).append("/").append(expToNextLevel).append(".\n");
        sb.append("\nХарактеристики:").append("\n");
        sb.append("Здоровье: ").append(getHp()).append("\\").append(getMaxHp()).append(".\n");
        sb.append("Ловкость: ").append(getAgility()).append(".\n");
        sb.append("Проворство: ").append(getDexterity()).append(".\n");
        sb.append("Сила удара: ").append(getPower()).append(".\n");
        sb.append("Броня: ").append(getDefence()).append(".\n");
        System.out.println(sb);
        GUtils.pressToContinue();
    }

    private void printInventory() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nВы используете оружие: ").append(currentWeapon.getRusName()).append("\n");
        sb.append("На вас надето: ").append(currentArmor.getRusName()).append("\n");
        sb.append("\nСодержимое сумки: ").append("\n");
        if (backpack.isEmpty()) {
            sb.append("в сумке ничего нет").append("\n");
        } else {
            for (Item item : backpack.keySet()) {
                sb.append(item.rusName).append(" - ").append(backpack.get(item)).append("шт. \n");
            }
        }
        System.out.println(sb.toString().trim());
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

    private void setExpToNextLevel() {
        expToNextLevel = level * 30;
    }


}
