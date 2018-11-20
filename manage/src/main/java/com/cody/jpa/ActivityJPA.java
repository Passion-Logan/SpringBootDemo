package com.cody.jpa;

import com.cody.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Cody_
 * @date 18/10/22
 */
public interface ActivityJPA extends JpaRepository<ActivityEntity, Long> {

    /**
     * 根据活动主键获取活动实体
     * @param id 活动主键
     * @return  活动实体
     */
    ActivityEntity getActivityEntityById(Long id);

}
