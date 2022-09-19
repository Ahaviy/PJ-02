package game;

public enum Item {
    COIN("серебряная монета"),
    NUT("орех"),
    SINGINGGRASS("трым-трава"),
    FUR("мех"),
    PIECEOFWOOD("деревяшка"),
    GLOWINGSTONE("свет-камень"),
    BONE("кость"),
    DRAGONBONE ("драконья кость"),
    OBSIDIAN("обсидиан"),
    OLDSTONE("древний камень"),
    SQUIRRELTAIL ("беличьий хвост"),
    WOLFFANG("волчий клык"),
    LESHIYHAIR("волосы лешего"),
    LIVINGWOOD("живое дерево"),
    BATWING("крылья летучей мыши"),
    SCORPIONTAIL("хвост скорпиона");

    final String rusName;

    Item(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}
