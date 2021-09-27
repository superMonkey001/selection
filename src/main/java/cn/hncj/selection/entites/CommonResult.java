package cn.hncj.selection.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private String msg;
    private Integer code;
    private T data;
    public CommonResult (String msg,Integer code){
        this(msg,code,null);
    }
}
