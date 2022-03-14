package view;

import domain.House;
import service.HouseService;
import utils.Utility;

public class HouseView {
    private boolean loop = true;
    private char key = ' ';
    private HouseService houseService = new HouseService(10);
    public void mainMenu() {

        do {
            System.out.println("=========房源出租系统=========");
            System.out.println("\t\t1 新增房源");
            System.out.println("\t\t2 查找房屋");
            System.out.println("\t\t3 删除房屋");
            System.out.println("\t\t4 修改房屋信息");
            System.out.println("\t\t5 房屋列表");
            System.out.println("\t\t6 退出");
            System.out.println("请输入你的选择（1-6）");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouses();
                    break;
                case '2':
                    findHouses();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    update();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
            }

        } while (loop);
    }

    public void exit() {
//        System.out.println("请输入你的选择(Y/N):请小心选择");
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
        return;
    }

    public void listHouses() {
        System.out.println("=========房源列表=========");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态（未出租/已出租）");
        House houses[] = houseService.list();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null) {
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("=========房源列表显示完毕=========");
    }

    public void addHouses() {
        System.out.println("=========添加房屋=========");
        System.out.println("姓名");
        String name = Utility.readString(8, "");
        System.out.println("电话");
        String phone = Utility.readString(8, "");
        System.out.println("地址");
        String address = Utility.readString(8, "");
        System.out.println("月租");
        int rent = Utility.readInt();
        System.out.println("状态");
        String state = Utility.readString(8, "");
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("=========添加房屋成功=========");
        } else {
            System.out.println("=========添加房屋失败=========");
        }
    }

    public void delHouse() {
        System.out.println("=========删除房屋信息=========");
        System.out.println("请输入待删除房屋的编号（-1退出）：");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("=========放弃删除房屋信息=========");
            return;
        }
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            if (houseService.del(delId)) {
                System.out.println("=========删除房屋信息成功=========");
            } else {
                System.out.println("=========删除房屋信息失败=========");
            }
        } else {
            System.out.println("=========放弃删除房屋信息=========");
        }
    }

    public void findHouses() {
        System.out.println("=========查找房屋信息=========");
        System.out.println("请输入查找的id：");
        int findId = Utility.readInt();
        House houses = houseService.findById(findId);
        if (houses != null) {
            System.out.println(houses);
        } else {
            System.out.println("=========查找房屋信息id不存在=========");
        }
    }

    public void update() {
        System.out.println("=========修改房屋信息=========");
        System.out.println("请选择待修改房屋编号(-1表示退出)");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("=========放弃修改房屋信息=========");
            return;
        }
        House house = houseService.findById(updateId);
        if (house == null) {
            System.out.println("=========修改房屋信息不存在=========");
            return;
        }
        System.out.println("姓名（" + house.getName() + "）：");
        String name = Utility.readString(8, "");
        if (!"".equals(name)) {
            house.setName(name);
        }

        System.out.println("电话（" + house.getPhone() + "）：");
        String phone = Utility.readString(8);
        if (!"".equals(phone)) {
            house.setPhone(phone);
        }
        System.out.println("地址（" + house.getAddress() + "）：");
        String address = Utility.readString(8);
        if (!"".equals(address)) {
            house.setAddress(address);
        } System.out.println("租金（" + house.getRent() + "）：");
        int rent = Utility.readInt(-1);
        if (rent != -1) {
            house.setRent(rent);
        } System.out.println("状态（" + house.getState() + "）：");
        String state = Utility.readString(8, "");
        if (!"".equals(state)) {
            house.setState(state);
        }
        System.out.println("=========修改房屋信息成功=========");
    }
}
