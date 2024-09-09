package com.spring.boot.serverforchess.websocket.entity;

public class ChessMove {
    private int fromCellX;
    private int fromCellY;
    private String fromCellType;

    private int toCellX;
    private int toCellY;

    public int getFromCellX() {
        return fromCellX;
    }

    public void setFromCellX(int fromCellX) {
        this.fromCellX = fromCellX;
    }

    public int getFromCellY() {
        return fromCellY;
    }

    public void setFromCellY(int fromCellY) {
        this.fromCellY = fromCellY;
    }

    public String getFromCellType() {
        return fromCellType;
    }

    public void setFromCellType(String fromCellType) {
        this.fromCellType = fromCellType;
    }

    public int getToCellX() {
        return toCellX;
    }

    public void setToCellX(int toCellX) {
        this.toCellX = toCellX;
    }

    public int getToCellY() {
        return toCellY;
    }

    public void setToCellY(int toCellY) {
        this.toCellY = toCellY;
    }

    @Override
    public String toString() {
        return "Движение фигуры " + fromCellType + " с координатам: " + fromCellX + "," + fromCellY + " на координаты: " + toCellX + "," + toCellY;
    }
}
