package com.example.xifu;

import java.util.ArrayList;
import java.util.List;

public class CityUtils {
    private static final String[] CITYS = {"福建省", "安徽省", "浙江省", "江苏省"};

    /**
     * 获取城市名
     *
     * @return
     */
    public static List<City> getCityList() {
        List<City> dataList = new ArrayList<>();
        String FU_JIAN = CITYS[0];
        dataList.add(new City("福州", FU_JIAN));
        dataList.add(new City("厦门", FU_JIAN));
        dataList.add(new City("泉州", FU_JIAN));
        dataList.add(new City("宁德", FU_JIAN));
        dataList.add(new City("漳州", FU_JIAN));

        String AN_HUI = CITYS[1];
        dataList.add(new City("合肥", AN_HUI));
        dataList.add(new City("芜湖", AN_HUI));
        dataList.add(new City("蚌埠", AN_HUI));

        String ZHE_JIANG = CITYS[2];
        dataList.add(new City("杭州", ZHE_JIANG));
        dataList.add(new City("宁波", ZHE_JIANG));
        dataList.add(new City("温州", ZHE_JIANG));
        dataList.add(new City("嘉兴", ZHE_JIANG));
        dataList.add(new City("绍兴", ZHE_JIANG));
        dataList.add(new City("金华", ZHE_JIANG));
        dataList.add(new City("湖州", ZHE_JIANG));
        dataList.add(new City("舟山", ZHE_JIANG));

        String JIANG_SU = CITYS[3];
        dataList.add(new City("南京", JIANG_SU));

        return dataList;
    }
}
