package areas;

public class Field extends Area{

    public Field(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        return "Поля окружающие вас кажутся спокойными и безметежными. Наврятли тут можно найти ценные ресурсы и"
                + " опасных врагов, но можно попытаться";
    }

    @Override
    public String getLocalityName() {
        return "поле";
    }



    @Override
    protected String lookForSomething() {
        return null;
    }

}