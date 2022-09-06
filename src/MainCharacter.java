import Monsters.Persona;

public class MainCharacter extends Persona {
    private static MainCharacter character;
    String name;

    public static MainCharacter getMainCharacter() {
        if (character == null) {
            character = new MainCharacter();
        }
        return character;
    }

    private  MainCharacter() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus(){
        StringBuilder sb = new StringBuilder();
        sb.append("Вас зовут ").append(name).append("\n");

        return sb.toString();
    }

}
