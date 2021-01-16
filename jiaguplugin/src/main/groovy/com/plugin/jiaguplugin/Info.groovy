package com.plugin.jiaguplugin

class Info{
    String userName
    String password
    String jiaguToolPath

    String getJiaguToolPath() {
        return jiaguToolPath
    }

    void setJiaguToolPath(String jiaguToolPath) {
        this.jiaguToolPath = jiaguToolPath
    }

    String getUserName() {
        return userName
    }

    void setUserName(String userName) {
        this.userName = userName
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }
}