package com.lz.domain.vo;

import com.lz.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 功能描述：路由的属性
 *
 * @Author: 李振
 * @Date: 2022/12/27 21:24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutersVo {

    private List<Menu> menus;
}
