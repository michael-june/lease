
package com.lau56.lease.web.admin.mapper;

import com.lau56.lease.model.entity.ApartmentFacility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApartmentFacilityMapperTest {

    @Autowired
    private ApartmentFacilityMapper apartmentFacilityMapper;

    @Test
    void testSelectList() {
        List<ApartmentFacility> list = apartmentFacilityMapper.selectList(null);
        // 断言不为 null，具体断言可根据实际情况调整
        assertThat(list).isNotNull();
    }
}