package Areas;

public class Forest extends Area{
    public Forest(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        return "Лес, окружающий вас, полон еды, а большое количество мелких животных говорит о том что хищники сдесь"
                + " не частые гости";
    }

    @Override
    public String getActions() {
        return null;
    }

    @Override
    public int getMaxActionCount() {
        return 0;
    }

    @Override
    public String getLocalityName() {
        return "лес";
    }
}
