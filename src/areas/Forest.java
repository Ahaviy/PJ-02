package areas;

public class Forest extends Area{
    public Forest(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        return "Лес, окружающий вас, полон еды, а большое количество мелких животных говорит о том что хищники здесь"
                + " не частые гости";
    }

    @Override
    public String getLocalityName() {
        return "лес";
    }

    @Override
    protected String lookForSomething() {
        return null;
    }


}