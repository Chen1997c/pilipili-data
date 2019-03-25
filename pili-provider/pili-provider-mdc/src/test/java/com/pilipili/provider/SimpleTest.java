package com.pilipili.provider;

import com.pilipili.provider.dao.VideoLabelRelDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述： test
 *
 * @author ChenJianChuan
 * @date 2019/3/25　15:14
 */
@SpringBootTest(classes = PiliProviderMdcApplication.class)
@RunWith(SpringRunner.class)
public class SimpleTest {

    @Autowired
    private VideoLabelRelDAO videoLabelRelDAO;

    @Test
    public void doTest() {
        System.out.println(videoLabelRelDAO.findAllByVideoId(1L));
    }
}
