package com.plugin.jiaguplugin

import com.android.builder.model.SigningConfig
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

class JiaguTask extends DefaultTask {
    Info jiaguInfo
    SigningConfig signingConfig
    File apkOutput

    //给任务创建分组
    JiaguTask() {
        group = "jiagu"
    }

    @TaskAction
    def jiagu() {

        //调用命令行
        //1.登陆命令 java -jar jiagu.jar -login
        project.exec {
            it.commandLine("java", "-jar", jiaguInfo.jiaguToolPath, "-login", jiaguInfo.userName, jiaguInfo.password)
        }

        if (signingConfig) {
            //2.导入签名 java -jar jiagu.jar -importsign
            project.exec {
                it.commandLine("java", "-jar", jiaguInfo.jiaguToolPath, "-importsign", signingConfig.storeFile.absolutePath,
                        signingConfig.storePassword, signingConfig.keyAlias, signingConfig.keyPassword)
            }

            //3.加固 java -jar jiagu.jar -jiagu
            project.exec {
                it.commandLine("java", "-jar", jiaguInfo.jiaguToolPath, "-jiagu", apkOutput.absolutePath,
                        apkOutput.parent,"-autosign")
            }
        }
    }
}
