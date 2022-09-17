package areas;

public class Village extends Area {

    public Village(String name, int dangerLevel) {
        super(name, dangerLevel);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вы находитесь в деревне.\n");
        sb.append("Это небольшая деревушка, в которую не особенно заходят путники. Несмотря на обветшалый вид, здесь "
                + "можно найти кров, торговца, а так же кузнеца.\n");
        return sb.toString().trim();
    }

    @Override
    public String getLocalityName() {
        return "деревня";
    }

    @Override
    public String getActions(int number) {
        StringBuilder sb = new StringBuilder();
        sb.append(number++).append(". Зайти в таверну.\n");
        sb.append(number++).append(". Зайти в кузнецу.\n");
        sb.append(number++).append(". Зайти в лавку.\n");
        sb.append(number).append(". Выйти из деревни навстречу приключениям");
        return sb.toString().trim();
    }

    @Override
    public String makeAction(int value) {
        switch (value) {
            case 1 -> {
                return "Tavern";
            }
            case 2 -> {
                return "Forge";
            }
            case 3 -> {
                return "Shop";
            }
            case 4 -> {
                return "Field01";
            }
        }
        return null;
    }

}
