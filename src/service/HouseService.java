package service;

import domain.House;

public class HouseService {

    private House[] houses;
    private int houseNums = 1;
    private int IdCounter = 1;
    public HouseService(int size) {
        houses = new House[size];
        houses[0] = new House(1, "Jack", "110", "通州区", 2000, "未出租");
    }

    public House[] list() {
        return houses;
    }

    public boolean add(House newHouse) {
        if (houseNums == houses.length) {
            System.out.println("数组已满，不能添加");
            return false;
        }
        houses[houseNums++] = newHouse;
        newHouse.setId(++IdCounter);
        return true;
    }

    public boolean del(int delId) {
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {
                index = i;
            }
        }
        if (index == -1) {
            return false;
        }
        for (int i = index; i < houses.length - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[--houseNums] = null;
        return true;
    }

    public House findById(int findId) {
        for (int i = 0; i < houseNums; i++) {
            if (findId == houses[i].getId()) {
                return houses[i];
            }
        }
        return null;
    }
}
