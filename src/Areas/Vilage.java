package Areas;

public class Vilage extends Area {

    public Vilage(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        return "Это небольшая деревушка, в которую не особенно заходят путники. Несмотря на обветшалый вид, сдесь можно"
                + " найти кров, торговца, а так же кузнеца";
    }

    @Override
    public String getLocalityName() {
        return "деревня";
    }
}
