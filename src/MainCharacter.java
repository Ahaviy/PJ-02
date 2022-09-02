import Monsters.Persona;

public class MainCharacter extends Persona {
    String name;

    public MainCharacter(String name) {
        this.name = name;
    }

    public String getStatus(){
        StringBuilder sb = new StringBuilder();
        sb.append("Вас зовут ").append(name).append("\n");

        return sb.toString();
    }

}
