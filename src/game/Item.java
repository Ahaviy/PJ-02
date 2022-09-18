package game;

public enum Item {
    COIN("серебряная монета"),
    SQUIRRELTAIL ("беличьий хвост"),
    WOLFFANG("волчий клык");

    final String rusName;

    Item(String rusName) {
        this.rusName = rusName;
    }
}
