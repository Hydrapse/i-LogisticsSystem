package com.tcsquad.ilogistics.domain;

public enum SequenceName {
    /**
     * Sequence的name值
     * 表单：任务单编号(taskid)，退换货单编号（returnfid），调货单编号（adjustid）
     * 命名：主站编号(mainsid)，配送站编号(subsid)，配送员编号（courierid），
     * 待完善
     */
    TASK_FORM("taskid", Group.FormName),
    RETURN_FORM("returnfid",Group.FormName),
    ADJUST_FORM("adjustid",Group.FormName),

    MAINSITEID("mainsid", Group.OtherName),
    SUBSITEID("subsid", Group.OtherName),

    COURIERID("courierid", Group.Nickname),
    ;


    private String value;
    private Group group;

    SequenceName(String str, Group group){
        this.value = str;
        this.group = group;
    }

    public String getValue() {
        return value;
    }

    /**
     * 功能描述:<br>
     * 判断某个状态码是否为指定组状态
     */
    public static boolean isInGroup(String value, Group group) {
        boolean include = false;
        for (SequenceName e: SequenceName.values()){
            if(e.value.equals(value) && e.group == group){
                include = true;
                break;
            }
        }
        return include;
    }

    public enum Group{
        FormName,
        CategoryId,
        Nickname,
        OtherName
    }

}
