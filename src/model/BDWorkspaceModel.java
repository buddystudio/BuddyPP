/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gsh
 */
public class BDWorkspaceModel 
{
    //public List<BDCodeTabModel> tabList = new ArrayList<BDCodeTabModel>();
    public List<BDCodeTabModel> tabList = new ArrayList<>();    // 工作区代码标签页列表
    public BDCodeTabModel curTab = null;                        // 当前激活的标签页
    
    public BDWorkspaceModel()
    {
        
    }
}
