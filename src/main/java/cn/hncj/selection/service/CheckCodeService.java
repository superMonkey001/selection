package cn.hncj.selection.service;

public interface CheckCodeService {
    public  void codeLogic(String phone);
    public  String getCode();
    public String getRedisCode(String phone);
    public int getCount(String phone);
}
