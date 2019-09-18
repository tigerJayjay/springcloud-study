package com.tigerjay.eurekadatabase;

import com.tigerjay.eurekadatabase.entity.DynamicProperty;
import com.tigerjay.eurekadatabase.mapper.DynamicPropertyMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaDatabaseApplicationTests {

    @Autowired
    private DynamicPropertyMapper dynamicPropertyMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<DynamicProperty> userList = dynamicPropertyMapper.selectList(null);
        Assert.assertEquals(1, userList.size());
        userList.forEach(System.out::println);
    }
}
