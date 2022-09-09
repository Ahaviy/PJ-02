package Areas;

public class Village extends Area {

    public Village(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вы находитесь в деревне.\n");
        sb.append("Это небольшая деревушка, в которую не особенно заходят путники. Несмотря на обветшалый вид, здесь ");
        sb.append("можно найти кров, торговца, а так же кузнеца.\n");
        sb.append(getDirectionsDescription());
        return sb.toString();
    }

    @Override
    public String getActions() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDefaultActions());
        sb.append("4. Зайти в таверну.\n");
        sb.append("5. Зайти в кузнецу.\n");
        sb.append("6. Зайти в лавку.\n");
        sb.append("7. Выйти из деревни навстречу приключениям");
        return sb.toString();
    }

    @Override
    public int getMaxActionCount() {
        return 7;
    }

    @Override
    public String getLocalityName() {
        return "деревня";
    }
}
