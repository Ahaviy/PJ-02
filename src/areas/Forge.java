package areas;

public class Forge extends Area{

    public Forge(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getLocalityName() {
        return null;
    }

    @Override
    protected String lookForSomething() {
        return null;
    }

}
