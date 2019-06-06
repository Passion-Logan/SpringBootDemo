package com.cody.service;

import com.cody.entity.ProductEntity;
import com.cody.entity.ProductRobbingRecordEntity;
import com.cody.mapper.ProductEntityMapper;
import com.cody.mapper.ProductRobbingRecordEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @File Name: com.cody.service
 * @Author: WQL //作者及
 * @Date: 2019/6/6 17:43//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Service
public class ConcurrencyService
{

    private static final Logger log= LoggerFactory.getLogger(ConcurrencyService.class);

    private static final String ProductNo="product_10010";

    @Autowired
    private ProductEntityMapper productMapper;

    @Autowired
    private ProductRobbingRecordEntityMapper productRobbingRecordMapper;

    /**
     * 处理抢单
     * @param mobile
     */
    public void manageRobbing(String mobile){
        /*try {
            Product product=productMapper.selectByProductNo(ProductNo);
            if (product!=null && product.getTotal()>0){
                log.info("当前手机号：{} 恭喜您抢到单了!",mobile);
                productMapper.updateTotal(product);
            }else{
                log.error("当前手机号：{} 抢不到单!",mobile);

            }
        }catch (Exception e){
            log.error("处理抢单发生异常：mobile={} ",mobile);
        }*/ //--v1.0

        //+v2.0
        try {
            ProductEntity product = productMapper.selectByProductNo(ProductNo);
            if (product!=null && product.getTotal()>0){
                int result=productMapper.updateTotal(product);
                if (result>0) {
                    ProductRobbingRecordEntity entity=new ProductRobbingRecordEntity();
                    entity.setMobile(mobile);
                    entity.setProductId(product.getId());
                    productRobbingRecordMapper.insertSelective(entity);
                }
            }
        }catch (Exception e){
            log.error("处理抢单发生异常：mobile={} ",mobile);
        }
    }
}