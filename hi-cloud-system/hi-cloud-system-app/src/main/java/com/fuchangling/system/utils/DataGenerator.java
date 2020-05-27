package com.fuchangling.system.utils;

import com.fuchangling.system.pojo.dto.NacosResultDTO;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 假数据生成器
 *
 * @author harlin
 */
public class DataGenerator {

    private static final Faker FAKER = new Faker(new Locale("zh-CN"));

    /**
     * 随机生成一定数量学生
     *
     * @param number 数量
     * @return 学生
     */
    public static List<NacosResultDTO> listStudentList(final int number) {
        return Stream.generate(() -> new NacosResultDTO(FAKER.number()
                .numberBetween(200, 400), FAKER.name().fullName(), FAKER.job().keySkills()))
                .limit(number).collect(Collectors.toList());
    }


    /**
     * main函数
     */
    public static void main(String[] args) throws Exception {
//        listStudentList(100).forEach(System.out::println);
        System.out.println(FAKER.address().cityPrefix());
        System.out.println(FAKER.address().city());
        System.out.println(FAKER.address().cityName());
    }
}
