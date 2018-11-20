package com.cody.utils;

import com.cody.entity.ActivityEntity;
import com.cody.entity.CommentEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 仅适用于本项目
 * List排序工具包
 * @author Cody_
 * @date 18/11/2
 */
@Service
public class ListSort {

    /**
     * 活动按照开始时间排序
     * @param list
     */
    public void ActivitySortByTime(List<ActivityEntity> list) {
        Collections.sort(list, new Comparator<ActivityEntity>() {

            @Override
            public int compare(ActivityEntity o1, ActivityEntity o2) {
                try {
                    if (o1.getStart().getTime() < o2.getStart().getTime()) {
                        return 1;
                    } else if (o1.getStart().getTime() > o2.getStart().getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return 0;
            }
        });
    }

    /**
     * 留言板按照留言时间排序
     * @param list
     */
    public void ListSortByTime(List<CommentEntity> list) {
        Collections.sort(list, new Comparator<CommentEntity>() {
            @Override
            public int compare(CommentEntity o1, CommentEntity o2) {
                /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");*/

                try {
                    if (o1.getCreateTime().getTime() < o2.getCreateTime().getTime()) {
                        return 1;
                    } else if (o1.getCreateTime().getTime() > o2.getCreateTime().getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                    /*Date dt1 = format.parse(o1.getCreateTime());
                    Date dt2 = o2.getCreateTime();
                    if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
                 return 0;
            }
        });
    }


}
