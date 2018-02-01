package com.hm.android.hmapp.bean;

/**
 * Created by jinxiangdong on 2018/1/30.
 */

public class infos4 {

    private String createTime;
    private String createUser;
    private String dataTypeId;
    private String dataValue;
    private String datalen;
    private String deviceId;
    private String devicetypeId;
    private String functionCode;
    private String paremeterName;
    private String percentage;
    private String pukId;
    private String registerAddress;
    private String slaveAddress;
    private String tiggerGroupId;
    private tiggerGroupInfo tiggerGroupInfo;
    private String updateTime;
    private String updateUser;

    private infos1 infos1;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(String dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getDatalen() {
        return datalen;
    }

    public void setDatalen(String datalen) {
        this.datalen = datalen;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevicetypeId() {
        return devicetypeId;
    }

    public void setDevicetypeId(String devicetypeId) {
        this.devicetypeId = devicetypeId;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getParemeterName() {
        return paremeterName;
    }

    public void setParemeterName(String paremeterName) {
        this.paremeterName = paremeterName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPukId() {
        return pukId;
    }

    public void setPukId(String pukId) {
        this.pukId = pukId;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getSlaveAddress() {
        return slaveAddress;
    }

    public void setSlaveAddress(String slaveAddress) {
        this.slaveAddress = slaveAddress;
    }

    public String getTiggerGroupId() {
        return tiggerGroupId;
    }

    public void setTiggerGroupId(String tiggerGroupId) {
        this.tiggerGroupId = tiggerGroupId;
    }

    public infos4.tiggerGroupInfo getTiggerGroupInfo() {
        return tiggerGroupInfo;
    }

    public void setTiggerGroupInfo(infos4.tiggerGroupInfo tiggerGroupInfo) {
        this.tiggerGroupInfo = tiggerGroupInfo;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


    public com.hm.android.hmapp.bean.infos1 getInfos1() {
        return infos1;
    }

    public void setInfos1(com.hm.android.hmapp.bean.infos1 infos1) {
        this.infos1 = infos1;
    }

    public class  tiggerGroupInfo {
        private String functionCode;
        private String number;
        private String pukId;
        private String slaveAddress;
        private String startAddress;

        public String getFunctionCode() {
            return functionCode;
        }

        public void setFunctionCode(String functionCode) {
            this.functionCode = functionCode;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPukId() {
            return pukId;
        }

        public void setPukId(String pukId) {
            this.pukId = pukId;
        }

        public String getSlaveAddress() {
            return slaveAddress;
        }

        public void setSlaveAddress(String slaveAddress) {
            this.slaveAddress = slaveAddress;
        }

        public String getStartAddress() {
            return startAddress;
        }

        public void setStartAddress(String startAddress) {
            this.startAddress = startAddress;
        }


    }
}
